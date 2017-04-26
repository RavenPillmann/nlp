package nlp;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
	public static ArrayList<String> tokenize(String inputString) {
		Pattern tokenPattern = Pattern.compile("((\\w)+'{0,1}(\\w)+)|[(\\.),(\\?)!:;\"']");
		ArrayList<String> tokens = new ArrayList<String>();

		Matcher tokenMatcher = tokenPattern.matcher(inputString);

		while (tokenMatcher.find()) {
			tokens.add(tokenMatcher.group());
		}

		return tokens;

		// Tokenize
		// Regex should separate out words as well as punctuation, but not punctuation the is contained within words.
		// Also, numbers
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter a phrase to parse");

		String input = sc.nextLine();

		System.out.println(Tokenizer.tokenize(input));

		sc.close();
	}
}