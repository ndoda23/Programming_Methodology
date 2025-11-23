
/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {

	public static final double BIG_OVAL = 72;
	public static final double AVERAGE_OVAL = 46.7;
	public static final double SMALL_OVAL = 21.54;

	public void run() {

		bigOval();
		averageOval();
		smallOval();

	}

	// This method is for the biggest oval , which is red.
	private void bigOval() {
		GOval oval = new GOval(2 * BIG_OVAL, 2 * BIG_OVAL);
		oval.setFilled(true);
		oval.setColor(Color.RED);
		add(oval, getWidth() / 2 - BIG_OVAL, getHeight() / 2 - BIG_OVAL);
	}

	// This method is for the average oval ,which is white.
	private void averageOval() {
		GOval averageOval = new GOval(2 * AVERAGE_OVAL, 2 * AVERAGE_OVAL);
		averageOval.setFilled(true);
		averageOval.setColor(Color.WHITE);
		add(averageOval, getWidth() / 2 - AVERAGE_OVAL, getHeight() / 2 - AVERAGE_OVAL);
	}

	// This method is for the smallest oval , which is red.
	private void smallOval() {
		GOval smallOval = new GOval(2 * SMALL_OVAL, 2 * SMALL_OVAL);
		smallOval.setFilled(true);
		smallOval.setColor(Color.RED);
		add(smallOval, getWidth() / 2 - SMALL_OVAL, getHeight() / 2 - SMALL_OVAL);
	}

}
