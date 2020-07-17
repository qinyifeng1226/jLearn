package com.qyf.jlearn.lang;

/**
 * 类描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/7/17 17:11
 */
public class ClassLoaderTest {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader loader = ClassLoaderTest.class.getClassLoader();

        // sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(loader.toString());
        // sun.misc.Launcher$ExtClassLoader@15975490
        System.out.println(loader.getParent().toString());
        // null
        System.out.println(loader.getParent().getParent());

        Class<?> classLoaderTest= Class.forName("com.qyf.jlearn.lang.ClassLoaderTest");
        ClassLoaderTest clt= (ClassLoaderTest) classLoaderTest.newInstance();
        clt.setId(11);

        Class<?> classLoaderTest2= loader.loadClass("com.qyf.jlearn.lang.ClassLoaderTest");
        ClassLoaderTest clt2= (ClassLoaderTest) classLoaderTest2.newInstance();
        clt2.setId(22);

        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));


    }
}
