package com.trendcore.asm;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TryCatchMethodAdviceVisitor extends AdviceAdapter {

    public static final String PROFILER_CLASS_NAME = "com/trendcore/Profiler";
    public static final String PROFILE_ANNOTATION_CLASS_NAME = "com/trendcore/Profile";

    private final Label tryLabel = new Label();
    private final Label catchLabel = new Label();
    private final String className;
    private ProfilerAnnotationFieldVisitor profilerAnnotationFieldVisitor;

    protected TryCatchMethodAdviceVisitor(String className, int api, MethodVisitor methodVisitor, int access, String name, String descriptor) {
        super(api, methodVisitor, access, name, descriptor);
        this.className = className;
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        //System.out.println(className + " visitAnnotation: desc="+desc+" visible="+visible);
        AnnotationVisitor annotationVisitor = super.visitAnnotation(desc, visible);
        String profileAnnotationDescriptor = "L" + PROFILE_ANNOTATION_CLASS_NAME + ";";
        if(visible && profileAnnotationDescriptor.equals(desc)) {
            profilerAnnotationFieldVisitor = new ProfilerAnnotationFieldVisitor(api, annotationVisitor);
            return profilerAnnotationFieldVisitor;
        }

        return annotationVisitor;
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

        if(profilerAnnotationFieldVisitor != null) {
            invokeWithArgumentProfilerMethod();
        } else {
            invokeDefaultProfilerMethod();
        }
    }

    private void invokeDefaultProfilerMethod() {
        String args = getArguments();

        visitLdcInsn(className);
        visitLdcInsn(getName());
        visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
        visitLdcInsn(args);
        visitMethodInsn(INVOKESTATIC, PROFILER_CLASS_NAME, "pushMethod", "(Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;)V", false);
    }

    private String getArguments() {
        return Arrays.stream(getArgumentTypes()).map(type -> type.toString()).collect(Collectors.joining(","));
    }

    /*
    https://github.com/codehaus/groovy-git/blob/7f940159920d4ea5bc727cfcbef8aba9b48c5e50/src/main/org/codehaus/groovy/runtime/ProxyGeneratorAdapter.java#L707
     */
    private void invokeWithArgumentProfilerMethod(){

        //First visit these parameter
        visitLdcInsn(className);
        visitLdcInsn(getName());
        visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
        visitLdcInsn(getArguments());
        visitLdcInsn(profilerAnnotationFieldVisitor.getCategory());

        /**
         * TODO
         */
        //Type[] args = Type.getArgumentTypes(desc);
        Type[] args = getArgumentTypes();
        BytecodeHelper.pushConstant(mv, args.length);
        visitTypeInsn(ANEWARRAY, "java/lang/Object");
        int size = 6;
        int idx = 1;
        for (int i = 0; i < args.length; i++) {
            Type arg = args[i];
            visitInsn(DUP);
            BytecodeHelper.pushConstant(mv, i);
            // primitive types must be boxed
            if (isPrimitive(arg)) {
                visitIntInsn(getLoadInsn(arg), idx);
                String wrappedType = getWrappedClassDescriptor(arg);
                visitMethodInsn(INVOKESTATIC, wrappedType, "valueOf", "(" + arg.getDescriptor() + ")L" + wrappedType + ";", false);
            } else {
                visitVarInsn(ALOAD, idx); // load argument i
            }
            size = Math.max(size, 5+registerLen(arg));
            idx += registerLen(arg);
            visitInsn(AASTORE); // store value into array
        }

        visitMethodInsn(INVOKESTATIC, PROFILER_CLASS_NAME,
                "pushMethod",
                "(Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V", false);
    }

    private String getWrappedClassDescriptor(Type type) {
        if (type == Type.BOOLEAN_TYPE) return "java/lang/Boolean";
        if (type == Type.BYTE_TYPE) return "java/lang/Byte";
        if (type == Type.CHAR_TYPE) return "java/lang/Character";
        if (type == Type.DOUBLE_TYPE) return "java/lang/Double";
        if (type == Type.FLOAT_TYPE) return "java/lang/Float";
        if (type == Type.INT_TYPE) return "java/lang/Integer";
        if (type == Type.LONG_TYPE) return "java/lang/Long";
        if (type == Type.SHORT_TYPE) return "java/lang/Short";
        throw new IllegalArgumentException("Unexpected type class [" + type + "]");
    }

    private static int getLoadInsn(final Type type) {
        if (type == Type.BOOLEAN_TYPE) return ILOAD;
        if (type == Type.BYTE_TYPE) return ILOAD;
        if (type == Type.CHAR_TYPE) return ILOAD;
        if (type == Type.DOUBLE_TYPE) return DLOAD;
        if (type == Type.FLOAT_TYPE) return FLOAD;
        if (type == Type.INT_TYPE) return ILOAD;
        if (type == Type.LONG_TYPE) return LLOAD;
        if (type == Type.SHORT_TYPE) return ILOAD;
        return ALOAD;
    }

    private boolean isPrimitive(final Type arg) {
        return arg == Type.BOOLEAN_TYPE
                || arg == Type.BYTE_TYPE
                || arg == Type.CHAR_TYPE
                || arg == Type.DOUBLE_TYPE
                || arg == Type.FLOAT_TYPE
                || arg == Type.INT_TYPE
                || arg == Type.LONG_TYPE
                || arg == Type.SHORT_TYPE;
    }

    private int registerLen(Type[] args) {
        int i = 0;
        for (Type arg : args) {
            i += registerLen(arg);
        }
        return i;
    }

    private int registerLen(final Type arg) {
        return arg== Type.DOUBLE_TYPE||arg==Type.LONG_TYPE?2:1;
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
