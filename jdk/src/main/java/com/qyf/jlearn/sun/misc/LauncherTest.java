package com.qyf.jlearn.sun.misc;

import com.qyf.jlearn.object.clone.A;
import sun.misc.Launcher;

import java.net.URL;

/**
 * 类描述：
 * Launcher作为JAVA应用的入口，根据双亲委派模型，Laucher是由JVM创建的，它类加载器应该是BootStrapClassLoader，
 * 这是一个C++编写的类加载器，是java应用体系中最顶层的类加载器，负责加载JVM需要的一些类库(<JAVA_HOME>/lib)。
 * <p>
 * Launcher在创建的时候，第一件事情就是获取ExtClassLoader, ExtClassLoader在JVM中是一个单例， 创建过程也是通过获取环境变量来获取ext加载的目录，生成一个ExtClassLoader，ExtClassLoader是URLClassLoader的子类。
 * <p>
 * 在获取ExtClassLoader之后，以此作为父类加载器创建一个 sun.misc.Launcher.AppClassLoader, AppClassLoader的加载路径是java.class.path标记的路径，相同的，AppClassLoader也是URLClassLoader的子类。最终会将当前线程的上下文类加载器设置为AppClassLoader。
 * 通过上述分析，当我们需要获取当前应用程序的AppClassLoader或者ExtClassLoader的时候，可以直接使用Launcher来访问。
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/7/16 18:02
 */
public class LauncherTest {
    public static void main(String[] args) {
        // 这里的classLoader是null，说明Launcher确实是BootstrapClassLoader加载的
        ClassLoader classLoader = Launcher.class.getClassLoader();
        System.out.println(classLoader);

        ClassLoader appClassLoader = Launcher.getLauncher().getClassLoader();
        ClassLoader extClassLoader = appClassLoader.getParent();

        System.out.println(appClassLoader);
        System.out.println(extClassLoader);

        final URL url = Launcher.class.getResource("/com/qyf/jlearn/sun/misc/LauncherTest.class");
        System.out.println(url);
        // file:/E:/workspace/github/jLearn/jdk/target/classes/com/qyf/jlearn/sun/misc/LauncherTest.class

        final URL url2 = A.class.getResource("/com/qyf/jlearn/sun/misc/LauncherTest.class");
        System.out.println(url2);
    }
}
