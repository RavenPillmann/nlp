package nlp;

import java.util.ArrayList;
import java.util.HashMap;

public class Tagger {

	/**
	* Returns a HashMap of Tags: Counts
	*
	* @param	taggedTokens 	An ArrayList of TaggedToken objects
	* @return 					A HashMap of Tags: Counts
	*/
	public static HashMap<String,Integer> getTagsAndCounts(ArrayList<TaggedToken> taggedTokens) {
		HashMap tags = new HashMap<String,Integer>();

		for (TaggedToken taggedToken : taggedTokens ) {
			String tag = taggedToken.getTag();

			if (!tags.containsKey(tag)) {
				tags.put(tag, 0);
			} else {
				int newValue = tags.get(tag)++;
				tags.put(tag, newValue);
			}
		}

		return tags;
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
	}

	public static ArrayList<TaggedToken> viterbi(String input, ArrayList<TaggedToken> trainingSet) {
		// TODO
	}

	public static void main(String[] args) {
		// TODO:
		// Test getTagsAndCounts
	}	
}