package interceptor.context;

import java.util.concurrent.LinkedBlockingQueue;

import interceptor.frame.services.ActionOutside;

public class MSGManager{ // this one designed for receive the messages from all devices

	private static final LinkedBlockingQueue <ActionOutside> msgQueue = new LinkedBlockingQueue<ActionOutside>( 50 /*MaxLengthOfQueue : in case of memory overloading.*/) ;	// thread-safe data structure
	
	public void msgEnqueue(ActionOutside msg) {
		try {
			msgQueue.put(msg);	// enqueue
		} catch (InterruptedException e) {
			System.err.println("Concurrent Error! - CAN NOT *PUT* NEW MSG IN MSGManager.");
			e.printStackTrace();
		}
	}
	
	public ActionOutside msgDequeue() {
		ActionOutside msg = null;
		try {
			msg = msgQueue.take(); // dequeue
		} catch (InterruptedException e) {
			System.err.println("Concurrent Error! - CAN NOT *TAKE* NEW MSG IN MSGManager.");
			e.printStackTrace();
		}
		return msg;
	}
	
	public boolean isQueueEmpty() {
		return msgQueue.isEmpty();
	}
	
}