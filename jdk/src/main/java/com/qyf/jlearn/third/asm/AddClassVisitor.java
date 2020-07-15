package com.qyf.jlearn.third.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.springframework.asm.Opcodes.ASM5;

/**
 * 类描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/7/15 18:26
 */
public class AddClassVisitor extends ClassVisitor {

    public AddClassVisitor(final ClassVisitor cv) {
        super(ASM5, cv);
    }

    @Override
    public MethodVisitor visitMethod(final int access, final String name, final String desc,
                                     final String signature,
                                     final String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        if (!name.equals("<init>")) {
            return new AddMethodVisitor(mv, access, name, desc);
        }
        return mv;
    }
}
