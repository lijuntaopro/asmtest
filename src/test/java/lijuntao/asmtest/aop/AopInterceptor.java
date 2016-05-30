package lijuntao.asmtest.aop;

public class AopInterceptor {
	public static void before(){
		System.out.println("before");
	}
	public static void after(){
		System.out.println("after");
	}
	public void before2(){
		System.out.println("before2");
	}
	public void after2(){
		System.out.println("after2");
	}
}
