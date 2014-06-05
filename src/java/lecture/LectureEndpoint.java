package lecture;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/chat/{lecture}", encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class LectureEndpoint {
	private final Logger log = Logger.getLogger(getClass().getName());

	@OnOpen
	public void open(final Session session, @PathParam("lecture") final String lecture) {
		log.log(Level.INFO, "session openend and bound to lecture: {0}", lecture);
		session.getUserProperties().put("lecture", lecture);
	}

	@OnMessage
	public void onMessage(final Session session, final Message message) {
                System.out.println("I'm in your shit: "+message.getMessageType());
		String lecture = (String) session.getUserProperties().get("lecture");
		try {
			for (Session s : session.getOpenSessions()) {
				if (s.isOpen()
						&& lecture.equals(s.getUserProperties().get("lecture"))) {
					s.getBasicRemote().sendObject(message);
				}
			}
		} catch (IOException | EncodeException e) {
			log.log(Level.WARNING, "onMessage failed", e);
		}
	}
}
