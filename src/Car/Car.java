package Car;

import java.util.Random;
import java.lang.Math;
/* 
 * Car class with all condition and changing of them
 */
public class Car {
	int x = 600;
	int y = 600;
	Random r = new Random();
	int rotation = 1;
	double speed = 0;
	double cwValue = r.nextDouble()*0.2+0.2;
	int frontArea = 4;
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
	public void toggleAcceleration(){
		standardAcceleration = standardAcceleration == 0? maxAcceleration: 0;
	}
	public void steer(byte direction){
		if(rotation + direction < 1){
			rotation = 360;
			
		}else if(rotation + direction > 360){
			rotation = 1;
			
		}else{
			rotation += direction;
		}
		
		
	}
	public void move(double speed, int rotation){
		if(rotation <= 90){
			x += (int) (Math.sin(rotation)*speed);
			y += (int) (Math.cos(rotation)*speed);
		}else if(90 < rotation && rotation <= 180){
			x += (int) (Math.cos(rotation - 90)*speed);
			y -= (int) (Math.sin(rotation - 90)*speed);
		}else if(180 < rotation && rotation <= 270){
			x -= (int) (Math.sin(rotation - 180)*speed);
			y -= (int) (Math.cos(rotation - 180)*speed);
		}else if(270 < rotation && rotation <= 360){
			x -= (int) (Math.cos(rotation - 270)*speed);
			y += (int) (Math.sin(rotation - 270)*speed);
		}
		
	}
}
