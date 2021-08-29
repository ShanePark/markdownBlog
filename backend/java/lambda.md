# 람다식 (Lambda Expression)

​	

Lambda는 2014년 3월 18일에 발표한 java 8에서 추가된 대표적인 기능입니다. 자바에 대한 함수형 언어의 기능 요구가 끊임없이 있어 왔고, 객체지향언어인 자바가 함수형 언어의 기능까지 갖추게 하는 일은 쉽지 않았을 테지만 그 어려운 일을 똑똑한 개발자들이 해준 덕분에 우리는 이 좋은 기능을 사용 할 수 있게 되었습니다.

​	

### 람다식이란?

람다는 간단하게 말해서 메서드를 하나의 식으로 표현 한 것입니다. 람다식은 함수를 간략하면서도 명확한 식으로 포현할 수 있게 해줍니다. 메서드를 람다식으로 표현하면 메서드의 이름과 반환값이 없기 때문에 람다식을 "익명함수" 라고 부릅니다.	

객체지향 언어인 자바에서는 method라는 용어를 사용하는데요, 특정 클래스에 반드시 속해야 한다는 제약이 있기 때문에 함수와는 다른 용어를 사용 해 온 것 입니다. 하지만 람다식을 통해 메서드가 하나의 독립적인 기능을 하게 됨 으로서 함수라는 용어를 사용하게 되었습니다.

​	

아래의 코드는 각각 List를 정렬할 때 Comparator의 compare 메서드를 구현해서 내림차순을 한번 해 보고, 그다음 오른차순으로 정렬 할 때는 람다식으로 진행 해 보았습니다.

```java
    public static void main(String[] args) {
        List<String> list = Arrays.asList("abc", "aaa", "bbb", "ddd", "aaa");
        // 내림차순
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println(list);

        // 오름차순
        Collections.sort(list, (s1,s2) -> s1.compareTo(s2));
        System.out.println(list);
    }
```

​	

![image-20210829191622585](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/lambda.assets/image-20210829191622585.png)

위의 코드에서 그 차이가 명확하게 구분됩니다.  Comparator를 구현하기 위해 코드 몇줄에 걸쳐 썼던 내용을, 아주 간단하게 처리 했습니다.

​	

### 람다식을 다루기 위한 인터페이스를 함수형 인터페이스(functional interface) 라고 합니다.

```java
@FunctionalInterface
```

​	

아래의 예제를 통해 FunctionalInterface를 확인 해 보겠습니다.

```java
package com.tistory.shanepark;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@FunctionalInterface
interface MyFunction{
    void run();
}
public class Lambda03 {
    static void execute(MyFunction func) {
        func.run();
    }

    static MyFunction getMyFunction(){
        MyFunction func = () -> System.out.println("func3.run()");
        return func;
    }

    public static void main(String[] args) {
        MyFunction func1 = () -> System.out.println("func1.run()");

        MyFunction func2 = new MyFunction(){

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
        execute(()-> System.out.println("run()"));
    }

}
```

MyFunction 이라는 FunctionalInterface를 선언 한 뒤에, 여러가지 상황을 만들었습니다.

​	

그리고 그 실행 결과 입니다.

![image-20210829193304166](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/lambda.assets/image-20210829193304166.png)

​		

```java
MyFunction func1 = () -> System.out.println("func1.run()");
```

이렇게 func1을 선언 했을 때와

​	

```java
        MyFunction func2 = new MyFunction(){

            @Override
            public void run() {
                System.out.println("func2.run()");
            }
        };
```

이렇게, 람다식으로 표현해 func2를 선언 했을때 둘 다 똑같이 동작합니다.



```java
    static MyFunction getMyFunction(){
        MyFunction func = () -> System.out.println("func3.run()");
        return func;
    }
```

getMyFunction 이라는 메서드를 선언 해, func라는 함수형 인터페이스를 반환 하도록 구현 한 내용도

​	

```java
MyFunction func3 = getMyFunction();
```

마찬가지로 동일하게 작동 하는 것이 확인 되었습니다.

​		

```java
    static void execute(MyFunction func) {
        func.run();
    }
```

execute 메서드는, 파라미터로 받은 함수형 인터페이스의 run 함수를 호출 하게끔 구현이 되어 있습니다.	

​	

```java
execute(func1);
```

그렇게 때문에 이렇게 호출을 해 준다면 알아서 func1가 가지고 있는 Lambda 함수인 System.out.println("func1.run()"); 가 호출 되는 것 입니다.

​		

```
execute(()-> System.out.println("run()"));
```

또한  함수형 인터페이스를 인자로 받기 때문에, 그자리에 바로 람다식을 사용 할 수도 있는 것 입니다.

​		

### 주의 할 점

함수형 인터페이스로 람다식을 참조할 수 있는 것일 뿐, 람다식의 타입이 함수형 인터페이스의 타입과 일치하는 것은 아닙니다. 람다식은 익명 객체이고, 익명 객체는 타입이 없기 때문 인데요. 정확히 말하자면 타입은 있는데 컴파일러가 임의로 이름을 정하기 때문에 알 수 없는 것 이라고 합니다. 그래서 대입 연산자의 양변의 타입을 일치시키기 위해 형 변환이 필요합니다.

```java
MyFunction f = (MyFunction)({}->{});
```

​	

람다식은 이름이 없지만 "객체" 임에도 불구하고 Object 타입으로 형변환 될 수 는 없습니다. **람다식은 오직 함수형 인터페이스로만** 형변환이 가능합니다.

​	

### 또한 람다식에 사용되는 외부 변수는 변경 할 수 없습니다.

람다식 내에서 참조하는 지역 변수는 final이 붙지 않았어도 상수로 간주 됩니다. 

![image-20210829195559011](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/lambda.assets/image-20210829195559011.png)

람다식 내에서 지역변수 i 를 사용하려고 하는데, 위에서 10으로 변경했기 때문에 에러가 발생하는 것을 확인 할 수 있습니다.

​	

![image-20210829195732933](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/lambda.assets/image-20210829195732933.png)

반면에 이렇게 Inner 클래스의 변수인 this.val 이나 Outer 클래스의 인스턴스 변수인 Outer.this.val 은 상수로 간주되지 않기 때문에 위에 보이는 것 처럼 값을 변경해도 문제가 되지 않습니다.

![image-20210829195814677](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/lambda.assets/image-20210829195814677.png)

​	

### java.util.function 패키지 

![image-20210829195915045](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/lambda.assets/image-20210829195915045.png)

대부분의 메서드들은 타입이 비슷 합니다. Parameter가 없거나 한개 아니면 두개.. return 값은 void로 없거나 혹은 한개. 심지어 지네릭 메서드로 정의한다면 매개변수나 반환 타입이 달라져도 아무런 문제가 되지 않습니다. 그래서 **java.util.function 패키지에 일반적으로 자주 쓰이는 형식의 메서드들을 함수형 인터페이스로** 미리 정의 해 두었는데요.

굳이 매번 새로운 함수형 인터페이스를 정의 할 필요 없이 이 패키지의 인터페이스를 활용 하기만 해도 대부분의 상황은 처리 해낼 수 있습니다.

​		

하나씩 둘러 보겠습니다.

| @FunctionalInterface | Method            | Descriptions                                                |
| :------------------- | ----------------- | ----------------------------------------------------------- |
| java.lang.Runnable   | void run()        | 매개변수도 없고, 반환값도 없음.                             |
| Supplier<T>          | T get()           | 매개변수는 없고, 반환값만 있음.                             |
| Consumer<T>          | void accept(T t)  | Supplier와 반대로 매개변수만 있고 반환값이 없음.            |
| Function<T,R>        | R apply(T t)      | 하나의 매개변수를 받아 결과를 반환하는 일반적인 함수.       |
| Predicatr<T>         | boolean test(T t) | 조건식을 표현하는 사용됨. 매개변수 하나, 반환 타입 boolean. |

​	

@FunctionalInterface	

### java.util.function.Function

> 하나의 매개변수를 받아 결과를 반환하는 일반적인 함수. 

```java
package com.tistory.shanepark;

import java.util.function.Function;

public class Lambda05 {

    public static void main(String[] args) {
        Function<String, Integer> f1 = s -> s.length();
        int result = f1.apply("hello");
        System.out.println(result);
    }

}
```

f1을 Function 이라는 인터페이스로 구현 했습니다. String 을 Parameter로 받아 Integer 타입의 값을 반환 하는데요, 그것을 이용해서 특정 문자열의 길이를 반환하는 함수를 작성 해 보았습니다.

![image-20210829201230189](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/lambda.assets/image-20210829201230189.png)

정확한 결과값인 5가 출력 됩니다.

​	

@FunctionalInterface

### java.util.function.Consumer

> 매개변수만 있고 반환값이 없음.

```java
package com.tistory.shanepark;

import java.util.function.Consumer;
import java.util.function.Function;

public class Lambda06Consumer {

    public static void main(String[] args) {
        Consumer<String> printHello = System.out::println;
        Consumer<String> printComma = (s) -> System.out.println(",");

        printHello.accept("Hello world");
    }

}
```

![image-20210829205224831](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/lambda.assets/image-20210829205224831.png)

​		

​	

@FunctionalInterface

### java.util.function.Predicate

> 매개변수 하나. 반환 타입은  boolean.

```java
package com.tistory.shanepark;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class Lambda07Predicate {

    public static void main(String[] args) {
        Predicate<String> isEmpty = (s) -> s == null || s.replace(" ", "").length() == 0;
        System.out.println(isEmpty.test(" "));
        System.out.println(isEmpty.test("abc"));

        String[] arr = new String[]{"abc", "  ", "dcf"};
        List<String> list = new ArrayList<>();
        Collections.addAll(list, arr);
        removeEmpty(list, isEmpty);
        System.out.println(list);

    }

    public static <T> void removeEmpty(List<T> list, Predicate<T> filter) {
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            T t = iterator.next();
            if (filter.test(t))
                iterator.remove();
        }
    }

}
 
```

코드는 위와 같이 짜 보았습니다.

isEmpty 라는 String 을 Parameter로 받아 해당 String이 empty인지 체크해 그 결과를 반환해 주는 isEmpty 라는 Preciate를 선언 했습니다. 그래서 " " 과 "abc"를 test 해 각각 true 와 false 를 반환 받았습니다.

조금의 응용을 해 보기 위해 removeEmpty 라는 void 함수를 만들어 보았습니다. list를 순회 하면서 만약 항목이 인자로 받은 Predicate 로 test 한 결과가 true 일 경우에 해당 iterator를 제거하게끔 했습니다. 그래서 "abc", "  ", "dcf" 가 담긴 list를 아까 선언해둔 isEmpty Predecate와 같이 removeEmpty 메서드에 parameter로 넣어 메서드를 호출 했고, list 를 print 했을 때 empty 인 "  "가   List에서 제거 된 것을 확인 할 수 있었습니다.

![image-20210829211238142](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/lambda.assets/image-20210829211238142.png)

​	

​	

@FunctionalInterface

### java.util.function.Supplier

> 매개변수는 없고, 반환값만 있음.

​	

Supplier를 이용해 간단하게 UUID Generator를 만들 어 보았습니다.

```java
package com.tistory.shanepark;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Lambda08Supplier {

    public static void main(String[] args) {
        Supplier<String> uuidGenerator = () -> UUID.randomUUID().toString();

        System.out.println(uuidGenerator.get());
        System.out.println(uuidGenerator.get());
        System.out.println(uuidGenerator.get());

    }

}
```

![image-20210829211713225](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/lambda.assets/image-20210829211713225.png)

​	

다른 방법으로도 활용 해 보았습니다. Supplier를 이용해 리스트를 생성합니다. 그리고는 Function을 이용해 args 받은 목록을 모두 UpperCase로 변경하며 해당 리스트에 추가 합니다. 

```java
package com.tistory.shanepark;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

public class Lambda08Supplier2 {

    public static void main(String[] args) {

        List<String> myList = list(ArrayList::new, s -> s.toUpperCase(), "one", "two", "three", "four");
        System.out.println(myList);

    }

    public static <T, R> List<R> list(Supplier<List<R>> factory, Function<T, R> func, T... args) {
        List<R> list = factory.get();
        for (T a : args) {
            list.add(func.apply(a));
        }
        return list;
    }

}
```

![image-20210829212338573](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/lambda.assets/image-20210829212338573.png)

이런 식으로도 활용 가능합니다.

​	

### 그 외

![image-20210829214300018](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/lambda.assets/image-20210829214300018.png)

java.util.function 에는 그 외에도 많은 인터페이스들이 있습니다. 매개변수가 두 개인 함수형 인터페이스는 접두사  Bi를 붙이며, 매개변수의 타입과 반환 타입의 타입이 모두 일치하는 UnaryOperator와 BinaryOperator 또한 있습니다.



### 컬렉션 프레임웍과 함수형 인터페이스 

Java 8로 넘어오며 컬렉션 프레임웍의 인터페이스에도 다수의 디폴트 메서드가 추가되었는데요, 그 중의 일부는 함수형 인터페이스를 사용하기도 합니다.

​			

### Collection

![image-20210829220418739](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/lambda.assets/image-20210829220418739.png)

### List

![image-20210829220445071](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/lambda.assets/image-20210829220445071.png)

### Iterable

![image-20210829220458068](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/lambda.assets/image-20210829220458068.png)



### Map

![image-20210829221306269](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/lambda.assets/image-20210829221306269.png)

![image-20210829221403394](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/lambda.assets/image-20210829221403394.png)

​	

한번에 이해하고 응용하기에는 어렵겠지만, 이런게 있다 라고 알고 있다가 하나씩 필요할 때 떠올려 사용하다 보면 금방 익숙해 지지 않을까 생각합니다.

​	

참고자료 : 자바의정석, 회사교육자료