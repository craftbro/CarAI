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

	//position
	int x = 600;
	int y = 600;
	
	//moving
	double rotation = 0;
	int direction = 0;
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
	public void breaks(){
		if(speed - 30 < 0){
			speed = 0;
		}else{
			speed -= 30;
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
		
		

		int addX = 0;
		int addY = 0;
		
	
		addX = (int) Math.round((Math.sin(Math.toRadians(rotation))*speed));
		addY = -(int) Math.round((Math.cos(Math.toRadians(rotation))*speed));
		
		Point oldPos = new Point(x, y);
		
		x += addX;
		y += addY;
		
		Point newPos = new Point(x, y);

		this.updateRotation(oldPos.distance(newPos));
	}
	
	public void update(){
		this.toggleAcceleration();
		this.setNewSpeed();
		if(rotation != 145){
			this.steer((byte)1);
		}else{
			this.steer((byte)0);
		}
	}

	public void draw(Graphics2D g, float scale, double xOffset, double yOffset) {
		this.move(this.speed/60, rotation);
		frame.draw(g, scale, xOffset, yOffset);
	}

}
