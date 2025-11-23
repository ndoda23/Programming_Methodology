
/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {

	public static final double RECTANGULAR_WIDTH = 170;
	public static final double RECTANGULAR_HEIGHT = 70;

	public void run() {

		headerRectangle();
		rectangleConsoleProgram();
		rectangleDialogProgram();
		rectangleGraphicsProgram();
		middleLine();
     	rightLine();
     	leftLine();
     	labelProgram();
		labelGraphicsProgram();
		labelConsoleProgram();
		labelDialogProgram();
	}
	
	
	
    // This method is for the main rectangle , which is named Program.
	private void headerRectangle() {
		GRect r1 = new GRect(RECTANGULAR_WIDTH, RECTANGULAR_HEIGHT);
		add(r1, getWidth() / 2 - RECTANGULAR_WIDTH / 2, 
				getHeight() / 3 - RECTANGULAR_HEIGHT / 2);
	}

	// This method is for rectangle named Console Program.
	private void rectangleConsoleProgram() {
		GRect r2 = new GRect(RECTANGULAR_WIDTH, RECTANGULAR_HEIGHT);
		add(r2, getWidth() / 2 - RECTANGULAR_WIDTH / 2,
				getHeight() - (getHeight() / 3) - RECTANGULAR_HEIGHT / 2);

	}
   
	//This method is for rectangle named Dialog Program.
	private void rectangleDialogProgram() {

		GRect r3 = new GRect(RECTANGULAR_WIDTH, RECTANGULAR_HEIGHT);
		add(r3, getWidth() - (getWidth() / 8) - RECTANGULAR_WIDTH,
				getHeight() - (getHeight() / 3) - RECTANGULAR_HEIGHT / 2);

	}	

	//This method is for rectangle named Graphics Program.
	private void rectangleGraphicsProgram() {
		GRect r4 = new GRect(RECTANGULAR_WIDTH, RECTANGULAR_HEIGHT);
		add(r4, getWidth() / 8,
				getHeight() - (getHeight() / 3) - RECTANGULAR_HEIGHT / 2);
	}

	// This method is for the line which connects Program rectangle and
	// Console rectangle
	private void middleLine(){
		GLine l1 = new GLine((getWidth() / 2 - RECTANGULAR_WIDTH / 2)+RECTANGULAR_WIDTH/2,
				(getHeight() / 3 - RECTANGULAR_HEIGHT / 2)+RECTANGULAR_HEIGHT,
				(getWidth() / 2 - RECTANGULAR_WIDTH / 2)+RECTANGULAR_WIDTH/2,
				getHeight() - (getHeight() / 3) - RECTANGULAR_HEIGHT / 2
				);
		add(l1);
	}
	
	// This method is for the line which connects Program rectangle and
	// Dialog rectangle.
	private void rightLine(){
		
		GLine l2 = new GLine((getWidth() / 2 - RECTANGULAR_WIDTH / 2)+RECTANGULAR_WIDTH/2,
				(getHeight() / 3 - RECTANGULAR_HEIGHT / 2)+RECTANGULAR_HEIGHT
				, getWidth() - (getWidth() / 8) - RECTANGULAR_WIDTH/2
				,getHeight() - (getHeight() / 3) - RECTANGULAR_HEIGHT / 2);
		add(l2);		
		
	}
	
	// This method is for the line which connects Program rectangle
	// and Graphics rectangle.
	private void leftLine(){
		GLine l3 = new GLine((getWidth() / 2 - RECTANGULAR_WIDTH / 2)+RECTANGULAR_WIDTH/2
				,(getHeight() / 3 - RECTANGULAR_HEIGHT / 2)+RECTANGULAR_HEIGHT
				,getWidth() / 8+(RECTANGULAR_WIDTH/2)
				,getHeight() - (getHeight() / 3) - RECTANGULAR_HEIGHT / 2
				);
		add(l3);
	}
	
	
	// This method is for the label - ''Program''.
	private void labelProgram(){
		GLabel labelProgram = new GLabel("Program");
		labelProgram.setLocation((getWidth() / 2 - labelProgram.getWidth()/2) , 
				getHeight() / 3 + labelProgram.getAscent()/2);
		add(labelProgram);
	}

	// This method is for the label - ''GraphicsProgram''.
	private void labelGraphicsProgram() {
		GLabel labelGraphicsProgram = new GLabel("GraphicsProgram");
		labelGraphicsProgram.setLocation((getWidth() / 8+RECTANGULAR_WIDTH/2)-labelGraphicsProgram.getWidth()/2,
				(getHeight() - (getHeight() / 3))+labelGraphicsProgram.getAscent()/2);
		add(labelGraphicsProgram);
	}
	
	// This method is for the label - ''ConsoleProgram''.
	private void labelConsoleProgram(){
		GLabel labelConsoleProgram = new GLabel("ConsoleProgram");
		labelConsoleProgram.setLocation(getWidth() / 2 - labelConsoleProgram.getWidth()/2,
				getHeight() - (getHeight() / 3) + labelConsoleProgram.getAscent()/2);
		
		add(labelConsoleProgram);
	}
	
	// This method is for the label - ''DialogProgram'.
	private void labelDialogProgram(){
		GLabel labelDialogProgram = new GLabel("DialogProgram");
		labelDialogProgram.setLocation(getWidth() - (getWidth() / 8) - RECTANGULAR_WIDTH/2 - labelDialogProgram.getWidth()/2,
				getHeight() - (getHeight() / 3) + labelDialogProgram.getAscent()/2);
		add(labelDialogProgram);
	}
	
}
