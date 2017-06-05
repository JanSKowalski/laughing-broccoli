import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Nbody{

  public static int numParticles;
  public static double worldRad, sunMass, _gravity;
  public static void main (String [] args){
    double totalTime = Double.parseDouble(args[0]);
    double timeChange = Double.parseDouble(args[1]);
    String inputFile = args[2];
    _gravity = 6.674*Math.pow(10,-11);
    System.out.println("total time = " + totalTime);
    System.out.println("delta t = " + timeChange);
    System.out.println("in file: " + inputFile);
    ArrayList<Planet> planetList = new ArrayList<Planet>();

    planetList = setupPlanets(inputFile);
    System.out.println("Sun mass = " + sunMass);
    for(int i = 0; i < planetList.size(); i++){
      planetList.get(i).DebugMe();
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
        myPlanets.add(new Planet(a, b, fileIn.nextDouble(), fileIn.nextDouble(), fileIn.nextDouble(), fileIn.next()));
        if(a == 0.0 && b == 0.0){
          sunMass = myPlanets.get(countLines).getMass();
        }
        countLines += 1;
      }
      for(int i = 0; i < myPlanets.size(); i++){
        myPlanets.get(i).setupForces(sunMass, _gravity);
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
