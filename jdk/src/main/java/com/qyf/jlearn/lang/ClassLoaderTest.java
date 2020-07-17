package com.qyf.jlearn.lang;

/**
 * 类描述：
 *
 * 如果父加载器不为空，那么调用父加载器的loadClass方法加载类，如果父加载器为空，那么调用虚拟机的加载器来加载类。
 * 如果以上两个步骤都没有成功的加载到类，那么调用自己的findClass(name)方法来加载类。
 *
 * 类装载器ClassLoader（一个抽象类）描述一下JVM加载class文件的原理机制
 *
 * 类装载器就是寻找类或接口字节码文件进行解析并构造JVM内部对象表示的组件，在java中类装载器把一个类装入JVM，经过以下步骤：
 *
 * 1、装载：查找和导入Class文件
 * 2、链接：其中解析步骤是可以选择的
 * （a）检查：检查载入的class文件数据的正确性
 * （b）准备：给类的静态变量分配存储空间
 * （c）解析：将符号引用转成直接引用
 * 3、初始化：对静态变量，静态代码块执行初始化工作
 *
 * 类装载工作由ClassLoder和其子类负责。JVM在运行时会产生三个ClassLoader：根装载器，ExtClassLoader(扩展类装载器)和AppClassLoader(应用类加载器)。
 *
 * 其中根装载器不是ClassLoader的子类，由C++编写，因此在java中看不到他，负责装载JRE的核心类库，如JRE目录下的rt.jar,charsets.jar等。
 *
 * ExtClassLoader是ClassLoder的子类，负责装载JRE扩展目录ext下的jar类包。
 *
 * AppClassLoader负责装载classpath路径下的类包。
 *
 * 这三个类装载器存在父子层级关系，即根装载器是ExtClassLoader的父装载器，ExtClassLoader是AppClassLoader的父装载器。默认情况下使用AppClassLoader装载应用程序的类。
 *
 * Java装载类使用“全盘负责委托机制”。“全盘负责”是指当一个ClassLoder装载一个类时，除非显示的使用另外一个ClassLoder，该类所依赖及引用的类也由这个ClassLoder载入；“委托机制”是指先委托父类装载器寻找目标类，只有在找不到的情况下才从自己的类路径中查找并装载目标类。这一点是从安全方面考虑的，试想如果一个人写了一个恶意的基础类（如java.lang.String）并加载到JVM将会引起严重的后果，但有了全盘负责制，java.lang.String永远是由根装载器来装载，避免以上情况发生 除了JVM默认的三个ClassLoder以外，第三方可以编写自己的类装载器，以实现一些特殊的需求。类文件被装载解析后，在JVM中都有一个对应的java.lang.Class对象，提供了类结构信息的描述。数组，枚举及基本数据类型，甚至void都拥有对应的Class对象。Class类没有public的构造方法，Class对象是在装载类时由JVM通过调用类装载器中的defineClass()方法自动构造的。
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
