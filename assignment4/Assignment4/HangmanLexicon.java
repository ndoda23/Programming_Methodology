/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import acm.util.*;

public class HangmanLexicon {

	private ArrayList <String> listOfLexicon = new ArrayList <String>();

	public HangmanLexicon() {
		// your initialization code goes here

		try {
			BufferedReader mkitxavi = new BufferedReader(new FileReader("HangmanLexicon.txt"));

			while (true) {
				String wordsFromLexicon = mkitxavi.readLine();
				if (wordsFromLexicon == null) {
					break;
				}else if(wordsFromLexicon != null){
				listOfLexicon.add(wordsFromLexicon);
				}
			}
			mkitxavi.close();

		} 
		
		// vitvaliswineb sxvadasxva saxis errorebs.
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	/** Returns the number of words in the lexicon. */
	

	public int getWordCount() {
		return listOfLexicon.size();
	}

	/** Returns the word at the specified index. */
	public String getWord(int index) {

		return listOfLexicon.get(index);

	}

}
