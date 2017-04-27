package nlp;

public class TaggedToken {
	public String token;
	public String tag;

	public TaggedToken(String token, String tag) {
		this.token = token;
		this.tag = tag;
	}

	// TODO: Use string formatter
	public String toString() {
		return this.token + " " + this.tag;
	}
}