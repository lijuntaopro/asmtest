package lijuntao.asmtest.asm;

import java.util.Arrays;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class MyClassWriter extends ClassWriter{

	public MyClassWriter(ClassReader classReader, int flags) {
		super(classReader, flags);
	}
	public MyClassWriter(int flags) {
		super (flags);
	}

	//1、当ClassReader调用accept,解析class文件内容，然后调用visit方法
	public void visit(int version, int access, String name, String signature,
			String superName, String[] interfaces) {
		System.out.println("version:"+version+";access:"+access+";name:"+name+";signature:"+signature+";superName:"+superName+";interfaces"+Arrays.toString(interfaces));
		System.out.println("visit:"+1);
		//改了名，就要对静态变量应用类名也改变，否则无法应用该静态变量。因为对静态成员赋值，需要生成clinit方法，该方法需要引用类名（类名改变就不行）
		super.visit(version, access, name+"_tmp", signature, superName, interfaces);
		//"()V"为参数和返回类型描述父，()代表无参，v代表为空，也可Type.getType(String.class)得到string类型的描述符
		System.out.println("Type.getDescriptor(String.class):"+Type.getDescriptor(String.class));
		System.out.println("tianjia field in");
		super.visitField(Opcodes.ACC_FINAL+Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "LESS", ""+Type.getDescriptor(String.class)+"", null,"hello world");
		//MethodVisitor mv = super.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
//		{
//			mv.visitCode();
//			mv.visitMethodInsn(Opcodes.INVOKESTATIC, "lijuntao.asmtest.aop.AopIntercepor", "before", "()V");
//			mv.visitMethodInsn(Opcodes.INVOKESTATIC, "lijuntao.asmtest.aop.AopIntercepor", "after", "()V");
//			mv.visitEnd();
//		}
	}
	@Override
	public void visitSource(String file, String debug) {
		System.out.println("visitSource:"+2);
		super.visitSource(file, debug);
	}
	@Override
	public void visitOuterClass(String owner, String name, String desc) {
		System.out.println("visitOuterClass:"+3);
		super.visitOuterClass(owner, name, desc);
	}
	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		System.out.println("visitAnnotation in");
		return super.visitAnnotation(desc, visible);
	}
	@Override
	public void visitAttribute(Attribute attr) {
		System.out.println("visitAttribute in");
		super.visitAttribute(attr);
	}
	@Override
	public void visitInnerClass(String name, String outerName,
			String innerName, int access) {
		System.out.println("visitInnerClass in");
		super.visitInnerClass(name, outerName, innerName, access);
	}
	@Override
	public FieldVisitor visitField(int access, String name, String desc,
			String signature, Object value) {
		System.out.println("visitField in");
		System.out.println("field:"+name);
		return super.visitField(access, name, desc, signature, value);
	}
	@Override
//	由classWriter调用
	//方法访问，加入返回的方法为空，代表则accept方法调用该方法返回为空，不能保存在ClassWriter，相当与删除方法
	public MethodVisitor visitMethod(int access, String name, String desc,
			String signature, String[] exceptions) {
		System.out.println("visitMethod in method name="+name+";desc="+desc);
		if("<clinit>".equals(name)){
//			L0
//		    LINENUMBER 8 L0
//		    LDC "hello str2"
//		    PUTSTATIC lijuntao/asmtest/source/SayHello.str2 : Ljava/lang/String;
//		   L1
//		    LINENUMBER 6 L1
//		    RETURN
//		    MAXSTACK = 1
//		    MAXLOCALS = 0
//			实现改变类名，静态变量赋值能成功
			System.out.println("signature:"+signature);
			MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
			mv.visitCode();
			Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(8, l0);
            
			mv.visitLdcInsn("hello str2");
			System.out.println(Type.getDescriptor(String.class));
			//填写类型时，必须要有";"
			mv.visitFieldInsn(Opcodes.PUTSTATIC, "lijuntao/asmtest/source/SayHello_tmp", "str2","Ljava/lang/String;");
			//mv.visitFieldInsn(Opcodes.GETSTATIC, "lijuntao/asmtest/source/SayHello_tmp", "str2", "Ljava/lang/String;");
			Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(6, l1);
			mv.visitInsn(Opcodes.RETURN);
			//设置栈和本地变量ClassWriter.COMPUTE_FRAMES有设置，这其无效
			mv.visitMaxs(1, 0);
			mv.visitEnd();
			return mv;	
		}else if("sayHello2".equals(name)){
			//返回来的MethodVisitor，已经有该方法的所有的基本信息
			System.out.println("sayHello desc:"+desc+";signature:"+signature);
//			MethodVisitor mv = new SayHelloMethodAdapter(super.visitMethod(access, name, desc, signature, exceptions));
//			MethodVisitor mv = new MyMethodAdapter(super.visitMethod(access, name, desc, signature, exceptions),name);
			MethodVisitor mv = new SayHelloMethodAdapter(new MyMethodAdapter(super.visitMethod(access, name, desc, signature, exceptions),name));
			return mv;
		}
		return super.visitMethod(access, name, desc, signature, exceptions);
	}
	@Override
	public void visitEnd() {
		System.out.println("visitEnd in");
		super.visitEnd();
	}
	
	
	

}
