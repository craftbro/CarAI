package Car;

import java.util.Random;
import java.lang.Math;
/* 
 * Car class with all condition and changing of them
 */
public class Car {
	Random r = new Random();
	int rotation = 0;
	double hSpeed = 0;
	double vSpeed = 0;
	double speed = Math.sqrt(vSpeed*vSpeed + hSpeed*hSpeed);
	static double cwValue = 0.38;
	static int frontArea = 4;
	double maxAcceleration = r.nextDouble()+1*10;
	double standardAcceleration = 0;
	int mass = r.nextInt(400)+800;
	int currentMaxSpeed = 120;
	int gravityPull = (int) (mass*9.8);
	double wheelFriction = r.nextDouble()*0.025+0.01;
	double wheelFrictionForce = wheelFriction*gravityPull;
	
	public double dragForce(){
		return 0.5*1.2922*this.speed*this.speed*this.cwValue*this.frontArea;
	}
	public int acceleration(){
		double F = ((this.mass*this.standardAcceleration) - dragForce()) - wheelFrictionForce*speed/120;
		return (int) (F/this.mass);
	}
	/*
	 * sets the new speed
	 */
	public void setNewSpeed(){
		this.speed += acceleration();
	}
}
