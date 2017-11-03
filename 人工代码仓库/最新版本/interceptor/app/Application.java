package interceptor.app;
  
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.imageio.spi.RegisterableService;

import com.sun.javafx.event.EventHandlerManager;
import com.sun.jndi.ldap.ManageReferralControl;
import com.sun.xml.internal.ws.api.message.Attachment;

import config.HomeAutoMationSystemApp;
import interceptor.dispatcher.Dispatcher.MyInterceptorStack;
import interceptor.frame.ConcreteFramework;
import interceptor.frame.services.DeviceCommand;
import interceptor.frame.services.ActionOutside;
 

public class Application {
	
	public Application() {
		initialser();
	}
	
	public void initialser() {
		new HomeAutoMationSystemApp().initialiseSystemConfig();
	}

	
//	public static void main(String[] args) { 
//		MyInterceptorConfig interceptorConfig = new MyInterceptorConfig();
//		Dispatcher.MyInterceptorStack interceptorStack = new Dispatcher.MyInterceptorStack ();
//		Dispatcher.DispatcherBuilder builder = new Dispatcher.DispatcherBuilder();
//	}

}







