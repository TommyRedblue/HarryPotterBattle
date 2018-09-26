package hpBattle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hp_fileHandler {

	public Hp_fileHandler() {
		// TODO Auto-generated constructor stub
	}

	
	public void wizReadWrite(String de, String hog, String mom) {
		
		try { // try must exist for it to even test if file exists, really weird
			
	// ***** Death Eater File *****
			
			// new file pointing directly to the de.in file
			File f = new File(de);
			Scanner in = new Scanner(f);
			
			// loop through lines in curses.in
			while(in.hasNextLine()) {
				
				// current line in file
				String curr_line = in.nextLine();
				String[] part = curr_line.split("#");
				
				String org = "d";
				String name = part[0];
				String bdate = part[1];
				int det = Integer.parseInt(part[2]);
				char gender = part[3].charAt(0);
				String dementor = part[4];
				String rank = part[5];
				
				DE_Wizard newDE = new DE_Wizard(org, name, bdate, det, gender, rank, dementor);
			}
			// close the streams
			in.close();
			
	// ***** HogWarts File *****
			
			// new file pointing directly to the hog.in file
			f = new File(hog);
			in = new Scanner(f);
			
			// loop through lines in curses.in
			while(in.hasNextLine()) {
				
				// current line in file
				String curr_line = in.nextLine();
				String[] part = curr_line.split("#");
				
				String org = "h";
				String name = part[0];
				String bdate = part[1];
				String house = part[2];
				int det = Integer.parseInt(part[3]);
				char gender = part[4].charAt(0);
				String mentor = part[5];
				String rank = part[6];
				
				HogwartsWizard newHog = new HogwartsWizard(org, name, bdate, det, gender, house, rank, mentor);
			}
			// close the streams
			in.close();
			
			
	// ***** MOM File *****
			
			// new file pointing directly to the mom.in file
			f = new File(mom);
			in = new Scanner(f);
			
			// loop through lines in curses.in
			while(in.hasNextLine()) {
				
				// current line in file
				String curr_line = in.nextLine();
				String[] part = curr_line.split("#");
				
				String org = "m";
				String name = part[0];
				String bdate = part[1];
				String dept = part[2];
				int det = Integer.parseInt(part[3]);
				char gender = part[4].charAt(0);
				String sup = part[5];
				String rank = part[6];
				
				MOM_Wizard newMOM = new MOM_Wizard(org, name, bdate, det, gender, rank, dept, sup);
			}
			// close the streams
			in.close();
			
			
			}
			
			catch(FileNotFoundException ex) {
				System.out.println("curses.in file not found, Ex: " + ex.getMessage());
			}
			
			catch(IOException ex) {
				System.out.println("Input/Output exception, Ex: " + ex.getMessage());
			}
			
		}
			
	
	// handles file io for curses.in files, extracts info
	public void curseReadWrite(String filePath) {
		
		try { // try must exist for it to even test if file exists, really weird
		
		// new file pointing directly to the curses.in file
		File f = new File(filePath);
		Scanner in = new Scanner(f);
		
		// indicates which line/curse its on, will be written to the output file. Used in below while loop
		int curseNumber = 1;
		
		// loop through lines in curses.in
		while(in.hasNextLine()) {
			
			// current line in file
			String curr_line = in.nextLine();
			String[] part = curr_line.split("#");
			
			String cName = part[0];
			int strength = Integer.parseInt(part[1]);
			
			// create new curse object
			Curse newCurse = new Curse(cName, strength);
			
			curseNumber++;
		}
		
		// close the streams
		in.close();
		}
		
		catch(FileNotFoundException ex) {
			System.out.println("curses.in file not found, Ex: " + ex.getMessage());
		}
		
		catch(IOException ex) {
			System.out.println("Input/Output exception, Ex: " + ex.getMessage());
		}
		
	}
	
}
