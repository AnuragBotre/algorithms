package com.trendcore.asm;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

public class MethodAdviceVisitor extends AdviceAdapter {

    private String className;
    private String name;

    private Label startFinally = new Label();

    protected MethodAdviceVisitor(String className, int api, MethodVisitor methodVisitor, int access, String name, String descriptor) {
        super(api, methodVisitor, access, name, descriptor);
        this.name = name;
        this.className = className;
    }


    @Override
    protected void onMethodEnter() {
        /*
        Profiler.pushMethod("profileMe",System.currentTimeMillis(),"java.lang.String,java.lang.String");
         */

        System.out.println(this.className + ":" + super.getName());
        visitLdcInsn(name);
        visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
        visitLdcInsn("java.lang.String,java.lang.String");
        visitMethodInsn(INVOKESTATIC, "com/trendcore/Profiler", "pushMethod", "(Ljava/lang/String;JLjava/lang/String;)V", false);

    }

    @Override
    protected void onMethodExit(int opcode) {
        onFinally(opcode);
    }


    private void onFinally(int opcode) {
        visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
        visitMethodInsn(INVOKESTATIC, "com/trendcore/Profiler", "popMethod", "(J)V", false);
    }
}
