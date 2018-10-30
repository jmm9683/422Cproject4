package assignment4;

import java.util.*;

public class MyCritter1 extends Critter {

	@Override
	public void doTimeStep() {
		return;
	}

	@Override
	public boolean fight(String opponent) {
		if (getEnergy() > 10) return true;
		return false;
	}
	
	public String toString() {
		return "1";
	}
	
	public void test (List<Critter> l) {
		
	}
}
