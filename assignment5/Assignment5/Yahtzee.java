
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

	// es aris mtavari metodi sadac xdeba sxva chashlili metodebis gamodzaxeba ,
	// tamashis dawyeba , pirveli meore mesame gagorebebi
	// da sabolood metodi romelic itvlis gamarjvebuls.
	private void playGame() {
		/* You fill this in */

		rolls();

		// tamashis dasrulebis shemdeg.
		for (int i = 0; i < nPlayers; i++) {

			calculateUpperScore(i + 1);

			// tu upper score metia 63 zea dagewereba bonus qula.
			if (upperScore >= 63) {

				// aq matricshi amatebs upper bonuss da updates uketebs rata
				// gamochndes cxrilshi
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

	// arendomebs kamatlebs.
	private void diceRandomizer() {

		for (int i = 0; i < N_DICE; i++) {

			int x = rgen.nextInt(1, 6);

			dice[i] = x;
		}

	}

	// es metodi aris gagorebebis da shemdeg am metodshi viyeneb whichcategory
	// metods romelic itvlis qulebs da sheyavs cxrilshi.
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

	// meorejer gagoreba
	private void secondGagoreba() {

		for (int i = 0; i < N_DICE; i++) {
			if (display.isDieSelected(i)) {
				dice[i] = rgen.nextInt(1, 6);
			}
		}
		display.displayDice(dice);

	}

	// mesamejer gagoreba
	private void thirdGagoreba() {

		for (int i = 0; i < N_DICE; i++) {
			if (display.isDieSelected(i)) {
				dice[i] = rgen.nextInt(1, 6);
			}
		}
		display.displayDice(dice);

	}

	// metodi romelic anaxlebs cxrils da wers yovel archeul kategoriashi qulas.
	private void WhichCategory(int whichPlayer, int category) {

		category = display.waitForPlayerToSelectCategory();

		// im shemtxbebasi tu category gamoyenebulia ukve , vadzlev sashualebas
		// tavidan airchios.
		if (usedCategories[category - 1][whichPlayer - 1] == 1) {
			display.printMessage("You have already selected this category, please try another!");
			WhichCategory(whichPlayer, category);
		}

		// tu category araris gamoyeenbuli
		else if (usedCategories[category - 1][whichPlayer - 1] == 0) {

			// tu sworad aarchia kategoria. vaupdateb cxrils da matricshi
			// shemyavs shesabamisi kategoriis qula.
			if (categoryChecker(dice, category) == true) {

				int score = scoreByCategory(category);

				display.updateScorecard(category, whichPlayer, scoreByCategory(category));

				matrixOfScores[category - 1][whichPlayer - 1] = score;

				// aq mivutiteb rom kategoria gamoyenebulia rata meorejer agar
				// gadaaweros.
				usedCategories[category - 1][whichPlayer - 1] = 1;

				matrixOfScores[TOTAL - 1][whichPlayer - 1] += score;

				display.updateScorecard(TOTAL, whichPlayer, matrixOfScores[TOTAL - 1][whichPlayer - 1]);

			}
			// tu kategoria arasworad aarchia im adgilas 0 chaiwereba.
			else {
				display.updateScorecard(category, whichPlayer, 0);

				matrixOfScores[category - 1][whichPlayer - 1] = 0;

				usedCategories[category - 1][whichPlayer - 1] = 1;
			}

		}

	}

	// itvlis upper scores.
	private void calculateUpperScore(int whichPlayer) {

		upperScore = 0;

		for (int i = 0; i < 6; i++) {
			upperScore += matrixOfScores[i][whichPlayer - 1];
		}

		display.updateScorecard(UPPER_SCORE, whichPlayer, upperScore);
	}

	// itvlis lower scores da gamoakvs cxrilshi
	private void calculateLowerScore(int whichPlayer) {
		
		lowerScore = 0;

		for (int i = 8; i < 15; i++) {
			lowerScore += matrixOfScores[i][whichPlayer - 1];
		}

		display.updateScorecard(LOWER_SCORE, whichPlayer, lowerScore);
	}

	// metodi imiss gamosatvlelad tu vin gaimarjva
	private void whoIsTheWinner() {
		// shevkmeni list sadac inaxeba titoeuli motamashis totalebi
		ArrayList<Integer> totals = new ArrayList<Integer>();

		int whichPlayer = 0;

		// vamateb listshi totalebs.
		for (int i = 0; i < nPlayers; i++) {
			totals.add(matrixOfScores[TOTAL - 1][i]);
		}

		// aq gamovavlen romlis totalia maqsimumi.
		int max = 0;
		for (int i = 0; i < totals.size(); i++) {
			if (totals.get(i) > max) {
				max = totals.get(i);
				whichPlayer = i;
			}
		}

		String winnerGuy = "";

		// playerNames arrayshi indexad vwer im cifrs romelmac moigo tamashi
		winnerGuy = playerNames[whichPlayer];

		display.printMessage("Congratulations " + winnerGuy + " you're the winner with a total score of " + max + "!");
	}

	// es martivi metodia qulebis gamosatvlelad.
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

	// aq mowmdeba kategoriebi tu sworad aris mititebuli.
	private boolean categoryChecker(int dice[], int category) {

		// 1 dan 6mde da chance yoveltvis sworia.
		if (category >= ONES && category <= SIXES || category == CHANCE) {

			return true;

		}

		// aq shevqmeni array sadac chawerilia monacemebi tu romeli ricxvi
		// ramdenjeraa gagorebuli.
		int statistics[] = new int[6];

		for (int i = 1; i <= dice.length; i++) {
			for (int j = 1; j <= 6; j++) {
				if (dice[i - 1] == j) {

					statistics[j - 1]++;

				}

			}
		}

		// aq ganxiluli mak titoeuli kategoria ra shemtxvevashia martali 
		// martivi kodit.
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
		// full houseshi 3 ertnairi unda ikos da danarcheni oric ertnairi. chadgmuli for
		// loopebit gadavyevi da tu piroba daakmayofila true aris.
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