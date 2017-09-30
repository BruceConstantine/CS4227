package homeservice;

/* The Command for turning on the light - ConcreteCommand #3 */
public class LightColorChange_Command implements Command { 
	
	Light _light;
	
	LightColorChange_Command(Light light){ _light = light; }
	
	public void execute(){
		_light.reinforceLightColor();
	}
	/**
	Can I make a extra function with some different parameters (interface) in here???	
	
	public void execute(parameters a, parameters b, ...){
		// some codes here ... ...
	}
	
	*/ 
	public void undo(){
		_light.recedeLightColor();
		
	}
}