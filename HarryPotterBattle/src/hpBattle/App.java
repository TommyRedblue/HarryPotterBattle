package hpBattle;

public class App {

	public static void main(String[] args) {
		
		Hp_fileHandler fh = new Hp_fileHandler();
		
		String curse_filePath = ".\\files\\curses.in";
		String de_filePath = ".\\files\\death_eaters.in";
		String hog_filePath = ".\\files\\hogwarts.in";
		String mom_filePath = ".\\files\\ministry_of_magic.in";
		
		
		fh.curseReadWrite(curse_filePath);
		fh.wizReadWrite(de_filePath, hog_filePath, mom_filePath);

		//HogwartsWizard.printWizList();
		
		// get the battle file the user chose
		if(args.length == 2) {
			String battleFilePath = ".\\files\\";
			String battleFile = args[0];
			battleFilePath += battleFile;
			
			String outputPath = ".\\files\\" + args[1];
			
			Battle battle = new Battle(battleFilePath, outputPath);
			
			battle.printTeamIntros();
			
			battle.startBattle();
			
			battle.printWinners();
			
			
			// AREA TO TEST METHODS 
			
			System.out.print("\n\n\nTesting SOME Methods\n");
			
			Wizard wiz = Wizard.getWizardByName("Albus Dumbledore");
			System.out.println("isHogwarts?" + wiz.isHogwarts());
			System.out.println(wiz.getRank());
			System.out.println(wiz.getName());
			System.out.println("Did he win or lose? (Assumes he had a battle in the input file) " + wiz.winnerOrLoser);

		}
		
		else {
			System.out.println("No file arguments entered! Enter the battle file name and an output file name as the 2 arguments");
		}
		// statically run the battle input files. Could take from cli or gui
		//String[] battles = new String[] {".\\files\\teamsrun1.in", ".\\files\\teamsrun2.in", ".\\files\\teamsrun3.in", ".\\files\\teamsrun4.in", ".\\files\\teamsrun5.in"};
		
		
		//HogwartsWizard test = new HogwartsWizard("HOGWARTS", "testName", "bdate", 3, 'm', "accio", 6, "GRYFFINDOR", "STUDENT", "mentor");
		
		
	}

}
