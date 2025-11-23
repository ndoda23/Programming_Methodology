/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

	
	private String name; 
	
	//rankebis masivi
	private int [] ranks = new int [NDECADES] ;
	
	private String newStr;
/* Constructor: NameSurferEntry(line) */
/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file.  Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 */
	public NameSurferEntry(String line) {
		// You fill this in //
	
		methodForName(line);
		methodForRanks(line);
		
	}
	
// Here, only the name is extracted from the string.
	private void methodForName(String line){
		
		name = "";
		for (int i = 0; i < line.length(); i++) {

			if (line.charAt(i) != ' ') {
				name += line.charAt(i);
			}
			
			if(line.charAt(i) == ' '){
				break;
			}
		}
		
		
	}
	
// Here, numbers are extracted from the string, and using a tokenizer, each number is placed into an array.
	private void methodForRanks(String line) {

		newStr = line.substring(name.length()+1);

		StringTokenizer tokenizer = new StringTokenizer(newStr," ");

		for (int i = 0; i < NDECADES; i++) {
			if (tokenizer.hasMoreTokens()) {
				ranks[i] = Integer.parseInt(tokenizer.nextToken());
			}
		}

	}

/* Method: getName() */
/**
 * Returns the name associated with this entry.
 */
	
	
	public String getName() {
		// You need to turn this stub into a real implementation //
		return name;
	}

/* Method: getRank(decade) */
/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
	public int getRank(int decade) {
		// You need to turn this stub into a real implementation //
		return ranks[decade];
	}

/* Method: toString() */
/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
	
	public String toString() {
		// You need to turn this stub into a real implementation //
		String text = "\"" + name + " " + "[";
		
		text += newStr + "]" + "\"";
				
		
		return text;
	}
}

