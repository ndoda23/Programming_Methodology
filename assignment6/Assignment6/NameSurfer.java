/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	private NameSurferGraph graph;
	private NameSurferDataBase dtbs;
	private JButton graphButton;
	private JButton clearButton;
	private JLabel nameField;
	private JTextField text;
	private NameSurferEntry rnk;
	
	
	// adding interactors.
	public void init() {
		nameField = new JLabel("Name");
		add(nameField, SOUTH);

		text = new JTextField(20);
		add(text, SOUTH);
		text.addActionListener(this);

		graphButton = new JButton("Graph");
		add(graphButton, SOUTH);

		clearButton = new JButton("Clear");
		add(clearButton, SOUTH);
		
		graph = new NameSurferGraph(); 
		add(graph); 
		
		dtbs = new NameSurferDataBase(NAMES_DATA_FILE);


		addActionListeners();
	}

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are clicked, so
	 * you will have to define a method to respond to button actions.
	 */
	
// Depending on which button is pressed, it determines what should happen. 
// If the name is entered, the graph is displayed. 
// If the name is not entered, the text fields are cleared and the user is prompted to enter a name.
	public void actionPerformed(ActionEvent e) {
		// You fill this in //
		if (e.getActionCommand().equals("Clear")) {
			text.setText("");
			graph.clear();
			graph.update();

		} else {
			rnk = dtbs.findEntry(text.getText());
			if (rnk == null) {
				text.setText("");
			} else {
				graph.addEntry(rnk);
				graph.update();
			}
			text.setText("");

		}
	}
}
