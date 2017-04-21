/*
* Reader for breaking text files into lines that can be handled in whatever way we see fit.
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
	/**
	* Returns an ArrayList of file paths in any folder, minus anything in exceptions.
	*
	* @param	folderPath	The path of the folder from current working directory
	* @param	exceptions	A list of exception file names (no path needed)
	* @return 				An ArrayList of file paths
	*/
	public static ArrayList<String> getListOfFilePathsFromFolder(String folderPath, ArrayList<String> exceptions) {
		// TODO:
		// Catch case where there are no exceptions
		// Get all files in folder, remove anything in exceptions
		// Return array
	}

	public static ArrayList<String> readMultipleTextFiles(ArrayList<String> filePaths) {
		ArrayList<String> lines = new ArrayList<String>();

		for(int i = 0; i < files.size(); i++) {
			lines.addAll(readSingleTextFile(files.get(i)));
		}

		return lines;
	}

	public static ArrayList<String> readSingleTextFile(String filePath) {
		ArrayList<String> lines = new ArrayList<String>();

		String currentLine = null;

		try {
			FileReader fileReader = new FileReader(filePath);

			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while((currentLine = bufferedReader.readLine()) != null) {
				lines.add(currentLine);
			}

			bufferedReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		} catch (Exception e) {
			System.out.println("Something went wrong.");
			e.printStackTrace();
		}

		return lines;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter a file path");

		sc.close();
	}	
}