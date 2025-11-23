/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import acm.util.RandomGenerator;

import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private ArrayList <NameSurferEntry> displayedEntries;
	
	public NameSurferGraph() {
		addComponentListener(this);
		//	 You fill in the rest //
		displayedEntries = new ArrayList <NameSurferEntry>();
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	// asuftavebs lists roca clears davawvebi.
	public void clear() {
		//	 You fill this in //
		displayedEntries.clear();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	//amatebs listshi
	public void addEntry(NameSurferEntry entry) {
		// You fill this in //
		displayedEntries.add(entry);
	}
	
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	
	// esaa mtavari metodi , aketebs updates.
	public void update() {
		// You fill this in //
		removeAll();
		drawLinesAndLabels();
		for (int i = 0; i < displayedEntries.size(); i++) {
		
	
			drawGraphicsForEntries(displayedEntries.get(i),i);
			
		}

	}

	
	// xatavs im vertikalur da horizontalur xazebs romlebic ar ishleba , aseve 
	// dekadebis labelebs.
	private void drawLinesAndLabels() {

		GLine line1 = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
		add(line1);

		GLine line2 = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
		add(line2);

		for (int i = 0; i < NDECADES; i++) {

			GLine verticalLines = new GLine(getWidth() / NDECADES * i, 0, getWidth() / NDECADES * i, getHeight());
			add(verticalLines);

		}

		drawLabels();
	}

	
	// dekadebis labelebi.
	private void drawLabels() {

		for (int i = 0; i < NDECADES; i++) {

			int difference = 10;
			int year = START_DECADE + difference * i;
			GLabel decades = new GLabel(Integer.toString(year));
			int h = getHeight() - 4;
			int distanceFromBorder = 2;
			add(decades, distanceFromBorder + getWidth() / NDECADES * i, h);

		}

	}
	
	
	// aq xdeba titoeuli saxelistvis grafikis awyoba.
	// gadavcem metods ricxvs tu meramdenea saxeli da amis mixedvit
	// enicheba ferebi. sul gvaqvs 5 feri.
	private void drawGraphicsForEntries (NameSurferEntry entry ,int k){
		
		Color color ;
		if(k%5==1){
			color=Color.RED;
		}
		else if(k%5==2){
			color=Color.BLUE;
		}else if(k%5==3){
			color=Color.ORANGE;
		}
		else if(k%5==4){
			color=Color.BLACK;
		}else{
			color=Color.GRAY;
		}
		for(int i = 0 ; i<NDECADES-1;i++){
			
			int rank = entry.getRank(i);
			int nextRank = entry.getRank(i+1); 
			
			//  1 rankis danayofi mtlian canvasze.
			double titoRankisFasi =  ((double) getHeight()-2*GRAPH_MARGIN_SIZE) / MAX_RANK;
		
			// aq calcalke ganvixile 4 shemtxveva roca erti da misi shemdegi indexis rankebi 
			// ra sheidzleba iyos.
			if(noneOfThemIsZero(rank,nextRank)){
				GLine line = new GLine((i*(double)(getWidth()/NDECADES)),titoRankisFasi*rank+GRAPH_MARGIN_SIZE,((i+1)*(double)(getWidth()/NDECADES)),(double)(titoRankisFasi*nextRank+GRAPH_MARGIN_SIZE));
				line.setColor(color);
				add(line);
				GLabel label = new GLabel(entry.getName()+" "+ rank);
				label.setColor(color);
				add(label,i*getWidth()/NDECADES,titoRankisFasi*rank+GRAPH_MARGIN_SIZE);
				
			}
			else if(isBothOfThemZero(rank,nextRank) ){
				GLine line1 = new GLine(i*(double)(getWidth()/NDECADES),getHeight()-GRAPH_MARGIN_SIZE,(i+1)*(double)(getWidth()/NDECADES),getHeight()-GRAPH_MARGIN_SIZE);
				line1.setColor(color);
				add(line1);
				GLabel label = new GLabel(entry.getName()+ "*");
				label.setColor(color);
				add(label,i*getWidth()/NDECADES,getHeight()-GRAPH_MARGIN_SIZE);
			}
			else if(firstIsZero(rank,nextRank)){
				GLine line2 = new GLine(i*(double)(getWidth()/NDECADES),getHeight()-GRAPH_MARGIN_SIZE,(i+1)*(double)(getWidth()/NDECADES),GRAPH_MARGIN_SIZE +nextRank*titoRankisFasi);
				line2.setColor(color);
				add(line2);
				GLabel label = new GLabel(entry.getName()+ "*");
				label.setColor(color);
				add(label,i*getWidth()/NDECADES,getHeight()-GRAPH_MARGIN_SIZE);
			}
			else if(secondRankIsZero(rank,nextRank)){
				GLine line3 = new GLine(i*(double)(getWidth()/NDECADES),GRAPH_MARGIN_SIZE+rank*titoRankisFasi,(i+1)*(double)(getWidth()/NDECADES),getHeight()-GRAPH_MARGIN_SIZE);
				line3.setColor(color);
				add(line3);
				GLabel label = new GLabel(entry.getName() + " " + rank);
				label.setColor(color);
				add(label,i*getWidth()/NDECADES,GRAPH_MARGIN_SIZE+rank*titoRankisFasi);
			}
		}
		
	// aq calke shemyavs bolo indexis labeli , radgan masivshi bolo indexis labels ar amatebs.
    // ganvixilav or shemtxvevas roca bolo indexis ranki 0 ia da roca ar aris 0.
	if(entry.getRank(10)!=0){	
	    int distance = 4;
		double fasi = ((double) getHeight()-2*GRAPH_MARGIN_SIZE) / MAX_RANK;
		GLabel label = new GLabel(entry.getName()+" "+entry.getRank(10));
		label.setColor(color);
		add(label,distance+10*(double)((getWidth()/NDECADES)),(double)(fasi*entry.getRank(10)+GRAPH_MARGIN_SIZE));
	}else if(entry.getRank(10)==0){
		GLabel label = new GLabel(entry.getName()+"*");
		label.setColor(color);
		add(label,10*getWidth()/NDECADES,getHeight()-GRAPH_MARGIN_SIZE);
	}
		
	}
	
	// rank da misi shemdegi rank orive nolia.
	private boolean isBothOfThemZero(int i, int j) {
		return (i == 0 && j == 0);
	}
	// shemdegi rank nolia.
	private boolean secondRankIsZero(int i, int j) {
		if (i != 0 && j == 0) {
			return true;
		}
		return false;
	}
	//pirveli rank nolia
	private boolean firstIsZero(int i, int j) {
		if (i == 0 && j != 0) {
			return true;
		}
		return false;
	}
	//arcerti ar aris noli
	private boolean noneOfThemIsZero(int i, int j ){
		if (i!=0 && j!=0){
			return true;
		}
		return false;
	}
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { 
		
		update(); 
		
	}
	public void componentShown(ComponentEvent e) { }
}
