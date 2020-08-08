package com.trendcore.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.lang.reflect.Modifier;

public class JavaClassAdapter extends ClassVisitor implements Opcodes {

    private final String className;

    public JavaClassAdapter(int api, ClassVisitor cv, String className) {
        super(api, cv);
        this.className = className;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);

        if(name.equalsIgnoreCase("run")) {
            return new TryCatchMethodAdviceVisitor(className,api,mv,access,name,desc);
        }

        return mv;
    }
}
