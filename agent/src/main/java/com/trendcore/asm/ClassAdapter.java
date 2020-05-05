package com.trendcore.asm;

import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.lang.reflect.Modifier;

public class ClassAdapter extends ClassVisitor implements Opcodes {

    private String className;
    private boolean isInterface;
    private boolean isAbstract;
    private boolean isEnum;

    static final int ENUM      = 0x00004000;

    public ClassAdapter(int api, ClassVisitor cv, String className) {
        super(api, cv);
        this.className = className;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        if(Modifier.isInterface(access)) {
            isInterface = true;
        }

        if(Modifier.isAbstract(access)) {
            isAbstract = true;
        }

        if((access & ENUM) != 0) {
            isEnum = true;
        }
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);

        if (AsmUtil.isSpecial(name)) {
            return mv;
        } else if(isInterface) {
            System.out.println(className + "." + name + "-> interface");
            return mv;
        } else if(isEnum) {
            System.out.println(className + "." + name + "-> enum");
            return mv;
        } else if(Modifier.isAbstract(access) || Modifier.isNative(access)) {
            System.out.println(className + "." + name + "-> method is abstract");
            return mv;
        }

        return mv == null ? null : new TryCatchMethodAdviceVisitor(className,api,mv,access,name,desc);
    }
}
