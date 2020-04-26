package com.trendcore.asm;


import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

public class MethodAdapter extends MethodVisitor implements Opcodes {

    private String className;

    public MethodAdapter(String className, final MethodVisitor mv) {
        super(ASM5, mv);
        this.className = className;
    }



    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        /*
        Profiler.pushMethod("profileMe",System.currentTimeMillis(),"java.lang.String,java.lang.String");
         */
        if(className.equals(owner)) {

            System.out.println(className + ":" + owner + " : " + name);

            mv.visitLdcInsn(name);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
            mv.visitLdcInsn("java.lang.String,java.lang.String");
            mv.visitMethodInsn(INVOKESTATIC, "com/trendcore/Profiler", "pushMethod", "(Ljava/lang/String;JLjava/lang/String;)V", false);
        }

        /* do call */
        mv.visitMethodInsn(opcode, owner, name, desc, itf);

        /*
        Profiler.popMethod(System.currentTimeMillis());
        */
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
        mv.visitMethodInsn(INVOKESTATIC, "com/trendcore/Profiler", "popMethod", "(J)V", false);

    }

}
