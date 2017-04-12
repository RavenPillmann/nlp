import java.util.ArrayList;
import java.util.Scanner;

public class Tokenizer {
	private String inputString;
	private ArrayList tokens;

	public Tokenizer(String inputString) {
		this.inputString = inputString;
	}

	public void tokenize() {
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

		String phrase = String.format("Your phrase was: %s", sc.nextLine());

		System.out.println(phrase);

		sc.close();
	}
}