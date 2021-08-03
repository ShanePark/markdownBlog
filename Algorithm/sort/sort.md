# java로 작성해보는 정렬 알고리즘과 성능 비교



### 얼마전 정렬 알고리즘을 손 코딩 해 볼 기회가 있었습니다.

갑자기 눈앞에 정렬 알고리즘을 손코딩 해야 하는데, 제한시간도 있다 보니 잠시동안 고민이 되었습니다. 

1. 당장에 간단하게 작성할 수 있는 버블정렬을 선택해 다른 문제 풀이에 쓸 시간을 조금 더 벌 것인가

2. 아니면 자바를 처음 배울때 직접 구현해보려다가 못했던 퀵정렬을 한번 작성해 볼 것인가.

> 결론부터 말하자면 안전한 길을 택했습니다. 솔직히 버블정렬의 쉬운 난이도에도 불구하고 항상 실제로 코드를 구동해보고 테스트 하기 전 까지는 맞는지 문제가 있는지 확신이 서지 않았습니다.

​	

​	

### 과거로 돌아가, 학원에서 초급 자바시간에 정렬에 대해 배우는 기회가 있었습니다.

 선택, 버블, 삽입 정렬에 대해서 간단하게 설명해주셨고 한번씩 코드를 따라 쳐 보고 지나갔었습니다. 그때 선생님께서 이렇게 말씀하셨습니다. 

>  지금 배운 정렬들은 외울 필요가 없고 그냥 한번 알고 지나가면 됩니다. 어차피 성능이 좋은 정렬도 아니고 필드에서는 쓰지 않아요.

​	

선택, 버블, 삽입정렬을 알려주시고 코드를 간단하게 작성해서 정렬이 되는걸 보여주실때는 정말 신기하고 해당 정렬들에 대해 대단하다는 생각을 하고 있었는데 선생님께서 말씀하신 "성능이 좋지 않은 정렬" 이라는 말에 의문을 품었습니다. 또한 좋지 않은 정렬이라고 하시는데 그렇다면 왜 좋은 정렬은 알려주시지 않는지도 궁금했습니다. 

 그날 저녁 인터넷을 뒤져가며 정렬 방법들에 대해 찾아봤고, 시간복잡도의 개념과 새로 학습하는 정렬 방법들의 직관적이지 못한 작동 원리에 머리가 아팠지만 "연산 횟수와 시간복잡도"에 대해 처음으로 고민하기 시작한 좋은 계기였습니다.

 오랜만에 그때의 노트를 꺼내어 봤습니다. 혼자 공부하며 나름대로 각 정렬들의 순위도 두었던게 재밌어 사진도 찍었습니다. 

![image-20210803111801033](https://github.com/Shane-Park/markdownBlog/raw/master/Algorithm/sort/sort.assets/image-20210803111801033.png)

![image-20210803112701129](https://github.com/Shane-Park/markdownBlog/raw/master/Algorithm/sort/sort.assets/image-20210803112701129.png)

​	

### 사실 정렬 알고리즘의 성능 순위는 상황에 따라 다릅니다

##### 버블 정렬

>  이미 정렬된 자료에서는 반복문을 한번만 돌면 되기 떄문에 최선의 성능을 보여줍니다. 물론 그걸 제외하면 최악의 알고리즘이라는 사실에는 변함이 없습니다.

##### 선택 정렬

> 일관성있게 n(n-1)/2 에 비래한 시간이 소요됩니다.

##### 삽입정렬

> 최악의 경우에서 n(n-1)/2 에 비래한 시간이 소요됩니다. 자료구조에 따라 뒤로 밀어내는데 걸리는 시간이 커질 수 있음을 유의해야 합니다. 하지만 배열의 크기가 작을 경우에는 상당히 효율적이어서 일반적으로 빠르다고 알려진 알고리즘 O(nlogn)의 정렬 보다 빠릅니다.

이렇게 작성하고 보니 저 때 적어둔 정렬 순위가 어느 정도 맞긴 한 것 같습니다.

​	

한번씩 코드를 작성해보며 정렬 코드가 제대로 되었는지 확인하고, 그 성능도 확인 해 보도록 하겠습니다.

​	

```java
package shane.study.sort;

import java.util.Arrays;

public class SortController {

	public static void main(String[] args) {
		
		// 총 배열의 길이 미리 정하기
		final int SIZE = 30000;
		
		// 정렬 알고리즘 선택
		Sorter sorter = new BubbleSort();
		
		// 각각 다른 방식으로 정렬할 두개의 배열 만들기
		int[] arr = new int[SIZE];
		int[] arr2 = new int[SIZE];
		for(int i=0; i<SIZE; i++) {
			int number = (int) ((Math.random() * SIZE)+1);
			arr[i] = number;
			arr2[i] = number;
		}
		
		long savedTime = System.currentTimeMillis();
		sorter.sort(arr);
		System.out.println(String.format("Custom sorting에 소요된 시간 : %dms", System.currentTimeMillis() - savedTime ));
		
		savedTime = System.currentTimeMillis();
		Arrays.sort(arr2);
		System.out.println(String.format("library sorting에 소요된 시간 : %dms", System.currentTimeMillis() - savedTime ));
		
		for(int i=0; i<SIZE; i++) {
			if(arr[i] != arr2[i]) {
				System.out.println("Sorting was incorrect");
				return;
			}
		}
		System.out.println("Perfect sorting!");

	}
	
	interface Sorter{
		public void sort(int[] arr);
	}

}
```

​	

테스트용 컨트롤러를 작성 해 보았습니다. Sorter 인터페이스를 구현한 정렬 클래스들을 만들 예정이고 첫번째로는 새로 구현한 코드로 정렬해보며 그 시간을 측정하고, 두번째로는 Arrays 클래스에 있는 sort 메서드를 이용해 정렬하며 그 시간을 측정합니다.

​	

![image-20210803115046492](https://github.com/Shane-Park/markdownBlog/raw/master/Algorithm/sort/sort.assets/image-20210803115046492.png)

> Arrays 클래스의 sort 메서드는 Dual- Pivot QuickSort 방식으로 정렬하네요.

​	

각각 정렬을 마친 후에는 제가 작성한 정렬 알고리즘에 에러가 없는지 확인 하기 위해 각 두개의 배열을 비교 합니다. 알고리즘 에러 체크는 잘 정렬된 배열과 비교해도 되고, 아니면 

```java
for(int i=0; i<SIZE-1; i++){
  if(arr[i] > arr[i+1]){
    // 정렬이 제대로 되지 않았음을 알리며 메서드 종료
    return;
  }
}
```

이런식으로 배열 한개로도 검증할 수도 있습니다. 

​	

## Bubble Sort (버블정렬)

일단 첫번째로 , 제가 손코딩 할때 작성했던 방법 그대로 버블정렬을 구현 해 보았습니다. 확인하려고 버블정렬 코드를 다시 찾아봤을때에는 보통 전부 뒤에서 부터 앞으로 정렬하는 코드 뿐이어서 불안 했는데요.

```java
package shane.study.sort;

import shane.study.sort.SortController.Sorter;

public class BubbleSort implements Sorter {

	@Override
	public void sort(int[] arr) {
		final int size = arr.length;
		for(int i=0; i<size; i++) {
			for(int j=i; j<size; j++) {
				if(arr[i] > arr[j]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		
	}


}
```

코드를 작성하고, 실행을 했을때



![image-20210803115636113](https://github.com/Shane-Park/markdownBlog/raw/master/Algorithm/sort/sort.assets/image-20210803115636113.png)

다행히도 손코딩 했던 알고리즘에 문제가 없었습니다! 굳이 문제가 있다면 그 소요 시간이 문제입니다.

조금 더 오래 걸리는 수준이 아니고 그냥 버블정렬을 사용하고 싶은 마음이 쏙 사라지는 성능입니다.

​	

```java
package shane.study.sort;

import shane.study.sort.SortController.Sorter;

public class Bubble2 implements Sorter {

	@Override
	public void sort(int[] arr) {
		for(int i=0; i<arr.length-1; i++){
			boolean flag = false;
			for(int j=0; j<arr.length-i-1; j++){
				if(arr[j]>arr[j+1]){
					int temp = arr[j];       
					arr[j] = arr[j+1]; 
					arr[j+1] = temp;   
					flag = true;
				}
			}
			if(!flag)
				break;
		}

	}


}
```

​	

중간에 정렬이 되었으면 break 하는 코드를 넣어서 작성해 보았는데

​	

![image-20210803115851600](https://github.com/Shane-Park/markdownBlog/raw/master/Algorithm/sort/sort.assets/image-20210803115851600.png)

미세하게 더 좋아지긴 하는데 정렬 라이브러리와 비교하면 이 역시 의미가 없습니다.

​	

### SelectionSort (선택정렬)

오래전의 손코딩 해뒀던 내용을 확인 해보고 싶어 초급자바 Github Repository에서 작성해둔 그대로 가져왔습니다.

```java
package shane.study.sort;

import shane.study.sort.SortController.Sorter;

public class SelectionSort implements Sorter {

	@Override
	public void sort(int[] arr) {
		for(int i=0; i<arr.length-1; i++){
			int min_index = i;
			for(int j=i+1; j<arr.length; j++){

				if(arr[j]<arr[min_index])
					min_index = j; // find min_index
			}
			int temp = arr[i];       //
			arr[i] = arr[min_index]; // swap number in min_index with number in 'i' index
			arr[min_index] = temp;   //
		}

	}

}
```

![image-20210803120459688](https://github.com/Shane-Park/markdownBlog/raw/master/Algorithm/sort/sort.assets/image-20210803120459688.png)

버블정렬에 비해 확실히 유의미한 성능 차이가 보입니다. O(n²) 이라고 다 같은 수준이 아니라고 말하는 것 같네요.

​	

### MergeSort (삽입정렬)

```java
package shane.study.sort;

import shane.study.sort.SortController.Sorter;

public class MergeSort implements Sorter {

	@Override
	public void sort(int[] arr) {
		for(int i=1; i<arr.length;i++){
			int temp=arr[i];
			int j;
			for(j=i-1; j>=0;j--){
				if(temp < arr[j])
					arr[j+1] = arr[j];
				else break;
			}
			arr[j+1] = temp;	
		}

	}

}

```

​	

이번엔 삽입 정렬 입니다.

![image-20210803120821148](https://github.com/Shane-Park/markdownBlog/raw/master/Algorithm/sort/sort.assets/image-20210803120821148.png)

약간 더 개선된 성능을 보여줍니다. 

​	

​	

이제 위의 알고리즘들과는 결이 다르다는 O(nlog(n)) 알고리즘을 테스트 해 보겠습니다.

​	

### QuickSort (퀵정렬)

평균적인 상황에서 최고의 성능을 나타내며, 컴퓨터로 가장 많이 구현된 정렬 알고리즘 중 하나입니다. java에서의 기본 정렬도 이 QuickSort를 응용한 Dual- Pivot QuickSort 을 이용했습니다. 

 퀵정렬은 최악의 경우에 시간복잡도가 O(n²)이 되는데, 피벗을 최소값이나 최대값으로 계속해서 잡는 경우에 그렇습니다. 대표적인 예로 피벗을 항상 배열의 첫 원소로 잡도록 구현해 두었는데 이미 정렬된 배열을 정렬 할 경우에는 전혀 분할이 되지 않고 하나씩 정렬하게 됩니다. 데이터가 이렇게 극단적인 경우에는 대충 구현된 퀵 정렬은 최악의 결과를 초래합니다.

이를 방지하기 위해 개발된 여러 기법들이 있는데, java에서 dual-pivot을 잡는 이유도 상기한 내용 때문입니다.

​	

오른쪽 피벗 선택 방식의 코드를 살펴보겠습니다.

```java
package shane.study.sort;

import shane.study.sort.SortController.Sorter;

public class QuickSort2 implements Sorter {

	@Override
	public void sort(int[] arr) {
		rPivotSort(arr, 0, arr.length-1);
	}
	
	private static void rPivotSort(int[] arr, int low, int high) {
		
		if(low >= high) {
			return;
		}
		int pivot = partition(arr, low, high);	
		
		rPivotSort(arr, low, pivot - 1);
		rPivotSort(arr, pivot + 1, high);
	}
	
	private static int partition(int[] arr, int left, int right) {
		int low = left;
		int high = right;
		int pivot = arr[right];
		
		while(low < high) {
			while(arr[low] < pivot && low < high) {
				low++;
			}
			while(arr[high] >= pivot && low < high) {
				high--;
			}
			
			swap(arr, low, high);
		}
		
		swap(arr, right, high);
		return high;
	}
 
	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

}
```

​	

실행을 해 보면

![image-20210803180859316](https://github.com/Shane-Park/markdownBlog/raw/master/Algorithm/sort/sort.assets/image-20210803180859316.png)

​	

java의 기본 sorting 보다 더 빠르게 끝냈습니다!  몇번을 해도 결과는 비슷했는데요. java 에서 채택한 dual-pivot Quick Sort의 경우에는 최악의 경우를 감안하여 평균적으로 더 좋은 성능을 내도록 설계 되어 있기 때문입니다.

​	

### 그러면 이번엔 그 최악의 경우를 테스트 해 보겠습니다.

```java
package shane.study.sort;

import java.util.Arrays;

import shane.study.sort.SortController.Sorter;

public class SortController2 {

	public static void main(String[] args) {
		
		// 총 배열의 길이 미리 정하기
		final int SIZE = 10000;
		
		// 정렬 알고리즘 선택
		Sorter sorter = new QuickSort();
		
		// 각각 다른 방식으로 정렬할 두개의 배열 만들기
		int[] arr = new int[SIZE];
		int[] arr2 = new int[SIZE];
		for(int i=0; i<SIZE; i++) {
			arr[i] = i;
			arr2[i] = i;
		}
		
		long savedTime = System.currentTimeMillis();
		sorter.sort(arr);
		System.out.println(String.format("Custom sorting에 소요된 시간 : %dms", System.currentTimeMillis() - savedTime ));
		
		savedTime = System.currentTimeMillis();
		Arrays.sort(arr2);
		System.out.println(String.format("library sorting에 소요된 시간 : %dms", System.currentTimeMillis() - savedTime ));
		
		for(int i=0; i<SIZE; i++) {
			if(arr[i] != arr2[i]) {
				System.out.println("Sorting was incorrect");
				return;
			}
		}
		System.out.println("Perfect sorting!");

	}
	
}
```

​	

각 배열에는 숫자가 랜덤이 아닌 0부터 순서대로 커지는 숫자가 들어갑니다. 이미 정렬이 된 상태인데요,

이 상태에서 Quick sort 성능 테스트를 진행 해 보겠습니다.

​	

![image-20210803181453835](https://github.com/Shane-Park/markdownBlog/raw/master/Algorithm/sort/sort.assets/image-20210803181453835.png)

아! 둘다 Quicksort인데, 시간차이가 굉장합니다. MergeSort로 **정렬 되지 않은 같은 길이의 배열을 정렬**할때 같은 시간이 소요됩니다. 정렬이 이미 된 배열을 정렬하는건 MergeSort가 워낙 강해서 비교할 필요도 없습니다.

​	

#### 그런데 여태 SIZE 를 30000으로 했는데 왜 10000으로 줄였을까요?

![image-20210803181812682](https://github.com/Shane-Park/markdownBlog/raw/master/Algorithm/sort/sort.assets/image-20210803181812682.png)

심지어 정렬된 배열을 QuickSort 방식으로 정렬하려고 하니 StackOverflow가 발생했습니다.

아주 기본적인 Quick Sort는 결국 실전에 사용하기에 부족합니다. java에서도 그렇기 때문에 아까 위에서의 예처럼 Quick Sort가 Dual-pivot Quick Sort보다 조금의 성능에서 우위에 있었다고 해서 사용할 수는 없었을 겁니다.

​	

이 외에도 Heap Sort, Tree Sort, 그리고 정렬 알고리즘들을 하이브리드로 모두 사용한 Tim Sort, Intro Sort 등 여러 정렬 알고리즘이 있습니다. 아! 빌게이츠가 제시했던 Pancake Sorting도 있네요.

​	

이렇게 몇 개의 정렬 알고리즘들을 알아 보았는데요 

라이브러리에 있는 정렬 함수들을 사용하더라도, **해당 라이브러리가 어떤 알고리즘으로 이루어 져 있는지, 그리고 상황에 따라 어떠한 정렬 알고리즘을 사용해야 하는지**를 알고 쓴다면 더 즐겁게 코딩 할 수 있을 것 같습니다.

​	

수고하셨습니다!

