package homeservice;

/* The Command for turning on the light - ConcreteCommand #2 */
public class LightTurnOff_Command implements Command { 
	
	Light _light;
	
	LightTurnOff_Command(Light light){ _light = light; }
	
	public void execute(){
		_light.turnOff();
	}
	
	public void undo(){
		_light.turnOn();
	}
}