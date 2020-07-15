package com.qyf.jlearn.third.asm;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * 类描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/7/15 18:25
 */
public class AddMethodVisitor extends AdviceAdapter {

    private boolean addAnnotation = false;

    protected AddMethodVisitor(final MethodVisitor mv, final int access, final String name,
                               final String desc) {
        super(ASM5, mv, access, name, desc);
    }

    @Override
    public AnnotationVisitor visitAnnotation(final String desc, final boolean visible) {
        if (visible && desc.equals("Lcom/qyf/jlearn/third/asm/Add;")) {
            addAnnotation = true;
        }
        return super.visitAnnotation(desc, visible);
    }

    @Override
    public void visitCode() {
        super.visitCode();
        if (!addAnnotation) {
            super.visitEnd();
            return;
        }
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitInsn(Opcodes.IADD);
        int newLocal = newLocal(Type.INT_TYPE);
        mv.visitVarInsn(Opcodes.ISTORE, newLocal);
        mv.visitVarInsn(Opcodes.ILOAD, newLocal);
        mv.visitInsn(Opcodes.IRETURN);
    }
}
