package homeservice;

/* The Invoker super-class */
import java.util.*;

public class LightInvoker_ImplementedByList{
	
	//How much command array should be decleared here?
	private List<Command> history;
	private int commandIndex;
	
	public LightInvoker_ImplementedByList(){
		history = new ArrayList<Command>();
		commandIndex = history.size();
	}
	
	public void executeCommand(Command cmd){
		cmd.execute();
		history.add(cmd);
		commandIndex = history.size() -  1; 	// update private attribute value
	}
	
	public void undo(){		//undo
		if(commandIndex > 0) {
			Command lastCommand = history.get(commandIndex--);
			lastCommand.undo();
		}
	}
	
	/* Can implement the command queue
	public void lastCommandPushed(){	
		if(commandIndex > 0) {
			Command lastCommand = history.get(--commandIndex);
			lastCommand.execute();
		}
	}
	
	public void forwardCommandPushed(){//do last command again
		if(commandIndex < history.size()) {
			Command lastCommand = history.get(++commandIndex);
			lastCommand.execute();
		}
	}
	*/
}