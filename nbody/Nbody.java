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
    String inputFile = args[2];
    _gravity = 6.674*Math.pow(10,-11);
    System.out.println("total time = " + totalTime);
    System.out.println("delta t = " + timeChange);
    System.out.println("in file: " + inputFile);

    planetList = setupPlanets(inputFile);
    for(int i = 0; i < planetList.size(); i++){
      planetList.get(i).DebugMe();
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
        System.out.println("Something happened and your happy sleeping thread was rudely awakened (interruptedException)");
      }
    }
  }


  public static void plotSystem(){
    StdDraw.clear();
    StdDraw.setXscale(-worldRad, +worldRad);
    StdDraw.setYscale(-worldRad, +worldRad);
    for(Planet temp : planetList){
      StdDraw.picture(temp.getX(), temp.getY(), temp.getName());
    }
  }

  public static void nextPos(){
    for(Planet y : planetList){
      y.setupForces();
      for(Planet x : planetList){
        if(x != y){
          y.addForce(x);
        }
      }
    }
    for(Planet z : planetList){
      z.calculateNext(timeChange);
    }
  }

  public static ArrayList<Planet> setupPlanets(String inputFile){
    File myFile = new File(inputFile);
    try{
      Scanner fileIn = new Scanner(myFile);
      ArrayList<Planet> myPlanets = new ArrayList<Planet>();
      int x = fileIn.nextInt();
      numParticles = x;
      System.out.println("numPlanets: " + x);
      double y = fileIn.nextDouble();
      worldRad = y;
      System.out.println("radius: " + y);
      int countLines = 0;
      while(countLines < numParticles){
        double a = fileIn.nextDouble();
        double b = fileIn.nextDouble();
        double c = fileIn.nextDouble();
        double d = fileIn.nextDouble();
        myPlanets.add(new Planet(a, b, c, d, fileIn.nextDouble(), fileIn.next()));
        countLines += 1;
      }
      return myPlanets;
    }
    catch(FileNotFoundException exception)
    {
      System.out.println("Error: File not found.");
      return null;
    }
  }
}
