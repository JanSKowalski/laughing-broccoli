/*******************************************************************************
 *  Name:    Elijah Karshner
 ******************************************************************************/

Programming Assignment 2: N-Body Simulation

How to use: Navigate to the nbody directory.
Run: javac Nbody.java

To run the code:
java Nbody int x int y String z
This takes three parameters from the command line:
x = total time
y = time change between calculations
z = file path

Possible syntax:
java NBody 157788000.0 25000.0 planets.txt
-Simulates the Solar System, with 5 planets

java NBody 157788000.0 25000.0 3body.txt
-Simulates a 3 body system

java NBody 157788000.0 25000.0 its-a-trap.txt
-Surprise Star Wars reference!

This code calculates the gravitational forces on n bodies in zero gravity space, updating from time = 0 to the desired end time by increments of the given time change, and draws the positions after a 1ms delay between iterations. It uses a new class, called Planet, and an ArrayList of such objects.

Limitations: Since this uses the Draw library of java, it is constantly redrawing the planets, giving them a frenzied, stuttering look.
