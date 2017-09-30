package homeservice;

public interface Command {
	public abstract void execute();
	public abstract void undo(); 	
}

class NoCommand implements Command{
	public void execute()	{}
	public void undo()		{}	
}