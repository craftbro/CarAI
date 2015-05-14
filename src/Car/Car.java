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
	double rotation = 0;
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
		if(rotation + direction/180*Math.PI < 1/180*Math.PI){
			rotation = 2*Math.PI;
			
		}else if(rotation + direction/180*Math.PI > Math.PI*2){
			rotation = 1/180*Math.PI;
			
		}else{
			rotation += direction/180*Math.PI;
		}
		
		
	}
	public void move(double speed, int rotation){
		if(rotation <= Math.PI*0.5){
			x += (int) (Math.sin(rotation)*speed);
			y += (int) (Math.cos(rotation)*speed);
		}else if(Math.PI*0.5 < rotation && rotation <= Math.PI){
			x += (int) (Math.cos(rotation - Math.PI*0.5)*speed);
			y -= (int) (Math.sin(rotation - Math.PI*0.5)*speed);
		}else if(Math.PI < rotation && rotation <= Math.PI*1.5){
			x -= (int) (Math.sin(rotation - Math.PI)*speed);
			y -= (int) (Math.cos(rotation - Math.PI)*speed);
		}else if(Math.PI*1.5 < rotation && rotation <= Math.PI*2){
			x -= (int) (Math.cos(rotation - Math.PI*1.5)*speed);
			y += (int) (Math.sin(rotation - Math.PI*1.5)*speed);
		}
		
	}
}
