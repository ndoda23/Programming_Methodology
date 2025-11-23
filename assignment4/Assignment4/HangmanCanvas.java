
/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

	/** Resets the display so that only the scaffold appears */

	public void reset() {
		/* You fill this in */
		drawSaxrchobela();

	}
	
	/**
	 * Updates the word on the screen to correspond to the current state of the
	 * game. The argument string shows what letters have been guessed so far;
	 * unguessed letters are indicated by hyphens.
	 */

	// label for blocked word 
	GLabel blockedLabel;

	public void displayWord(String word) {

		int x = getWidth() / 2 - BEAM_LENGTH;

		int y = SCAFFOLD_HEIGHT + HEAD_RADIUS * 4 / 3;

		if (blockedLabel == null) {
			wordsLabel(word, x, y);
		} else if (blockedLabel != null) {

			blockedLabel.setLabel(word);

		}
	}

	
	// this method adds label.
	private void wordsLabel(String word, int x, int y) {

		blockedLabel = new GLabel(word, x, y);
		blockedLabel.setFont("Serif-bold-36");

		add(blockedLabel);
	}

	/**
	 * Updates the display to correspond to an incorrect guess by the user.
	 * Calling this method causes the next body part to appear on the scaffold
	 * and adds the letter to the list of incorrect guesses that appears at the
	 * bottom of the window.
	 */

	// label for incorrect guesses
	GLabel characterLabel;

	// string for incorrect chars
	private String arasworiChars = "";
	
	public void noteIncorrectGuess(char letter) {
		/* You fill this in */

		arasworiChars += letter;

		if (characterLabel == null) {
			characterLabel = new GLabel(arasworiChars, getWidth() / 2 - BEAM_LENGTH,
					SCAFFOLD_HEIGHT + HEAD_RADIUS * 2);

			characterLabel.setFont("Serif-bold-18");
			add(characterLabel);
		} else if (characterLabel != null) {

			characterLabel.setFont("Serif-bold-18");

			characterLabel.setLabel(arasworiChars);

		}

		// adding body parts 
		if (arasworiChars.length() == 1) {
			drawHead();
		}
		if (arasworiChars.length() == 2) {
			drawBody();
		}
		if (arasworiChars.length() == 3) {
			drawLeftHand();
		}
		if (arasworiChars.length() == 4) {
			drawRightHand();
		}
		if (arasworiChars.length() == 5) {
			drawLeftLeg();
		}
		if (arasworiChars.length() == 6) {
			drawRightLeg();
		}
		if (arasworiChars.length() == 7) {
			drawLeftFoot();
		}
		if (arasworiChars.length() == 8) {
			drawRightFoot();
		}

	}

	// It draws head.
	private void drawHead() {
		GOval oval = new GOval(HEAD_RADIUS * 2, HEAD_RADIUS * 2);

		int x = getWidth() / 2 - HEAD_RADIUS;
		int y = getHeight() / 16;

		oval.setLocation(x, y);

		oval.setFilled(false);

		add(oval);
	}

	
	// It draws scaffold , beam and rope.
	private void drawSaxrchobela() {
		GLine scaffold = new GLine(getWidth() / 2 - 2 * UPPER_ARM_LENGTH, getHeight() / 16 - ROPE_LENGTH,
				getWidth() / 2 - 2 * UPPER_ARM_LENGTH, getHeight() / 16 - ROPE_LENGTH + SCAFFOLD_HEIGHT);

		add(scaffold);

		GLine beam = new GLine(getWidth() / 2 - 2 * UPPER_ARM_LENGTH, getHeight() / 16 - ROPE_LENGTH, getWidth() / 2,
				getHeight() / 16 - ROPE_LENGTH);

		add(beam);

		GLine rope = new GLine(getWidth() / 2, getHeight() / 16 - ROPE_LENGTH, getWidth() / 2, getHeight() / 16);

		add(rope);

	}

	// It draws body.
	private void drawBody() {

		GLine body = new GLine(getWidth() / 2, getHeight() / 16 + 2 * HEAD_RADIUS, getWidth() / 2,
				getHeight() / 16 - ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH);

		add(body);

	}

	
	// It draws left hand.
	private void drawLeftHand() {

		GLine leftUpperHand = new GLine(getWidth() / 2, getHeight() / 16 + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
				getWidth() / 2 - UPPER_ARM_LENGTH, getHeight() / 16 + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);

		add(leftUpperHand);

		GLine leftLowerHand = new GLine(getWidth() / 2 - UPPER_ARM_LENGTH,
				getHeight() / 16 + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD, getWidth() / 2 - UPPER_ARM_LENGTH,
				getHeight() / 16 + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);

		add(leftLowerHand);

	}

	
	// It draws right hand.
	private void drawRightHand() {

		GLine rightUpperHand = new GLine(getWidth() / 2, getHeight() / 16 + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
				getWidth() / 2 + UPPER_ARM_LENGTH, getHeight() / 16 + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);

		add(rightUpperHand);

		GLine rightLowerHand = new GLine(getWidth() / 2 + UPPER_ARM_LENGTH,
				getHeight() / 16 + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD, getWidth() / 2 + UPPER_ARM_LENGTH,
				getHeight() / 16 + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);

		add(rightLowerHand);
	}

	
	// It draws left leg.
	private void drawLeftLeg() {

		GLine leftHip = new GLine(getWidth() / 2, getHeight() / 16 - ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH,
				getWidth() / 2 - HIP_WIDTH, getHeight() / 16 - ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH);

		add(leftHip);

		GLine leftLeg = new GLine(getWidth() / 2 - HIP_WIDTH,
				getHeight() / 16 - ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH, getWidth() / 2 - HIP_WIDTH,
				getHeight() / 16 - ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);

		add(leftLeg);
	}

	
	// It draws right leg.
	private void drawRightLeg() {

		GLine rightHip = new GLine(getWidth() / 2, getHeight() / 16 - ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH,
				getWidth() / 2 + HIP_WIDTH, getHeight() / 16 - ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH);

		add(rightHip);

		GLine rightLeg = new GLine(getWidth() / 2 + HIP_WIDTH,
				getHeight() / 16 - ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH, getWidth() / 2 + HIP_WIDTH,
				getHeight() / 16 - ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);

		add(rightLeg);
	}

	
	// It draws left foot.
	private void drawLeftFoot() {

		GLine leftFoot = new GLine(getWidth() / 2 - HIP_WIDTH,
				getHeight() / 16 - ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH,
				getWidth() / 2 - HIP_WIDTH - FOOT_LENGTH,
				getHeight() / 16 - ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);

		add(leftFoot);

	}

	
	// It draws right foot.
	private void drawRightFoot() {

		GLine rightFoot = new GLine(getWidth() / 2 + HIP_WIDTH,
				getHeight() / 16 - ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH,
				getWidth() / 2 + HIP_WIDTH + FOOT_LENGTH,
				getHeight() / 16 - ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);

		add(rightFoot);

	}

	/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

}
