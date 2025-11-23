
/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import java.util.ArrayList;
import java.util.Arrays;

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	/* Private instance variables */

	// instance variable for upper score
	int upperScore;
	// instance variable for upper score
	int lowerScore;
	// here we enter categories which are already used. We need it to prevent
	// selecting the same categories twice.
	private int usedCategories[][];
	// Points are entered in this matrix according to categories.
	private int matrixOfScores[][];
	private int category;
	// array for dice
	private int[] dice = new int[N_DICE];;
	private int nPlayers;
	// array for players names.
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();

	public static void main(String[] args) {
		new Yahtzee().start(args);
	}

	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		matrixOfScores = new int[N_CATEGORIES][nPlayers];
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		usedCategories = new int[N_CATEGORIES][nPlayers];
		playGame();
	}


	// This is the main method where calls to other auxiliary methods happen,
    // including starting the game, handling the first, second, and third guesses,
    // and finally the method that determines the winner.
	private void playGame() {
		/* You fill this in */

		rolls();

          // After the game ends.
		for (int i = 0; i < nPlayers; i++) {

			calculateUpperScore(i + 1);

			// If the upper score is greater than or equal to 63, award the bonus points.

			if (upperScore >= 63) {

				 // Add the upper bonus in the score matrix and update it
                 // so it appears correctly on the scorecard.
				matrixOfScores[UPPER_BONUS - 1][i] = 35;
				display.updateScorecard(UPPER_BONUS, i + 1, 35);
				matrixOfScores[TOTAL - 1][i] = matrixOfScores[TOTAL - 1][i] + matrixOfScores[UPPER_BONUS - 1][i];
				display.updateScorecard(TOTAL, i + 1, matrixOfScores[TOTAL - 1][i]);

			} else {

				display.updateScorecard(UPPER_BONUS, i + 1, 0);
			}

		}

		for (int i = 0; i < nPlayers; i++) {

			calculateLowerScore(i + 1);

		}

		whoIsTheWinner();

	}

	// This method randomizes dices.
	private void diceRandomizer() {

		for (int i = 0; i < N_DICE; i++) {

			int x = rgen.nextInt(1, 6);

			dice[i] = x;
		}

	}
	
// This method is for validations, and afterward in this method I use the
// whichCategory method, which calculates the grades and fills them into the array.

	private void rolls() {

		for (int i = 0; i < N_SCORING_CATEGORIES; i++) {
			for (int j = 1; j <= nPlayers; j++) {
				display.printMessage(playerNames[j - 1] + "'s turn! Click ''Roll Dice'' to roll the dice.");
				display.waitForPlayerToClickRoll(j);
				diceRandomizer();
				display.displayDice(dice);
				display.printMessage("Select the dice you wish to re-roll and click ''Roll Again.''");
				display.waitForPlayerToSelectDice();
				secondGagoreba();
				display.printMessage("Select the dice you wish to re-roll and click ''Roll Again.''");
				display.waitForPlayerToSelectDice();
				thirdGagoreba();
				display.printMessage("Select a category for this roll.");
				WhichCategory(j, category);

			}
		}
	}

	//second validation
	private void secondGagoreba() {

		for (int i = 0; i < N_DICE; i++) {
			if (display.isDieSelected(i)) {
				dice[i] = rgen.nextInt(1, 6);
			}
		}
		display.displayDice(dice);

	}

	//third validation
	private void thirdGagoreba() {

		for (int i = 0; i < N_DICE; i++) {
			if (display.isDieSelected(i)) {
				dice[i] = rgen.nextInt(1, 6);
			}
		}
		display.displayDice(dice);

	}

// A method that updates the array and writes the grade in each selected category.
	private void WhichCategory(int whichPlayer, int category) {

		category = display.waitForPlayerToSelectCategory();

		// In case the category has already been used, I allow the user
        // to choose again.

		if (usedCategories[category - 1][whichPlayer - 1] == 1) {
			display.printMessage("You have already selected this category, please try another!");
			WhichCategory(whichPlayer, category);
		}

		// if category isn't used.
		else if (usedCategories[category - 1][whichPlayer - 1] == 0) {

			// If the user selects the correct category, I update the array and
            // insert the corresponding category's grade into the matrix.
			if (categoryChecker(dice, category) == true) {

				int score = scoreByCategory(category);

				display.updateScorecard(category, whichPlayer, scoreByCategory(category));

				matrixOfScores[category - 1][whichPlayer - 1] = score;

				// Here I mark that the category has been used so it cannot be 
                // selected a second time.
				usedCategories[category - 1][whichPlayer - 1] = 1;

				matrixOfScores[TOTAL - 1][whichPlayer - 1] += score;

				display.updateScorecard(TOTAL, whichPlayer, matrixOfScores[TOTAL - 1][whichPlayer - 1]);

			}
// If the category is selected incorrectly, a 0 will be written in that spot.
			else {
				display.updateScorecard(category, whichPlayer, 0);

				matrixOfScores[category - 1][whichPlayer - 1] = 0;

				usedCategories[category - 1][whichPlayer - 1] = 1;
			}

		}

	}

	// Calculates upper scores.
	private void calculateUpperScore(int whichPlayer) {

		upperScore = 0;

		for (int i = 0; i < 6; i++) {
			upperScore += matrixOfScores[i][whichPlayer - 1];
		}

		display.updateScorecard(UPPER_SCORE, whichPlayer, upperScore);
	}

// Calculates the lower scores and displays them in the array.
	private void calculateLowerScore(int whichPlayer) {
		
		lowerScore = 0;

		for (int i = 8; i < 15; i++) {
			lowerScore += matrixOfScores[i][whichPlayer - 1];
		}

		display.updateScorecard(LOWER_SCORE, whichPlayer, lowerScore);
	}

// Method to determine who won
	private void whoIsTheWinner() {
    // Created a list where each player's total scores will be stored
		ArrayList<Integer> totals = new ArrayList<Integer>();

		int whichPlayer = 0;

// I add the totals to the list.
		for (int i = 0; i < nPlayers; i++) {
			totals.add(matrixOfScores[TOTAL - 1][i]);
		}

// Here I determine whose total score is the maximum.
		int max = 0;
		for (int i = 0; i < totals.size(); i++) {
			if (totals.get(i) > max) {
				max = totals.get(i);
				whichPlayer = i;
			}
		}

		String winnerGuy = "";

// In the playerNames array, I use the index of the player who won the game
		winnerGuy = playerNames[whichPlayer];

		display.printMessage("Congratulations " + winnerGuy + " you're the winner with a total score of " + max + "!");
	}

// This is a simple method for displaying the scores.
	private int scoreByCategory(int category) {

		int score = 0;
		if (category == ONES) {
			for (int i = 0; i < N_DICE; i++) {
				if (dice[i] == 1) {
					score = score + 1;
				}
			}
		} else if (category == TWOS) {
			for (int i = 0; i < N_DICE; i++) {
				if (dice[i] == 2) {
					score = score + 2;
				}
			}
		} else if (category == THREES) {
			for (int i = 0; i < N_DICE; i++) {
				if (dice[i] == 3) {
					score = score + 3;
				}
			}
		} else if (category == FOURS) {
			for (int i = 0; i < N_DICE; i++) {
				if (dice[i] == 4) {
					score = score + 4;
				}
			}
		} else if (category == FIVES) {
			for (int i = 0; i < N_DICE; i++) {
				if (dice[i] == 5) {
					score = score + 5;
				}
			}
		} else if (category == SIXES) {
			for (int i = 0; i < N_DICE; i++) {
				if (dice[i] == 6) {
					score = score + 6;
				}
			}
		} else if (category == FULL_HOUSE) {
			score = score + 25;
		} else if (category == SMALL_STRAIGHT) {
			score = score + 30;
		} else if (category == LARGE_STRAIGHT) {
			score = score + 40;
		} else if (category == YAHTZEE) {
			score = score + 50;
		} else if (category == CHANCE || category == THREE_OF_A_KIND || category == FOUR_OF_A_KIND) {
			for (int i = 0; i < N_DICE; i++) {
				score += dice[i];
			}
		}

		return score;

	}

// Here we check the categories if they are valid.
	private boolean categoryChecker(int dice[], int category) {

    // Categories 1 to 6 and Chance are always valid.
		if (category >= ONES && category <= SIXES || category == CHANCE) {

			return true;

		}

		 // Here I create an array where the occurrences of each number
         // rolled on the dice are counted.
		int statistics[] = new int[6];

		for (int i = 1; i <= dice.length; i++) {
			for (int j = 1; j <= 6; j++) {
				if (dice[i - 1] == j) {

					statistics[j - 1]++;

				}

			}
		}

		 // Here, based on the calculated counts, I determine which complex
         // category can be valid and return true if it is valid.
		if (category == THREE_OF_A_KIND) {
			if (statistics[0] >= 3 || statistics[1] >= 3 || statistics[2] >= 3 || statistics[3] >= 3
					|| statistics[4] >= 3 || statistics[5] >= 3) {
				return true;
			}
		} else if (category == FOUR_OF_A_KIND) {
			if (statistics[0] >= 4 || statistics[1] >= 4 || statistics[2] >= 4 || statistics[3] >= 4
					|| statistics[4] >= 4 || statistics[5] >= 4) {
				return true;
			}
		}
		// In a Full House, there must be three of one kind and two of another. 
        // Using nested for loops, I check this, and if the condition is met, return true.
		else if (category == FULL_HOUSE) {
			for (int j = 0; j < statistics.length; j++) {
				for (int i = 0; i < statistics.length; i++) {

					if (statistics[i] == 3 && statistics[j] == 2) {
						return true;
					}

				}
			}
		} else if (category == SMALL_STRAIGHT) {
			if (statistics[0] >= 1 && statistics[1] >= 1 && statistics[2] >= 1 && statistics[3] >= 1) {
				return true;
			} else if (statistics[1] >= 1 && statistics[2] >= 1 && statistics[3] >= 1 && statistics[4] >= 1) {
				return true;
			} else if (statistics[2] >= 1 && statistics[3] >= 1 && statistics[4] >= 1 && statistics[5] >= 1) {
				return true;
			}
		} else if (category == LARGE_STRAIGHT) {
			if (statistics[0] >= 1 && statistics[1] >= 1 && statistics[2] >= 1 && statistics[3] >= 1
					&& statistics[4] >= 1) {
				return true;
			} else if (statistics[1] >= 1 && statistics[2] >= 1 && statistics[3] >= 1 && statistics[4] >= 1
					&& statistics[5] >= 1) {
				return true;
			}
		} else if (category == YAHTZEE) {

			if (statistics[0] == 5 || statistics[1] == 5 || statistics[2] == 5 || statistics[3] == 5
					|| statistics[4] == 5 || statistics[5] == 5) {
				return true;
			}

		}
		return false;
	}
}
