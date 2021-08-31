# Spring StopWatch.class

​	

```
public class StopWatch
extends Object
```

> Simple stop watch, allowing for timing of a number of tasks, exposing total running time and running time for each named task.
>
> Conceals use of [`System.nanoTime()`](https://docs.oracle.com/javase/8/docs/api/java/lang/System.html?is-external=true#nanoTime--), improving the readability of application code and reducing the likelihood of calculation errors.
>
> Note that this object is not designed to be thread-safe and does not use synchronization.
>
> This class is normally used to verify performance during proof-of-concept work and in development, rather than as part of production applications.
>
> As of Spring Framework 5.2, running time is tracked and reported in nanoseconds.

### Spring의 StopWatch 클래스는 여러개 작업의 시간을 측정해주며, 총 소요시간과 각각의 이름붙인 작업들에 소요된 시간을 표시해줍니다. System.nanoTime() 의 사용을 감추고 어플리케이션 코드의 가독성을 높일 뿐만 아니라 계산 오류의 가능성도 줄여줍니다. 

> Thread-safe 로 디자인 되지 않았으며 동기화를 사용하지 않습니다.

Spring Framework 5.2 부터 러닝타임을 nano-second로 측정 한다고 하네요. 지금까지 매번 개발 하면서 특정 소요 시간을 측정 하려면 지금의 시간을 저장할 변수를 생성 하고, 지금 시간을 기록 하고, 작업 후에는 그때의 시간에서 저장해 둔 시간을 빼고 그 소요 시간을 구해 내곤 했었는데요. 코드가 엄청 지저분해 지고 귀찮기도 한 문제가 있었습니다. 그 문제를 깔끔하게 해결해 주는 클래스 입니다.

​		

공식 문서는 아래의 내용을 참고하시면 됩니다. 해당 포스팅도 아래 문서를 토대로 작성 하였습니다.

https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/util/StopWatch.html

​			

### 아주 간단한 예시 코드를 한번 작성 해 보겠습니다.

```java
package com.tistory.shanepark.lambda.spring;

import org.springframework.util.StopWatch;

public class StopWatchClass {
    public static void main(String[] args) {

        // StopWatch 생성
        StopWatch stopwatch = new StopWatch();

        // 타이머 시작
        stopwatch.start();

        // 작업
        fibonacci(45);

        // 타이머 종료
        stopwatch.stop();

        // 결과 분석
        System.out.println(stopwatch.prettyPrint());

    }

    public static int fibonacci(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

}	
```

​	

어디 시간 측정 할 만한 코드가 없나 생각해봤다가, 예전에 Dynamic Programming 공부 할 때 시간 측정해서 비교용으로 작성 했던 재귀형 피보나치 수열이 생각나서 그걸로 해 보았습니다.

StopWatch 객체를 생성 하고,  start() 메서드를 호출 해 준 다음에, 측정 할 작업을 마치고 stop() 메서드를 호출 하면 끝 입니다. 그 결과는 prettyPrint() 메서드를 호출 하면 이쁘게 nano second 단위로 출력 해 줍니다.

​		

![image-20210831212402500](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/StopWatch.assets/image-20210831212402500.png)

​	

1 sec = 1,000 ms = 1,000,000 ns 입니다.

45개의 피보나치 수열을 계산하는데 총 3.82 초의 시간이 걸렸네요.

​		

### StopWatch 클래스의 기본 사용 용도를 알았으니 조금 더 자세히 알아 보겠습니다.

StopWatch 클래스의 코드를 까 보니 소스코드가 200여줄 정도 밖에 안 될 정도로 매우 간단합니다. 

![image-20210831212912804](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/StopWatch.assets/image-20210831212912804.png)

위에 나온 것 처럼 IntelliJ 에서 .class 를 토대로 역 컴파일 한 코드라고는 하지만 코드를 보니 정말 작동 시간 측정 용도로만 작성 된 클래스 입니다.

​	

### Constructors (생성자)

![image-20210831212753401](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/StopWatch.assets/image-20210831212753401.png)

​			

생성자로는 id를 parameter로 받는 생성자 하나와 기본 생성자. 이렇게 총 2개가 있네요.

​	

### Methods

​	![image-20210831213204057](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/StopWatch.assets/image-20210831213204057.png)

intelliJ IDEA에서 코드 어시스트를 받아 보니 이만큼의 메서드가 나옵니다. 

일단 생성 할 때 id 를 넣어서 생성도 해보고, start 할 때 taskName 도 넣어서 테스트 해볼 만한 코드를 작성 해 보겠습니다.

​		

```java
package com.tistory.shanepark.lambda.spring;

import org.springframework.util.StopWatch;

public class StopWatchClass2 {
    static final int number = 45;
    static long[] memo= new long[number+1];

    public static void main(String[] args) {

        StopWatch stopwatch = new StopWatch("fibonacci");

        // 1번 작업
        stopwatch.start("recursive");
        fibonacci(number);
        stopwatch.stop();

        // 2번 작업
        stopwatch.start("DP");
        fibonacciDp(number);
        stopwatch.stop();

        // 결과 분석
        System.out.println(stopwatch.prettyPrint());

    }

    public static long fibonacci(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static long fibonacciDp(int n){
        if(n==0)
            return 0;
        if(n==1)
            return 1;
        if(memo[n] != 0) {
            return memo[n];
        }else {
            return memo[n] = fibonacciDp(n-1) + fibonacciDp(n-2);
        }
    }

}
```

Recursive 와 Dynamic Programming 방식의 피보나치 계산을 각각 한 뒤 시간을 비교 해 보는 코드 입니다.

​		

![image-20210831221325041](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/StopWatch.assets/image-20210831221325041.png)

전체 해당 StopWatch의 소요시간을 보여주고, 각각의 Task를 작업하는데 걸린 시간과 비중을 보여줍니다.

같은 결과물이지만 어떤 알고리즘이냐에 따라 이렇게 말도 안되는 소요시간 차이가 납니다.

​		

그 외에도 몇가지 메서드들이 있지만 딱히 어려운 내용이 아니기 때문에 포스팅은 여기까지 하겠습니다.

잘 생각 해뒀다가 성능 확인이 필요할 때 한번씩 써먹어 보면 되겠습니다 !

