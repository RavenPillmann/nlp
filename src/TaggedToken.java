package nlp;

public class TaggedToken {
	private String token;
	private String tag;
	private boolean isStarter;
	private boolean isEnder;

	public TaggedToken(String token, String tag, boolean isStarter, boolean isEnder) {
		this.token = token;
		this.tag = tag;
		this.isStarter = isStarter;
		this.isEnder = isEnder;
	}

	public String getToken() {
		return this.token;
	}

	public String getTag() {
		return this.tag;
	}

	public boolean isStarter() {
		return this.isStarter;
	}

	public boolean isEnder() {
		return this.isEnder;
	}

	// TODO: Use string formatter
	public String toString() {
		return this.token + " " + this.tag + " " + this.isStarter + " " + this.isEnder;
	}
}