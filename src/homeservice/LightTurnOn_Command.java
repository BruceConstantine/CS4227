package homeservice;

/* The Command for turning on the light - ConcreteCommand #1 */
public class LightTurnOn_Command implements Command { 
	
	Light _light;
	
	LightTurnOn_Command(Light light){ _light = light; }
	
	public void execute(){
		_light.turnOn();
	}
	
	public void undo(){
		_light.turnOff();
	}
}