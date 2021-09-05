# 스트림 (Stream)

​	

​	

## 스트림이란 ? 

>https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html 

​	

Stream이 추가 되기 전인 Java 7 버전 까지에서는 많은 수의 데이터들을 다룰 때에 컬렉션이나 배열에 데이터를 담아 둔 후, 원하는 결과를 얻기 위해서 반복문 혹은 Iterator를 이용하는 방법 만으로 코드를 작성 해 왔습니다. 

```java
package com.tistory.shanepark.stream;

public class Stream01 {
    public static void main(String[] args) {
        int[] numbers = {1,2,3,4,5,6,7,8,9,10};

        for(int n : numbers){
            System.out.println(n);
        }
    }
}
```

​	

하지만 이러한 방식으로 작성 된 코드에는 몇가지 단점이 있었는데요.

- 코드가 너무 길고 알아 보기 어렵다.
- 코드의 재 사용성이 떨어진다.
- 데이터 소스마다 다른 방식으로 다루어야 한다.

물론 Collection 이나 Iterator 와 같은 인터페이스들을 이용해서 컬렉션을 다루는 방식을 표준 화 시켰기 때문에 어느 정도 까지는 비슷하게 다룰 수 있지만, 각 컬렉션 클래스에는 같은 기능의 메서드들이 중복되어 정의되어 있습니다. 

​	

```java
Collections.sort(list);
Arrays.sort(arr);
```

위의 코드에서 보이는 것 처럼, List를 정렬 할 때와 배열을 정렬 할 때 사용하는 메서드가 각각 다릅니다.

​		

### 이러한 문제를 해결하기 위해 Stream 이 추가되었습니다.

Java 8 에서 추가 된 Stream은 데이터 소스를 추상화 하고, 데이터를 다룰 때 자주 사용되는 메서드들을 정의 해 두었습니다. "추상화" 되어 있다는 것은 데이터 소스에 상관 없이 같은 방식으로 다룰 수 있게 되었다는 것을 의미하며 또한 코드의 재 사용성이 좋아졌다는 것을 의미합니다. 스트림을 사용한다면 배열, 컬렉션 뿐만 아니라 파일에 저장 된 데이터 또한 모두 같은 방식으로 다룰 수 있습니다.

​	

위에서 for문으로 출력했던 코드를 Stream 형식으로 변경한다면.

```java
package com.tistory.shanepark.stream;

import java.util.Arrays;
import java.util.stream.Stream;

public class Stream01 {
    public static void main(String[] args) {

        Integer[] numbers = new Integer[]{1,2,3,4,5,6,7,8,9,10};

        Stream<Integer> stream = Arrays.stream(numbers);

        stream.forEach(System.out::println);

    }
}
```

이롷개 stream을 이용 해 작성할 수 있습니다.

​	

```java
package com.tistory.shanepark.stream;

import java.util.Arrays;

public class Stream01 {
    public static void main(String[] args) {

        Integer[] numbers = new Integer[]{1,2,3,4,5,6,7,8,9,10};

        Arrays.stream(numbers).forEach(System.out::println);

    }
}
```

이렇게 하면 단 한줄로도 순회하며 출력하는 코드를 작성 할 수 있습니다.

![image-20210905212522547](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/stream.assets/image-20210905212522547.png)

> 실행 결과

​	

​	

```java
package com.tistory.shanepark.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Stream02 {
    public static void main(String[] args) {

        Integer[] arr = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, arr);

        list.stream().forEach(System.out::println);

    }
}
```

이번엔 List 를 똑같은 방식으로 출력하는 코드 입니다. 보면 눈치 채겼겟지만, stream을 만들어 낸 후에는 그걸 이용하는 코드가 정확히 일치합니다.

​	

이번에는 숫자가 뒤죽박죽 되어 있을 경우에 그것을 정렬 한 후에 출력 해 내는 코드를 작성해보겠습니다.

각각  Array 와 List의 경우에 Stream을 적용하지 않았다면 코드가 아래와 같은데요,

```java
package com.tistory.shanepark.stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Stream03 {
    public static void main(String[] args) {

        Integer[] arr = new Integer[]{8,2,5,4,3,6,7,9,1,10};
        List list = Arrays.asList(arr);

        Arrays.sort(arr);
        for(int n : arr){
            System.out.println(n);
        }

        Collections.sort(list);
        Iterator<Integer> it = list.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }

    }
}
```

​	

같은 내용을 Stream으로 표현한다면 이렇게 간단합니다.

```java
package com.tistory.shanepark.stream;

import java.util.Arrays;
import java.util.List;

public class Stream04 {
    public static void main(String[] args) {

        Integer[] arr = new Integer[]{8,2,5,4,3,6,7,9,1,10};
        List list = Arrays.asList(arr);

        Arrays.stream(arr).sorted().forEach(System.out::println);
        list.stream().sorted().forEach(System.out::println);

    }
}
```

​	

심지어 Array 와 List 지만, Stream으로서 다루는 방식이 완전히 똑같습니다. 코드가 간결하고, 이해하기도 쉬우며, 재사용성 마저도 높습니다.

​	

### 스트림은 데이터 소스를 변경하지 않습니다.

스트림은 데이터 소스로 부터 데이터를 읽어오기만 할 뿐, 데이터 소스를 변경 하지 않는다는 차이가 있습니다. 또한 필요하다면 스트림으로 받아온 데이터를 다시 컬렉션이나 배열에 담아서 반환 해 낼 수도 있습니다. 게다가 스트림은 Iterator 처럼 일회용 입니다. 한 번 사용하면 닫혀서 다시 사용 할 수 없기 때문에 필요하면 또 다시 스트림을 생성해서 사용해야 합니다.

​	

### 스트림은 여러개의 연산을 연속해서 연결 할 수 있습니다.

위에서 이미 한 것 처럼 정렬 하고 그 스트림을 그대로 반복 시킬 수 있습니다. 스트림의 연산은 중간 연산과 최종 연산으로 나눌 수 있는데, 중간 연산은 연속해서 연결 할 수 있는 반면 단어에서 느껴지는 것 처럼 최종 연산은 스트림의 요소를 소모하기 때문에 단 한번만 연산이 가능합니다. 위에서의 sorted() 는 중간 연산, forEach는 최종 연산에 해당합니다.

​	

```java
package com.tistory.shanepark.stream;

import java.util.Arrays;
import java.util.List;

public class Stream05 {
    public static void main(String[] args) {

        Integer[] arr = new Integer[]{8,5,5,4,3,6,7,10,1,9};
        List<Integer> list = Arrays.asList(arr);

        list.stream().distinct()
                    .limit(5)
                    .sorted()
                    .filter(n -> n<=5)
                    .forEach(System.out::println);

    }
}
```

![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/stream.assets/image-20210905214721091.png)

위의 코드와 실행 결과를 보면 좀 더 이해가 쉽습니다. distinct()를 통해 일단 중복 데이터인 5가 한개 제거 되었습니다. 다음으로 limit5 를 거쳐 8,5,4,3,6 이 Stream에 남았겠네요. 이제 sorted() 를 통해 정렬 이 됩니다. 3,4,5,6,8 이 있네요. 그 다음연산으로 filter에서 5보다 큰 숫자들은 제외가 됩니다. 그러면 3,4,5 만 남았네요. forEach를 통해 해당 숫자 들을 출력 해 주었습니다.

바로 여기에서 distinct(), limit(), sorted(), filter() 가 모두 **중간 연산**이며, 스트림을 소모 하며 연산을 수행한 forEach가 **최종 연산** 입니다. 스트림을 쉽게 다룬 저 모든 연산들을 하나하나 스트림 없이 코드 작성하며 다루었을 걸 생각하면 그 편리함이 어느 정도 와 닿습니다.

​	

## Stream에 정의된 연산들

> https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html 를 확인하면 전체 목록을 확인 하실 수 있습니다.

​	

### map

![image-20210905215237124](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/stream.assets/image-20210905215237124.png)

스트림의 요소를 변환 합니다. intermediate operation (중간연산) 이라는 설명도 해주네요. 

그 용도를 아주 쉽게 예시를 들어보겠습니다.

​	

```java
package com.tistory.shanepark.stream;

import java.util.Arrays;
import java.util.List;

public class Stream06 {
    public static void main(String[] args) {

        Integer[] arr = new Integer[]{8,5,5,4,3,1,7,2,1,9};
        List<Integer> list = Arrays.asList(arr);

        list.stream().map((i) -> i+1).forEach(System.out::println);

    }
}
```

실행 결과를 한번 예측 해 볼 까요? 코드를 보면 워낙 직관적이기때문에 누구라도 쉽게 알 수 있습니다.

​	

![image-20210905215552921](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/stream.assets/image-20210905215552921.png)

네. 맞습니다. 모든 스트림의 요소를 1씩 추가 하도록 했습니다. 이처럼 map 은 스트림의 요소를 변환 하는 데 사용되며, 중간 연산의 핵심 입니다.

​		

### flatMap	

![image-20210905215719820](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/stream.assets/image-20210905215719820.png)

핵심 중간연산을 하나 더 뽑자면 flatMap 이 있습니다. 제법 설명도 길게 되어 있네요.

스트림의 타입이 Stream<T[]> 인 경우에는 Stream<T> 로 다루는 것이 더 필요 할 때가 있는데요, 그럴 때는 map() 대신에 flatMap()을 사용합니다.



```java
package com.tistory.shanepark.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Stream06 {
    public static void main(String[] args) {

        Stream<String[]> stream = Stream.of(new String[]{"jenny", "shane"}, new String[]{"game", "programming"});

        stream.forEach(System.out::println);

    }
}
```

![image-20210905220810292](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/stream.assets/image-20210905220810292.png)

​	이런식으로 String 배열을 T 로 가진 Stream이 있습니다. 이 때 각 요소의 문자열들을 합쳐서 문자열이 요소인 스트림 Stream<String>을 만들려면 어떻게 해야 할까요?

Stream의 요소를 변환 해야 하기 때문에 map()을 써야 하겠네요, 배열을 스트림으로 만들어주는 Arrays.stream(T[])를 함께 사용해봅니다.

​	

```java
package com.tistory.shanepark.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Stream06 {
    public static void main(String[] args) {

        Stream<String[]> stream = Stream.of(new String[]{"jenny", "shane"}, new String[]{"game", "programming"});

        Stream<Stream<String>> stream2 = stream.map(Arrays::stream);

        stream2.forEach(System.out::println);

    }
}
```

![image-20210905221340843](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/stream.assets/image-20210905221340843.png)

​		

stream.map 을 썼는데 그 변환 결과가 Stream<String> 이 아닌 Stream<Stream<String>> 이 되었습니다. 스트림의 스트림이 되어 버렸네요. 이럴 때에는 map 이 아닌 flatMap 을 사용하면 원하는 결과를 얻을 수 있습니다.

​	

```java
package com.tistory.shanepark.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Stream06 {
    public static void main(String[] args) {

        Stream<String[]> stream = Stream.of(new String[]{"jenny", "shane"}, new String[]{"game", "programming"});

        stream.flatMap(Arrays::stream).forEach(System.out::println);

    }
}
```

![image-20210905221631900](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/stream.assets/image-20210905221631900.png)

이제야 원했던 결과가 나옵니다.

​	

### 핵심 중간연산이 map()과 flatMap() 이라면, 핵심 최종 연산은 reduce()와 collect() 입니다.

​	

### reduce()

![image-20210905221909088](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/stream.assets/image-20210905221909088.png)

reduce 라는 이름이 말 해주는 것 처럼, 스트림의 요소를 하나 씩 줄여 가면서 연산을 수행 하고, 최종 결과를 반환 하게 됩니다. 이번에도 예시 코드를 먼저 만들어 보겠습니다.

​	

```java
package com.tistory.shanepark.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Stream07Reduce {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9,10});

        int sum = list.stream().reduce(0, (a,b) -> a+b);

        System.out.println(sum);

    }
}
```

![image-20210905222547574](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/stream.assets/image-20210905222547574.png)

​	

초기값을 0으로 시작해, stream을 하나씩 소비 해 가며 그 합을 계속 더해갑니다. 최종적으로 sum에 남은 숫자는 스트림에 있는 모든 수의 합이 됩니다. 마찬가지로 최대, 최소값도 어렵지 않게 구할 수 있습니다.

​	

```java
package com.tistory.shanepark.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Stream07Reduce2 {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9,10});

        Optional<Integer> max = list.stream().reduce((a, b) -> a>b?a:b);
        System.out.println(max.orElse(0));

    }
}
```

![image-20210905222952919](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/stream.assets/image-20210905222952919.png)

​	최대값을 구해 보았습니다. 타입이 IntStream인 경우에는 Optional 객체를 사용 했는데요, Strem<T> 와는 다르게 IntStream에 정의 된 reduce() 의 반환 타입이 OptionalInt 이기 때문입니다. Optional 을 사용 해 보지 않았다면 .orElse(0)이 어떤 의미인지 잘 모를 수도 있는데, 값이 없다면 대신 0을 반환하라는 것 입니다. max.get()을 통해 값을 받아 올 수 도 있지만, Optional 객체를 사용하는 것 자체가 Null을 직접 다루지 않기 위해서 이며, .get을 했다가는 또 다시 NullPointException의 가능성이 있기 때문에 orElse를 사용하는 것 입니다.

​	

### collect()

![image-20210905223333923](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/stream.assets/image-20210905223333923.png)

>  마지막으로 collect 입니다. 사실 스트림을 다룬 다면 지금 핵심으로 꼽은 네가지 메서드만 사용하는 경우는 별로 없고 매우 다양한 메서드들을 조합해서 사용하게 되는데요. 스트림의 기초를 먼저 알게 된다면 그 이후는 크게 어렵지 않게 응용 할 수 있을 테니 collect 까지만 다루어 보겠습니다. 

자바의 정석 저자인 남궁성 선생님은 스트림의 최종 연산 중 에서 가장 복잡하면서도 유용하게 활용될 수 있는 것이 바로 이 collect() 라고 하셨습니다. reduce() 에서는 하나씩 소모해 가며 연산을 했는데요, collect의 경우에는 스트림의 요소를 수집하기 위한 것 이며, 어떻게 수집 할 것인가에 대한 방법이 정의된 것이 collector 입니다. collect()는 매개변수의 타입이 Collector 인데요, Collector 인터페이스를  구현 한 것으로 직접 구현 할 수도 있으며 미리 작성된 것을 사용 할 수도 있습니다.

​	

![image-20210905223905913](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/stream.assets/image-20210905223905913.png)

아까 중간연산자들에 대해 공부 할 때, limit(), sorted(), filter() 등의 중간 연산을 거친 스트림을 최종적으로 collect 해 보려고 합니다. IDE의 도움을 받으니 toList, toSet, toCollection 등등 여러가지 기존에 이미 작성되어 있던 Collector를 사용 할 수 있습니다.

​	

```java
package com.tistory.shanepark.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stream08Collect {
    public static void main(String[] args) {

        Integer[] arr = new Integer[]{8,2,5,4,3,1,7,10,1,9};
        List<Integer> list = Arrays.asList(arr);

        List<Integer> newList = list.stream().distinct()
                .limit(5)
                .sorted()
                .filter(n -> n<=5)
                .collect(Collectors.toList());

        System.out.println(newList);

    }
}
```

![image-20210905224055727](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/stream.assets/image-20210905224055727.png)

그렇게 스트림을 수집해 새로 만든 List는 또 다시 원하는 대로 얼마든지 사용 할 수 있습니다.

​		

```java
package com.tistory.shanepark.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Person{
    int number;
    String name;
    Person(int number, String name){
        this.number = number;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }
}

public class Stream08Collect2 {

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person(1,"김두한"));
        list.add(new Person(2,"시라소니"));

        Map<Integer, Person> map = list.stream().collect(Collectors.toMap(p->p.number, p->p));

        System.out.println(map.get(2));

    }
}
```

​	

이번엔 Stream을 Map으로도 수집 해 보았습니다.  Map 의 경우에는 key-value 쌍으로 저장 해야 하기 때문에 객체의 어떤 필드를 키로 사용할지, 그리고 어떤 필드를 value 로 사용할 지를 정해줘야 합니다. 저는 p.number를 key로, p 자체를 value로 하게끔 해 보았습니다. 그렇게 만든  map 에서 key로 2를 넣어 조회를 하면,

![image-20210905224737470](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/stream.assets/image-20210905224737470.png)

의도한대로 2번 number를 가지고 있는 시라소니를 조회 해 옵니다.

​	

이 외에도 굉장히 많은 메서드가 있습니다.

​	

### 맺음말

 스트림은 여기에서 끝나지 않고 훨씬 방대하고 다양한 활용이 가능합니다만, 처음부터 다 하려면 탈이 날 수 있겠죠. 

아주 기본적인 스트림의 개념과 왜 사용하는지, 어떻게 사용하는지, 그리고 어떤 기본적인 메서드들이 있는지 함께 알아보았습니다. 스트림이 어렵고 낯설어서 피하고 싶을 수도 있겠지만 그 편리함과 다양한 매력에 빠져든다면 스트림 없는 자바 코딩은 상상하기 싫은 날이 올 것이라고 생각합니다. 저도 아직은 익숙하지 않지만 간단한 코드부터 하나씩 스트림으로 작성하려 노력 해 보며 익숙해지기 위한 과정을 거쳐 보려고 합니다. 이상입니다.

​	

참고자료 : 자바의 정석 