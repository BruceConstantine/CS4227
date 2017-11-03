package test;

import interceptor.app.Application;
import interceptor.frame.ConcreteFramework;
import interceptor.frame.services.DeviceCommand;
import interceptor.frame.services.ThirdPartyRequest;
import interceptor.frame.services.ActionOutside;

public class Test {

	public static void main(String[] args) { 
		
		ConcreteFramework framework = new ConcreteFramework(); 
		Application app = new Application();
		
		
		framework.startService(); 

		ActionOutside request = new DeviceCommand();
		request.sendToFramework();
		
		try {
			Thread.sleep(1111);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new ThirdPartyRequest().sendToFramework();
	}

}
