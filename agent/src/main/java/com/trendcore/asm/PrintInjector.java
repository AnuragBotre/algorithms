package com.trendcore.asm;

import org.objectweb.asm.*;
import org.objectweb.asm.commons.AdviceAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PrintInjector extends ClassVisitor {

    public PrintInjector(int api, ClassVisitor mv) {
        super(api, mv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access,  name,  desc,  signature,  exceptions);
        System.out.println(String.format("%s %s %s", name, desc, signature));
        if(name.equals("PrintInt"))
            mv = new PrintSingleIntParameter(Opcodes.ASM5, mv, access, name, desc);
        return mv;
    }

    public static void main(String[] args) throws IOException {
        ClassReader cr = new ClassReader(new FileInputStream(new File("bin\\Print.class")));
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        PrintInjector pi = new PrintInjector(Opcodes.ASM5, cw);

        cr.accept(pi,  0);

        FileOutputStream fos = new FileOutputStream(new File("Print.class"));
        fos.write(cw.toByteArray());
        fos.flush();
        fos.close();
    }

    class PrintSingleIntParameter extends AdviceAdapter {

        protected PrintSingleIntParameter(int api, MethodVisitor mv, int access,
                                          String name, String desc) {
            super(api, mv, access, name, desc);
        }

        @Override
        protected void onMethodEnter() {
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(ILOAD, 0); //1 instead of 0 if PrintInt wasn't static
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
        }
    }
}
