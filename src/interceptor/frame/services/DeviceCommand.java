package interceptor.frame.services;

import interceptor.frame.ConcreteFramework;

public class DeviceCommand implements ActionOutside {
	
	@Override
	public void executeService() {
		System.out.println("This this a Command Request from Android Mobile."); 
	}

	@Override
	public void sendToFramework() {
		ConcreteFramework.getMSGManager().msgEnqueue(this);
	} 
} 
