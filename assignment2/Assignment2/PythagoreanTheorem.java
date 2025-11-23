
/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		println("Enter values to compute Pythagorean theorem.");
		int a = readInt("a: ");
		int b = readInt("b: ");
   		double answer = hypotenuse(a, b);
		if (a >= 0 && b >= 0) {
			println("c = " + answer);
			
		} 
        // If any negative number is printed. We should tell the costumer
		// that we can't use Pythagorean theorem in negative numbers.
		else if (a < 0 || b < 0) {  
			println("Both of the numbers must be positive");
		}
	}
   // This method calculates hypotenuse with the help of formula 
   // a^2 + b^2 = c^2 
	public double hypotenuse(int a, int b) {

		double c = Math.sqrt(a * a + b * b);

		return c;

	}
}
