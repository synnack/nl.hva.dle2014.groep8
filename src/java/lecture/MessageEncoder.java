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
        
        switch (message.getMessageType()) {
            case "CHAT_MESSAGE":
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
            case "OFFER_SDP":
                OfferSDPMessage offerSDPMessage = (OfferSDPMessage)message.getData();
                return Json.createObjectBuilder()
                        .add("message_type", message.getMessageType())
                        .add("data", Json.createObjectBuilder()
                                .add("sdp", offerSDPMessage.getSDP()))
                        .build()
                        .toString();
            default:
                return "{}";
        }
    }
}
