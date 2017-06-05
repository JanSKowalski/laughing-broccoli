import java.lang.Math;
public class Planet<E>{
  private Double _xCoord, _yCoord, _xVel, _yVel, _mass, _forceSum, _radius;
  private String _pict;

  public Planet(Double xCoord, Double yCoord, Double xVel, Double yVel, Double mass, String pictu){
    _xCoord = xCoord;
    _yCoord = yCoord;
    _xVel = xVel;
    _yVel = yVel;
    _radius = Math.sqrt(Math.pow(_xCoord, 2) + Math.pow(_yCoord, 2));
    _mass = mass;
    _pict = pictu;
    _forceSum = 0.0;
  }

  public void setupForces(double theSun, double bigG){
    if(theSun == _mass){
      return;
    }
    _forceSum = (bigG * theSun * _mass) / (_radius * _radius);
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

  public void DebugMe(){
    System.out.println("My name is " + _pict);
    System.out.println("X: " + _xCoord);
    System.out.println("Y: " + _yCoord);
    System.out.println("X Velocity: " + _xVel);
    System.out.println("Y Velocity: " + _yVel);
    System.out.println("Mass: " + _mass);
    System.out.println("Radius: " + _radius);
    System.out.println("Normal force: " + _forceSum);
    return;
  }

  public void calculateNext(double deltaTime){
    double xForce = _xCoord / _radius;
    double yForce = _yCoord / _radius;
    double xAccel = xForce / _mass;
    double yAccel = yForce / _mass;
    _xVel += deltaTime * xAccel;
    _yVel += deltaTime * yAccel;
    _xCoord += deltaTime * _xVel;
    _yCoord += deltaTime * _yVel;
    System.out.println("xForce: " + xForce);
    System.out.println("yForce: " + yForce);
    System.out.println("xAccel: " + xAccel);
    System.out.println("yAccel: " + yAccel);
  }
}
