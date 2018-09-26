package hpBattle;

import java.util.ArrayList;

import hpBattle.Wizard.Organization;

public class DE_Wizard extends Wizard{
	
	private static ArrayList<DE_Wizard> wizList = new ArrayList<DE_Wizard>();
	
	String dementor = "";
	
	
	public DE_Wizard() {
		
	}
	
	
	public DE_Wizard(String org, String name, String bdate, int det, char gender, String rank, String dementor) {
		super(org, name, bdate, det, gender, rank);
		this.dementor = dementor;
		
		wizList.add(this);
	}

	
	@Override
	public String toString() {
		return this.name + "   Organization: " + this.organization + "   Curse: " + this.curse.getName();
	}
	
	
	public static DE_Wizard getWizardByName(String name) {
		for(DE_Wizard wiz : wizList) {
			if(wiz.name.equals(name)) {
				return wiz;
			}
		}
		return null;
	}
	
}
