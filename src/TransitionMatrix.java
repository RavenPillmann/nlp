package nlp;

import java.util.ArrayList;
import java.util.HashMap;

// For testing in main class only
import java.util.Arrays;

public class TransitionMatrix {
	public static final String TOTAL = "total";
	public static final String START_OF_SENTENCE = "start";

	private HashMap<String,HashMap<String,Integer>> transitionMatrix;

	public TransitionMatrix(ArrayList<TaggedToken> taggedTokens) {
		this.transitionMatrix = this.getTagTransitionsAndCounts(taggedTokens);
	}

	/**
	* Returns a HashMap of Tags: Counts
	*
	* @param	taggedTokens 	An ArrayList of TaggedToken objects
	* @return 					A HashMap of Tags: Counts
	*/
	private HashMap<String,Integer> getTagsAndCounts(ArrayList<TaggedToken> taggedTokens) {
		HashMap<String,Integer> tags = new HashMap<String,Integer>();

		for (TaggedToken taggedToken : taggedTokens) {
			String tag = taggedToken.getTag();

			if (!tags.containsKey(tag)) {
				tags.put(tag, 1);
			} else {
				int newValue = tags.get(tag);
				newValue = newValue + 1;
				tags.put(tag, newValue);
			}
		}

		return tags;
	}

	/**
	* Returns a HashMap or Hashmaps of Tags: [Preceeding Tag: Counts]
	*
	* @param	taggedTokens	An ArrayList of TaggedToken objects (assumed to be in the order written)
	* @return 					A HashMap of Hashmaps with counts of tag transitions,
	*/
	private HashMap<String,HashMap<String,Integer>> getTagTransitionsAndCounts(ArrayList<TaggedToken> taggedTokens) {
		HashMap<String,HashMap<String,Integer>> tagTransitions = new HashMap<String,HashMap<String,Integer>>();

		String lastTag = START_OF_SENTENCE;

		for (TaggedToken taggedToken : taggedTokens) {
			String tag = taggedToken.getTag();
			boolean isStarter = taggedToken.isStarter();

			if (isStarter) {
				lastTag = START_OF_SENTENCE;
			}

			if (!tagTransitions.containsKey(tag)) {
				HashMap<String,Integer> transition = new HashMap<String,Integer>();

				transition.put(lastTag, 1);
				transition.put(TOTAL, 1);
				tagTransitions.put(tag, transition);
			} else {
				HashMap<String,Integer> transitions = tagTransitions.get(tag);

				if (transitions.containsKey(lastTag)) {
					int newCount = transitions.get(lastTag) + 1;
					transitions.put(lastTag, newCount);
				} else {
					transitions.put(lastTag, 1);
				}

				int newTotal = transitions.get(TOTAL) + 1;
				transitions.put(TOTAL, newTotal);
			}

			lastTag = tag;
		}

		return tagTransitions;
	}

	/**
	* Returns a probability based on the transitionMatrix
	*
	* @param tag  					A tag to transition to
	* @param preceedingTag			the tag preceeding the main tag
	* @return 						A probability of that transition using counts
	*/
	public double getProbabilityFromTagTransitionHashMap(String tag, String preceedingTag) {
		double probability = 1.0;

		if (this.transitionMatrix.containsKey(tag)) {
			double denominator = this.transitionMatrix.containsKey(tag) ? (double)(this.transitionMatrix.get(tag).get(preceedingTag)) + 1.0 : 1.0;
			double numerator = (double)(this.transitionMatrix.get(tag).get(TOTAL)) + 1.0;

			probability = denominator / numerator;
		}

		return probability;
	}

	public static void main(String[] args) {
		ArrayList<String> lines = Reader.readLinesFromFolder("../brownCorpus", new ArrayList<String>(Arrays.asList("CONTENTS", "README", "cats.txt")));

		ArrayList<TaggedToken> taggedTokens = Parser.createTagsFromBrownCorpus(lines);

		TransitionMatrix tMatrix = new TransitionMatrix(taggedTokens);

		HashMap<String,Integer> tags = tMatrix.getTagsAndCounts(taggedTokens);

		// System.out.println("tags: " + tags);

		HashMap<String,HashMap<String,Integer>> tagTransitions = tMatrix.getTagTransitionsAndCounts(taggedTokens);

		// System.out.println("tag transitions: " + tagTransitions);

		for (String tag : tags.keySet()) {
			if (!tags.get(tag).equals(tagTransitions.get(tag).get(TOTAL))) {
				System.out.println("MISMATCH IN TAG COUNT: " + tags.get(tag) + " " + tagTransitions.get(tag).get(TOTAL));
			}
		}
	}
}