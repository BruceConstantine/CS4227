package config;
//
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
 

/**
 * 对于此类最大的争议是	： 程序是否可以在运行时增加拦截器的功能和个数。
 * 		也就是说			： 所有的拦截器的配置是否是在程序进行编译部署的时候就已经被设置完毕了
 * 如果是， 可以用IterceptorConfig类去读取xml配置文件/或者其他文件配置，从而进行注册拦截器到Dispatcher上
 *   否则， 要用Reflect动态机制去实现一个可动态增删拦截器的类去实现拦截器的注册 .>>> 不过我觉得不用
 */
/*
class MyInterceptorConfig { 
	//   代理栈用于指明：框架所提供的服务/具体事件（DeviceCommand）和相应拦截器的关系 -> 一个服务对应多少要被执行的拦截器

	//   这里是指 对于框架已经定义的服务有不同的拦截器执行策略， 比如，用户登陆事件是要进行logging和身份验证等等，
	//所以这个业务涉及到的interceptor比较多；然而有的业务的拦截器栈就一点，因此要对不同的进行业务逻辑进行拦截器的映射
	private static Map<ActionOutside, MyInterceptorStack> proxyMap = new HashMap<ActionOutside, MyInterceptorStack >();
	  
	//对proxy进行配置方法->对传入的拦截器进行配置：配置相应的事件和拦截器的对应关系.
	public static void //还是 Object ?
						bind( ActionOutside serviceInFramework, MyInterceptorStack interceptorStack ) {
		proxyMap.put(serviceInFramework, interceptorStack);
	} 
	
	public Map<ActionOutside, MyInterceptorStack> getProxyMap() {
		return proxyMap;
	}
	
	public static class MyInterceptorStack {  
		private Stack<MyInterceptor> interceptorStack; 	//定义一个拦截器栈
		
		public MyInterceptorStack() {			
			interceptorStack = new Stack<MyInterceptor>(); 
		}

		public void attach( MyInterceptor interceptor ){
			interceptorStack.push( interceptor );
		}  
		
		public Stack<MyInterceptor> getInterceptorStack(){
			return interceptorStack;
		}
	}
}
*/