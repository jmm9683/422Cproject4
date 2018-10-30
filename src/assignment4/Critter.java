package assignment4;
/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * Jake Morrissey
 * jmm9683
 * 16345
 * NO PARTNER
 * Slip days used: 1
 * Fall 2018
 */


import java.util.Iterator;
import java.util.List;
import assignment4.Params;
/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
	private boolean moved;
	private static boolean fighting;
	
	/*
	 * walking method
	 * direction ranges from 0-7
	 * must check if critter has already moved during this worldStep
	 * must check if this is a fighting call - need to confirm new location is empty
	 * regardless- subtract walking energy
	 */
	protected final void walk(int direction) {
		if (fighting && !moved) {
			/**
			 * try to move to the specified location if 
			 */
			boolean empty = true;
			int temp_x_coord = x_coord;
			int temp_y_coord = y_coord;
			if (direction == 0) {
				temp_x_coord += 1;
			}
			if (direction == 1) {
				temp_x_coord += 1;
				temp_y_coord -= 1;
			}
			if (direction == 2) {
				temp_y_coord -= 1;
			}
			if (direction == 3) {
				temp_x_coord -= 1;
				temp_y_coord -= 1;
			}
			if (direction == 4) {
				temp_x_coord -= 1;
			}
			if (direction == 5) {
				temp_x_coord -= 1;
				temp_y_coord += 1;
			}
			if (direction == 6) {
				temp_y_coord += 1;
			}
			if (direction == 7) {
				temp_x_coord += 1;
				temp_y_coord += 1;
			}
			if (temp_x_coord >= Params.world_width) {
				temp_x_coord -= Params.world_width;
			}
			else if (temp_x_coord < 0) {
				temp_x_coord += Params.world_width;
			}
			if (temp_y_coord >= Params.world_height) {
				temp_y_coord -= Params.world_height;
			}
			else if (temp_y_coord < 0) {
				temp_y_coord += Params.world_height;
			}
			for (Critter crit : population) {
				if (crit.x_coord == temp_x_coord
						&& crit.y_coord == temp_y_coord) {
					empty = false; 
					break;
				}
			}
			if (empty) {
				x_coord = temp_x_coord;
				y_coord = temp_y_coord;
			}
			
		}

		else if (!moved){
			if (direction == 0) {
				x_coord += 1;
			}
			if (direction == 1) {
				x_coord += 1;
				y_coord -= 1;
			}
			if (direction == 2) {
				y_coord -= 1;
			}
			if (direction == 3) {
				x_coord -= 1;
				y_coord -= 1;
			}
			if (direction == 4) {
				x_coord -= 1;
			}
			if (direction == 5) {
				x_coord -= 1;
				y_coord += 1;
			}
			if (direction == 6) {
				y_coord += 1;
			}
			if (direction == 7) {
				x_coord += 1;
				y_coord += 1;
			}
	
		}

		if (x_coord >= Params.world_width) {
			x_coord -= Params.world_width;
		}
		else if (x_coord < 0) {
			x_coord += Params.world_width;
		}
		if (y_coord >= Params.world_height) {
			y_coord -= Params.world_height;
		}
		else if (y_coord < 0) {
			y_coord += Params.world_height;
		}

		energy -= Params.walk_energy_cost;
		moved = true;
		
		
	}
	/*
	 * run method- similar to walking
	 * When fighting - need to check itself if location is safe because it can go through critters when running
	 * Need to make sure critter has not moved yet
	 * When not fighting - do walk twice with the same location
	 * Adjust energy with run_energy
	 * 
	 */
	protected final void run(int direction) {
		int current_energy = energy;
		if(fighting && !moved) {
			boolean empty = true;
			int temp_x_coord = x_coord;
			int temp_y_coord = y_coord;
			if (direction == 0) {
				temp_x_coord += 2;
			}
			if (direction == 1) {
				temp_x_coord += 2;
				temp_y_coord -= 2;
			}
			if (direction == 2) {
				temp_y_coord -= 2;
			}
			if (direction == 3) {
				temp_x_coord -= 2;
				temp_y_coord -= 2;
			}
			if (direction == 4) {
				temp_x_coord -= 2;
			}
			if (direction == 5) {
				temp_x_coord -= 2;
				temp_y_coord += 2;
			}
			if (direction == 6) {
				temp_y_coord += 2;
			}
			if (direction == 7) {
				temp_x_coord += 2;
				temp_y_coord += 2;
			}
			if (temp_x_coord >= Params.world_width) {
				temp_x_coord -= Params.world_width;
			}
			else if (temp_x_coord < 0) {
				temp_x_coord += Params.world_width;
			}
			if (temp_y_coord >= Params.world_height) {
				temp_y_coord -= Params.world_height;
			}
			else if (temp_y_coord < 0) {
				temp_y_coord += Params.world_height;
			}
			for (Critter crit : population) {
				if (crit.x_coord == temp_x_coord
						&& crit.y_coord == temp_y_coord) {
					empty = false; 
					break;
				}
			}
			if (empty) {
				x_coord = temp_x_coord;
				y_coord = temp_y_coord;
			}
			
			
		}
		else if (!moved){
			walk(direction);
			moved = false;
			walk(direction);
			
		}
		energy = current_energy - Params.run_energy_cost;
		moved = true;

		
	}
	/*
	 * Reproduce if there is enough energy
	 * add new offspring to babies list, (add to population at end of timestep)
	 * must set coordinate according to direction input and parent coordinates
	 * must set energy of parent and baby to half of parent (rounding up and down respectively)
	 */
	protected final void reproduce(Critter offspring, int direction) {
		if (energy < Params.min_reproduce_energy) {
			return;
		}
		offspring.energy = energy/2;
		energy = energy - offspring.energy;
		if (direction == 0) {
			offspring.x_coord = x_coord+1;
			offspring.y_coord = y_coord;
		}
		if (direction == 1) {
			offspring.x_coord = x_coord+1;
			offspring.y_coord = y_coord-1;
		}
		if (direction == 2) {
			offspring.x_coord = x_coord;
			offspring.y_coord = y_coord-1;
			
		}
		if (direction == 3) {
			offspring.x_coord = x_coord-1;
			offspring.y_coord = y_coord-1;
		}
		if (direction == 4) {
			offspring.x_coord = x_coord-1;
			offspring.y_coord = y_coord;
		}
		if (direction == 5) {
			offspring.x_coord = x_coord-1;
			offspring.y_coord = y_coord+1;
		}
		if (direction == 6) {
			offspring.x_coord = x_coord;
			offspring.y_coord = y_coord+1;
		}
		if (direction == 7) {
			offspring.x_coord = x_coord+1;
			offspring.y_coord = y_coord+1;
		}
		if (offspring.x_coord >= Params.world_width) {
			offspring.x_coord -= Params.world_width;
		}
		else if (offspring.x_coord < 0) {
			offspring.x_coord += Params.world_width;
		}
		if (offspring.y_coord >= Params.world_height) {
			offspring.y_coord -= Params.world_height;
		}
		else if (offspring.y_coord < 0) {
			offspring.y_coord += Params.world_height;
		}
		babies.add(offspring);
		
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		try {
			Critter newCrit = (Critter)Class.forName("assignment4." + critter_class_name).newInstance();
			newCrit.x_coord = getRandomInt(Params.world_width);
			newCrit.y_coord = getRandomInt(Params.world_height);
			newCrit.energy = Params.start_energy;
			population.add(newCrit);
		}
		catch(Exception e) {
			throw new InvalidCritterException(critter_class_name);
		}
		catch (NoClassDefFoundError e) {
			throw new InvalidCritterException(critter_class_name);
		}
		
		
	}
	
	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
		try {
			Critter testCrit = (Critter)Class.forName("assignment4." + critter_class_name).newInstance();
			
			for (Critter crit : population) {
				if (crit.getClass() == testCrit.getClass()) {
					result.add(crit);
				}
			}
		}
		catch(Exception e) {
			throw new InvalidCritterException(critter_class_name);
		}
		return result;
	}
	
	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();		
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		population.clear();
	}
	/*
	 * loop through all critters and execute their timestep and subtract rest energy
	 * find living critters that are the same spot and have them fight
	 * add babies
	 * resuply the algae
	 * remove dead critters
	 */
	public static void worldTimeStep() throws InvalidCritterException {
		//doTimeStep
		for (int i = population.size()-1; i >= 0; i--) {
			population.get(i).moved = false;
			population.get(i).doTimeStep();
			population.get(i).energy -= Params.rest_energy_cost;
			//System.out.println(population.get(i).toString() + " energy low: " + population.get(i).energy);
		}
		//fighting
		fighting = true;
		for (int i = population.size()-1; i>=0; i--) {
			for (int j = population.size()-1; j>=0; j--) {
				if (j != i 
						&& population.get(j).x_coord == population.get(i).x_coord
						&& population.get(j).y_coord == population.get(i).y_coord
						&& population.get(i).energy > 0
						&& population.get(j).energy > 0) {
					boolean iFight = population.get(i).fight(population.get(j).toString());
					boolean jFight = population.get(j).fight(population.get(i).toString());
					//System.out.println("FIGHT");
					if (population.get(j).x_coord == population.get(i).x_coord
							&& population.get(j).y_coord == population.get(j).y_coord
							&& population.get(i).energy > 0
							&& population.get(j).energy > 0) {
						//both roll to see who kills
						if (iFight && jFight) {
							int iRoll = getRandomInt(population.get(i).energy);
							int jRoll = getRandomInt(population.get(j).energy);
							if (iRoll >= jRoll) {
								population.get(i).energy += (population.get(j).energy/2);
								population.get(j).energy = -1;
							}
							else {
								population.get(j).energy += (population.get(i).energy/2);
								population.get(i).energy = -1;
								
							}
							
						}
						//i kills
						else if (iFight && !jFight) {
							population.get(i).energy += (population.get(j).energy/2);
							population.get(j).energy = -1;
							
						}
						//j kills
						else if (!iFight && jFight) {
							population.get(j).energy += (population.get(i).energy/2);
							population.get(i).energy = -1;
							
						}
						//both don't want to fight - i defaults as killer
						else if (!iFight && !jFight) {
							population.get(i).energy += (population.get(j).energy/2);
							population.get(j).energy = -1;	
						}
						
					}	
				}
			}
		}
		//End of fighting
		fighting = false;
		
		//add the offspring
		population.addAll(babies);
		babies.clear();
		
		//cull the dead
		int algaeCount = 0;
		for (int i = population.size()-1; i>=0; i--) {
			
			if (population.get(i).toString().equals("@")) {
				algaeCount++;
			}
			if (population.get(i).energy <= 0) {
				if (population.get(i).toString().equals("@")) {
					algaeCount--;
				}
				population.remove(i);
			}
		}
		//System.out.println(algaeCount);
		//refresh algae
		while(algaeCount < Params.refresh_algae_count) {
			try{
				makeCritter("Algae");
			}
			catch (Exception e) {
				throw new InvalidCritterException("Algae failed to refresh"); 
			}
			algaeCount++;
			
		}
	}
	/*
	 * display world according to width and height
	 * if critter is location display its toString()
	 */
	public static void displayWorld() {
		System.out.print("+");
		for (int i = 0; i < Params.world_width; i++){
			System.out.print("-");
		}
		System.out.println("+");
		for (int j = 0; j < Params.world_height; j++) {
			System.out.print("|");
			for (int i = 0; i < Params.world_width; i++) {
				int critFound = 0;
				for (Critter c: population) {
					if (c.x_coord == i && c.y_coord == j) {
						System.out.print(c.toString());
						
						critFound = 1;
						break;
					}
				}
				if (critFound == 0)
					System.out.print(" " );
			}
			System.out.println("|");
		}
		System.out.print("+");
		for (int i = 0; i < Params.world_width; i++){
			System.out.print("-");
		}
		System.out.print("+\n");

	}
}
