import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
	private String inputString;
	private ArrayList<String> tokens;

	public Tokenizer(String inputString) {
		this.inputString = inputString;
	}

	public void tokenize() {
		Pattern tokenPattern = Pattern.compile("((\\w)+'{0,1}(\\w)+)|[(\\.),(\\?)!:;\"']");
		this.tokens = new ArrayList<String>();

		Matcher tokenMatcher = tokenPattern.matcher(this.inputString);

		while (tokenMatcher.find()) {
			this.tokens.add(tokenMatcher.group());
		}

		// Tokenize
		// Regex should separate out words as well as punctuation, but not punctuation the is contained within words.
		// Also, numbers
	}

	public ArrayList getTokens() {
		return this.tokens;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter a phrase to parse");

		String input = sc.nextLine();

		Tokenizer tokenizer = new Tokenizer(input);
		tokenizer.tokenize();

		System.out.println(tokenizer.tokens);

		sc.close();
	}
}