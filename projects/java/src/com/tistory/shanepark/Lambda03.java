package com.tistory.shanepark;

@FunctionalInterface
interface MyFunction {
    void run();
}

public class Lambda03 {
    static void execute(MyFunction func) {
        func.run();
    }

    static MyFunction getMyFunction() {
        MyFunction func = () -> System.out.println("func3.run()");
        return func;
    }

    public static void main(String[] args) {
        MyFunction func1 = () -> System.out.println("func1.run()");

        MyFunction func2 = new MyFunction() {

            @Override
            public void run() {
                System.out.println("func2.run()");
            }
        };

        MyFunction func3 = getMyFunction();

        func1.run();
        func2.run();
        func3.run();

        execute(func1);
        execute(() -> System.out.println("run()"));
    }

}
