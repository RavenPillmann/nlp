package nlp;

public class TaggedToken {
	private String token;
	private String tag;
	private boolean isStarter;

	public TaggedToken(String token, String tag, boolean isStarter) {
		this.token = token;
		this.tag = tag;
		this.isStarter = isStarter;
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

	// TODO: Use string formatter
	public String toString() {
		return this.token + " " + this.tag + " " + this.isStarter;
	}
}