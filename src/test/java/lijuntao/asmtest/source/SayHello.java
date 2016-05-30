package lijuntao.asmtest.source;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;

import lijuntao.asmtest.aop.AopInterceptor;

public class SayHello {
	private String str1;
	public static String str2 = "hello str2";
	public String str3;
	public static String str4;
	
	@SafeVarargs
	public void sayHello(String name){
		System.out.println("hello "+name);
	}
	public void sayHello(){
		System.out.println("hello");
	}
	public void sayHello2(){
//		AopInterceptor.before();
		System.out.println("hello2");
//		AopInterceptor.after();
//		AopInterceptor.after();
	}
	public static void sayHello1(){
//		lijuntao.asmtest.aop.AopInterceptor.before();
		System.out.println("hello");
//		lijuntao.asmtest.aop.AopInterceptor.after();
	}
}
