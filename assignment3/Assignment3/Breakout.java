
/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;

	// Instance variables.
	
	private GRect paddle;

	private GRect rect;

	private GOval ball;

	private GLabel labelWinner;

	private GLabel labelLoser;

	
	// We need random generator to evaluate vx. 
	private RandomGenerator rgen = RandomGenerator.getInstance();

	private double vx;

	private double vy = 3.0;

	// I need this variable to count how many bricks are left. 
	// When there are 0 bricks left , you win the game.
	private int total = NBRICK_ROWS * NBRICKS_PER_ROW;

	// I need this variable to counter how many turns are left.
	// When the ball goes below Y axis I reduce this variable by one.
	// At the start of the game you have only 3 chances,
	// When there are 0 turns left , you lose the game.
	private int turnsLeft = NTURNS;

	/* Method: run() */
	/** Runs the Breakout program. */

	public void run() {
		/* You fill this in, along with any subsidiary methods */

		paintTheRectangles();
		paintThePaddle();
		paintTheBall();
		addMouseListeners();
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5)) {
			vx = -vx;
		}
		while (true) {
			movingTheBall();

			// This is the case when the ball lands below the paddle. If it goes below
			// The paddle 3 times, the game is over.
			if (ball.getY() + 2 * BALL_RADIUS >= getHeight()) {
				vx = rgen.nextDouble(1.0, 3.0);
				if (rgen.nextBoolean(0.5)) {
					vx = -vx;
				}
				remove(ball);
				paintTheBall();
				turnsLeft--;
				if (turnsLeft == 0) {
					removeAll();
					redBackgroundAfterLosing();
					labelLoser();
					break;
				}
			}
            // If the ball does not go below the paddle. The program
			// keeps going.
			checkWhatToDo();
			if (total == 0) {
				removeAll();
				greenBackgroundAfterWinning();
				labelWinner();
				break;
			}

		}

	}

	// This method draws the rectangles 10x10.
	// I managed to change the colors of the rectangles with the help of
	// if else statements and the value of j.
	private void paintTheRectangles() {

		for (int i = 0; i < NBRICK_ROWS; i++) {

			for (int j = 0; j < NBRICKS_PER_ROW; j++) {
				rect = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
				rect.setFilled(true);
				rect.setLocation(BRICK_SEP / 2 + (BRICK_SEP + BRICK_WIDTH) * i,
						(BRICK_Y_OFFSET + NBRICK_ROWS * BRICK_HEIGHT + NBRICK_ROWS * BRICK_SEP)
								- j * (BRICK_HEIGHT + BRICK_SEP));
				add(rect);

				if (j < 2) {
					rect.setColor(Color.CYAN);
				} else if (j < 4) {
					rect.setColor(Color.GREEN);
				} else if (j < 6) {
					rect.setColor(Color.YELLOW);
				} else if (j < 8) {
					rect.setColor(Color.ORANGE);
				} else if (j < 10) {
					rect.setColor(Color.RED);
				}
			}
		}
	}

	// This method draws the paddle.
	private void paintThePaddle() {
		paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		int paddleX = getWidth() / 2 - PADDLE_WIDTH / 2;
		int paddleY = getHeight() - PADDLE_Y_OFFSET;
		paddle.setLocation(paddleX, paddleY);
		paddle.setFilled(true);
		add(paddle);
	}

	// This method draws the ball.
	private void paintTheBall() {
		ball = new GOval(2 * BALL_RADIUS, 2 * BALL_RADIUS);
		ball.setFilled(true);
		ball.setLocation(getWidth() / 2 - BALL_RADIUS, getHeight() / 2 - BALL_RADIUS);
		add(ball);
	}

	// This method help paddle to move with mouse.
	// This method assumes that the paddle does not go beyond the borders.
	public void mouseMoved(MouseEvent e) {

		if (e.getX() >= 0 && e.getX() < getWidth() - PADDLE_WIDTH - BRICK_SEP / 2) {
			paddle.setLocation(e.getX(), getHeight() - PADDLE_Y_OFFSET);
		}
	}

	// In this method we move the ball in the given borders.
	// This method assumes that the ball does not go beyond the borders.
	private void movingTheBall() {
		if (ball.getX() + 2 * BALL_RADIUS >= getWidth() || ball.getX() <= 0) {
			vx = -vx;
		}

		if (ball.getY() <= 0) {
			vy = -vy;
		}

		ball.move(vx, vy);
		pause(10);

	}

	// This method checks which object the ball hits. If the ball hits the
	// paddle
	// We want to return paddle. If it hits the rectangle , we want to return the
	// rectangle.
	// If it hits nor rectangle neither paddle , we return null.
	private GObject getCollidingObject() {
		if (getElementAt(ball.getX(), ball.getY()) != null) {
			GObject collider = getElementAt(ball.getX(), ball.getY());
			return collider;
		} else if (getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS) != null) {
			GObject collider = getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS);
			return collider;
		} else if (getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY()) != null) {
			GObject collider = getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY());
			return collider;
		} else if (getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS) != null) {

			GObject collider = getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS);
			return collider;
		} else {
			return null;
		}
	}

	// This method checks what to do when the ball touches the paddle or rectangles.
	// When it touches the rectangle we want to remove the rectangle and
	// change direction of the ball. 
	// When the ball touches
	// The paddle we want to change the ball's direction. That's why we change
	// vy.
	private void checkWhatToDo() {

		GObject collider = getCollidingObject();

		
			if (collider!=null & collider == paddle) {
               // modulshi svams vy-s da shemdeg atrialebs.
				vy = -Math.abs(vy);

			} 
			if (collider!=null & collider != paddle){

				remove(collider);
				vy = -vy;
				total -= 1;
			}
		}
	

	// This label appears when you win the game.
	private void labelWinner() {
		labelWinner = new GLabel("You Won !");
		labelWinner.setLocation(getWidth() / 2 - labelWinner.getWidth(), getHeight() / 2 + labelWinner.getAscent() / 2);
		labelWinner.setFont("Serif-bold-24");
		add(labelWinner);
	}

	// This label appears when you lose the game.
	private void labelLoser() {
		labelLoser = new GLabel("You Lost !");
		labelLoser.setLocation(getWidth() / 2 - labelLoser.getWidth(), getHeight() / 2 + labelLoser.getAscent() / 2);
		labelLoser.setFont("Serif-bold-24");
		add(labelLoser);
	}

	// This is background rectangle.
	// When the player loses the game red background appears.
	private void redBackgroundAfterLosing() {
		GRect BG1 = new GRect(400, 600);
		BG1.setFilled(true);
		BG1.setColor(Color.RED);
		add(BG1, 0, 0);
	}

	// This is background rectangle.
	// When the player wins the game green background appears.
	private void greenBackgroundAfterWinning() {
		GRect BG2 = new GRect(400, 600);
		BG2.setFilled(true);
		BG2.setColor(Color.GREEN);
		add(BG2, 0, 0);
	}
}
