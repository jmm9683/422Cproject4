package assignment4;

/* CRITTERS Critter2.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * Jake Morrissey
 * jmm9683
 * 16345
 * NO PARTNER
 * Slip days used: 1
 * Fall 2018
 */

/*
 * Critter2
 * Evert timestep:
 * - walks in a random direction
 * Every Fight:
 * - if it is an algae, then eat it (return true)
 * - else -> reproduce and return false
 */


public class Critter2 extends Critter{
		
	@Override
	public String toString() { return "2"; }
	private int dir;
	public Critter2() {
		dir = Critter.getRandomInt(8);
	}
	@Override
	public void doTimeStep() {
		walk(dir);
	}
	@Override
	public boolean fight(String oponent) {
		if(oponent.equals("@")) {
			return true;
		}
		Critter2 baby = new Critter2();
		reproduce(baby,Critter.getRandomInt(8));
		return false;
	}

}
