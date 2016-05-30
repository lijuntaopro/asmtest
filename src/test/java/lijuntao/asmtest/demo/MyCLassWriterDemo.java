package lijuntao.asmtest.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Arrays;

import lijuntao.asmtest.asm.MyClassWriter;
import lijuntao.asmtest.loader.LoadAndTest;

import org.apache.commons.beanutils.MethodUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.util.TraceClassVisitor;

public class MyCLassWriterDemo {
	public static void main(String[] args) throws Exception {
		InputStream is = FirstDemo.class.getResourceAsStream("/lijuntao/asmtest/source/SayHello.class");
		ClassReader reader = null;
		try {
			reader = new ClassReader(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ClassWriter cw = new MyClassWriter(reader,0);
		ClassVisitor cv = new TraceClassVisitor(cw, new PrintWriter(System.out));
		//要不0，要不2，1会出错
		reader.accept(cv, ClassReader.SKIP_FRAMES);
		
        readerAndWriter("lijuntao.asmtest.source.SayHello_tmp",cw.toByteArray());
       
	}
	
	public static void readerAndWriter(String className,byte[] bs){
		new LoadAndTest(className,bs) {
			
			@Override
			public void test() throws Exception {
				// TODO Auto-generated method stub
				Object obj = class1.newInstance();
				Field field = class1.getDeclaredField("LESS");
				Object fieldAttr = field.get(obj);
				System.out.println("fieldAttr:"+fieldAttr);
				Field field2 = class1.getDeclaredField("str2");
				Object fieldAttr2 = field2.get(obj);
				System.out.println("fieldAttr2:"+fieldAttr2);
				MethodUtils.invokeMethod(obj, "sayHello", new Object[]{"lijuntao"});
				MethodUtils.invokeMethod(obj, "sayHello2", null);
			}
		};
	}
	public void test(){
		
	}
}
