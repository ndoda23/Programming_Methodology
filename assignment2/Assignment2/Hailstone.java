
/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		int number = readInt("Enter a number: ");

		calculate(number);

	}
    // We give this method number and it multiplies number by 2 
    // if it is even and it multiplies by 3 and adds 1 if it is
	// odd. Program does it until number becomes 1. 
	// When the program finishes working it prints the number of 
	// steps. 
	// If we consider 0 as a natural number  and costumer enters it ,
	// we should print we  can't multiple 0. 
	private void calculate(int number) {

		int total = 0;

		while (number != 1) {
			if (number % 2 == 0 && number != 0) {

				println(number + " is even so I take half: " + number / 2);
				number = number / 2;

				total++;
			} else if (number == 0) {
				println("0 is natural number, but you can't divide or multiplicate it.");
				break;
			} else {
				println(number + " is odd, so I make 3n+1: " + (number * 3 + 1));
				number = (number * 3 + 1);

				total++;
			}
		}
		if (number != 0) {
			println("The process took " + total + " to reach 1");
		}
	}
}
