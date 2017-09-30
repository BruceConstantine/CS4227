package homeservice;

/* The Receiver class */
public class Light{
	
	//for any light, they have the same color.
	private static final String [] lightColor = { "Green" ,
													"Gold" ,
													"Sliver",
													"Blingbling..." };
	
	// should the Light class exposed their data field as public ?
	// Or add the getter/setter parterns here??
	private int 	currentLightColor_index;
	private String  currentLightColor;
	//No -- Because the concrete command should know about the reference of Receiver
	//Notice:
	// IF: you find that it is need more code for getting message/information like add getter/setter 
	// It must have mistake, which can be amended by changing the reference direction / dependency.
	
	public Light(){
		currentLightColor = lightColor[currentLightColor_index];
	}
	
	public void turnOn(){
		System.out.println("Light is ** On **.");
		showLightColor();
	}
	
	public void turnOff(){
		System.out.println("Light is ** Off **.");
	}
	
	public void reinforceLightColor(){
		
		if(currentLightColor_index < lightColor.length)
			this.currentLightColor_index++;
		else
			this.currentLightColor_index = 0;
		showLightColor();
	}
	
	public void recedeLightColor(){
		
		if(currentLightColor_index > 0)
			this.currentLightColor_index--;
		else
			this.currentLightColor_index = lightColor.length - 1; 
		showLightColor();
	}
	
	
	private void showLightColor(){ 
		currentLightColor = lightColor[currentLightColor_index];
		System.out.println("Light Color is " + currentLightColor );
	}
	
}