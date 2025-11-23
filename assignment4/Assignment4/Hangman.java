
/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {

	private HangmanCanvas canvas;

	// cdebis raodenoba.
	private int numOfGuesses = 8;

	//randomgenerator rata sityvebi randomad davageneriro.
	private RandomGenerator rgen = RandomGenerator.getInstance();

	private HangmanLexicon words;

	//random word array listidan.
	private String randomWord;

	
	private String blockedWord;

	//aso romelsac motamashe shemoitans.
	private char enteredSymbol;

	private String incorrectLetters;
	
	// canvasistvis.
	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
	}

	public void run() {

		words = new HangmanLexicon();
		randomWord = pickRandomWord();
		blockedWord = howManyLetters();

		println("Welcome to Hangman!");
		println("The word now looks like this: " + blockedWord + randomWord);
		println("You have " + numOfGuesses + " guesses left.");
		playingGame();
	}

	
	// igebs listidan randomad romelime sityvas.
	public String pickRandomWord() {

		int x = rgen.nextInt(0, words.getWordCount() - 1);

		randomWord = words.getWord(x);

		return randomWord;
	}

	//itvlis ramdeni asoa da wers imden - s. 
	private String howManyLetters() {

		String res = "";

		for (int i = 0; i < randomWord.length(); i++) {
			res += "-";
		}
		return res;
	}

	// tamashis mtavari nawili swored aq mimdinareobs.
	private void playingGame() {

		while (numOfGuesses >= 0) {

			canvas.reset();
			canvas.displayWord(blockedWord);
			String entered = readLine("Your guess: ");
			//tu patara asoa vzrdit , radgan pirobashi ase gvaqvs mocemuli
			entered = entered.toUpperCase();
			enteredSymbol = entered.charAt(0);

			//vitvaliswineb tu shemotanili stringis zoma 1 ze metia.
			if (entered.length() > 1) {
				println("You have to enter only 1 symbol.");
				continue;
			}

			// es imistvis tu asos garda raime sxva shemoiyvana.
			int x = 0;

			for (char c = 'A'; c <= 'Z'; c++) {

				if (enteredSymbol == c) {
					x++;
				}

			}

			if (x != 1) {

				println("You must enter letter from the alphabet!");
				continue;

			}

			// aq amowmebs pirobebs , emtxveva tu ara randomwords shemotanili symbolo.
			checkConditions();

			//roca cdebis raodenoba amoiwureba waage.
			if (numOfGuesses == 0) {

				println("You're completely hung.");
				println("The word was: " + randomWord);
				println("You lose.");

				break;
			}

			//tu shemotanili simboloebit aawyve randomword , moige.
			if (randomWord.equals(blockedWord)) {

				println("You guessed the word:" + " " + randomWord);
				println("You win.");
				canvas.displayWord(blockedWord);

				break;

			}
		}
	}

	
	// amowmebs pirobebs , emtxveva tu ara shemotanili simbolo randomwordis romelime indexad myof simmbolos.
	// tu emtxveva blockedWordshi am asos gamoachens , tu ar emtxveva cdebis raodenobas dagaklebs.
	private void checkConditions() {

		if (randomWord.indexOf(enteredSymbol) == -1) {
			if (numOfGuesses > 0) {
				canvas.noteIncorrectGuess(enteredSymbol);
				println("There are no " + enteredSymbol + "'s " + "in the word.");
				numOfGuesses--;
				println("The word now looks like this: " + blockedWord);

				println("You have " + numOfGuesses + " guesses left.");
			}
		}

		else if (randomWord.indexOf(enteredSymbol) != -1) {

			blockedWordUpdating();

			println("That guess is correct.");
			if (!randomWord.equals(blockedWord)) {
				println("The word now looks like this: " + blockedWord);
				println("You have " + numOfGuesses + " guesses left.");

			}

		}

	}

	
	// dablokil sityvas updates uketebs tu momxmareblis mier chawerili simbolo emtxveva randomWordis romelime asos.
	private void blockedWordUpdating() {

		String newWord = "";

		for (int i = 0; i < randomWord.length(); i++) {

			char c = randomWord.charAt(i);

			if (c == enteredSymbol) {
				newWord = newWord + enteredSymbol;
			} else if (c != enteredSymbol) {
				newWord = newWord + blockedWord.charAt(i);
				incorrectLetters += enteredSymbol;
			}
		}
		blockedWord = newWord;
	}

}
