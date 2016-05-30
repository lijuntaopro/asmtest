package lijuntao.asmtest.asm;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class SayHelloMethodAdapter extends MethodAdapter{

	public SayHelloMethodAdapter(MethodVisitor mv) {
		super(mv);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void visitCode() {
//		System.out.println("SayHelloMethodAdapter visitCode begin\n");
//		mv.visitCode();
//		Label l0 = new Label();
//		mv.visitLabel(l0);
//		mv.visitLineNumber(22, l0);
//		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "lijuntao/asmtest/aop/AopInterceptor", "before", "()V");
//		
//		
//		Label l1 = new Label();
//		mv.visitLabel(l1);
//		mv.visitLineNumber(23, l1);
//		mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
//		mv.visitLdcInsn("hello2");
//		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,"java/io/PrintStream", "println", "(Ljava/lang/String;)V");
//		
//		Label l2 = new Label();
//		mv.visitLabel(l2);
//		mv.visitLineNumber(24, l2);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "lijuntao/asmtest/aop/AopInterceptor", "before", "()V");
//		
//		
//		
//		Label l3 = new Label();
//		mv.visitLabel(l3);
//		mv.visitLineNumber(25, l3);
//		mv.visitInsn(Opcodes.RETURN);
//		
//		Label l4 = new Label();
//		mv.visitLabel(l4);
//		mv.visitMethodInsn(Opcodes.INVOKESTATIC,"lijuntao/asmtest/aop/AopInterceptor", "after", "()V");
//		mv.visitMaxs(2, 1);
//		visitEnd();
		System.out.println("SayHelloMethodAdapter visitCode end\n");
	}

	@Override
	public void visitEnd() {
		mv.visitMethodInsn(Opcodes.INVOKESTATIC,"lijuntao/asmtest/aop/AopInterceptor", "after", "()V");
		super.visitEnd();
	}
	
	
}
