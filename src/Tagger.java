package nlp;

import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;
import java.lang.Double;
// For testing in main
import java.util.Arrays;

public class Tagger {
	// TODO: These need to be in a separate file
	public static final String START_OF_SENTENCE = "start";
	public static final String END_OF_SENTENCE = "end";

	private static double[][] viterbiForwardStep(double[][] scoreMatrix, TransitionMatrix transitionMatrix, EmissionMatrix emissionMatrix, ArrayList<String> input, String[] tags) {
		for (int column = 0; column < input.size(); column++) {
			for (int row = 0; row < scoreMatrix.length; row++) {
				// first column
				if (column == 0) {
					double transitionProbability = transitionMatrix.getProbabilityFromTagTransitionHashMap(tags[row], START_OF_SENTENCE);
					double emissionProbability = emissionMatrix.getProbabilityFromEmissionHashMap(input.get(column), tags[row]);
					double score = -Math.log(transitionProbability) - Math.log(emissionProbability);
					scoreMatrix[row][column] = score;
				}

				// last column
				else if (column == input.size() - 1) {
					// get min
					double min = Double.POSITIVE_INFINITY;

					for (int previousTag = 0; previousTag < tags.length; previousTag++) {
						double previousTagScore = scoreMatrix[row][column - 1];
						double transitionProbability = transitionMatrix.getProbabilityFromTagTransitionHashMap(tags[previousTag], END_OF_SENTENCE);
						double score = previousTagScore - Math.log(transitionProbability);

						if (score < min) {
							min = score;
						}
					}

					scoreMatrix[row][column] = min;
				}

				// middle columns
				else {					
					double min = Double.POSITIVE_INFINITY;

					// Naive third loop
					for (int previousTag = 0; previousTag < tags.length; previousTag++) {
						double previousTagScore = scoreMatrix[row][column - 1];
						double transitionProbability = transitionMatrix.getProbabilityFromTagTransitionHashMap(tags[row], tags[previousTag]);
						double emissionProbability = emissionMatrix.getProbabilityFromEmissionHashMap(input.get(column), tags[row]);

						double score = previousTagScore - Math.log(transitionProbability) - Math.log(emissionProbability);

						if (score < min) {
							min = score;
						}
					}

					scoreMatrix[row][column] = min;
				}
			}
		}

		return scoreMatrix;
	}

	private static ArrayList<String> viterbiBackwardStep(double[][] scoreMatrix, String[] tags) {
		ArrayList<String> inputTags = new ArrayList<String>();

		for (int column = scoreMatrix.length; column >= 0; column--) {
			double min = Double.POSITIVE_INFINITY;
			String tag = tags[0];

			for (int row = 0; row < tags.length; row++) {
				if (scoreMatrix[row][column] < min) {
					min = scoreMatrix[row][column];
					tag = tags[row];
				}
			}

			inputTags.add(tag);
		}

		Collections.reverse(inputTags);

		return inputTags;
	}

	public static ArrayList<String> viterbi(String input, ArrayList<TaggedToken> trainingSet) {
		TransitionMatrix transitionMatrix = new TransitionMatrix(trainingSet);
		EmissionMatrix emissionMatrix = new EmissionMatrix(trainingSet);
		ArrayList<String> inputList = Tokenizer.tokenize(input);

		double[][] scoreMatrix = new double[transitionMatrix.size()][inputList.size()];
		
		String[] tags = (String[]) transitionMatrix.keySet().toArray(new String[transitionMatrix.keySet().size()]);
		double[][] forwardScoreMatrix = viterbiForwardStep(scoreMatrix, transitionMatrix, emissionMatrix, inputList, tags);
		ArrayList<String> inputTags = viterbiBackwardStep(forwardScoreMatrix, tags);

		return inputTags;
	}

	public static void main(String[] args) {
		ArrayList<String> lines = Reader.readLinesFromFolder("../brownCorpus", new ArrayList<String>(Arrays.asList("CONTENTS", "README", "cats.txt")));

		ArrayList<TaggedToken> taggedTokens = Parser.createTagsFromBrownCorpus(lines);

		TransitionMatrix tMatrix = new TransitionMatrix(taggedTokens);
	}	
}