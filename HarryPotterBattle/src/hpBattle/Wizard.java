package hpBattle;

import java.util.ArrayList;

import hpBattle.Wizard.DeathEaterRank;
import hpBattle.Wizard.HogwartsRank;
import hpBattle.Wizard.MinistryRank;

public class Wizard {

	enum Organization {HOGWARTS, DEATH_EATERS, MINISTRY_OF_MAGIC};
	enum HogwartsRank {STUDENT, FACULTY, STAFF};
	enum MinistryRank {OFFICIAL, AUROR, UNSPEAKABLE, DEPARTMENT_CHAIR, MINISTER};
	enum DeathEaterRank {APPRENTICE, FELLOW, MASTER, LORD};
	
	private static ArrayList<Wizard> wizList = new ArrayList<Wizard>();
	
	protected Organization organization;
	protected String name = "";
	protected String bdate = "";
	protected int determination = 0;
	protected char gender = ' ';
	protected Curse curse = new Curse();
	protected int curseStrength = 0;
	protected char winnerOrLoser = ' ';
	protected HogwartsRank hRank;
	protected MinistryRank mRank;
	protected DeathEaterRank dRank;
	
	
	public Wizard() {
		
	}
	
	public Wizard(String org, String name, String bdate, int det, char gender, String rank) {
		
		if (org == "h") {this.organization = Organization.HOGWARTS;	this.hRank = HogwartsRank.valueOf(rank.toUpperCase());}
		if (org == "d") {this.organization = Organization.DEATH_EATERS;	this.dRank = DeathEaterRank.valueOf(rank.toUpperCase());}
		if (org == "m") {this.organization = Organization.MINISTRY_OF_MAGIC; this.mRank = MinistryRank.valueOf(rank.replaceAll(" ", "_").toUpperCase());}
		this.name = name;
		this.bdate = bdate;
		this.determination = det;
		this.gender = gender;
		
		wizList.add(this);
		
	}
	
	public static void printWizList() {
		for(Object wiz : wizList) {
			System.out.println(wiz);
		}
		System.out.println(wizList.size());
	}
	
	// should always be overriden by subclasses.
	public String toString() {
		return this.name + "   " + this.organization + "   " + this.curse.getName();
	}
	
	public void SetLoser() {
		this.winnerOrLoser = 'L';
	}

	public void setWinner() {
		this.winnerOrLoser = 'W';
	}
	
	public Boolean isLoser() {
		return this.winnerOrLoser == 'L' ? true : false;
	}
	
	public Boolean isWinner() {
		return this.winnerOrLoser == 'W' ? true : false;
	}
	
	public Boolean isHogwarts() {
		return this.organization.name() == "HOGWARTS" ? true : false;
	}
	
	public Boolean isDeathEater() {
		return this.organization.name() == "DEATH_EATERS" ? true : false;
	}
	
	public Boolean isMinistryOfMagic() {
		return this.organization.name() == "MINISTRY_OF_MAGIC" ? true : false;
	}
	
	public void setCurse(Curse curse) {
		this.curse = curse;
		this.curseStrength = curse.getStrength();
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getOrg() {
		return this.organization.toString();
	}
	
	public String getRank() {
		if(this.organization.toString().equals("HOGWARTS")) {return this.hRank.toString();}
		else if(this.organization.toString().equals("MINISTRY_OF_MAGIC")) {return this.mRank.toString();}
		else if(this.organization.toString().equals("DEATH_EATERS")) {
			if(this.dRank.toString().equals("MASTER") && this.gender == 'f') {
				return "MISTRESS";
			}
			if(this.dRank.toString().equals("LORD") && this.gender == 'f') {
				return "LADY";
			}
			
			else {
				return this.dRank.toString();
			}
				
		}
		else {return null;}
	}
	
	public int getDetermination() {
		return this.determination;
	}
	
	public static Wizard getWizardByName(String name) {
		for(Wizard wiz : wizList) {
			if(wiz.name.equals(name)) {
				return wiz;
			}
		}
		return null;
	}
	
	
}
