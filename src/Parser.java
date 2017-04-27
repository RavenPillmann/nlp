package nlp;

import java.util.Arrays;
import java.util.ArrayList;

public class Parser {
	public static ArrayList<TaggedToken> createTagsFromBrownCorpus(ArrayList<String> lines) {
		ArrayList<TaggedToken> taggedTokens = new ArrayList<TaggedToken>();

		for (String line : lines) {
			String[] rawTaggedTokenStrings = line.split("\\s+");

			for (String rawTaggedTokenString : rawTaggedTokenStrings) {
				String[] tokenAndTag = rawTaggedTokenString.split("/");
				taggedTokens.add(new TaggedToken(tokenAndTag[0], tokenAndTag[1]));
			}
		}

		return taggedTokens;
	}

	public static void main(String[] args) {
		ArrayList<String> lines = Reader.readLinesFromFolder("../brownCorpus", new ArrayList<String>(Arrays.asList("CONTENTS", "README", "cats.txt")));

		ArrayList<TaggedToken> taggedTokens = Parser.createTagsFromBrownCorpus(lines);

		for (TaggedToken taggedToken : taggedTokens) {
			System.out.println(taggedToken.toString());
		}
	}
}