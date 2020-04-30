package com.trendcore.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ClassAdapter extends ClassVisitor implements Opcodes {

    private String className;

    public ClassAdapter(int api, ClassVisitor cv, String className) {
        super(api, cv);
        this.className = className;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);

        if (AsmUtil.isSpecial(name)) {
            return mv;
        }

        return mv == null ? null : new TryCatchMethodAdviceVisitor(className,api,mv,access,name,desc);
    }
}
