package com.trendcore.asm;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

public class TryCatchMethodAdviceVisitor extends AdviceAdapter {

    private Label label1;
    private Label label2;
    private Label label3;

    protected TryCatchMethodAdviceVisitor(int api, MethodVisitor methodVisitor, int access, String name, String descriptor) {
        super(api, methodVisitor, access, name, descriptor);
    }

    @Override
    public void visitCode() {
        super.visitCode();
        Label label0 = new Label();
        label1 = new Label();
        label2 = new Label();
        visitTryCatchBlock(label0, label1, label2, null);
    }

    @Override
    protected void onMethodEnter() {
        /*
        Profiler.pushMethod("profileMe",System.currentTimeMillis(),"java.lang.String,java.lang.String");
         */

        //System.out.println(this.className + ":" + super.getName());

        label3 = new Label();
        visitLabel(label3);

        visitLdcInsn(getName());
        visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
        visitLdcInsn("java.lang.String,java.lang.String");
        visitMethodInsn(INVOKESTATIC, "com/trendcore/Profiler", "pushMethod", "(Ljava/lang/String;JLjava/lang/String;)V", false);

    }

    @Override
    protected void onMethodExit(int opcode) {
        visitLabel(label1);
        //methodVisitor.visitLineNumber(14, label1);
        visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
        visitMethodInsn(INVOKESTATIC, "com/trendcore/Profiler", "popMethod", "(J)V", false);

        Label label4 = new Label();
        visitLabel(label4);
        //methodVisitor.visitLineNumber(15, label4);
        Label label5 = new Label();
        visitJumpInsn(GOTO, label5);
        visitLabel(label2);
        //methodVisitor.visitLineNumber(14, label2);
        visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[] {"java/lang/Throwable"});
        visitVarInsn(ASTORE, 3);
        visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
        visitMethodInsn(INVOKESTATIC, "com/trendcore/Profiler", "popMethod", "(J)V", false);
        Label label6 = new Label();
        visitLabel(label6);
        //methodVisitor.visitLineNumber(15, label6);
        visitVarInsn(ALOAD, 3);
        visitInsn(ATHROW);
        visitLabel(label5);
        //mv.visitLineNumber(17, label5);
        visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        visitInsn(RETURN);

        /*Label label7 = new Label();
        visitLabel(label7);
        visitLocalVariable("this", "Lcom/trendcore/TryFinallyBlockASM;", null, label3, label7, 0);*/
    }


}
