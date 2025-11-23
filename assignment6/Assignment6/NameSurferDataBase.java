import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {
	
	
	private Map <String , NameSurferEntry> base = new HashMap <String, NameSurferEntry>();
/* Constructor: NameSurferDataBase(filename) */
/**
 * Creates a new NameSurferDataBase and initializes it using the
 * data in the specified file.  The constructor throws an error
 * exception if the requested file does not exist or if an error
 * occurs as the file is being read.
 */
	
	// kitxulobs names - data fails da base hashmapshi sheaqvs saxeli da 
	// am saxelis mixedvit monacemebi.
	public NameSurferDataBase(String filename) {
		// You fill this in //
		

		try {

			BufferedReader  reader = new BufferedReader(new FileReader(NAMES_DATA_FILE));

			while (true) {
				String row = reader.readLine();
				if (row == null) {
					break;
				}
				NameSurferEntry entry = new NameSurferEntry(row);
				String nm = entry.getName();
				base.put(nm, entry);
				
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one exists. If
	 * the name does not appear in the database, this method returns null.
	 */
	
	// aq vitvaliswineb shemtxvevas rom didi asoti shemoiyvanon saxeli
	// mas gadavaketeb iset stringad , rogorc mocemuli gvak names -data fileshi.
	public NameSurferEntry findEntry(String name) {
		// You need to turn this stub into a real implementation //

		name = name.toUpperCase();
		String rem = name.substring(1);
		rem = rem.toLowerCase();
		name = name.charAt(0) + rem;

		if(name.equals("")){
			return null;
		}
		else{
			return base.get(name);
		}
	}
}
