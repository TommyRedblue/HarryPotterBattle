package hpBattle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Battle {
	
	private String team1 = "";
	private String team2 = "";
	private ArrayList<Wizard> team1_Lineup = new ArrayList<Wizard>();
	private ArrayList<Wizard> team2_Lineup = new ArrayList<Wizard>();
	private int team1_Score = 0;
	private int team2_Score = 0;
	private ArrayList<Wizard> t1_winners = new ArrayList<Wizard>();
	private ArrayList<Wizard> t2_winners = new ArrayList<Wizard>();
	String output_filePath = "";
	

	public Battle() {
		// TODO Auto-generated constructor stub
	}
	
	// initializes a battle file. Determines teams
	public Battle(String filePath, String outputPath) {
		
		try {
		
		this.output_filePath = outputPath;
			
		// create scanner for file user chose
		File f = new File(filePath);
		Scanner in  = new Scanner(f);
		
		// the first line is different from the rest. Collect team names
		String first_line = in.nextLine();
		String[] firstSplit = first_line.split("#");
		this.team1 = firstSplit[0];
		this.team2 = firstSplit[1];
		
		ArrayList<String> wizards_in_file = new ArrayList<String>();
		
		while(in.hasNextLine()) {
			String line = in.nextLine();
			if(!line.equals("")) {
				wizards_in_file.add(line);
			}
		}
			
		int num_of_wizards = wizards_in_file.size();
		
		// go through each wizard line. find their object by given name. add them to their team arraylist. 
		for(int i = 0; i < num_of_wizards; i++) {
			
			if(!wizards_in_file.get(i).equals("")){
				// wiz 1 competing
				String[] wiz1 = wizards_in_file.get(i).split("#");
				
				if(wiz1.length > 1) { // last line still collects 1 element of blank
					// get wizard from wizard objects based on their affiliation
					
					Curse curse = Curse.getCurseByName(wiz1[2]);
					
					// Add wizard to their team lineup based on their organization
					if(wiz1[1].equals("HOGWARTS") && HogwartsWizard.getWizardByName(wiz1[0]) != null) {
						HogwartsWizard hw = HogwartsWizard.getWizardByName(wiz1[0]);
						hw.setCurse(curse);
						if(i <= (num_of_wizards/2) - 1) {team1_Lineup.add(HogwartsWizard.getWizardByName(wiz1[0]));}
						else if (i > (num_of_wizards/2) - 1) {team2_Lineup.add(HogwartsWizard.getWizardByName(wiz1[0]));}
						}
					else if(wiz1[1].equals("MINISTRY_OF_MAGIC") && MOM_Wizard.getWizardByName(wiz1[0]) != null) {
						MOM_Wizard mw = MOM_Wizard.getWizardByName(wiz1[0]);
						mw.setCurse(curse);
						if(i <= (num_of_wizards/2) - 1) {team1_Lineup.add(MOM_Wizard.getWizardByName(wiz1[0]));}
						else if (i > (num_of_wizards/2) - 1) {team2_Lineup.add(MOM_Wizard.getWizardByName(wiz1[0]));}
						}
					else if(wiz1[1].equals("DEATH_EATERS") && DE_Wizard.getWizardByName(wiz1[0]) != null) {
						DE_Wizard dw = DE_Wizard.getWizardByName(wiz1[0]);
						dw.setCurse(curse);
						if(i <= (num_of_wizards/2) - 1) {team1_Lineup.add(DE_Wizard.getWizardByName(wiz1[0]));}
						else if (i > (num_of_wizards/2) - 1) {team2_Lineup.add(DE_Wizard.getWizardByName(wiz1[0]));}		
						}
					else {System.out.println("Invalid Organization identifier or Wizard not found!" + wiz1[0] + "|" + wiz1[1] + "|"); break;}
			}
		}
		}
		
			in.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
	}

	
	public void printTeamIntros() {
		
		try {
		int t1Counter = 1;
		int t2Counter = 1;
		
		PrintWriter pw = new PrintWriter(new FileOutputStream(new File(output_filePath), false));
		
		pw.println("**** TEAMS: \n\nTEAM " + this.team1);
		for(Wizard w : team1_Lineup) {
			pw.println("\t" + t1Counter + " " + w.getName() + "   Organization: " + w.getOrg() + "   Rank: " + w.getRank() + "   Curse: " + w.curse.getName());
			t1Counter++;
			
		}
		
		pw.println("\nTEAM " + this.team2);
		for(Wizard w: team2_Lineup) {
			pw.println("\t" + t2Counter + " " + w.getName() + "   Organization: " + w.getOrg() + "   Rank: " + w.getRank() + "   Curse: " + w.curse.getName());
			t2Counter++;
		}
		
		pw.flush();
		if(pw != null) {
			pw.close();
		}
		}
		catch(FileNotFoundException ex) {
			System.out.println("Output file not found!");
		}

	}
	
	public void Duel(Wizard t1, Wizard t2) {
		
		try {
		
		PrintWriter pw = new PrintWriter(new FileOutputStream(new File(output_filePath), true));
		
		pw.println();
		
		if((t1.curse.CurseImpact(t1.curse, t1)) > (t2.curse.CurseImpact(t2.curse, t2))) {
			// Print results of duel
			pw.println("Team 1:   " + t1.getName() + "   " + t1.curse.getName() + "   " + t1.curse.CurseImpact(t1.curse, t1) + "PU     *** WINNER");			
			pw.println("Team 2:   " + t2.getName() + "   " + t2.curse.getName() + "   " + t2.curse.CurseImpact(t2.curse, t2) + "PU     *** LOSER\n");
			
			// Update Score
			this.team1_Score++;
			
			// Update wizard winner/loser field
			t1.setWinner();
			t2.SetLoser();
			
			// add winner to winner list to print later
			this.t1_winners.add(t1);
		}
		
		else if((t1.curse.CurseImpact(t1.curse, t1)) < (t2.curse.CurseImpact(t2.curse, t2))) {
			// Print results of duel
			pw.println("Team 1:   " + t1.getName() + "   " + t1.curse.getName() + "   " + t1.curse.CurseImpact(t1.curse, t1) + "PU     *** LOSER");
			pw.println("Team 2:   " + t2.getName() + "   " + t2.curse.getName() + "   " + t2.curse.CurseImpact(t2.curse, t2) + "PU     *** WINNER\n");
			
			
			// Update Score
			this.team2_Score++;
			
			// Update wizard winner/loser field
			t1.SetLoser();
			t2.setWinner();
			
			// add winner to winner list to print later
			this.t2_winners.add(t2);
		}
		
		// if wizards tie
		else if((t1.curse.CurseImpact(t1.curse, t1)) == (t2.curse.CurseImpact(t2.curse, t2))) {
			// flip a coin, 0 or 1
			long coin_flip = Math.round(Math.random());
			
			if(coin_flip == 0) {
				// Print results of duel
				pw.println("Team 1:   " + t1.getName() + "   " + t1.curse.getName() + "   " + t1.curse.CurseImpact(t1.curse, t1) + "PU     *** WINNER");
				pw.println("Team 2:   " + t2.getName() + "   " + t2.curse.getName() + "   " + t2.curse.CurseImpact(t2.curse, t2) + "PU     *** LOSER\n");
				
				
				// Update Score
				this.team1_Score++;
				
				// Update wizard winner/loser field
				t1.setWinner();
				t2.SetLoser();
				
				// add winner to winner list to print later
				this.t1_winners.add(t1);
			}
			
			else if (coin_flip == 1) {
				// Print results of duel
				pw.println("Team 1:   " + t1.getName() + "   " + t1.curse.getName() + "   " + t1.curse.CurseImpact(t1.curse, t1) + "PU     *** LOSER");
				pw.println("Team 2:   " + t2.getName() + "   " + t2.curse.getName() + "   " + t2.curse.CurseImpact(t2.curse, t2) + "PU     *** WINNER\n");
				
				// Update Score
				this.team2_Score++;
				
				// Update wizard winner/loser field
				t1.SetLoser();
				t2.setWinner();
				
				// add winner to winner list to print later
				this.t2_winners.add(t2);
			}
		}
		
		pw.close();
		}
		catch(FileNotFoundException ex) {
			System.out.print("Output file not found!");
		}
		
	}
	
	
	// runs the battle
	public void startBattle() {
		
		if(team1_Lineup.size() != team2_Lineup.size()) {
			System.out.println("Team sizes are different! Battle cannot start!");
			return;
		}
		
		try {
		
		PrintWriter pw = new PrintWriter(new FileOutputStream(new File(output_filePath), true));
		
		// team sizes should be equal as checked above
		int size_of_teams = team1_Lineup.size();
		
		int battleCounter = 1;
		
		pw.println("\n\n****HEAD-TO-HEAD BATTLE****");
		pw.flush();
		
		for(int i = 0; i < size_of_teams; i++) {
			pw.println("***Battle " + (i+1));
			pw.flush();
			
			this.Duel(team1_Lineup.get(i), team2_Lineup.get(i)); // Sends the matchup to a duel. Duel() will handle printing the winner and updating scores, etc
		}
		pw.close();
		
		}
		
		catch(FileNotFoundException ex) {
			System.out.println("Output file not found!");
		}
	}
	
	
	public void printWinners() {
		
		try {
		// counters for lists of winners. Directions looked unclear on the number that should be displayed with the wizard. Winners in directions don't match who actually wins?
		int t1Counter = 1;
		int t2Counter = 1;
		
		// will be used to print results to output file
		PrintWriter pw = new PrintWriter(new FileOutputStream(new File(output_filePath), true));
		
		// print winners of team 1
		pw.println("\n\n****WINNERS****\nTEAM " + team1);
		for(Wizard w : t1_winners) {
			pw.println("\t" + t1Counter + "  " + w.name);
			t1Counter++;
		}
		
		// print winners of team 2
		pw.println("\nTEAM " + team2);
		for(Wizard w : t2_winners) {
			pw.println("\t" + t2Counter + "  " + w.name);
			t2Counter++;
		}
		
		// print winning team
		pw.println("\n****WINNER****");
		if(team1_Score > team2_Score) {pw.println("TEAM " + team1);}
		else if(team1_Score < team2_Score) {pw.println("TEAM " + team2);}
		else if(team1_Score == team2_Score) {
			
			long coin_flip = Math.round(Math.random());
			
			if(coin_flip == 0) {pw.println("TEAM " + team1);}
			else if (coin_flip == 1) {pw.println("TEAM " + team2);}
		}
		
		pw.close();
		}
		
		catch(FileNotFoundException ex) {
			System.out.println("Output file not found!" + ex.getMessage());
		}
	}
	
}
