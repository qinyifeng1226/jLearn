package com.qyf.jlearn.third.asm;

import org.apache.commons.io.FileUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.IOException;
import static org.objectweb.asm.ClassWriter.COMPUTE_MAXS;

/**
 * 类描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/7/15 18:27
 */
public class AsmTest {
    public static void main(String[] args)
            throws IOException {
        ClassReader classReader = new ClassReader("com/qyf/jlearn/third/asm/Hello");
        ClassWriter classWriter = new ClassWriter(COMPUTE_MAXS);
        AddClassVisitor metricClassVisitor = new AddClassVisitor(classWriter);
        classReader.accept(metricClassVisitor, ClassReader.SKIP_FRAMES);
        byte[] bytes = classWriter.toByteArray();
        File file = new File("Hello.class");
        file.createNewFile();
        FileUtils.writeByteArrayToFile(file, bytes);
    }
}
