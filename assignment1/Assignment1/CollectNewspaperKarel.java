import stanford.karel.SuperKarel;

public class CollectNewspaperKarel extends SuperKarel {
	public void run() {
		goToPickTheNewspaper();
		pickTheNewspaper();
		goBack();
	}

	// This method helps our robot to reach the destination.
	private void goToPickTheNewspaper() {
		move();
		move();
		turnRight();
		move();
		turnLeft();
		move();
	}

	// Karel picks the newspaper with this method
	// and he turns around to go back.
	private void pickTheNewspaper() {
		pickBeeper();
		turnAround();
	}

	// Karel goes back to his first location.
	private void goBack() {
		move();
		move();
		move();
		turnRight();
		move();
		turnRight();
	}
}
