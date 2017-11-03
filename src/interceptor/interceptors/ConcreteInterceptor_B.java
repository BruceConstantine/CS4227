package interceptor.interceptors;

public class ConcreteInterceptor_B implements MyInterceptor {
	
	public void executeOutofBandService () {
		System.out.println("The out-of-band service: blablabla.. is be executed... ");
	}
}