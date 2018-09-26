package hpBattle;

import java.util.ArrayList;

public class Curse {

	public Curse() {

	}
	
	private static ArrayList<Curse> curseList= new ArrayList<Curse>();
	
	private String name;
	private int strength;
	
	
	public Curse(String cName, int strength) {
		
		this.name = cName;
		this.strength = strength;
		
		curseList.add(this);
	}
	
	
	public static Curse getCurseByName(String name) {
		for(Curse curse: curseList) {
			if (curse.name.equals(name)){
				return curse;
			}
		}
		return null;
	}
	
	
	public static Boolean curseExists(String name) {
		for(Curse curse: curseList) {
			if (curse.name.equals(name)){
				return true;
			}
		}
		return false;
	}
	
	
	public static void printCurseList() {
		for(Curse curse : curseList) {
			System.out.println(curse.name);
		}
	}
	
	
	public static Boolean isUnforgiveable(String cName) {
		if(cName.equals("crucio") || cName.equals("imperio") || cName.equals("avada kedavra")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int getStrength() {
		return this.strength;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int CurseImpact(Curse c, Wizard w) {
		if(Curse.isUnforgiveable(c.name)) {
			return (w.getDetermination() * c.getStrength());
		}
		else {
			return c.getStrength();
		}
	}


}
