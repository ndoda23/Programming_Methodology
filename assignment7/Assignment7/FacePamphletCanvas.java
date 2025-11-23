/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	private Iterator<String> iterator;
	GLabel messageLabel = new GLabel("");
	Color nameColor = Color.BLUE;
	private GLabel nameLabel;
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	
	
	public FacePamphletCanvas() {
		// You fill this in
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		// You fill this in

		
		messageLabel.setLabel(msg);
		messageLabel.setLocation(getWidth()/2-messageLabel.getWidth()/2,
		getHeight()-BOTTOM_MESSAGE_MARGIN);
		messageLabel.setFont(MESSAGE_FONT);
		add(messageLabel);

	}
	
	// removes everything on canvas.
	public void remove(FacePamphletProfile profile){
		removeAll();
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */

	public void displayProfile(FacePamphletProfile profile) {
		// You fill this in
		removeAll();
		String name = profile.getName();
		String status = profile.getStatus();
		iterator = profile.getFriends();
		GImage image = profile.getImage();
		drawName(name);
		drawImage(image);
		drawStatus(status,profile);
		drawFriendList(iterator);
	}
	
	
	// draws friends list.
	private void drawFriendList(Iterator<String> friendsList) {
		// TODO Auto-generated method stub
		GLabel header = new GLabel("Friends:");
	    header.setLocation(getWidth()/2-header.getWidth()/2,IMAGE_MARGIN + TOP_MARGIN + nameLabel.getAscent() + header.getAscent()/2);
		header.setFont(PROFILE_FRIEND_LABEL_FONT);
	    add(header);
	    for(int i = 1;friendsList.hasNext();i++){
	    	GLabel nextFriend = new GLabel(friendsList.next());	
	    	nextFriend.setFont(PROFILE_FRIEND_FONT );
	    	// distance between friends
	    	int dist = 15;
	    	add(nextFriend,getWidth()/2-header.getWidth()/2,
	    			IMAGE_MARGIN + TOP_MARGIN + nameLabel.getAscent() + header.getAscent()/2 +dist*i);
	    }
	}


	// Sets the status message; for each update, the status is overwritten.
    // If no status is provided, the current status remains unchanged.
	private void drawStatus(String st,FacePamphletProfile profile) {
		// TODO Auto-generated method stub
		
		String name = profile.getName();
		if( st.equals("")){
			GLabel statusLabel = new GLabel("No current status");
			statusLabel.setFont(PROFILE_STATUS_FONT);
			add(statusLabel,LEFT_MARGIN ,STATUS_MARGIN +IMAGE_MARGIN + TOP_MARGIN + nameLabel.getAscent()+IMAGE_HEIGHT);

		}else{
			GLabel statusLabel = new GLabel(name+ " is " + st);
			statusLabel.setFont(PROFILE_STATUS_FONT);
			statusLabel.setLocation(LEFT_MARGIN ,STATUS_MARGIN +IMAGE_MARGIN + TOP_MARGIN + nameLabel.getAscent()+IMAGE_HEIGHT);
			add(statusLabel);
		}
		
	}


	// Displays the photo; if the photo is null, it will be ignored.
    // In a simple implementation, it could display: "No Image".
	private void drawImage(GImage image) {
		// TODO Auto-generated method stub
		if(image==null){
			GRect rect = new GRect(IMAGE_WIDTH,IMAGE_HEIGHT);
			rect.setFilled(false);
			rect.setLocation(LEFT_MARGIN,IMAGE_MARGIN + TOP_MARGIN + nameLabel.getAscent());
			add(rect);
			GLabel noImage = new GLabel("No Image");
			noImage.setFont(PROFILE_IMAGE_FONT);
			noImage.setLocation(LEFT_MARGIN+IMAGE_WIDTH/2-noImage.getWidth()/2,
					IMAGE_MARGIN + TOP_MARGIN + nameLabel.getAscent()+IMAGE_HEIGHT/2+noImage.getAscent()/2);
			add(noImage);
		}else{ 
			 image.setBounds(LEFT_MARGIN,IMAGE_MARGIN + TOP_MARGIN + nameLabel.getAscent(),IMAGE_WIDTH,IMAGE_HEIGHT);
			
			 add(image);
		}
	}
						
	private void drawName(String Name){
		nameLabel = new GLabel (Name);
		nameLabel.setFont(PROFILE_NAME_FONT);
		nameLabel.setColor(nameColor);
		nameLabel.setLocation(LEFT_MARGIN,TOP_MARGIN + nameLabel.getAscent());
		add(nameLabel);
		
	}
	
	

	
}
