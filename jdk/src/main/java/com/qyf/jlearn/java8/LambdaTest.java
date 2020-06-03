package com.qyf.jlearn.java8;

/**
 * 类描述：
 * <p>
 * lambda 表达式的语法格式如下：
 * (parameters) -> expression
 * 或
 * (parameters) ->{ statements; }
 * 以下是lambda表达式的重要特征:
 * <p>
 * 可选类型声明：不需要声明参数类型，编译器可以统一识别参数值。
 * 可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号。
 * 可选的大括号：如果主体包含了一个语句，就不需要使用大括号。
 * 可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值。
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/6/3 11:20
 */
public class LambdaTest {

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    public interface Converter<T1, T2> {
        void convert(int i);
    }

    public static void main(String args[]) {
        LambdaTest tester = new LambdaTest();

        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;

        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;

        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };

        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;

        MathOperation mathOperation = new MathOperation() {
            @Override
            public int operation(int a, int b) {
                return a + b;

            }
        };
        MathOperation mathOperation2 = new MathOperation() {
            @Override
            public int operation(int a, int b) {
                return a - b;

            }
        };

        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operate(10, 5, division));

        System.out.println("10 + 5 = " + tester.operate(10, 4, mathOperation));
        System.out.println("10 - 5 = " + tester.operate(10, 4, mathOperation2));

        // 不用括号
        GreetingService greetService1 = message ->
                System.out.println("Hello " + message);

        // 用括号
        GreetingService greetService2 = (message) ->
                System.out.println("Hello " + message);

        greetService1.sayMessage("Runoob");
        greetService2.sayMessage("Google");

        final int num = 1;
        Converter<Integer, String> s = (param) -> System.out.println(param + num);
        // 输出结果为 3
        s.convert(2);
    }

}
