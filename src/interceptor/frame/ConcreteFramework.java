package interceptor.frame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import interceptor.context.ActionContext;
import interceptor.context.MSGManager;
import interceptor.dispatcher.Dispatcher;
import interceptor.dispatcher.Dispatcher.MyInterceptorStack;
import interceptor.frame.services.ActionOutside;
import config.*;

public class ConcreteFramework{

	private static final MSGManager msgManager = new MSGManager();
	private Dispatcher dispatcher;
	
	public ConcreteFramework() {
		dispatcher = new Dispatcher();
	}
	
	public void startService() { // start framework for providing services to outside invokers
		Thread eventListenThread = new Thread( new MessageListenTask() );
		eventListenThread.start();	// Start a new Thread for listen event from outside 
	}	
	
	private class MessageListenTask implements Runnable { // Listen the event into framework.
		@Override
		public void run() {
			while (true) { 		//continually listen the outside services request 
				List<Thread> handleMsgThread_List = new Vector<Thread>();  	//Vector is thread-safe	
				if (!msgManager.isQueueEmpty()) { // checkMSGQueue(): message queue is not empty
					Thread onEventOccur = createAnEventHandlerThread();
					handleMsgThread_List.add(onEventOccur);
					onEventOccur.start();
					 
				} else {		//no message come here, have a rest for thread
					try { 	
						for (Thread eachThread: handleMsgThread_List ) { // for each handler to join the current thread for executing 
								eachThread.join(); 		// blocking this thread until each Thread is finished and have a rest together.
						}
						handleMsgThread_List  = null;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}	// before have a rest, it must wait until there is no any ongoing thread.
					restThread(); // because outside messages are not high current type, thus, have a rest for CPU. 
				}
			}
		} 

		private Thread createAnEventHandlerThread() {
			return new Thread(new Runnable() {
				@Override
				public void run() {
					//TODO: Create Context Object here
					createContextObject();
					ActionOutside msg = msgManager.msgDequeue();
					dispatcher.callbackInterceptors(msg);
					//here is the process for executing out-of-band services		//dispatcher.interceptAll();
					msg.executeService();					
				}
			});
		}
		
		private void createContextObject() {
			//createContextObject();
		}

		private void restThread() {
			int a_second = 10000 ;
			int interval = a_second / 2; 	//Sleep for a half second 
			System.err.println("No Message in queue at present, Thread gona sleep 3 s");
			try {
				Thread.sleep(interval);		//Set interval time for relieve CPU time
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.err.println("Thread finish sleep.");
		}
	}

	public static MSGManager getMSGManager() {
		return msgManager;
	}
}