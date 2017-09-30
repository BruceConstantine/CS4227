package homeservice;

/* The Invoker super-class */
import java.util.*;

public class LightInvoker{
	
	//How much command array should be decleared here? 
	private Stack<Command> history ; 
	
	public LightInvoker(){
		history = new Stack<Command>(); 
	}
	
	public void executeCommand(Command cmd){
		cmd.execute();
		history.push(cmd); 
	}
	
	public void undo(){		//undo 
		Command lastCommand = history.pop();
		lastCommand.undo(); 
	}
	 
	
	/**
	*** Function signatures are too clear to needn't for any command queue... like following:
	public void lightEnforcePushed(){
	  this.history.add(cmd); // optional 

	public void lightRecedePushed(){
	  this.history.add(cmd); // optional 
	}
	
	public void lightOnPushed(){
	  this.history.add(cmd); // optional 
	}
	
	public void lightOffPushed(){
	  this.history.add(cmd); // optional 
	}	*/
}