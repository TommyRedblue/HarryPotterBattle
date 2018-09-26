package hpBattle;

import java.util.ArrayList;

import hpBattle.Wizard.Organization;

public class HogwartsWizard extends Wizard {

	enum House{GRYFFINDOR, SLYTHERIN, RAVENCLAW, HUFFLEPUFF;}

	
	private static ArrayList<HogwartsWizard> wizList = new ArrayList<HogwartsWizard>();
	
	private House house;

	String mentor = "";
	
	public HogwartsWizard() {
		
	}
	
	public HogwartsWizard(String org, String name, String bdate, int det, char gender, String house, String rank, String mentor) {
		super(org, name, bdate, det, gender, rank);
		this.house = House.valueOf(house.toUpperCase());

		this.mentor = mentor;

		wizList.add(this);
	}
	
	@Override
	public String toString() {
		return this.name + "   Organization: " + this.organization +  "   Curse: " + this.curse.getName();
	}
	
	
	public static HogwartsWizard getWizardByName(String inName) {
		for(HogwartsWizard wiz : wizList) {			
			if(wiz.name.equals(inName)) {
				return wiz;
			}
		}
		return null;
	}
	
	public static void printWizList() {
		for(HogwartsWizard wiz : wizList) {
			System.out.println(wiz.name);
		}
	}

}
