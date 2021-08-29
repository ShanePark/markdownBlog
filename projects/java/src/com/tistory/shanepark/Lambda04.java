package com.tistory.shanepark;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
interface MyFunction2{
    void myMethod();
}
class Outer{
    int val = 10;

    class Inner{
        int val = 20;

        void method(int i){
            int val = 30;
//            i = 10;

            MyFunction2 f = ()->{
                System.out.printf("i : %d%n", i);
                System.out.printf("val : %d%n", val);
                System.out.printf("this.val : %d%n", ++this.val);
                System.out.printf("Outer.this.val : %d%n", ++Outer.this.val);
            };
            f.myMethod();
        }
    }
}

public class Lambda04 {

    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.method(100);
    }

}
