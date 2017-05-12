package nlp;

import java.util.ArrayList;
import java.util.HashMap;

// For testing in main class only
import java.util.Arrays;

public class Tagger {
	public static final String TOTAL = "total";
	public static final String START_OF_SENTENCE = "start"

	/**
	* Returns a HashMap of Tags: Counts
	*
	* @param	taggedTokens 	An ArrayList of TaggedToken objects
	* @return 					A HashMap of Tags: Counts
	*/
	public static HashMap<String,Integer> getTagsAndCounts(ArrayList<TaggedToken> taggedTokens) {
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
	* Returns a HashMap or Hashmaps of Tags: [Tags: Counts]
	*
	* @param	taggedTokens	An ArrayList of TaggedToken objects (assumed to be in the order written)
	* @return 					A HashMap of Hashmaps with counts of tag transitions,
	*/
	public static HashMap<String,HashMap<String,Integer>> getTagTransitionsAndCounts(ArrayList<TaggedToken>, taggedTokens) {
		HashMap<String,HashMap<String,Integer>> tagTransitions = new HashMap<String,HashMap<String,Integer>>();

		String lastTag = START_OF_SENTENCE;

		for (TaggedToken taggedToken : taggedTokens) {
			String tag = taggedToken.getTag();
			boolean isStarter = taggedToken.isStarter();

			if (isStarter) {
				lastTag = START_OF_SENTENCE;
			}

			if (!tags.containsKey(tag)) {
				// Create HashMap
				// put one in tag, one in total
				// update last tag
				HashMap<String,Integer> transition = new HashMap<String,Integer>();
				transition.put(lastTag, 1);
				transition.put(TOTAL, 1);
				tags.put(tag, transition);
				lastTag = tag;
			} else {
				// Check if leading tag exists. 
				// If so, add one to both that and one to TOTAL
				// If not, create it with one and add one to TOTAL
				// update last tag

				HashMap<String,Integer> transitions = tags.get(tag);
				if (!transitions.containsKey(lastTag)) {
					
				}
			}
		}

		return tagTransitions;
	}

	/**
	* Returns a HashMap of transition probabilities
	*
	* @param	taggedTokens	An ArrayList of TaggedToken objects, assumed to be in the correct order
	* @return 					A HashMap in which each key is a main tag and each value is a hashMap, in which each key is a tag preceeding the main tag
	*/

	public static Integer[][] createTransitionMatrix(ArrayList<TaggedToken> taggedTokens) {
		// TODO:
		// Create a transition matrix by calculating probabilities of (t_n|t_n-1)
		// P(t_n|t_n-1) = C(t_n-1, t_n)/C(t_n-1)

		return new Integer[10][10];
	}

	public static ArrayList<TaggedToken> viterbi(String input, ArrayList<TaggedToken> trainingSet) {
		// TODO
		return new ArrayList<TaggedToken>();
	}

	public static void main(String[] args) {
		// TODO:
		// Test getTagsAndCounts
		ArrayList<String> lines = Reader.readLinesFromFolder("../brownCorpus", new ArrayList<String>(Arrays.asList("CONTENTS", "README", "cats.txt")));

		ArrayList<TaggedToken> taggedTokens = Parser.createTagsFromBrownCorpus(lines);

		HashMap<String,Integer> tags = getTagsAndCounts(taggedTokens);

		System.out.println("tags: " + tags);
	}	
}