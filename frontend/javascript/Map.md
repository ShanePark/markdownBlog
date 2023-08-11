# 제발 한국인이라면 자바스크립트 Object를 Map 처럼 사용하지 맙시다.

## Intro

오늘도 열심히 코딩하던 중, 자바스크립트에서 만든 key-value 쌍을 서버사이드에 어떻게 넘길지 고민 하는 일이 있었습니다. 

자바스크립트에서는 **Object** 를 사용해 다른 언어에서의 Map 과 유사하게 사용 할 수 있기 때문에 딱히 Map을 사용 해 본 적이 아직까지는 없었는데요

<img src="https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/javascript/Map.assets/image-20210902220829730.webp" width=643 height=217>

**org.springframework.boot.json.JsonParser** 인터페이스를 확인해보면 JSON 데이터를 List 혹은 Map 으로 파싱할 수 있습니다. 그래서 Object 형태로 보내지 않고 Map 형태로 마샬링해서 보낸다면 다시 언마샬링 할 때도 좀 더 간단하지 않을까 하는 생각에 자바스크립트 진영에도 Map이 있는지와 어떻게 쓰는지에 대해 찾아보았습니다.

https://betterprogramming.pub/stop-using-objects-as-hash-maps-in-javascript-9a272e85f6a8

자바스크립트의 Map 사용에 대한 참고자료를 찾다가 위의 글을 우연히 찾아 읽어봤는데, 정리도 잘 되어있고 Object를 쓰지 말고 왜 Map을 써야 하는지에 대해 명확히 설명해 주기에 번역 및 개인적으로 정리를 한번 해 보았습니다.

## Javascript Map Object

### Map

Map은 우리가 코딩할 때 가장 흔하게 사용하는 자료 구조 중 하나 입니다. `key`-`value` 쌍으로 이루어진 **Map**은 **key**를 통해 아주 쉽게 데이터에 접근 할 수 있는데요 

> 자바에서는 Map을 사용 할 때 순서가 필요없는 대부분의 상황에서는 HashMap 구현체를 사용하고 있습니다.

특이하게도 자바스크립트 에서는 그냥 일반 **Object**를 Map처럼 사용하는 것도 가능하고 정말 편리합니다. 그래서 많은 분들이 최소 한번 이상 그렇게 사용 해 보셨을 거라고 생각합니다.

- 아래의 코드는 Javascipt Object를 Map 처럼 사용하는 예 입니다.

```javascript
const map = {};

// key-value pair 넣기
map['key1'] = '값1';
map['key2'] = '값2';
map['key3'] = '값3';

// 특정 key를 가지고 있는지 확인하기
if (map.hasOwnProperty('key1')) {
  console.log('Map이 key1을 포함하고 있음.');
}

// 특정 key의 value를 찾기
console.log(map['key1']);
```

하지만 오직 이 목적을 위해 만들어진 자바스크립트 객체가 있습니다. 그 이름도 **Map** 입니다.

> https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Map

그리고 당연하게도 <u>모든 브라우저</u>에서 지원 합니다.

![image-20210902222822091](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/javascript/Map.assets/image-20210902222822091.webp)

> IE 만 안되니까 다 되는게 맞습니다!

## 왜 Map을 써야 하는가?

자바스크립트에서 Map을 Object 대신 사용하면 여러가지 장점이 있습니다. 한번 같이 알아보겠습니다.

### 1. Key로 여러가지 타입을 사용 할 수 있습니다.

```javascript
const map = new Map();
const myFunction = () => console.log('function 입니다.');
const myNumber = 1234;
const myObject = {
  name: 'plainObject',
  otherKey: '다른 값'
};

map.set(myFunction, 'key 로 function을 사용한 예');
map.set(myNumber, 'key 로 number를 사용한 예');
map.set(myObject, 'key 로 object를 사용한 예');

console.log(map.get(myFunction)); // key 로 function을 사용한 예
console.log(map.get(myNumber)); // key 로 number를 사용한 예
console.log(map.get(myObject)); // key 로 object를 사용한 예
```

위의 코드를 한번 보겠습니다. 

map의 **key**로 `function`, `number` , 그리고 `Object` 를 사용했습니다. 만약 Object를 Map 용도로 사용했다면 **String** 밖에 사용 못 했을 텐데 말이죠.

### 2. Map의 크기를 훨씬 빠르고 쉽게 측정 할 수 있습니다.

- Map 사용

```javascript
const map = new Map();
map.set('키1', 1);
map.set('키2', 1);
...
map.set('키100', 1);

console.log(map.size) // 100, 실행시간: O(1)
```

map의 크기를 O(1) 의 시간복잡도로 금새 구할 수 있습니다. 코드가 간단한건 덤 입니다.

- Object 사용

```javascript
const plainObjMap = {};
plainObjMap['키1'] = 1;
plainObjMap['키2'] = 1;
...
plainObjMap['키100'] = 1;

console.log(Object.keys(plainObjMap).length) // 100, 실행시간: O(n)
```

객체의 key들을 받아온 후, 그 길이를 구해야 합니다. `O(n)` 시간이 소요 됩니다.

### 3. 더 나은 성능

**Map**은 entry들의 잦은 추가와 제거에 최적화 되어 있으며 그 목적으로 만들어졌습니다. 게다가 위에서 알아 본 것 처럼 entry 갯수를 구하는데 항상 일정한 O(1) 시간이 듭니다. 

반면 **Plain Object**의 entry 수를 구하려면 하나씩 일일히 카운팅 되어야 해서 O(n)의 시간이 들죠. 

원 글 필자의 경우 1000만개의 entry들이 들어간 데이터를 대상으로 맥북 프로에서 Plain Javascript Object의 size를 재는데는 1600ms 가량의 시간이 소요 된 반면, Map 에서는 1ms 이하의 시간이 들었다고 합니다. 또한 어떠한 key 도 string으로 캐스팅할 필요가 없기 때문에 아주 많은 시간을 절약 할 수 있습니다.	

### 4. 직접 Iteration 합니다.

Object는 key를 먼저 모두 찾아 낸 다음에야 그것들을 토대로 순회합니다. 

하지만 Map은 어떨까요? Map은 그 자체가 iterable 하기 때문에 바로 사용 할 수 있습니다.

```javascript
// 1. Map을 순회하는 예
const map = new Map();
map.set('키1', 1);
map.set('키2', 2);
map.set('키3', 3);

for (let [key, value] of map) {
  console.log(`${key} = ${value}`);
}

// 2. Plain Object를 순회하는 예
const plainObjMap = {};
plainObjMap['키1'] = 1;
plainObjMap['키2'] = 2;
plainObjMap['키3'] = 3;

for (let key of Object.keys(plainObjMap)) {
  const value = plainObjMap[key];
  console.log(`${key} = ${value}`);
}

// * 출력 결과는 동일
// 키1 = 1
// 키2 = 2
// 키3 = 3
```

### 5. 무려 Key에 순서가 있습니다!

ECMAScript 2015 이전에는 Object의 key의 특정 순서가 보장 되지 않았습니다. 반면 Map을 통한 Iterating의 경우에는 추가한 순서에 따른 순번을 보장 해 줍니다.

### 6. Key overriding이 없습니다!

`Plain Object`는 스스로의 **prototype** 때문에 특정 몇몇 **key**들을 예약어처럼 가지고 있습니다. 이러한 현상은 일반 객체가 자신의 프로토타입 체인을 가지고 있기 때문에 발생합니다. Object의 프로토타입에는 일반적으로 `toString`, `constructor`, `valueOf` 등과 같은 메서드가 미리 정의되어 있으며, 이러한 key를 사용하면 프로토타입의 메서드와 충돌이 발생할 수 있습니다.

> ECMAScript 2015 부터는 Object.create(null)을 통해 plain object map을 만들 수 있기는 합니다.

```javascript
const map = new Map();
map.set('키1', 1);
map.set('키2', 2);
map.set('toString', 3); // Map에서는 문제 없습니다.

const plainObjMap = {};
plainObjMap['키1'] = 1;
plainObjMap['키2'] = 2;
plainObjMap['toString'] = 3; // toString은 이미 선점되어 있습니다. toString()을 사용할 수 없게됩니다.
```

## 결론

Map의 구현이 되지 않아서 Object를 사용 해야 했던 시대는 끝났다고 합니다. 

Object가 매우 유용한 것은 사실이지만, 더 이상은 key-value 쌍이 필요한 상황에서 최선의 선택은 아닙니다.

이제 간단히 Map의 몇몇 함수들을 정리하며 글 마치겠습니다.

- 생성자 

```javascript
let map = new Map();
```

- Map 크기 확인

```javascript
map.size;
```

- 모든 key,value 지우기

```javascript
map.clear();
```

- key로 value 구하기

```javascript
map.get(key);
```

- 특정  key를 가지고 있는지 확인하기

```javascript
map.has(key)
```

- 특정 key로 value 를 Map에 넣기 (반환값은 Map object)

```javascript
map.set(key, value);
```

- [key,value]의 array 를 삽입 된 순서대로 가진 새로운 Iterator 반환

```javascript
map.entries()
```

이상입니다. 

**Reference**

- https://betterprogramming.pub/stop-using-objects-as-hash-maps-in-javascript-9a272e85f6a8
