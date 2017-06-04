import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Nbody{

  public static int numParticles;
  public static double worldRad;
  public static void main (String [] args){
    double totalTime = Double.parseDouble(args[0]);
    double timeChange = Double.parseDouble(args[1]);
    String inputFile = args[2];
    System.out.println("total time = " + totalTime);
    System.out.println("delta t = " + timeChange);
    System.out.println("in file: " + inputFile);
    ArrayList<Planet> planetList = new ArrayList<Planet>();

    planetList = setupPlanets(inputFile);

    for(int i = 0; i < planetList.size(); i++){
      planetList.get(i).DebugMe();
    }
  }

  public static ArrayList<Planet> setupPlanets(String inputFile){
    File myFile = new File(inputFile);
    try{
      Scanner fileIn = new Scanner(myFile);
      ArrayList<Planet> myPlanets = new ArrayList<Planet>();
      int countLines = 0;
      while(fileIn.hasNextLine()){
        if(countLines == 0){
          int x = fileIn.nextInt();
          numParticles = x;
          System.out.println("numPlanets: " + x);
          countLines += 1;
        }
        if(countLines == 1){
          double y = fileIn.nextDouble();
          worldRad = y;
          System.out.println("radius: " + y);
          countLines += 1;
        }
        if(countLines > 1){
          myPlanets.add(new Planet(fileIn.nextDouble(), fileIn.nextDouble(), fileIn.nextDouble(), fileIn.nextDouble(), fileIn.nextDouble(), fileIn.next()));
        }
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
