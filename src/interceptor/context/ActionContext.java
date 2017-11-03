package interceptor.context;

import java.util.Map;

public class ActionContext {
	
	
	private static final ThreadLocal<?> actionContext = new ThreadLocal<> (); // different ActionContext Object has different value.
	
	Map<String, Object> context;
	
	public ActionContext (Map<String, Object> context) {
		this.context = context;
	}

	
	
}
