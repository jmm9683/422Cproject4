package assignment4;
/* CRITTERS Main.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * Jake Morrissey
 * jmm9683
 * 16345
 * NO PARTNER
 * Slip days used: 1
 * Fall 2018
 */

import java.util.Scanner;



import java.io.*;
import java.lang.reflect.Method;


/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

    static Scanner kb;	// scanner connected to keyboard input, or input file
    private static String inputFile;	// input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
    private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;	// if you want to restore output to console


    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name, 
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     */
    public static void main(String[] args) { 
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));			
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }
        /*
         * "quit" will break the loop
         * make,step,show,seed,stats commands
         */
        while(true) {
        	System.out.print("critters>");
        	String input = kb.nextLine();
        	String[] inputs = input.split("\\s+");
        	boolean validCommand = false;
    		if (inputs[0].equals("quit")
    				|| inputs[0].equals("show")
    				|| inputs[0].equals("step")
    				|| inputs[0].equals("seed")
    				|| inputs[0].equals("make")
    				|| inputs[0].equals("stats")) {
    			validCommand = true;
    		}
        	if (input.equals("quit"))
        		break;
        	else if (input.equals("show"))
        		Critter.displayWorld();
        	else if (input.equals("step")) {
        		try {
					Critter.worldTimeStep();
				} catch (InvalidCritterException e) {
					e.printStackTrace();
				}
        	}
        	//size two commands (step, seed, stats, make)
        	else if (inputs.length == 2) {
        		if (inputs[0].equals("step")) {
        			try {
        				int steps = Integer.parseInt(inputs[1]);
        				while (steps > 0) {
        					try {
            					Critter.worldTimeStep();
            				} catch (InvalidCritterException e) {
            					e.printStackTrace();
            				}
        					steps--;
        				}
        			}
        			catch(Exception e) {
        				System.out.println("error processing: " + input);
        			}	
        		}
        		else if (inputs[0].equals("seed")) {
        			try {
        				int seed = Integer.parseInt(inputs[1]);
        				Critter.setSeed(seed);
        			}
        			catch(Exception e) {
        				System.out.println("error processing: " + input);
        			}	
        		}
        		else if (inputs[0].equals("stats")) {
        			try {
        				//accessing the subclasses runStats
        				Critter testCrit = (Critter)Class.forName("assignment4." + inputs[1]).newInstance();
						Method stats = testCrit.getClass().getMethod("runStats", java.util.List.class);
						stats.invoke(testCrit, Critter.getInstances(inputs[1]));
					} catch (Exception e) {
						System.out.println("error processing: " + input);
					}
        		}
        		else if (inputs[0].equals("make")) {
        			try {
        				Critter.makeCritter(inputs[1]);
        			}
        			catch(Exception e){
        				System.out.println("error processing: " + input);
        			}
        			
        		}
        		else {
        			if (validCommand) {
        				System.out.println("error processing: " + input);
        			}
        			else{
        				System.out.println("invalid command: " + input);
        			}
        		}
        	}
        	//size three commands (make)
        	else if (inputs.length == 3) {
        		if (inputs[0].equals("make")){
        			try {
        				int makes = Integer.parseInt(inputs[2]);
        				while (makes > 0) {
        					Critter.makeCritter(inputs[1]);
        					makes--;
        				}
        			}
        			catch(Exception e) {
        				System.out.println("error processing: " + input);
        			}
        			
        		}
        		else {
        			if (validCommand) {
        				System.out.println("error processing: " + input);
        			}
        			else{
        				System.out.println("invalid command: " + input);
        			}
        		}
        	}
        	//anything else is an error
        	else
        	{
    			if (validCommand) {
    				System.out.println("error processing: " + input);
    			}
    			else{
    				System.out.println("invalid command: " + input);
    			}
        	}
        	
        
        }
        
        /* Write your code above */
        System.out.flush();

    }
}
