package Car;

import java.util.Random;
import java.lang.Math;
/** 
 * Car class with all condition and changing of them
 */
public class Car {
	Random r = new Random();
	
	//position
	int x = 600;
	int y = 600;
	
	//moving
	double rotation = 0;
	double speed = 0;
	
	//Acceleration
	double maxAcceleration = r.nextDouble()+1*10;
	double standardAcceleration = 0;
	
	//Friction values: drag, and wheel friction
	double cwValue = r.nextDouble()*0.2+0.2;
	int frontArea = 4;
	int mass = r.nextInt(400)+800;
	int gravityPull = (int) (mass*9.8);
	double wheelFriction = r.nextDouble()*0.025+0.01;
	double wheelFrictionForce = wheelFriction*gravityPull;
	
	//maximum speed on current road: Not used yet
	int currentMaxSpeed = 120;
	
	//calculate the drag
	public double dragForce(){
		return 0.5*1.2922*this.speed*this.speed*this.cwValue*this.frontArea;
	}
	//calculate the acceleration: wheelFrictionForce is changed to prevent negative speed
	public int acceleration(){
		double F = ((this.mass*this.standardAcceleration) - dragForce()) - wheelFrictionForce*speed/120;
		return (int) (F/this.mass);
	}
	/**
	 * sets the new speed
	 */
	//to add the acceleration up to the speed
	public void setNewSpeed(){
		this.speed += acceleration();
	}
	/**
	 * toggle the acceleration
	 */
	public void toggleAcceleration(){
		standardAcceleration = standardAcceleration == 0? maxAcceleration: 0;
	}
	/**
	 * changes the rotation based on -1, 0 and +1
	 * @param direction
	 */
	public void steer(byte direction){
		//if smaller then 1 deg, exception check
		if(rotation + direction/180*Math.PI < 1/180*Math.PI){
			rotation = 2*Math.PI;
		//if greater then 360 deg, exception check	
		}else if(rotation + direction/180*Math.PI > Math.PI*2){
			rotation = 1/180*Math.PI;
		//to change the rotation	
		}else{
			rotation += direction/180*Math.PI;
		}
		
		
	}
	/**
	 * moves the car in the direction of its rotation
	 * @param speed
	 * @param rotation
	 */
	public void move(double speed, int rotation){
		
		//first quadrant of rotation
		if(rotation <= Math.PI*0.5){
			x += (int) (Math.sin(rotation)*speed);
			y += (int) (Math.cos(rotation)*speed);
			
		//second quadrant of rotation
		}else if(Math.PI*0.5 < rotation && rotation <= Math.PI){
			x += (int) (Math.cos(rotation - Math.PI*0.5)*speed);
			y -= (int) (Math.sin(rotation - Math.PI*0.5)*speed);
			
		//third quadrant of rotation
		}else if(Math.PI < rotation && rotation <= Math.PI*1.5){
			x -= (int) (Math.sin(rotation - Math.PI)*speed);
			y -= (int) (Math.cos(rotation - Math.PI)*speed);
		//fourth and last quadrant of rotation
		}else if(Math.PI*1.5 < rotation && rotation <= Math.PI*2){
			x -= (int) (Math.cos(rotation - Math.PI*1.5)*speed);
			y += (int) (Math.sin(rotation - Math.PI*1.5)*speed);
		}
		
	}
}
