package com.trendcore.asm;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

public class TryCatchMethodAdviceVisitor extends AdviceAdapter {

    private final Label tryLabel = new Label();
    private final Label catchLabel = new Label();

    protected TryCatchMethodAdviceVisitor(int api, MethodVisitor methodVisitor, int access, String name, String descriptor) {
        super(api, methodVisitor, access, name, descriptor);
    }

    @Override
    public void visitCode() {
        mv.visitTryCatchBlock(tryLabel, catchLabel, catchLabel, null);
        super.visitCode();

        mv.visitLabel(tryLabel);
    }

    @Override
    protected void onMethodEnter() {
        /*
        Profiler.pushMethod("profileMe",System.currentTimeMillis(),"java.lang.String,java.lang.String");
         */

        //System.out.println(this.className + ":" + super.getName());

        visitLdcInsn(getName());
        visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
        visitLdcInsn("java.lang.String,java.lang.String");
        visitMethodInsn(INVOKESTATIC, "com/trendcore/Profiler", "pushMethod", "(Ljava/lang/String;JLjava/lang/String;)V", false);

    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        mv.visitLabel(catchLabel);
        int throwableLocal = newLocal(Type.getType(Throwable.class));
        storeLocal(throwableLocal);
        onFinally();
        loadLocal(throwableLocal);
        throwException();
        mv.visitMaxs(maxStack, maxLocals);
    }

    @Override
    public void visitInsn(int opcode) {
        switch (opcode) {
            case ARETURN:
            case DRETURN:
            case FRETURN:
            case IRETURN:
            case LRETURN:
            case RETURN:
                onFinally();
                mv.visitInsn(opcode);
                break;
            default:
                mv.visitInsn(opcode);
        }
    }

    private void onFinally() {
        visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
        visitMethodInsn(INVOKESTATIC, "com/trendcore/Profiler", "popMethod", "(J)V", false);
    }
}
