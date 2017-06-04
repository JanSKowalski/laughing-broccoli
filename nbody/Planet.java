public class Planet<E>{
    private Double _xCoord, _yCoord, _xVel, _yVel, _mass;
    private String _pict;

    public Planet(Double xCoord, Double yCoord, Double xVel, Double yVel, Double mass, String pictu){
      _xCoord = xCoord;
      _yCoord = yCoord;
      _xVel = xVel;
      _yVel = yVel;
      _mass = mass;
      _pict = pictu;
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
      return;
    }
}
