package lecture;

import java.io.StringReader;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class MessageDecoder implements Decoder.Text<Message> {
	@Override
	public void init(final EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public Message decode(final String textMessage) throws DecodeException {
            Message msg = new Message();
            JsonObject obj = Json.createReader(new StringReader(textMessage))
                            .readObject();
            
            if (obj.getString("message_type").equals("CHAT_MESSAGE")) {
                JsonObject data = obj.getJsonObject("data");
                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setMessage(data.getString("message"));
                chatMessage.setSender(data.getString("sender"));
                chatMessage.setReceived(new Date());

                msg.setMessageType("CHAT_MESSAGE");
                msg.setData(chatMessage);
            } else if (obj.getString("message_type").equals("OFFER_SDP")) {
                
                JsonObject data = obj.getJsonObject("data");
                OfferSDPMessage offerSDPMessage = new OfferSDPMessage();
                offerSDPMessage.setSDP(data.getString("sdp"));

                msg.setMessageType("OFFER_SDP");
                msg.setData(offerSDPMessage);
            }

            return msg;
	}

	@Override
	public boolean willDecode(final String s) {
		return true;
	}
}
