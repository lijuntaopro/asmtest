package lijuntao.asmtest.demo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import lijuntao.asmtest.asm.AopImplClassVisitor;
import lijuntao.asmtest.asm.MyClassWriter;
import lijuntao.asmtest.loader.WebAppClassLoader;
import lijuntao.asmtest.source.SayHello;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.MethodUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
//import org.objectweb.asm.util.TraceClassVisitor;
import org.objectweb.asm.Opcodes;

public class FirstDemo {
	public static void main(String[] args) {
		InputStream is = FirstDemo.class.getResourceAsStream("/lijuntao/asmtest/source/SayHello.class");
		ClassReader reader = null;
		try {
			reader = new ClassReader(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ClassWriter classWriter = new MyClassWriter(reader,ClassWriter.COMPUTE_MAXS);
		{
//		这些为放在错误的地方，相应的visit方法要放在MyClassWriter的方法中，有classReader来遍历的调用
//		FieldVisitor field = classWriter.visitField(Opcodes.ACC_PUBLIC, "str", ""+Type.getDescriptor(String.class)+"", null, "hello world");
//		field.visitEnd();
//		MethodVisitor mv = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "sayHello3", "()V", null, null);
//		mv.visitCode();
//		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "lijuntao.asmtest.aop.AopInterceptor", "before", "()V");
//		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "lijuntao.asmtest.aop.AopInterceptor", "after", "()V");
//		mv.visitEnd();
		
		}
		//可以打印修改后的class文件
//		TraceClassVisitor tcv = new TraceClassVisitor(classWriter,new PrintWriter(System.out));
		reader.accept(classWriter, ClassReader.SKIP_DEBUG);
		try {
			OutputStream os = new FileOutputStream("C:/SayHello_tmp.class");
			os.write(classWriter.toByteArray());
			os.flush();
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebAppClassLoader loader = new WebAppClassLoader();
		Class class1 = loader.defineClass("lijuntao.asmtest.source.SayHello_tmp", classWriter.toByteArray());
		try {
			Object object = class1.newInstance();
			MethodUtils.invokeMethod(object, "sayHello", null);
			Field field = class1.getDeclaredField("str");
			field.setAccessible(true);
			field.set(object, "hello world 2");
			Object object2 = field.get(object);
			
			System.out.println("object2:"+object2);
//			String string = BeanUtils.getProperty(object,"str");
//			System.out.println("str:"+string);
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
