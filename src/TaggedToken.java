package nlp;

public class TaggedToken {
	private String token;
	private String tag;

	public TaggedToken(String token, String tag) {
		this.token = token;
		this.tag = tag;
	}

	public getToken() {
		return this.token;
	}

	public getTag() {
		return this.tag;
	}

	// TODO: Use string formatter
	public String toString() {
		return this.token + " " + this.tag;
	}
}