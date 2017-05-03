package nlp;

import java.util.ArrayList;
import java.util.Hashtable;

public class Tagger {

	/**
	* Returns a Hashtable of Tags: Counts
	*
	* @param	taggedTokens 	An ArrayList of TaggedToken objects
	* @return 					A Hashtable of Tags: Counts
	*/
	public static Hashtable getTagsAndCounts(ArrayList<TaggedToken> taggedTokens) {
		Hashtable tags = new Hashtable();

		for (TaggedToken taggedToken : taggedTokens ) {
			String tag = taggedToken.getTag;

			if (!tags.containsKey(tag)) {
				tags.put(tag, 0);
			} else {
				int newValue = tags.get(tag)++;
				tags.put(tag, newValue);
			}
		}

		return tags;
	}

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