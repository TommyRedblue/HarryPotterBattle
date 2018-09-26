package hpBattle;

import java.util.ArrayList;

public class MOM_Wizard extends Wizard {


	enum Department {INTERNATIONAL_MAGICAL_COOPERATION("INTERNATIONAL MAGICAL COOPERATION"), MAGICAL_LAW_ENFORCEMENT("MAGICAL LAW ENFORCEMENT"), MAGICAL_TRANSPORTATION("MAGICAL_TRANSPORTATION"), REGULATION_AND_CONTROL_OF_MAGICAL_CREATURES("REGULATION_AND_CONTROL_OF_MAGICAL_CREATURES"), MYSTERIES("MYSTERIES"), MAGICAL_GAMES_AND_SPORTS("MAGICAL_GAMES_AND_SPORTS"), MAGICAL_ACCIDENTS_AND_CATASTROPHES("MAGICAL_ACCIDENTS_AND_CATASTROPHES");
		private String name;
		Department(String name){
			this.name= name;
		}};
		
	private static ArrayList<MOM_Wizard> wizList = new ArrayList<MOM_Wizard>();
	
	private Department dept;
	private String supervisor;
		
		
	public MOM_Wizard() {
		
	}
	
	public MOM_Wizard(String org, String name, String bdate, int det, char gender, String rank, String dept, String supervisor) {
		super(org, name, bdate, det, gender, rank);
		this.dept = Department.valueOf(dept.replaceAll(" ", "_").toUpperCase());
		this.supervisor = supervisor;
			
		wizList.add(this);
	}
	
	
	@Override
	public String toString() {
		return this.name + "   Organization: " + this.organization  + "   Curse: " + this.curse.getName();
	}
	
	public static MOM_Wizard getWizardByName(String name) {
		for(MOM_Wizard wiz : wizList) {
			if(wiz.name.equals(name)) {
				return wiz;
			}
		}
		return null;
	}

}
