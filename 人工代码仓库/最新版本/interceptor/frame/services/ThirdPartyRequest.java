package interceptor.frame.services;

import interceptor.frame.ConcreteFramework;

public class ThirdPartyRequest implements ActionOutside {
	
	@Override
	public void executeService() {
		System.out.println("This this a HTTP Request from the third party");
		System.out.println(this.getClass().getName());
	}

	@Override
	public void sendToFramework() {
		ConcreteFramework.getMSGManager().msgEnqueue(this);
	} 
} 
