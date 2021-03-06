package Car;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Random;
/** 
 * Car class with all condition and changing of them
 */
public class Car {
	Random r = new Random();
	
	boolean doBreak = false;
	
	//position
	double x = 600;
	double y = 600;
	
	//moving
	double rotation = 0;
	int direction = 0;
	double speed = 0;
	
	//Acceleration
	double maxAcceleration = r.nextDouble()+1*20;
	double standardAcceleration = 0;
	
	//Friction values: drag, and wheel friction
	double cwValue = r.nextDouble()*0.2+0.2;
	int frontArea = 4;
	int mass = r.nextInt(400)+800;
	int gravityPull = (int) (mass*9.8);
	double wheelFriction = r.nextDouble()*0.025+0.01;
	double wheelFrictionForce = wheelFriction*gravityPull;
	
	//maximum speed on current road
	int currentSpeedLimit = 120;
	boolean isUnderSpeedLimit = true;
	boolean keepsToSpeedLimit = true;
	
	//The object used for drawing the car with the right rotation
	CarFrame frame;
	
	public Car(){
		this.frame = new CarFrame(this);
	}
	
	//calculate the drag
	public double dragForce(){
		return 0.5*1.2922*this.speed*this.speed*this.cwValue*this.frontArea;
	}
	//calculate the acceleration: wheelFrictionForce is changed to prevent negative speed
	public double acceleration(){
		double F = ((this.mass*this.standardAcceleration) - dragForce()) - wheelFrictionForce*(speed > 0? 1 : 0);
		return (F/this.mass)/60;
	}
	/**
	 * sets the new speed
	 */
	//to add the acceleration up to the speed
	public void setNewSpeed(){
		this.speed += acceleration();
		isUnderSpeedLimit = speed <= currentSpeedLimit;
	}
	/**
	 * toggle the acceleration
	 */
	public void toggleAcceleration(){
		if((!isUnderSpeedLimit) && keepsToSpeedLimit){
			standardAcceleration = 0;
			doBreak = true;
		}else{
			//test the breaks
			if (doBreak){
				breaks();
			}else{
			standardAcceleration = isUnderSpeedLimit? maxAcceleration: 0;
			}
		}
	}
	public void breaks(){
		if(speed - 1 < 0){
			speed = 0;
		}else{
			speed -= 0.5;
		}
	}
	/**
	 * changes the direction of the wheels
	 * @param direction
	 */
	public void steer(byte direction){
		this.direction = direction;
	}
	
	/**
	 * Updates the current rotation
	 */
	public void updateRotation(double d){
		if(d == 0) return;
		this.rotation += (double)(this.direction);
		this.rotation = this.rotation%360;
		if(this.rotation < 0) this.rotation = 360 + this.rotation;
	
	}
	
	/**
	 * moves the car in the direction of its rotation
	 * @param speed
	 * @param rotation2
	 */
	public void move(double speed, double rotation){
		
		Point oldPos = new Point((int)x, (int)y);
		
		x +=  Math.sin(Math.toRadians(rotation))*speed;
		y += -Math.cos(Math.toRadians(rotation))*speed;
		
		Point newPos = new Point((int)x, (int)y);

		this.updateRotation(oldPos.distance(newPos));
	}
	
	public void update(){
		this.toggleAcceleration();
		this.setNewSpeed();
		this.steer((byte)1);
		
	}

	public void draw(Graphics2D g, float scale, double xOffset, double yOffset) {
		this.move(this.speed/60, rotation);
		frame.draw(g, scale, xOffset, yOffset);
	}

}
