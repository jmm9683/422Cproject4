package assignment4;

/* CRITTERS Critter1.java
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
 * Critter1
 * Every timestep:
 * - runs in a random direction
 * - attempts to reproduces once
 * Every fight:
 * - accepts fight
 */

public class Critter1 extends Critter{

	@Override
	public String toString() { return "1"; }
	private int dir;
	public Critter1() {
		dir = Critter.getRandomInt(8);
		
	}
	@Override
	public void doTimeStep() {
		run(dir);
		Critter1 baby = new Critter1();
		reproduce(baby, Critter.getRandomInt(8));
		
	}

	@Override
	public boolean fight(String oponent) {
		return true;
	}
	

}
