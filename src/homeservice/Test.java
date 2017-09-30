package homeservice;


/* The test client class or client */
public class Test{
	public static void main(String []args){
		Light lamp = getLight();
		Command switchUp = new LightTurnOn_Command(lamp);
		Command switchDown = new LightTurnOff_Command(lamp);
		Command switchColorChange = new LightColorChange_Command(lamp);
		
		LightInvoker remote = new LightInvoker();
		 
		//NOTICE!
		//Although the Receiver class  ( Light ) can be invoked, it actually has violate the command pattern principle, that -> invoker call the receiver.
		//lamp.turnOn();
		
		remote.executeCommand(switchUp);
		remote.executeCommand(switchColorChange);
		remote.undo();
		remote.executeCommand(switchUp);
		remote.undo();
	}
	//Class cannot be instancated within the static funtion 
	
	public static Light getLight(){ // Factory Method Here
		return new Light(); 
	}
	
} 