
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

	// number of guesses
	private int numOfGuesses = 8;

	// for generating words
	private RandomGenerator rgen = RandomGenerator.getInstance();

	private HangmanLexicon words;

	
	private String randomWord;

	
	private String blockedWord;

	// letter for player 
	private char enteredSymbol;

	private String incorrectLetters;
	
	// canva
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

	
	// takes random word from the list
	public String pickRandomWord() {

		int x = rgen.nextInt(0, words.getWordCount() - 1);

		randomWord = words.getWord(x);

		return randomWord;
	}

	// counts number of letters
	private String howManyLetters() {

		String res = "";

		for (int i = 0; i < randomWord.length(); i++) {
			res += "-";
		}
		return res;
	}

	// main gameplay
	private void playingGame() {

		while (numOfGuesses >= 0) {

			canvas.reset();
			canvas.displayWord(blockedWord);
			String entered = readLine("Your guess: ");
			
			entered = entered.toUpperCase();
			enteredSymbol = entered.charAt(0);

			// if entered string is longer than 1
			if (entered.length() > 1) {
				println("You have to enter only 1 symbol.");
				continue;
			}

			// if you don't entered letter 
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

			// condition checker 
			checkConditions();

			// You lose when u have no more attempts
			if (numOfGuesses == 0) {

				println("You're completely hung.");
				println("The word was: " + randomWord);
				println("You lose.");

				break;
			}

			// You win if you manage to build random word
			if (randomWord.equals(blockedWord)) {

				println("You guessed the word:" + " " + randomWord);
				println("You win.");
				canvas.displayWord(blockedWord);

				break;

			}
		}
	}

	
    // Checks conditions: verifies whether the incoming character
    // matches the character found at any index of randomWord.
    // If it matches, it reveals that letter in blockedWord;
    // If it does not match, it decreases the number of attempts.
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
	
	// This method updates blocked word after every correct attempt from the user.
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
