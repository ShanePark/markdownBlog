# java ) Fail-Safe Iterator vs Fail-Fast Iterator

​	

## 1 소개

이번 글에서는 Fail-fast와 Fail-Safe Iterator의 개념에 대해 알아보겠습니다. 영어로 된 문서를 읽으며 공부하다보니 의미에 맞게 번역하는 과정에서 제가 전문 번역가가 아니라 다소 표현이 딱딱한 느낌이 날 수도 있습니다. 	

간단한 차이를 말씀드리고 시작하자면, **Fail-Fast** 시스템에서는 오류가 발생하는 즉시 작업을 중단하고 오류를 알립니다. 또한 진행중이던 전체 작업을 모두 중단시킵니다. 반면에 **Fail-Safe** 시스템에서는 문제가 생겨도 작업을 중단 하지 않습니다. 해당 시스템에서는 오류 발생을 가능한 피하려고 합니다.

​	

​		

## 2 Fail-Fast Iterators

java의 컬렉션은 modCount 라는 내부의 counter를 운용합니다. Collection에 item이 추가되거나 제거될 때마다 이 modCount 라 불리는 카운터가 증가합니다. iterating 중에 next()를 호출 할 때 마다 현 상황의 modCount 값을 초기의 값과 비교를 하는데요, 만약 거기에서 조금이라도 다르다면 즉시 ConcurrentModificationException을 throw 하고 전체 작업을 중단합니다.

ArrayList, HashMap 등과 같은 java.util 패키지의 컬렉션에 대한 기본 iterator들은 Fail-Fast 방식입니다.

​	

### 다소 억지스러워 보여도 하나의 예를 들어보겠습니다.

보통 코딩 테스트 문제 풀이 등을 하다가 순회 중인 list에 변화를 무의식적으로 줬을때 이와 같은 상황에 한번씩 처해보셨을 텐데요,

![image-20210823214235253](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/fail-fast.assets/image-20210823214235253.png)

위의 코드를 실행 하면,

​	

![image-20210823214349764](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/fail-fast.assets/image-20210823214349764.png)



이렇게 ConcurrentModificationException을 맞이하게 됩니다. 자바에서는 Iterator가 for문을 이용해 순환 중에 데이터에 변경이 있으면 Fail-Fast 방식이면 바로 해당 오류를 throw 하도록 구현되어 있습니다.

​	

```java
public static void main(String[] args) {
  List<Integer> numbers = new ArrayList<>();
  Collections.addAll(numbers, new Integer[]{1,2,3,4,5});

  Iterator<Integer> iterator = numbers.iterator();
  while (iterator.hasNext()) {
    Integer number = iterator.next();
    numbers.add(6);
  }
}
```

이런 코드도 마찬가지 입니다.



반면에 Collection을 순회 하는 동안에 Iterator의 remove() 메서드를 사용하며 제거한다면, 그건 완전히 안전하며 예외를 발생시키지 않습니다.

​	

아래의 코드와 같은 경우에는 

```java
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        Collections.addAll(numbers, new Integer[]{1,2,3,4,5});

        Iterator<Integer> iterator = numbers.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            iterator.remove();
        }
    }
```

​	

보다시피 어떠한 에러도 발생하지 않습니다.

![image-20210823220625203](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/fail-fast.assets/image-20210823220625203.png)

​		

## 3 Fail-Safe Iterators

Fail-Safe iterators는 예외 처리의 불편함을 넘어 실패가 없는 것을 선호합니다.

이러한 Iterator는 실제 Collection의 클론을 만들어 해당 클론을 순회합니다. 만약 iterator가 생성된 후에 어떠한 경우의 변화라도 발생한다고 해도, 순회 하는 클론은 여전히 수정되지 않은 상태로 남아 있습니다. 이러한 Iterator들은 수정이 가해진다고 해도 계속해서 순회를 이어갑니다.

하지만, 그 어디에도 완벽한 Fail-Safe Iterator는 없다고 합니다. 그래서 Fail-Safe 보다는 Weakly Consistent (약한 일관성) 이 더 올바른 표현이 되겠습니다. 만약 Collection이 순회되는 도중 변화가 생긴다면 그 보장성이 약해집니다. 이러한 동작은 컬렉션마다 다를 수 있으며 해당 내용은 Collectons의 Javadocs에 각각 문서화 되어 있습니다.

하지만 Fail-Safe Iterators는 몇가지의 단점이 있습니다. 첫번째는, Iterator가 업데이트 된 Collection의 데이터를 보장하지 못한다는 점인데, 실제 컬렉션이 아닌 그것의 클론으로 작업을 하다 보니 그렇습니다.

또 하나의 단점으로는 Collection의 사본을 생성함으로서 일어나는 시간과 메모리의 overhead 입니다.

Fail-Safe 컬렉션의 예로는 java.util.concurrent 패키지의 ConcurrentHashMap, CopyOnWriteArrayList 등이 있습니다.



![image-20210823222157760](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/fail-fast.assets/image-20210823222157760.png)



위에 보이는 것 처럼, 아까 ArrayList로 순회 했을 때는 에러가 났던 코드이지만, 지금은 에러가 나지 않는 것을 확인 할 수 있습니다. 대신 나중에 여러번 새로 추가된 6이라는 숫자는 전혀 순회해내지 못합니다.



## 4 결론

이번 포스팅에서 Fail-Safe와 Fail-Fast Iterators가 Java에서 어떻게 구현되었는지 알아 보았습니다.

빠르게 실패할 것인가, 아니면 안전하게 실패할 것인가. 지금의 문제 상황을 어떻게 트러블 슈팅 해내는게 맞는지 한번 잘 고민해 볼 때입니다.