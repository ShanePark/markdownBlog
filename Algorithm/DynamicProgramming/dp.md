# 피보나치 수열과 프로그래머스 땅따먹기 문제로 알아보는 Dynamic Programming (동적 프로그래밍)



https://programmers.co.kr/learn/courses/30/lessons/12913

![image-20210731191647775](https://github.com/Shane-Park/markdownBlog/raw/master/Algorithm/DynamicProgramming/dp.assets/image-20210731191647775.png)

> 자세한 문제는 programmers를 통해 확인 해 주세요.



### 땅따먹기라고 하지만, 우리가 알고있는 땅따먹기와는 거리가 있습니다.

>  차라리 어렸을 적 하고 놀던 "사방치기"를 떠올리는 것이 조금 더 가깝습니다.



맨 첫줄 부터 시작해서

|  1   |  2   |  3   |  5   |
| :--: | :--: | :--: | :--: |
|  5   |  6   |  7   |  8   |
|  4   |  3   |  2   |  1   |



한줄씩 아래로 내려가는데, 일단 지금 밟은 열은 다음번 행에서 또 밟을 수가 없습니다.

예를 들어 첫 줄에서 5로 시작했다면, 다음 줄에서는 8을 밟을 수 없습니다.



무조건 지금 상황에서의 최선을 선택하는 "탐욕법" 으로 문제를 푼다면 다음 줄에서 더 큰 숫자의 기회를 잃을 수 있기때문에 다른 접근이 필요합니다. 위의 예시에서는 큰 차이가 없지만 어쨌든 첫 줄에서 5를 선택한다면 다음 줄에서 가장 큰 숫자인 8을 선택 할 수 없게 됩니다. 



### DFS나 BFS 는 어떨까?

행의 개수가 100,000개 까지 될 수 있다는 제약 조건이 있기 때문에 DFS나 BFS를 사용하면 효율이 굉장히 좋지 않을 것이고 그렇게 하나하나 다 따져야 하는 문제도 아니라고 생각했습니다.



### for문을 이용한 풀이

> 처음에는 지금의 선택이 다음 라인에서 어떤 영향을 주는지 까지만 고려하면 된다고 생각 했습니다.
>
> 그래서 한 줄 한 줄에서 숫자를 선택할때, 다음 한줄에서의 숫자 까지 고려하며 내려가면 되겠다고 생각하고 코드를 작성했습니다.

```java
    static int solution(int[][] land) {
    	
    	int length = land.length;
    	
    	int sum = 0;
    	int current = -1;
    	for(int i=0; i<length; i++) {
    		int temp = 0;
    		int tempMax = 0;
    		if(i == length - 1) {
    			int[] arr = land[i];
    			for(int l=0; l<4; l++) {
    				if(tempMax < arr[l]) {
    					temp = l;
    					tempMax = arr[l];
    				}
    			}
    		}else {
    			for(int j=0; j<4; j++) {
    				if(current == j) continue;
    				for(int k=0; k<4; k++) {
    					if(j == k) continue;
    					if(land[i][j]+land[i+1][k] > tempMax) {
    						tempMax = land[i][j]+land[i+1][k];
    						temp = j;
    					}
    				}
    			}
    		}
    		current = temp;
    		sum += land[i][current];
    	}
    	
        return sum;
    }
```



결론적으로는 이 코드는 예제의 케이스는 통과했지만 제출했을때 어떠한 테스트 케이스도 통과하지 못했습니다.



|  1   |  1   |  1   |  1   |
| :--: | :--: | :--: | :--: |
|  2   |  2   |  2   |  3   |
|  3   |  3   |  3   |  6   |
|  4   |  4   |  4   |  7   |



이런 경우가 있을때 위의 코드로 짜면, 1 -> 2 -> 3 -> 7 을 선택해서 총 13이 나오지만,

더 좋은 방법은 1 -> 3 -> 3-> 7 로 내려간 14 입니다.



두번째 줄에서 선택을 할 때, 3번째 줄에서 "6"이라는 가장 큰 숫자를 선택해야 한다는 판단에 "3" 이라는 숫자를 포기하고 "2"를 선택 한 것이지만, 정작 세번째 줄에 왔을 때에는 네번째 줄의 7을 포기할 수 없어 "6"을 포기하게 됩니다. 두 번째 줄에서의 희생이 아무런 의미가 없어 진 것입니다.



이때 동적 계획법을 사용하면 간결한 코드로 쉽게 문제를 해결 할 수 있습니다.



# Dynamic Programming (동적 계획법)

동적 계획법이란 특정 범위까지의 값을 구하기 위해 그것과 다른 범위까지의 값을 이용하여 효율적으로 값을 구하는 알고리즘 설계 기법입니다. 엄밀히 말해 동적 계획법은 구체적인 알고리즘이라기 보다는 문제를 해결하는 패러다임에 가깝습니다. 어떤 문제를 해결하기 위해 그 문제를 더 작은 문제의 연장선으로 생각하고, 과거에 구했던 해를 활용하는 방식의 알고리즘 입니다.



### 복잡한 문제를 간단한 여러 개의 문제로 나누어 푸는 방법

이라고 생각하면 됩니다. 컴퓨터 프로그래밍에서 뿐만 아니라 수학과 경제학 등 여러 분야에서 사용됩니다. 가장 빠른 길을 찾을때 그리디 알고리즘 에서는 전체적인 상황을 고려 하지 않고 순간순간 교차로가 보일 때 마다 가장 빠른 경로를 탐색하는 반면 DP 로는 우리가 갈 수 있는 모든 상황과 교통 정체를 전부 감안하여 최적의 경로를 찾아낸다고 보면 됩니다. 

그리디 알고리즘은 경로를 검색하는 시간이 없어 즉효성이 있는 반면, 동적 계획법은 그리디 알고리즘에 비해 시간적으로는 효율적이지 못할 지 언정 그 결과에 대해서는 확실히 보장을 합니다.



### 가장 간단한 예가 피보나치 수열 입니다



피보나치 수열을 출력해주는 코드를 작성 해 보겠습니다.

```java
public class Fibonacci {

	public static void main(String[] args) {
		Fibonacci fibonacci = new Fibonacci();
		for(int i=1; i<=10; i++) {
			System.out.println(fibonacci.fibonacci(i));
		}

	}
	
	public int fibonacci(int n){
		if(n==0)
			return 0;
		if(n==1)
			return 1;
		return fibonacci(n-1) + fibonacci(n-2);
		
	}

}
```



for 반복문 안의 조건에 들어가는 숫자 번째까지의 피보나치 수열을 출력 해 주는 프로그램 입니다.

프로그램을 실행하면

![image-20210731211437156](https://github.com/Shane-Park/markdownBlog/raw/master/Algorithm/DynamicProgramming/dp.assets/image-20210731211437156.png)



결과를 이렇게 보여줍니다.



이 코드에는 문제가 있는데요, 첫번째, 두번째, 세번째... 번째의 피보나치 수열 숫자를 한번 구했는데, 사용하지 않고 계속 또 계산을 합니다. 무슨 말인지 모르시겠다면 fibonacci(5)를 구하는 계산을 보여드리겠습니다.



1. fibonacci(5)
2. fibonacci(4) + fibonacci(3)
3. (fibonacci(3) + fibonacci(2)) + (fibonacci(2) + fibonacci(1))
4. ((fibonacci(2) + fibonacci(1)) + (fibonacci(1) + fibonacci(0))) + ((fibonacci(1) + fibonacci(0) + fibonacci(1))
5. (((fibonacci(1) + fibonacci(0)) + fibonacci(1)) + (fibonacci(1) +fibonacci(0))) + ((fibonacci(1) + fibonacci(0)) + fibonacci(1))



3번째 줄 에서의 fibonacci(2)가 중복 계산되고, 이 알고리즘의 시간 복잡도는 지수 함수가 됩니다. 똑같은 계산을 몇번이고 하게 됩니다. n이 커질수록 중복되는 작업은 계속 늘어나게 됩니다.



여기에서 각 함수의 계산값을 저장하도록  **메모이제이션(memoization)**을 이용해 피보나치 수열을 구현해보겠습니다.



```java
public class FibonacciDP {
	
	static int[] memo;

	public static void main(String[] args) {
		final int n = 10;
		memo = new int[n+1];
		
		FibonacciDP fibonacci = new FibonacciDP();
		
		for(int i=1; i<=n; i++) {
			System.out.println(fibonacci.fibonacci(i));
		}

	}
	
	public int fibonacci(int n){
		if(n==0)
			return 0;
		if(n==1)
			return 1;
		if(memo[n] != 0) {
			return memo[n];
		}else {
			return memo[n] = fibonacci(n-1) + fibonacci(n-2);
		}
		
	}

}
```

![image-20210731212853227](https://github.com/Shane-Park/markdownBlog/raw/master/Algorithm/DynamicProgramming/dp.assets/image-20210731212853227.png)

물론 그 계산 결과는 똑같습니다만, 그 과정에 큰 차이가 있습니다. 한번 했단 계산은 다시 하지 않습니다.

이렇게 각 계산값을 저장하면, 중복 계산이 없어지기 때문에 시간 복잡도는 O(n)이 됩니다.



## 그 차이를 실제로 테스트 해 보도록 하겠습니다. 

일단 fibonacci의 크기가 굉장히 커 지기 때문에 return 타입을 long으로 변경 해 주어야 합니다. 그렇지 않으면 금방 overflow가 발생합니다.

50번째 숫자까지 구하는 시간을 비교해보도록 하겠습니다. 



### 일단 첫번째, 재귀함수로 작성한 코드입니다. 

```java
public class Fibonacci {

	public static void main(String[] args) {
		
		final int n = 50;
		
		Fibonacci fibonacci = new Fibonacci();
		
		for(int i=1; i<=n; i++) {
			System.out.println(fibonacci.fibonacci(i));
		}

	}
	
	public long fibonacci(int n){
		if(n==0)
			return 0;
		if(n==1)
			return 1;
		return fibonacci(n-1) + fibonacci(n-2);
		
	}

}
```

![image-20210731213326186](https://github.com/Shane-Park/markdownBlog/raw/master/Algorithm/DynamicProgramming/dp.assets/image-20210731213326186.png)

CPU가 바쁘게 연산하고 있습니다.





![image-20210731213505513](https://github.com/Shane-Park/markdownBlog/raw/master/Algorithm/DynamicProgramming/dp.assets/image-20210731213505513.png)

<img src="https://github.com/Shane-Park/markdownBlog/raw/master/Algorithm/DynamicProgramming/dp.assets/image-20210731213519244.png" alt="image-20210731213519244" style="zoom:200%;" />



1분 50초 만에 결과가 나왔습니다. 처음에는 계산을 빠르게 하는데 40번째 넘어가면서 부터는 한번 한번 계산하는걸 정말 힘겨워 합니다. 50이 그나마 현실적인 숫자인데 거기에서 조금만 더 올라가면 얼마나 오래 걸릴지도 모르겠습니다.



### 이번엔 Dynamic Programming 방식의 코드 입니다

```java
public class FibonacciDP {
	
	static long[] memo;

	public static void main(String[] args) {
		final int n = 50;
		memo = new long[n+1];
		
		FibonacciDP fibonacci = new FibonacciDP();
		
		for(int i=1; i<=n; i++) {
			System.out.println(fibonacci.fibonacci(i));
		}

	}
	
	public long fibonacci(int n){
		if(n==0)
			return 0;
		if(n==1)
			return 1;
		if(memo[n] != 0) {
			return memo[n];
		}else {
			return memo[n] = fibonacci(n-1) + fibonacci(n-2);
		}
		
	}

}
```

<img src="https://github.com/Shane-Park/markdownBlog/raw/master/Algorithm/DynamicProgramming/dp.assets/image-20210731213841482.png" alt="image-20210731213841482" style="zoom:200%;" />

1초만에 결과가 나왔습니다. 허무하게도 비교가 무의미할 정도의 차이입니다. CPU 사용량을 체크할 것도 없이 바로 종료가 되어버렸습니다. 50번째가 문제가 아니라 90번째가 넘어가도 실행하는 즉시 결과를 알려 주었습니다.



![image-20210731214024253](https://github.com/Shane-Park/markdownBlog/raw/master/Algorithm/DynamicProgramming/dp.assets/image-20210731214024253.png)



long타입은 -9223372036854775808 ~ 9223372036854775807 의 범위를 표시할 수 있는데 정확히 93번째 피보나치 수열 부터 그 범위를 넘어가기 때문에 overflow가 발생하기 시작했습니다. 그 와중에도 연산은 1초 만에 바로 되는 속도를 보여줍니다.



#### 동적계획법을 사용해야 하는 이유가 충분히 납득 되었을 거라고 생각됩니다.



이제 다시 프로그래머스 땅따먹기 문제로 돌아와 보겠습니다.



|  1   |  1   |  1   |  1   |
| :--: | :--: | :--: | :--: |
|  2   |  2   |  2   |  3   |
|  3   |  3   |  3   |  6   |
|  4   |  4   |  4   |  7   |



한번씩 내려가면서, 거기까지 내려올때의 최고의 합을 기억하며 내려간다면 어떨까요?



|  1   |  1   |  1   |  1   |
| :--: | :--: | :--: | :--: |
|  3   |  3   |  3   |  4   |
|  7   |  7   |  7   |  9   |
|  13  |  13  |  13  |  14  |



내려오면서 각 자리에 최선의 데이터를 저장하면서 내려왔다고 했을때 새로 생성되는 표 입니다. 이해가 잘 될 지 모르겠는데, 첫째줄에는 이전까지의 데이터가 없으니 각자 그대로의 데이터를 가지고 있습니다.

둘째줄에서는 바로 윗 줄에서 본인과 겹치는 열을 제외한 나머지 3개 열의 데이터 중에 가장 큰 값을 찾아 그 합을 저장하도록 합니다. 첫째줄의 데이터가 모두 1이기 떄문에 둘째줄에서는 모두 1만 추가된 값이 저장된 것 입니다.

셋째줄에서는 1,2,3 열에서는 2번째 줄에서 가장 큰 4열의 "4"값을 스스로 더해 7을 각자 저장하지만 네번째 열은 "4"가 바로 위에 있기 때문에 그 차선인 "3"을 찾아 본인의 값인 6과 합쳐 9 를 스스로에 저장합니다.

그러고 마지막 줄에 와서도 1,2,3 열에서는 3번째줄 4열에 있는 9가 가장 크기 때문에 각자 가진 4에 9를 합친 13을 값으로 저장하지만, 4행4열에서는 본인의 위에 있는 숫자는 사용 할 수 없으니 그 차선인 7을 스스로와 더해 14를 저장하도록 한 것입니다.



그렇게 마지막 줄에 왔을때, 가장 큰 값을 가진 14를 반환하면 됩니다. 이해가 잘 안된다면 손으로 연습장에 그려보며 한칸씩 내려가보면 이해가 쉽습니다.



#### 이제 코드로 구현을 해 보도록 하겠습니다.

```
  int solution(int[][] land) {
    	final int length = land.length;
    	
    	for(int i=1; i<length; i++) {
    		land[i][0] += max(land[i-1][1], land[i-1][2], land[i-1][3]);
    		land[i][1] += max(land[i-1][0], land[i-1][2], land[i-1][3]);
    		land[i][2] += max(land[i-1][1], land[i-1][3], land[i-1][0]);
    		land[i][3] += max(land[i-1][1], land[i-1][2], land[i-1][0]);
    	}
    	
    	return max(land[length-1]);
    }
    
    public int max(int a, int b, int c) {
    	return Math.max(Math.max(a, b), c);
    }
    
    public int max(int[] arr) {
    	int max = 0;
    	for(int number : arr) {
    		max = Math.max(max, number);
    	}
    	return max;
    }
```

최대한 solution 메서드가 보기 좋게끔 최대값 구하는 메서드는 모두 따로 빼 보았습니다.

land를 두번째 행 (i=1) 부터 하나씩 내려가며 자신의 윗 행에서 본인과 일치하지 않는 열 중 가장 큰 값을 찾아 합친 후 그 값을 배열에 저장합니다. 그러고 마지막에는 마지막 행 에서 가장 큰 값을 찾아 반환 하도록 하는 간단한 코드입니다.



![image-20210731220256644](https://github.com/Shane-Park/markdownBlog/raw/master/Algorithm/DynamicProgramming/dp.assets/image-20210731220256644.png)



이렇게 제출하면 땅따먹기 문제를 통과 하게 됩니다.



Dynamic Programming 이해에 충분히 도움이 되었으면 좋겠습니다. 혹시 이해가 안되는 부분이 있으면 댓글에 남겨주시면 제가 도울 수 있는 선에서 최대한 도와드리도록 하겠습니다. 감사합니다.