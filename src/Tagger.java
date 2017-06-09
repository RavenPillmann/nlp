package nlp;

import java.util.ArrayList;

// For testing in main
import java.util.Arrays;

public class Tagger {

	// Momentarily returning void because I'm not sure what I want out of this...
	private static double[][] viterbiForwardStep(double[][] scoreMatrix, TransitionMatrix transitionMatrix, EmissionMatrix emissionMatrix) {
		return scoreMatrix;
	}

	public static ArrayList<TaggedToken> viterbi(String input, ArrayList<TaggedToken> trainingSet) {
		TransitionMatrix transitionMatrix = new TransitionMatrix(trainingSet);
		EmissionMatrix emissionMatrix = new EmissionMatrix(trainingSet);

		double[][] scoreMatrix = new double[transitionMatrix.size()][input.length()];

		double[][] forwardScoreMatrix = viterbiForwardStep(scoreMatrix, transitionMatrix, emissionMatrix);

		return new ArrayList<TaggedToken>();
	}

	public static void main(String[] args) {
		ArrayList<String> lines = Reader.readLinesFromFolder("../brownCorpus", new ArrayList<String>(Arrays.asList("CONTENTS", "README", "cats.txt")));

		ArrayList<TaggedToken> taggedTokens = Parser.createTagsFromBrownCorpus(lines);

		TransitionMatrix tMatrix = new TransitionMatrix(taggedTokens);
	}	
}