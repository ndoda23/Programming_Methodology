\import stanford.karel.SuperKarel;

public class StoneMasonKarel extends SuperKarel {
	public void run() {

		while (frontIsClear()) {
			goAndRepair();
			goBack();
			moveFourTimes();
		}

		repairTheLastColumn(); // This method repairs the last column

	}

	// This method helps the robot to repair the entire one column
	private void goAndRepair() {
		turnLeft();
		while (frontIsClear()) {
			if (beepersPresent()) {
				move();
			}
			if (noBeepersPresent()) {
				if (frontIsClear()) {
					putBeeper();
					move();
				} else {
					putBeeper();
				}
			}
		}
	}

	// With this method Karel comes back to the first line and is getting ready
	// to move on. If there is no beeper on the first line , he puts it.
	private void goBack() {
		turnAround();
		while (frontIsClear()) {
			move();
		}
		if (noBeepersPresent()) {
			putBeeper();
		}
		turnLeft();

	}

	// Karel has to think about repairing a wall in every 4 steps , so this
	// method helps him to move four times in a row.
	private void moveFourTimes() {
		move();
		move();
		move();
		move();
	}

	// This method repairs the last column
	private void repairTheLastColumn() {
		goAndRepair();
		goBack();
	}
}