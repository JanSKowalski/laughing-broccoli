import java.lang.Math;
public class Planet<E>{
  private Double _xCoord, _yCoord, _xVel, _yVel, _mass, _xForce, _yForce, _radius, bigG;
  private String _pict;

  public Planet(Double xCoord, Double yCoord, Double xVel, Double yVel, Double mass, String pictu){
    _xCoord = xCoord;
    _yCoord = yCoord;
    _xVel = xVel;
    _yVel = yVel;
    _radius = Math.sqrt(Math.pow(_xCoord, 2) + Math.pow(_yCoord, 2));
    _mass = mass;
    _pict = pictu;
    bigG = 6.674*Math.pow(10,-11);
  }

  public void setupForces(){
    _xForce = 0.0;
    _yForce = 0.0;
  }

  public void addForce(Planet b){
    double xDist = b.getX() - _xCoord;
    double yDist = b.getY() - _yCoord;
    double newRadius = Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
    double resultantForce = (bigG * _mass * b.getMass()) / (newRadius * newRadius);
    _xForce += resultantForce * (xDist / newRadius);
    _yForce += resultantForce * (yDist / newRadius);
  }

  public double getMass(){
    return _mass;
  }

  public double getX(){
    return _xCoord;
  }

  public double getY(){
    return _yCoord;
  }

  public double getXvel(){
    return _xVel;
  }

  public double getYvel(){
    return _yVel;
  }

  public String getName(){
    return _pict;
  }

  public void DebugMe(){
    System.out.println("My name is " + _pict);
    System.out.println("X: " + _xCoord);
    System.out.println("Y: " + _yCoord);
    System.out.println("X Velocity: " + _xVel);
    System.out.println("Y Velocity: " + _yVel);
    System.out.println("Mass: " + _mass);
    System.out.println("Radius: " + _radius);
    System.out.println("x component force: " + _xForce);
    System.out.println("x component force: " + _yForce);
  }

  public void calculateNext(double deltaTime){
    double xAccel = _xForce / _mass;
    double yAccel = _yForce / _mass;
    _xVel += deltaTime * xAccel;
    _yVel += deltaTime * yAccel;
    _xCoord += deltaTime * _xVel;
    _yCoord += deltaTime * _yVel;
    System.out.println("My name is " + _pict);
    System.out.println("xForce: " + _xForce);
    System.out.println("yForce: " + _yForce);
    System.out.println("X Velocity: " + _xVel);
    System.out.println("Y Velocity: " + _yVel);
    System.out.println("xAccel: " + xAccel);
    System.out.println("yAccel: " + yAccel);
    System.out.println("X: " + _xCoord);
    System.out.println("Y: " + _yCoord);
  }
}
