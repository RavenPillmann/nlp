package nlp;

import java.util.ArrayList;

// For testing in main
import java.util.Arrays;

public class Tagger {

	public static ArrayList<TaggedToken> viterbi(String input, ArrayList<TaggedToken> trainingSet) {
		// TODO
		return new ArrayList<TaggedToken>();
	}

	public static void main(String[] args) {
		ArrayList<String> lines = Reader.readLinesFromFolder("../brownCorpus", new ArrayList<String>(Arrays.asList("CONTENTS", "README", "cats.txt")));

		ArrayList<TaggedToken> taggedTokens = Parser.createTagsFromBrownCorpus(lines);

		TransitionMatrix tMatrix = new TransitionMatrix(taggedTokens);
	}	
}