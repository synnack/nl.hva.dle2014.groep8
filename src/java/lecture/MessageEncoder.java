package lecture;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<Message> {
	@Override
	public void init(final EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public String encode(final Message message) throws EncodeException {
            if (!message.getMessageType().equals("CHAT_MESSAGE")) {
                return "{}";
            }
            ChatMessage chatMessage = (ChatMessage)message.getData();
            System.out.println("Sending "+message.getMessageType());
            return Json.createObjectBuilder()
                    .add("message_type", message.getMessageType())
                    .add("data", Json.createObjectBuilder()
                            .add("message", chatMessage.getMessage())
                            .add("sender", chatMessage.getSender())
                            .add("received", chatMessage.getReceived().toString()))
                    .build()
                    .toString();
	}
}
