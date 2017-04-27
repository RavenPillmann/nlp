/*
* Reader for breaking text files into lines that can be handled in whatever way we see fit.
*/

package nlp;

import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {

	/**
	* Overall method that extracts lines from a given folderPath
	* 
	* @param	folderPath	The path of the folder
	* @param	exceptions 	A list of exception file names (no path)
	* @return 				An ArrayList of lines
	*/
	public static ArrayList<String> readLinesFromFolder(String folderPath, ArrayList<String> exceptions) {
		ArrayList<String> filePaths = Reader.getListOfFilePathsFromFolder(folderPath, exceptions);
		ArrayList<String> lines = Reader.readMultipleTextFiles(filePaths);

		return lines;
	}

	/**
	* Returns an ArrayList of file paths in any folder, minus anything in exceptions.
	*
	* @param	folderPath	The path of the folder
	* @param	exceptions	A list of exception file names (no path needed)
	* @return 				An ArrayList of file paths
	*/
	public static ArrayList<String> getListOfFilePathsFromFolder(String folderPath, ArrayList<String> exceptions) {
		ArrayList<String> filePaths = new ArrayList<String>(); 

		try {
			File folder = new File(folderPath);
			File[] listOfContents = folder.listFiles();

			for (int i = 0; i < listOfContents.length; i++) {
				String contentName = listOfContents[i].getName(); // TODO: DOES THIS RETURN THE FILE EXTENSION???

				if (listOfContents[i].isFile() && !exceptions.contains(contentName)) {
					filePaths.add(listOfContents[i].getAbsolutePath());
				}
			}
		} catch (Exception e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}

		return filePaths;
	}

	/**
	* Returns an ArrayList of lines from multiple files
	*
	* @param	filePaths 	File paths to read
	* @return 				An ArrayList of ArrayLists
	*/
	public static ArrayList<String> readMultipleTextFiles(ArrayList<String> filePaths) {
		ArrayList<String> lines = new ArrayList<String>();

		for(int i = 0; i < filePaths.size(); i++) {
			lines.addAll(readSingleTextFile(filePaths.get(i)));
		}

		return lines;
	}

	/**
	* Return an ArrayList of lines from a single file
	*
	* @param	filePath 	File path to a single file
	* @return 				An ArrayList of strings, where each string is a line in a file
	*/
	public static ArrayList<String> readSingleTextFile(String filePath) {
		ArrayList<String> lines = new ArrayList<String>();

		String currentLine = null;

		try {
			FileReader fileReader = new FileReader(filePath);

			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while((currentLine = bufferedReader.readLine()) != null) {
				// Preprocess the lines by trimming them and discarding empties
				String trimmedCurrentLine = currentLine.trim();
				if (!trimmedCurrentLine.equals("")) {
					lines.add(trimmedCurrentLine);
				}
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

		System.out.println("Enter a folder path");

		String folderPath = sc.nextLine();

		System.out.println("Enter any exceptions (names of files to not read) separated by spaces");

		String exceptionsString = sc.nextLine();

		String[] exceptions = exceptionsString.split("\\s+");

		ArrayList<String> temp = new ArrayList<String>(Arrays.asList(exceptions));
		ArrayList<String> files = Reader.getListOfFilePathsFromFolder(folderPath, temp);
		ArrayList<String> lines = Reader.readMultipleTextFiles(files);

		System.out.println("There are " + Integer.toString(lines.size()) + " lines in this folder");

		sc.close();
	}	
}