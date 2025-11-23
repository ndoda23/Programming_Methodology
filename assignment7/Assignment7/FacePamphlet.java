/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	
	GImage image = null; 
	
	// buttons
    private JButton addButton;
    private JButton deleteButton;
    private JButton lookUpButton;
    private JButton changeStatusButton;
    private JButton changePictureButton;
    private JButton addFriendButton;

    
    private JTextField nameField;
    private JTextField statusField;
    private JTextField pictureField;
    private JTextField friendField;
    
    
    // data base
    private FacePamphletDatabase info = new FacePamphletDatabase();
    
    
    // last profile
	private FacePamphletProfile lastProf = null;
	private FacePamphletProfile friendProf = null;
	
	
	// for canvas
	private FacePamphletCanvas canvas;
	
	// adding iteractors and actionlisteners.
	public void init() {
		// You fill this in
		
		JLabel nameLabel = new JLabel("Name");
		add(nameLabel, NORTH);
		
		nameField = new JTextField(15);
		add(nameField,NORTH);
		
		addButton = new JButton ("Add");
		add(addButton,NORTH);
		
		deleteButton = new JButton ("Delete");
		add(deleteButton,NORTH);
		
		lookUpButton = new JButton("Lookup");
		add(lookUpButton,NORTH);
		
		statusField = new JTextField(15);
		add(statusField,WEST);
		statusField.addActionListener(this);
		
		changeStatusButton = new JButton("Change Status");
		add(changeStatusButton,WEST);
		
		add(new JLabel(EMPTY_LABEL_TEXT), WEST); 
		
		pictureField = new JTextField(15);
		add(pictureField,WEST);
		pictureField.addActionListener(this);
		
		changePictureButton = new JButton("Change Picture");
		add(changePictureButton,WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST); 
		
		friendField = new JTextField(15);
		add(friendField,WEST);
		friendField.addActionListener(this);
		
		addFriendButton = new JButton("Add Friend");
		add(addFriendButton,WEST);
		
		canvas = new FacePamphletCanvas(); 
		add(canvas);

	
		addActionListeners();
    }
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		// You fill this in as well as add any additional methods
    	
// If the Add button is pressed
    	if(e.getSource().equals(addButton) && !nameField.getText().equals("")){
    // If the account does not exist
    		if(!info.containsProfile(nameField.getText())){
    			lastProf = new FacePamphletProfile(nameField.getText());
    			info.addProfile(lastProf);
    			canvas.displayProfile(lastProf);
    			canvas.showMessage("New profile created.");
    		}
    // If the account already exists
    		else{
    			lastProf = info.getProfile(nameField.getText());
    			canvas.displayProfile(lastProf);
    			canvas.showMessage("A profile with the name " + lastProf.getName() +" already exists.");
    		}
    	}
       //  delete button
    	else if(e.getSource().equals(deleteButton) && !nameField.getText().equals("")){
    		
       // If the account exists and should be deleted
    		if(info.containsProfile(nameField.getText())){
    			lastProf=null;
    			info.deleteProfile(nameField.getText());
    			canvas.remove(lastProf);
    			canvas.showMessage("Profile of  " + nameField.getText() + " deleted" );
    		}
       // Account with this name does not exist.
    		else{
    			lastProf=null;
    			canvas.showMessage("A profile with the name of " + nameField.getText() + " "+"does not exist.");
    		}
    	}
    	// look up button
    	else if(e.getSource().equals(lookUpButton) && !nameField.getText().equals("")){
    		
        // If the account exists, display its profile.
    		if(info.containsProfile(nameField.getText())){
 
    			lastProf = info.getProfile(nameField.getText());
    			canvas.displayProfile(lastProf);
    			canvas.showMessage("Displaying " + nameField.getText());
    		}
        // If the profile does not exist, show an appropriate message.
    		else{
    		
    			canvas.remove(lastProf);
    			canvas.showMessage("A profile with the name " +nameField.getText() + " does not exist.");
    			lastProf = null;
    		}
    		
    	}
    	// changestatus button
    	else if(e.getSource().equals(changeStatusButton) || e.getSource().equals(statusField) && !statusField.getText().equals("")){
    		// profile does not exist.
    		if(lastProf==null){

    			canvas.showMessage("Please select a profile to change status");
    			statusField.setText("");
    		}
    		// The profile exists and the status is not empty; update the profile
            // and display an appropriate message.
    		else{

				lastProf.setStatus(statusField.getText());
				canvas.displayProfile(lastProf);
				canvas.showMessage("Status updated to " + lastProf.getStatus());
				statusField.setText("");
				
    		}
    		
    	}
    	// change picture button
    	else if(e.getSource().equals(changePictureButton) || e.getSource().equals(pictureField) && !pictureField.getText().equals("")){
    		// if profile exists
    		if(lastProf!=null){		
    		try { 
    		image = new GImage(pictureField.getText());
    		lastProf.setImage(image);
    		canvas.displayProfile(lastProf);
    		canvas.showMessage("Picture updated");
    		pictureField.setText("");
    		} catch (ErrorException ex) { 
    		// Code that is executed if the filename cannot be opened. 
    			image = null;
    			// incorrect file name
    			canvas.showMessage("Unable to open image file: " + pictureField.getText());
    			pictureField.setText("");
    		  }
    		}
    		// if profile doesn't exists
    		else{
    			canvas.showMessage("Please select a profile to change picture");
    		    pictureField.setText("");
    		}
    	}
    	// addFriend button
    	else if(e.getSource().equals(addFriendButton) || e.getSource().equals(friendField) && !friendField.getText().equals("")){
    		
    		if(lastProf!=null){
    		// if profile exists
    			if(info.containsProfile(friendField.getText())){
    				friendProf = info.getProfile(friendField.getText());
    				// if is friend already
    				if(!lastProf.addFriend(friendField.getText())){
    					
    					canvas.showMessage(lastProf.getName() + " already has " + friendField.getText()+" as a friend");
    					friendField.setText("");
    				}
                    // If they are no friends.
     				else{
    					friendProf.addFriend(lastProf.getName());
    					lastProf.addFriend(friendField.getText());
    					canvas.displayProfile(lastProf);
    					canvas.showMessage(friendField.getText()+" added as a friend");
    					friendField.setText("");
    				}
    			}
    			//  if profile doesn't exist
    			else{

    			canvas.showMessage(friendField.getText()+" does not exist.");
    			friendField.setText("");
    				
    			}
    		}
    		
    		else{
    			canvas.showMessage("Please select a profile to add friend.");
    			friendField.setText("");
    			}
       	}
	
	}

}
