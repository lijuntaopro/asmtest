package lijuntao.asmtest.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.Arrays;

import lijuntao.asmtest.loader.LoadAndTest;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class CLassWriterDemo {
	public static void main(String[] args) throws Exception {
		//生成一个类只需要ClassWriter组件即可
        ClassWriter cw = new ClassWriter(0);
        //通过visit方法确定类的头部信息
        cw.visit(Opcodes.V1_5, Opcodes.ACC_PUBLIC,
                "com/asm3/Comparable", null, "java/lang/Object", null);
        //定义类的属性
        cw.visitField(Opcodes.ACC_PUBLIC+Opcodes.ACC_FINAL+Opcodes.ACC_STATIC,
                "LESS", "I", null, new Integer(-1)).visitEnd();
        cw.visitField(Opcodes.ACC_PUBLIC+Opcodes.ACC_FINAL+Opcodes.ACC_STATIC,
                "EQUAL", "I", null, new Integer(0)).visitEnd();
        cw.visitField(Opcodes.ACC_PUBLIC+Opcodes.ACC_FINAL+Opcodes.ACC_STATIC,
                "GREATER", "I", null, new Integer(1)).visitEnd();
        //定义类的方法
        cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>",
        		"()V", null, null).visitEnd();
        cw.visitMethod(Opcodes.ACC_PUBLIC, "compareTo",
                "(Ljava/lang/Object;)I", null, null).visitEnd();
        cw.visitEnd(); //使cw类已经完成
        //将cw转换成字节数组写到文件里面去
        readerAndWriter("com.asm3.Comparable",cw.toByteArray());
       
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
			}
		};
	}
	public void test(){
		
	}
}
