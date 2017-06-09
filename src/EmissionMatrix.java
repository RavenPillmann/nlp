package nlp;

import java.util.HashMap;
import java.util.ArrayList;

// For testing in main
import java.util.Arrays;

// A class for calculating P(Word | POS)
public class EmissionMatrix extends HashMap {
	public static final String TOTAL = "total";

	private HashMap<String,HashMap<String,Integer>> emissionMatrix;
	// {POS: {word: count, total: count}}

	public EmissionMatrix(ArrayList<TaggedToken> taggedTokens) {
		this.emissionMatrix = this.getTokenEmissionsAndCounts(taggedTokens);
	}

	/**
	* Returns a HashMap of HashMaps of Token: {tag: counts}
	*
	* @param 	taggedTokens	An ArrayList of Tagged Token objects in original order
	* @return  					A HashMap of {token: {tag: count}}
	*/
	private HashMap<String,HashMap<String, Integer>> getTokenEmissionsAndCounts(ArrayList<TaggedToken> taggedTokens) {
		HashMap<String,HashMap<String, Integer>> emissions = new HashMap<String,HashMap<String,Integer>>();

		for (TaggedToken taggedToken : taggedTokens) {
			String tag = taggedToken.getTag();
			String token = taggedToken.getToken();

			if (!emissions.containsKey(token)) {
				HashMap<String,Integer> emission = new HashMap<String,Integer>();

				emission.put(tag, 1);
				emission.put(TOTAL, 1);
				emissions.put(token, emission);
			} else {
				HashMap<String,Integer> emission = emissions.get(token);

				if (emission.containsKey(tag)) {
					int newCount = emission.get(tag) + 1;
					emission.put(tag, newCount);
				} else {
					emission.put(tag, 1);
				}

				int newTotal = emission.get(TOTAL) + 1;
				emission.put(TOTAL, newTotal);
			}
		}

		return emissions;
	}

	/**
	* Calculates the probability that a tag emitted a token
	*
	* @param 	token 			The emitted token (word)
	* @param  	tag 			The emitting tag
	* @return 					A probability of the tag emitting a token
	*/
	public double getProbabilityFromEmissionHashMap(String token, String tag) {
		double probability = 1.0;

		if (this.emissionMatrix.containsKey(token)) {
			double denominator = this.emissionMatrix.containsKey(token) ? (double)(this.emissionMatrix.get(token).get(tag)) + 1.0 : 1.0;
			double numerator = (double)(this.emissionMatrix.get(token).get(TOTAL)) + 1.0;

			probability = denominator / numerator;
		}

		return probability;
	}

	public static void main(String[] args) {
		ArrayList<String> lines = Reader.readLinesFromFolder("../brownCorpus", new ArrayList<String>(Arrays.asList("CONTENTS", "README", "cats.txt")));

		ArrayList<TaggedToken> taggedTokens = Parser.createTagsFromBrownCorpus(lines);

		EmissionMatrix emissionMatrix = new EmissionMatrix(taggedTokens);
	}
}