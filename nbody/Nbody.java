import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

public class Nbody{

  public static int numParticles;
  public static double worldRad, _gravity, totalTime, timeChange;
  public static ArrayList<Planet> planetList = new ArrayList<Planet>();
  public static void main (String [] args){
    totalTime = Double.parseDouble(args[0]);
    timeChange = Double.parseDouble(args[1]);
    //Gets time increment and total time from the command line
    String inputFile = args[2];
    //Gets file path from the command line
    _gravity = 6.674*Math.pow(10,-11);
    //Sets up universal gravitational constant
    System.out.println("total time = " + totalTime);
    System.out.println("delta t = " + timeChange);
    System.out.println("in file: " + inputFile);

    planetList = setupPlanets(inputFile);
    for(int i = 0; i < planetList.size(); i++){
      planetList.get(i).DebugMe();
      //Print out all attributes of every planet in the list
    }
    double timeNow = 0.0;
    while(timeNow <= totalTime){
      try
      {
        plotSystem();
        nextPos();
        timeNow += timeChange;
        Thread.sleep(1);
      }
      catch(InterruptedException exception)
      {
        System.out.println("Something happened and your happy thread was rudely interrupted (interruptedException)");
      }
      //Cycles through graphing the system, updating all the planet data, incrementing time, and pausing from time t = 0 to t = totalTime
    }
  }


  public static void plotSystem(){
    StdDraw.clear();
    StdDraw.setXscale(-worldRad, +worldRad);
    StdDraw.setYscale(-worldRad, +worldRad);
    //Sets scale of the drawing pad
    for(Planet temp : planetList){
      StdDraw.picture(temp.getX(), temp.getY(), temp.getName());
    }
    //draws each planet in the list at x, y, with the provided image file
  }

  public static void nextPos(){
    for(Planet y : planetList){
      y.setupForces();
      for(Planet x : planetList){
        if(x != y){
          y.addForce(x);
        }
      }
      //For each planet, updates the total force on the planet by calculating with each other planet in the list
    }
    for(Planet z : planetList){
      z.calculateNext(timeChange);
    }
    //Calculates the next position for each planet
  }

  public static ArrayList<Planet> setupPlanets(String inputFile){
    File myFile = new File(inputFile);
    //Creates a file from the provided path
    try{
      Scanner fileIn = new Scanner(myFile);
      ArrayList<Planet> myPlanets = new ArrayList<Planet>();
      int x = fileIn.nextInt();
      numParticles = x;
      //records the number of planets in the system to not read comments at the bottom of the file
      System.out.println("numPlanets: " + x);
      double y = fileIn.nextDouble();
      worldRad = y;
      //Records the radius of the system for the drawing pad
      System.out.println("radius: " + y);
      int countLines = 0;
      while(countLines < numParticles){
        double a = fileIn.nextDouble(); //x Coordinate
        double b = fileIn.nextDouble(); //y coordinate
        double c = fileIn.nextDouble(); //x velocity
        double d = fileIn.nextDouble(); //y velocity
        myPlanets.add(new Planet(a, b, c, d, fileIn.nextDouble(), fileIn.next())); //Creates a new planet with that data
        countLines += 1;
      }
      //Inputs data for all planets into Planet constructor, and places all planets into the myPlanets list
      return myPlanets;
    }
    catch(FileNotFoundException exception)
    {
      System.out.println("Error: File not found.");
      return null;
      //catches a file not found exception
    }
  }
}
