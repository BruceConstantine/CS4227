package interceptor.interceptors;

public class ConcreteInterceptor_A implements MyInterceptor {
	public void executeOutofBandService(){
		System.out.println("The out-of-band service: logging is outputing... "); 
	}
}