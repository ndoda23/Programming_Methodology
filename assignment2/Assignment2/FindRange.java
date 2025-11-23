
/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {

	// We can change that symbol easily.
	private static final int SYMBOL = 0;

	public void run() {
		 
			println("This program finds the largest and smallest numbers.");
			int value = readInt("? ");
			
			firstValue(value);
			}			
	// This method does the main job. We give this method the first value,
	// after this , minimum and maximum becomes this first value. 
	// When I enter other values, the program compares it to each other
	// and we get minimum and maximum of all the values. Moreover,
	// I've got additional code. After the code finishes , program 
	// prints the difference between maximum and minimum. 
	private void firstValue(int value){

		int max = value;
		int min = value;

		if (value == SYMBOL) {
			println("You entered the special symbol.");
		} else if (value != SYMBOL) {
			while (true) {
				int number = readInt("? ");
				if (number != SYMBOL) {
					if (number > max) {
						max = number;
					}
					if (number < min) {
						min = number;
					}
				}
				if (number == SYMBOL) {
					println("smallest: " + min);
					println("largest: " + max);
/* additional */	println("The difference between the smallest and largest is: " + (max - min));
					break;
				}
			}
		}
	}

	}


