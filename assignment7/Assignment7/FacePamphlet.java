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
	
	// aq ubralod vamateb iteraqtorebs da actionlisteners.
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
    	
    	// tu daawva addButtons
    	if(e.getSource().equals(addButton) && !nameField.getText().equals("")){
    		// tu ar arsebobs acc
    		if(!info.containsProfile(nameField.getText())){
    			lastProf = new FacePamphletProfile(nameField.getText());
    			info.addProfile(lastProf);
    			canvas.displayProfile(lastProf);
    			canvas.showMessage("New profile created.");
    		}
    		// tu ukve arsebobs am saxelit acc.
    		else{
    			lastProf = info.getProfile(nameField.getText());
    			canvas.displayProfile(lastProf);
    			canvas.showMessage("A profile with the name " + lastProf.getName() +" already exists.");
    		}
    	}
    	//  delete button
    	else if(e.getSource().equals(deleteButton) && !nameField.getText().equals("")){
    		
    		// tu arsebobs acc da vshli.
    		if(info.containsProfile(nameField.getText())){
    			lastProf=null;
    			info.deleteProfile(nameField.getText());
    			canvas.remove(lastProf);
    			canvas.showMessage("Profile of  " + nameField.getText() + " deleted" );
    		}
    		// tu am saxelit acc ar arsebobs.
    		else{
    			lastProf=null;
    			canvas.showMessage("A profile with the name of " + nameField.getText() + " "+"does not exist.");
    		}
    	}
    	// look up button
    	else if(e.getSource().equals(lookUpButton) && !nameField.getText().equals("")){
    		
    		// tu arsebobs acc mis profileze unda gadavide.
    		if(info.containsProfile(nameField.getText())){
 
    			lastProf = info.getProfile(nameField.getText());
    			canvas.displayProfile(lastProf);
    			canvas.showMessage("Displaying " + nameField.getText());
    		}
    		// tu ar arsebobs profile shesabamisi mesiji gamochndeba.
    		else{
    		
    			canvas.remove(lastProf);
    			canvas.showMessage("A profile with the name " +nameField.getText() + " does not exist.");
    			lastProf = null;
    		}
    		
    	}
    	// changestatus button
    	else if(e.getSource().equals(changeStatusButton) || e.getSource().equals(statusField) && !statusField.getText().equals("")){
    		// ar arsebobs profile.
    		if(lastProf==null){

    			canvas.showMessage("Please select a profile to change status");
    			statusField.setText("");
    		}
    		// arsebobs profile da statuss vucvli , vaupdateb profiles da
    		// gamomakvs shesabamisi mesijic.
    		else{

				lastProf.setStatus(statusField.getText());
				canvas.displayProfile(lastProf);
				canvas.showMessage("Status updated to " + lastProf.getStatus());
				statusField.setText("");
				
    		}
    		
    	}
    	// change picture button
    	else if(e.getSource().equals(changePictureButton) || e.getSource().equals(pictureField) && !pictureField.getText().equals("")){
    		// tu profile arsebobs
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
    			// araswori file name
    			canvas.showMessage("Unable to open image file: " + pictureField.getText());
    			pictureField.setText("");
    		  }
    		}
    		// tu profile ar arsebobs.
    		else{
    			canvas.showMessage("Please select a profile to change picture");
    		    pictureField.setText("");
    		}
    	}
    	// addFriend button
    	else if(e.getSource().equals(addFriendButton) || e.getSource().equals(friendField) && !friendField.getText().equals("")){
    		
    		if(lastProf!=null){
    			// tu arsebobs proifle
    			if(info.containsProfile(friendField.getText())){
    				friendProf = info.getProfile(friendField.getText());
    				// tu ukve megobaria
    				if(!lastProf.addFriend(friendField.getText())){
    					
    					canvas.showMessage(lastProf.getName() + " already has " + friendField.getText()+" as a friend");
    					friendField.setText("");
    				}
    				// tu jer ar yofilan megobrebi
    				else{
    					friendProf.addFriend(lastProf.getName());
    					lastProf.addFriend(friendField.getText());
    					canvas.displayProfile(lastProf);
    					canvas.showMessage(friendField.getText()+" added as a friend");
    					friendField.setText("");
    				}
    			}
    			// tu ar arsebobs profile
    			else{

    			canvas.showMessage(friendField.getText()+" does not exist.");
    			friendField.setText("");
    				
    			}
    		}
    		// txove rom airchios profili.
    		else{
    			canvas.showMessage("Please select a profile to add friend.");
    			friendField.setText("");
    			}
       	}
	
	}

}
