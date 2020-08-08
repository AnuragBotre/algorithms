package com.trendcore.agent;

import com.trendcore.asm.ClassAdapter;
import com.trendcore.asm.JavaClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class JavaClassTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        ClassReader cr = new ClassReader(classfileBuffer);
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        JavaClassAdapter classAdapter = new JavaClassAdapter(Opcodes.ASM8, cw, className);

        cr.accept(classAdapter, ClassReader.EXPAND_FRAMES);

        return cw.toByteArray();
    }
}
