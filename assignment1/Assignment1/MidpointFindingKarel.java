
/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	public void run() {
		goToTheTopMiddlePoint();
		goToTheMiddlePoint();
	}

	// This method helps Karel to go to the top middle point. As we know
	// the sizes of rows and columns are the same, so karel goes up two times
	// and moves right one time. With this method we manage to make him go to
	// the middle point on the highest row.
	private void goToTheTopMiddlePoint() {
		while (leftIsClear()) {
			move();
			turnLeft();
			move();
			if (frontIsClear()) {
				move();
			}
			turnRight();
		}
	}

	// When karel moves to the top middle point he turns right
	// and moves to the middle point on the first row.
	private void goToTheMiddlePoint() {
		turnRight();
		while (frontIsClear()) {
			move();
		}
		turnLeft();
		putBeeper();
	}

}
