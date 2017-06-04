import java.util.Scanner;
public class Nbody{
  public static void main (String [] args){
    double totalTime = Double.parseDouble(args[0]);
    double timeChange = Double.parseDouble(args[1]);
    String inputFile = args[2];
    //Scanner fileIn = new Scanner(new File(inputFile));
    Planet test = new Planet(1.1, 2.2, 3.3, 4.4, 5.5, "earth.jpeg");
    test.DebugMe();
    System.out.println("total time = " + totalTime);
    System.out.println("delta t = " + timeChange);
    System.out.println("in file: " + inputFile);
  }
}
