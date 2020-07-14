package com.trendcore.agent;

import com.trendcore.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class ClassTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer)
            throws IllegalClassFormatException {

        if (className.contains("com.trendcore")) {

            if(!(className.startsWith("com.trendcore.agent") ||
                    className.startsWith("com.trendcore.asm") ||
                    className.startsWith("com.trendcore.classloader") )) {


                ClassReader cr = new ClassReader(classfileBuffer);
                ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
                ClassAdapter classAdapter = new ClassAdapter(Opcodes.ASM8, cw, className);

                cr.accept(classAdapter, ClassReader.EXPAND_FRAMES);

                return cw.toByteArray();
            }
        }
        return null;
    }
}
