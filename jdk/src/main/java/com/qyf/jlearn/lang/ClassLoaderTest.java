package com.qyf.jlearn.lang;

/**
 * 类描述：
 * <p>
 * 如果父加载器不为空，那么调用父加载器的loadClass方法加载类，如果父加载器为空，那么调用虚拟机的加载器来加载类。
 * 如果以上两个步骤都没有成功的加载到类，那么调用自己的findClass(name)方法来加载类。
 * <p>
 * 类装载器ClassLoader（一个抽象类）描述一下JVM加载class文件的原理机制
 * <p>
 * 类装载器就是寻找类或接口字节码文件进行解析并构造JVM内部对象表示的组件，在java中类装载器把一个类装入JVM，经过以下步骤：
 * <p>
 * 1、装载：查找和导入Class文件
 * 2、链接：其中解析步骤是可以选择的
 * （a）检查：检查载入的class文件数据的正确性
 * （b）准备：给类的静态变量分配存储空间
 * （c）解析：将符号引用转成直接引用
 * 3、初始化：对静态变量，静态代码块执行初始化工作
 * <p>
 * 类装载工作由ClassLoder和其子类负责。JVM在运行时会产生三个ClassLoader：根装载器，ExtClassLoader(扩展类装载器)和AppClassLoader(应用类加载器)。
 * <p>
 * 其中根装载器不是ClassLoader的子类，由C++编写，因此在java中看不到他，负责装载JRE的核心类库，如JRE目录下的rt.jar,charsets.jar等。
 * <p>
 * ExtClassLoader是ClassLoder的子类，负责装载JRE扩展目录ext下的jar类包。
 * <p>
 * AppClassLoader负责装载classpath路径下的类包。
 * <p>
 * 这三个类装载器存在父子层级关系，即根装载器是ExtClassLoader的父装载器，ExtClassLoader是AppClassLoader的父装载器。默认情况下使用AppClassLoader装载应用程序的类。
 * <p>
 * Java装载类使用“全盘负责委托机制”。“全盘负责”是指当一个ClassLoder装载一个类时，除非显示的使用另外一个ClassLoder，该类所依赖及引用的类也由这个ClassLoder载入；“委托机制”是指先委托父类装载器寻找目标类，只有在找不到的情况下才从自己的类路径中查找并装载目标类。这一点是从安全方面考虑的，试想如果一个人写了一个恶意的基础类（如java.lang.String）并加载到JVM将会引起严重的后果，但有了全盘负责制，java.lang.String永远是由根装载器来装载，避免以上情况发生 除了JVM默认的三个ClassLoder以外，第三方可以编写自己的类装载器，以实现一些特殊的需求。类文件被装载解析后，在JVM中都有一个对应的java.lang.Class对象，提供了类结构信息的描述。数组，枚举及基本数据类型，甚至void都拥有对应的Class对象。Class类没有public的构造方法，Class对象是在装载类时由JVM通过调用类装载器中的defineClass()方法自动构造的。
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/7/17 17:11
 */
public class ClassLoaderTest {
    private static int sid = 100;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    {
        System.out.println("普通代码块");
    }

    static {
        System.out.println("静态代码块sid：" + sid);
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader loader = ClassLoaderTest.class.getClassLoader();

        // sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(loader.toString());
        // sun.misc.Launcher$ExtClassLoader@15975490
        System.out.println(loader.getParent().toString());
        // null
        System.out.println(loader.getParent().getParent());

        // class.forName()前者除了将类的.class文件加载到jvm中之外，还会对类进行解释，执行类中的static块，当然还可以指定是否执行静态块。
        Class<?> classLoaderTest = Class.forName("com.qyf.jlearn.lang.ClassLoaderTest");
        System.out.println(classLoaderTest.getName());

        // classLoader只干一件事情，就是将.class文件加载到jvm中，不会执行static中的内容,只有在newInstance才会去执行static块。
        Class<?> classLoaderTest2 = loader.loadClass("com.qyf.jlearn.lang.ClassLoaderTest");
        ClassLoaderTest clt2 = (ClassLoaderTest) classLoaderTest2.newInstance();
        clt2.setId(22);

        System.out.println(System.getProperty("sun.boot.class.path"));
        // E:\Program Files\Java\jdk1.8.0_201\jre\lib\resources.jar;E:\Program Files\Java\jdk1.8.0_201\jre\lib\rt.jar;E:\Program Files\Java\jdk1.8.0_201\jre\lib\sunrsasign.jar;E:\Program Files\Java\jdk1.8.0_201\jre\lib\jsse.jar;E:\Program Files\Java\jdk1.8.0_201\jre\lib\jce.jar;E:\Program Files\Java\jdk1.8.0_201\jre\lib\charsets.jar;E:\Program Files\Java\jdk1.8.0_201\jre\lib\jfr.jar;E:\Program Files\Java\jdk1.8.0_201\jre\classes

        System.out.println(System.getProperty("java.ext.dirs"));
        // E:\Program Files\Java\jdk1.8.0_201\jre\lib\ext;C:\WINDOWS\Sun\Java\lib\ext

        // 包含pom依赖第三方jar
        System.out.println(System.getProperty("java.class.path"));
        // E:\Program Files\Java\jdk1.8.0_201\jre\lib\charsets.jar;E:\Program Files\Java\jdk1.8.0_201\jre\lib\deploy.jar;E:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\access-bridge-64.jar;E:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\cldrdata.jar;E:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\dnsns.jar;E:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\jaccess.jar;E:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\jfxrt.jar;E:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\localedata.jar;E:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\nashorn.jar;E:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\sunec.jar;E:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\sunjce_provider.jar;E:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\sunmscapi.jar;E:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\sunpkcs11.jar;E:\Program Files\Java\jdk1.8.0_201\jre\lib\ext\zipfs.jar;E:\Program Files\Java\jdk1.8.0_201\jre\lib\javaws.jar;E:\Program Files\Java\jdk1.8.0_201\jre\lib\jce.jar;E:\Program Files\Java\jdk1.8.0_201\jre\lib\jfr.jar;E:\Program Files\Java\jdk1.8.0_201\jre\lib\jfxswt.jar;E:\Program Files\Java\jdk1.8.0_201\jre\lib\jsse.jar;E:\Program Files\Java\jdk1.8.0_201\jre\lib\management-agent.jar;E:\Program Files\Java\jdk1.8.0_201\jre\lib\plugin.jar;E:\Program Files\Java\jdk1.8.0_201\jre\lib\resources.jar;E:\Program Files\Java\jdk1.8.0_201\jre\lib\rt.jar;E:\workspace\github\jLearn\jdk\target\classes;E:\maven\repository\org\springframework\boot\spring-boot-starter-web\1.5.3.RELEASE\spring-boot-starter-web-1.5.3.RELEASE.jar;E:\maven\repository\org\springframework\boot\spring-boot-starter\1.5.3.RELEASE\spring-boot-starter-1.5.3.RELEASE.jar;E:\maven\repository\org\springframework\boot\spring-boot-starter-logging\1.5.3.RELEASE\spring-boot-starter-logging-1.5.3.RELEASE.jar;E:\maven\repository\ch\qos\logback\logback-classic\1.1.11\logback-classic-1.1.11.jar;E:\maven\repository\ch\qos\logback\logback-core\1.1.11\logback-core-1.1.11.jar;E:\maven\repository\org\slf4j\jcl-over-slf4j\1.7.25\jcl-over-slf4j-1.7.25.jar;E:\maven\repository\org\slf4j\jul-to-slf4j\1.7.25\jul-to-slf4j-1.7.25.jar;E:\maven\repository\org\slf4j\log4j-over-slf4j\1.7.25\log4j-over-slf4j-1.7.25.jar;E:\maven\repository\org\yaml\snakeyaml\1.17\snakeyaml-1.17.jar;E:\maven\repository\org\springframework\boot\spring-boot-starter-tomcat\1.5.3.RELEASE\spring-boot-starter-tomcat-1.5.3.RELEASE.jar;E:\maven\repository\org\apache\tomcat\embed\tomcat-embed-core\8.5.14\tomcat-embed-core-8.5.14.jar;E:\maven\repository\org\apache\tomcat\embed\tomcat-embed-websocket\8.5.14\tomcat-embed-websocket-8.5.14.jar;E:\maven\repository\org\hibernate\hibernate-validator\5.3.5.Final\hibernate-validator-5.3.5.Final.jar;E:\maven\repository\javax\validation\validation-api\1.1.0.Final\validation-api-1.1.0.Final.jar;E:\maven\repository\org\jboss\logging\jboss-logging\3.3.1.Final\jboss-logging-3.3.1.Final.jar;E:\maven\repository\com\fasterxml\jackson\core\jackson-databind\2.8.8\jackson-databind-2.8.8.jar;E:\maven\repository\com\fasterxml\jackson\core\jackson-annotations\2.8.0\jackson-annotations-2.8.0.jar;E:\maven\repository\com\fasterxml\jackson\core\jackson-core\2.8.8\jackson-core-2.8.8.jar;E:\maven\repository\org\springframework\spring-web\4.3.8.RELEASE\spring-web-4.3.8.RELEASE.jar;E:\maven\repository\org\springframework\spring-beans\4.3.8.RELEASE\spring-beans-4.3.8.RELEASE.jar;E:\maven\repository\org\springframework\spring-context\4.3.8.RELEASE\spring-context-4.3.8.RELEASE.jar;E:\maven\repository\org\springframework\spring-webmvc\4.3.8.RELEASE\spring-webmvc-4.3.8.RELEASE.jar;E:\maven\repository\org\springframework\spring-expression\4.3.8.RELEASE\spring-expression-4.3.8.RELEASE.jar;E:\maven\repository\org\springframework\boot\spring-boot-starter-actuator\1.5.3.RELEASE\spring-boot-starter-actuator-1.5.3.RELEASE.jar;E:\maven\repository\org\springframework\boot\spring-boot-actuator\1.5.3.RELEASE\spring-boot-actuator-1.5.3.RELEASE.jar;E:\maven\repository\org\springframework\boot\spring-boot-starter-validation\1.5.3.RELEASE\spring-boot-starter-validation-1.5.3.RELEASE.jar;E:\maven\repository\org\apache\tomcat\embed\tomcat-embed-el\8.5.14\tomcat-embed-el-8.5.14.jar;E:\maven\repository\org\mybatis\spring\boot\mybatis-spring-boot-starter\1.3.2\mybatis-spring-boot-starter-1.3.2.jar;E:\maven\repository\org\springframework\boot\spring-boot-starter-jdbc\1.5.3.RELEASE\spring-boot-starter-jdbc-1.5.3.RELEASE.jar;E:\maven\repository\org\apache\tomcat\tomcat-jdbc\8.5.14\tomcat-jdbc-8.5.14.jar;E:\maven\repository\org\apache\tomcat\tomcat-juli\8.5.14\tomcat-juli-8.5.14.jar;E:\maven\repository\org\springframework\spring-jdbc\4.3.8.RELEASE\spring-jdbc-4.3.8.RELEASE.jar;E:\maven\repository\org\mybatis\spring\boot\mybatis-spring-boot-autoconfigure\1.3.2\mybatis-spring-boot-autoconfigure-1.3.2.jar;E:\maven\repository\org\mybatis\mybatis\3.4.6\mybatis-3.4.6.jar;E:\maven\repository\org\mybatis\mybatis-spring\1.3.2\mybatis-spring-1.3.2.jar;E:\maven\repository\org\springframework\boot\spring-boot-starter-aop\1.5.3.RELEASE\spring-boot-starter-aop-1.5.3.RELEASE.jar;E:\maven\repository\org\springframework\spring-aop\4.3.8.RELEASE\spring-aop-4.3.8.RELEASE.jar;E:\maven\repository\org\aspectj\aspectjweaver\1.8.10\aspectjweaver-1.8.10.jar;E:\maven\repository\org\springframework\boot\spring-boot-autoconfigure\1.5.3.RELEASE\spring-boot-autoconfigure-1.5.3.RELEASE.jar;E:\maven\repository\org\springframework\boot\spring-boot\1.5.3.RELEASE\spring-boot-1.5.3.RELEASE.jar;E:\maven\repository\org\springframework\boot\spring-boot-starter-security\1.5.3.RELEASE\spring-boot-starter-security-1.5.3.RELEASE.jar;E:\maven\repository\org\springframework\security\spring-security-config\4.2.2.RELEASE\spring-security-config-4.2.2.RELEASE.jar;E:\maven\repository\org\springframework\security\spring-security-core\4.2.2.RELEASE\spring-security-core-4.2.2.RELEASE.jar;E:\maven\repository\org\springframework\security\spring-security-web\4.2.2.RELEASE\spring-security-web-4.2.2.RELEASE.jar;E:\maven\repository\org\springframework\security\spring-security-jwt\1.0.9.RELEASE\spring-security-jwt-1.0.9.RELEASE.jar;E:\maven\repository\org\bouncycastle\bcpkix-jdk15on\1.56\bcpkix-jdk15on-1.56.jar;E:\maven\repository\org\bouncycastle\bcprov-jdk15on\1.56\bcprov-jdk15on-1.56.jar;E:\maven\repository\org\springframework\boot\spring-boot-starter-quartz\2.1.6.RELEASE\spring-boot-starter-quartz-2.1.6.RELEASE.jar;E:\maven\repository\org\springframework\spring-context-support\4.3.8.RELEASE\spring-context-support-4.3.8.RELEASE.jar;E:\maven\repository\org\springframework\spring-tx\4.3.8.RELEASE\spring-tx-4.3.8.RELEASE.jar;E:\maven\repository\org\quartz-scheduler\quartz\2.3.1\quartz-2.3.1.jar;E:\maven\repository\com\mchange\mchange-commons-java\0.2.15\mchange-commons-java-0.2.15.jar;E:\maven\repository\io\jsonwebtoken\jjwt\0.9.0\jjwt-0.9.0.jar;E:\maven\repository\io\springfox\springfox-swagger2\2.8.0\springfox-swagger2-2.8.0.jar;E:\maven\repository\io\swagger\swagger-annotations\1.5.14\swagger-annotations-1.5.14.jar;E:\maven\repository\io\swagger\swagger-models\1.5.14\swagger-models-1.5.14.jar;E:\maven\repository\io\springfox\springfox-spi\2.8.0\springfox-spi-2.8.0.jar;E:\maven\repository\io\springfox\springfox-core\2.8.0\springfox-core-2.8.0.jar;E:\maven\repository\net\bytebuddy\byte-buddy\1.7.9\byte-buddy-1.7.9.jar;E:\maven\repository\io\springfox\springfox-schema\2.8.0\springfox-schema-2.8.0.jar;E:\maven\repository\io\springfox\springfox-swagger-common\2.8.0\springfox-swagger-common-2.8.0.jar;E:\maven\repository\io\springfox\springfox-spring-web\2.8.0\springfox-spring-web-2.8.0.jar;E:\maven\repository\org\reflections\reflections\0.9.11\reflections-0.9.11.jar;E:\maven\repository\org\javassist\javassist\3.21.0-GA\javassist-3.21.0-GA.jar;E:\maven\repository\com\google\guava\guava\20.0\guava-20.0.jar;E:\maven\repository\com\fasterxml\classmate\1.3.3\classmate-1.3.3.jar;E:\maven\repository\org\slf4j\slf4j-api\1.7.25\slf4j-api-1.7.25.jar;E:\maven\repository\org\springframework\plugin\spring-plugin-core\1.2.0.RELEASE\spring-plugin-core-1.2.0.RELEASE.jar;E:\maven\repository\org\springframework\plugin\spring-plugin-metadata\1.2.0.RELEASE\spring-plugin-metadata-1.2.0.RELEASE.jar;E:\maven\repository\org\mapstruct\mapstruct\1.2.0.Final\mapstruct-1.2.0.Final.jar;E:\maven\repository\io\springfox\springfox-swagger-ui\2.8.0\springfox-swagger-ui-2.8.0.jar;E:\maven\repository\io\springfox\springfox-bean-validators\2.8.0\springfox-bean-validators-2.8.0.jar;E:\maven\repository\org\projectlombok\lombok\1.18.2\lombok-1.18.2.jar;E:\maven\repository\commons-codec\commons-codec\1.11\commons-codec-1.11.jar;E:\maven\repository\com\alibaba\druid-spring-boot-starter\1.1.9\druid-spring-boot-starter-1.1.9.jar;E:\maven\repository\com\alibaba\druid\1.1.9\druid-1.1.9.jar;E:\maven\repository\mysql\mysql-connector-java\8.0.16\mysql-connector-java-8.0.16.jar;E:\maven\repository\com\google\protobuf\protobuf-java\3.6.1\protobuf-java-3.6.1.jar;E:\maven\repository\com\baomidou\mybatisplus-spring-boot-starter\1.0.5\mybatisplus-spring-boot-starter-1.0.5.jar;E:\maven\repository\org\springframework\boot\spring-boot-configuration-processor\1.5.3.RELEASE\spring-boot-configuration-processor-1.5.3.RELEASE.jar;E:\maven\repository\com\vaadin\external\google\android-json\0.0.20131108.vaadin1\android-json-0.0.20131108.vaadin1.jar;E:\maven\repository\javax\persistence\persistence-api\1.0\persistence-api-1.0.jar;E:\maven\repository\com\alibaba\fastjson\1.2.72\fastjson-1.2.72.jar;E:\maven\repository\com\baomidou\mybatis-plus\2.1.8\mybatis-plus-2.1.8.jar;E:\maven\repository\com\baomidou\mybatis-plus-support\2.1.8\mybatis-plus-support-2.1.8.jar;E:\maven\repository\com\baomidou\mybatis-plus-core\2.1.8\mybatis-plus-core-2.1.8.jar;E:\maven\repository\com\baomidou\mybatis-plus-generate\2.1.8\mybatis-plus-generate-2.1.8.jar;E:\maven\repository\org\apache\velocity\velocity-engine-core\2.0\velocity-engine-core-2.0.jar;E:\maven\repository\org\apache\commons\commons-lang3\3.5\commons-lang3-3.5.jar;E:\maven\repository\org\springframework\spring-core\4.3.8.RELEASE\spring-core-4.3.8.RELEASE.jar;E:\maven\repository\dom4j\dom4j\1.6.1\dom4j-1.6.1.jar;E:\maven\repository\xml-apis\xml-apis\1.4.01\xml-apis-1.4.01.jar;E:\maven\repository\com\github\pagehelper\pagehelper\4.1.6\pagehelper-4.1.6.jar;E:\maven\repository\com\github\jsqlparser\jsqlparser\0.9.5\jsqlparser-0.9.5.jar;E:\maven\repository\javax\xml\bind\jaxb-api\2.3.0\jaxb-api-2.3.0.jar;E:\maven\repository\com\google\code\findbugs\jsr305\3.0.2\jsr305-3.0.2.jar;E:\maven\repository\org\ow2\asm\asm\5.1\asm-5.1.jar;E:\maven\repository\org\ow2\asm\asm-commons\5.1\asm-commons-5.1.jar;E:\maven\repository\org\ow2\asm\asm-tree\5.1\asm-tree-5.1.jar;E:\maven\repository\commons-io\commons-io\1.3.2\commons-io-1.3.2.jar;E:\Program Files\JetBrains\IntelliJ IDEA 2018.3.5\lib\idea_rt.jar;C:\Users\Administrator\.IntelliJIdea2018.3\system\captureAgent\debugger-agent.jar

    }
}
