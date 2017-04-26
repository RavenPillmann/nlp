package nlp;

import java.util.ArrayList;

public class Parser {
	public ArrayList<TaggedToken> createTagsFromBrownCorpus(ArrayList<String> lines) {
		ArrayList<TaggedToken> taggedTokens = new ArrayList<TaggedToken>();

		for (String line : lines) {
			String[] rawTaggedTokenStrings = line.split("\\s+");

			for (String rawTaggedTokenString : rawTaggedTokenStrings) {
				Strings[] tokenAndTag = rawTaggedTokenString.split("/");
				taggedTokens.add(new TaggedToken(tokenAndTag[0], tokenAndTag[1]));
			}
		}
	}

	public static void main(String[] args) {

	}
}