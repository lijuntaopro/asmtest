package lijuntao.asmtest.loader;

import java.net.URL;
import java.net.URLClassLoader;

public class ASMClassLoader extends URLClassLoader{

	public ASMClassLoader(URL[] urls) {
		super(urls);
		// TODO Auto-generated constructor stub
	}
	public ASMClassLoader() {
		super(null);
		// TODO Auto-generated constructor stub
	}
	
	public Class<?> loadClass(byte[] bs) throws ClassNotFoundException{
		Class<?> class1 = defineClass(bs, 0, bs.length);
		return null;
	}

}
