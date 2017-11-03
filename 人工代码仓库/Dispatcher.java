package interceptor.dispatcher;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;
import config.*;
import interceptor.dispatcher.Dispatcher.MyInterceptorStack;
import interceptor.frame.services.ActionOutside;
import interceptor.interceptors.MyInterceptor;
 

public class Dispatcher{
	 
	private final Map< Class<? extends ActionOutside>, MyInterceptorStack > interceptorsMap = InterceptorConfigXMLParser.getInterceptorMap();
	
//	public Dispatcher( DispatcherBuilder dispatcherBuilder ) {
//		
//	}
	
	public Dispatcher() { }
	
	public void callbackInterceptors (ActionOutside msg) { 
		MyInterceptorStack  interceptorStack = interceptorsMap.get(msg.getClass());
		if ( interceptorStack == null || interceptorStack.isEmpty() ) {
			/** TODO: If the action/event is not be configurated in interceptorConfig.xml file, what gona next?*/
			///Just return is not correct here, it should do something like throws exceptions or ...
			// I am too tired to keep... see you tomorrow...
			return ;
		} else {
			for (MyInterceptor interceptor : interceptorStack ) {
				interceptor.executeOutofBandService(); 
			}
		}
	}
	
	/*书上写的是为了自动化注册Interceptor
	 * 在此类的构造方法里实现将拦截器注册的代码
	 * public static class RegisterHelper {
	 * }
	 */ 
	public static class DispatcherBuilder {  
		Map< ActionOutside, MyInterceptorStack > interceptorsMap; 	// define a interceptor stack
		
		public DispatcherBuilder() {			
			interceptorsMap = new HashMap <ActionOutside, MyInterceptorStack> (); 
		} 

		public DispatcherBuilder bind(ActionOutside actionOutside, MyInterceptorStack interceptorStack) { 
			interceptorsMap.put(actionOutside,interceptorStack);
			return this;
		}
		
		public DispatcherBuilder bind(ActionOutside actionOutside, Stack<MyInterceptor> interceptorStack) {  
			interceptorsMap.put(actionOutside, new MyInterceptorStack(interceptorStack) );
			return this;
		}
		
//		public Dispatcher build(){
//			return new Dispatcher(this);
//		}
	}

	public static class MyInterceptorStack implements Iterable<MyInterceptor> {  
		private Stack<MyInterceptor> interceptorStack; 	//Define a interceptor stack
		
		public MyInterceptorStack() {			
			interceptorStack = new Stack<MyInterceptor>(); 
		}
		
		public MyInterceptorStack( Stack<MyInterceptor> interceptorStack) {			
			this.interceptorStack = interceptorStack; 
		}
		
		public void push( MyInterceptor interceptor ){
			interceptorStack.push( interceptor );
		}  

		public Stack<MyInterceptor> getInterceptorStack(){
			return interceptorStack;
		}
		
		public boolean isEmpty(){
			return interceptorStack.isEmpty();
		}

		@Override
		public Iterator<MyInterceptor> iterator() { 
			return new InterceptorIterator() ;
		}
		
		private class InterceptorIterator implements Iterator<MyInterceptor> {

			private Stack<MyInterceptor> interceptorStack_ite;
			
			public InterceptorIterator() {
				interceptorStack_ite = new Stack();
				interceptorStack_ite.addAll(interceptorStack);
			}
			
			@Override
			public boolean hasNext() {
				return !interceptorStack_ite.isEmpty();
			}

			@Override
			public MyInterceptor next() { 
				if(hasNext())
					return interceptorStack_ite.pop();
				else
					return null; 
			}
			
		}
		
	}
	
	
}