package Enviorment;

public class TrafficLight {
	
	TrafficLightColours currentTrafficLightColour = TrafficLightColours.RED;
	int counter =  -1;
	
	public void toggleTrafficLight(){
		
		switch (currentTrafficLightColour) {
        	case GREEN:
        		counter = 120;
    			currentTrafficLightColour = TrafficLightColours.ORANGE;
        		break;
                
        	case ORANGE:
        		currentTrafficLightColour = TrafficLightColours.RED;
        		break;
                     
        	case RED:
        		currentTrafficLightColour = TrafficLightColours.GREEN;
        		break;
		}
		
	}
	
	public void counterUpdate(){
		if(counter > 0){
			counter -= 1;
		}else if(counter == 0){
			this.toggleTrafficLight();
			counter -= 1;
		}
	}
	
	public TrafficLightColours getColour(){
		return  currentTrafficLightColour;
	}
	
	public void update(){
		this.counterUpdate();
	}
}
