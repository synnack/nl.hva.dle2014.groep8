package lecture;

public class Message {
	private String messageType;
	private Object data;

	public final String getMessageType() {
		return messageType;
	}

	public final void setMessageType(final String messageType) {
		this.messageType = messageType;
	}

	public final Object getData() {
		return data;
	}

	public final void setData(final Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Message [ messageType=" + messageType + " ]";
	}
}
