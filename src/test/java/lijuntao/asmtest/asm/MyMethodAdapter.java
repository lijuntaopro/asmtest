package lijuntao.asmtest.asm;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;

public class MyMethodAdapter extends MethodAdapter{
	private String methodName = "";
	public MyMethodAdapter(MethodVisitor mv) {
		super(mv);
		// TODO Auto-generated constructor stub
	}
	public MyMethodAdapter(MethodVisitor mv,String methodName) {
		super(mv);
		this.methodName = methodName;
	}

	@Override
	public AnnotationVisitor visitAnnotationDefault() {
		System.out.println(methodName+":visitAnnotationDefault");
		return super.visitAnnotationDefault();
	}

	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		System.out.println(methodName+":visitAnnotation:desc="+desc+";visible="+visible);
		return super.visitAnnotation(desc, visible);
	}

	@Override
	public AnnotationVisitor visitParameterAnnotation(int parameter,
			String desc, boolean visible) {
		System.out.println(methodName+":visitParameterAnnotation:parameter="+parameter+";desc="+desc+";visible="+visible);
		return super.visitParameterAnnotation(parameter, desc, visible);
	}

	@Override
	public void visitAttribute(Attribute attr) {
		System.out.println(methodName+":visitAttribute:type="+attr.type);
		super.visitAttribute(attr);
	}

	@Override
	public void visitCode() {
		System.out.println(methodName+":visitCode");
		super.visitCode();
	}

	@Override
	public void visitFrame(int type, int nLocal, Object[] local, int nStack,
			Object[] stack) {
		System.out.println(methodName+":visitFrame:type="+type+";nLocal="+nLocal+";local="+local+";nStack="+nStack+";stack="+stack);
		super.visitFrame(type, nLocal, local, nStack, stack);
	}

	@Override
	public void visitInsn(int opcode) {
		System.out.println(methodName+":visitInsn:opcode="+opcode);
		super.visitInsn(opcode);
	}

	@Override
	public void visitIntInsn(int opcode, int operand) {
		System.out.println(methodName+":visitIntInsn:opcode="+opcode);
		super.visitIntInsn(opcode, operand);
	}

	@Override
	public void visitVarInsn(int opcode, int var) {
		System.out.println(methodName+":visitVarInsn:opcode="+opcode);
		super.visitVarInsn(opcode, var);
	}

	@Override
	public void visitTypeInsn(int opcode, String type) {
		System.out.println(methodName+":visitTypeInsn:opcode="+opcode);
		super.visitTypeInsn(opcode, type);
	}

	@Override
	public void visitFieldInsn(int opcode, String owner, String name,
			String desc) {
		System.out.println(methodName+":visitFieldInsn:opcode="+opcode+";owner="+owner+";name="+name+";desc="+desc);
		super.visitFieldInsn(opcode, owner, name, desc);
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name,
			String desc) {
		System.out.println(methodName+":visitMethodInsn:opcode="+opcode+";owner="+owner+";name="+name+";desc="+desc);
		super.visitMethodInsn(opcode, owner, name, desc);
	}

	@Override
	public void visitJumpInsn(int opcode, Label label) {
		System.out.println(methodName+":visitJumpInsn:opcode="+opcode);
		super.visitJumpInsn(opcode, label);
	}

	@Override
	public void visitLabel(Label label) {
		System.out.println(methodName+":visitLabel");
		super.visitLabel(label);
	}

	@Override
	public void visitLdcInsn(Object cst) {
		System.out.println(methodName+":visitLdcInsn:cst="+cst);
		super.visitLdcInsn(cst);
	}

	@Override
	public void visitIincInsn(int var, int increment) {
		System.out.println(methodName+":visitIincInsn");
		super.visitIincInsn(var, increment);
	}

	@Override
	public void visitTableSwitchInsn(int min, int max, Label dflt,
			Label[] labels) {
		System.out.println(methodName+":visitTableSwitchInsn");
		super.visitTableSwitchInsn(min, max, dflt, labels);
	}

	@Override
	public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
		System.out.println(methodName+":visitLookupSwitchInsn");
		super.visitLookupSwitchInsn(dflt, keys, labels);
	}

	@Override
	public void visitMultiANewArrayInsn(String desc, int dims) {
		System.out.println(methodName+":visitMultiANewArrayInsn");
		super.visitMultiANewArrayInsn(desc, dims);
	}

	@Override
	public void visitTryCatchBlock(Label start, Label end, Label handler,
			String type) {
		System.out.println(methodName+":visitTryCatchBlock");
		super.visitTryCatchBlock(start, end, handler, type);
	}

	@Override
	public void visitLocalVariable(String name, String desc, String signature,
			Label start, Label end, int index) {
		System.out.println(methodName+":visitLocalVariable");
		super.visitLocalVariable(name, desc, signature, start, end, index);
	}

	@Override
	public void visitLineNumber(int line, Label start) {
		System.out.println(methodName+":visitLineNumber");
		super.visitLineNumber(line, start);
	}

	@Override
	public void visitMaxs(int maxStack, int maxLocals) {
		System.out.println(methodName+":visitMaxs");
		super.visitMaxs(maxStack, maxLocals);
	}

	@Override
	public void visitEnd() {
		System.out.println(methodName+":visitEnd");
		super.visitEnd();
	}
	

}
