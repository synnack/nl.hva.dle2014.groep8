package lecture;

public class OfferSDPMessage {
	private String sdp;


	public final String getSDP() {
		return sdp;
	}

	public final void setSDP(final String sdp) {
		this.sdp = sdp;
	}

	@Override
	public String toString() {
		return "OfferSDPMessage [sdp=" + sdp + "]";
	}
}
