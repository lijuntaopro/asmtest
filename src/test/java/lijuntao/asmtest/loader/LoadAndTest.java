package lijuntao.asmtest.loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class LoadAndTest {
	private String prefix; 
	private String suffix = ".class"; 
	public Class<?> class1;
	private String sampleName;
	public LoadAndTest(String className,byte[] bs){
		int i = className.lastIndexOf(".");
		if(i!=-1)
			sampleName = className.substring(i+1);
		else
			sampleName = className;
		writer(sampleName,bs);
		WebAppClassLoader loader = new WebAppClassLoader();
		class1 = loader.defineClass(className, bs);
		try {
			test();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public LoadAndTest(String className,byte[] bs,String prefix){
		this(className,bs);
	}
	public void writer(String sampleName,byte[] bs){
		String path = "";
		if(prefix==null)
			path = "C:/"+sampleName+suffix;
		else
			path = prefix + "/" + sampleName+suffix;
		File file = new File(path);
        FileOutputStream fout = null;
		try {
			fout = new FileOutputStream(file);
			fout.write(bs);
			fout.close();
			System.out.println("类写在："+path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public abstract void test() throws Exception;
}