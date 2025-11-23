import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	public void run() {
		while (leftIsClear()) {
			oddLine();
			evenLine();
		}

		// we need it for the last line when we have
		// dimensions for example (7x7 , 5x5 , 6x5 ...) , in general
		// when the number of rows is odd
		if (leftIsBlocked()) {
			if (facingEast()) {
				putBeeper();
			}
			while (frontIsClear()) {
				move();
				if (frontIsClear()) {
					move();
					putBeeper();
				}
			}
		}
	}

	// This method works for odd rows (1,3,5 ...)
	private void oddLine() {
		putBeeper();
		while (frontIsClear()) {
			move();
			if (frontIsClear()) {
				move();
				putBeeper();
			}
		}
		goBack();
	}

	// This method works for even rows(2,4,6 ...)
	private void evenLine() {
		while (frontIsClear()) {
			move();
			putBeeper();
			if (frontIsClear()) {
				move();
			}
		}
		goBack();
	}

	// When the robot finishes working on the line , he goes back to
	// his position and is getting ready to do the operation on another line.
	private void goBack() {
		turnAround();
		while (frontIsClear()) {
			move();
		}
		turnRight();
		if (frontIsClear()) {
			move();
			turnRight();
		}
	}
}

















