# 제발 한국인이라면 자바스크립트 Object를 Map 처럼 사용하지 맙시다.	

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/javascript/Map.assets/aa.webp)

>  Photo by [Steve Johnson](https://unsplash.com/@steve_j?utm_source=medium&utm_medium=referral) on [Unsplash](https://unsplash.com/?utm_source=medium&utm_medium=referral)

## Introduction

오늘 코딩하던 중에 Javascript 에서 만든 Map을 Controller 쪽으로 어떻게 넘길지에 대해 고민 하는 일이 있었습니다. JavaScript 에서는 Object 를 사용하면 왠만하면 다른 언어에서의 Map 과 유사하게 사용 할 수 있기 때문에 딱히 Map을 사용 해 본 적이 없었는데요.  

![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/javascript/Map.assets/image-20210902220829730.webp)

특히 나중에 자바에서 스프링 부트에서 제공하는 JsonParser 를 이용하면 JSON 데이터를 List 아니면 Map 으로 Parsing을 하더라고요. Object 형태로 보내지 않고 Map을 Marshalling 해서 보낸다면 다시 언마샬링 할 때도 좀 더 쉽게 하지 않을까 생각 했습니다.

https://betterprogramming.pub/stop-using-objects-as-hash-maps-in-javascript-9a272e85f6a8

그래서 Javascript에서의 Map 사용에 대한 reference를 찾다가 만난 글 인데 정리도 좋고 Object를 쓰지 말고 왜 Map을 써야 하는지에 대해 명확히 설명해 주기에 한번 읽고 정리해보았습니다.

## Javascript Map Object

### Map

Map은 우리가 코딩할 때 가장 흔하게 사용하는 자료 구조 중 하나 입니다. key-value 쌍으로 이루어진 Map들은 그것의 key를 통해 아주 쉽게 데이터에 접근 할 수 있는데요. 자바에서는 Map을 사용 할 때 대부분의 상황에서는 HashMap 사용하게 됩니다. 물론 저도 Map을 생성할 때 대부분의 순서가 필요 없는 상황에서는 HashMap을 생성 합니다. 

그런데, javaScript 에서는 그냥 일반 Object를 Map처럼 사용하는 것도 정말 편리합니다. 그리고 많은 분들이 최소 한번 이상 그렇게 사용 해 보셨을 거라고 생각합니다.

- 아래의 코드는 Javascipt Object를 Map 처럼 사용하는 예 입니다.

```javascript
const map = {};

// key-value pair 넣기
map['key1'] = 'value1';
map['key2'] = 'value2';
map['key3'] = 'value3';

// 특정 key를 가지고 있는지 확인하기
if (map.hasOwnProperty('key1')) {
  console.log('Map contains key1');
}

// 특정 key의 value를 찾기
console.log(map['key1']);
```

하지만 오직 이런 목적을 위해 만들어진 Javascipt 자료 구조가 있습니다. 바로 Map 입니다.

> https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Map

그리고 당연하게도 모든 브라우저에서 지원 합니다.

![image-20210902222822091](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/javascript/Map.assets/image-20210902222822091.webp)

> Internet Explorer는 예뻐할래야 예뻐 할 수가 없습니다. IE 만 안되니까 다 되는게 맞습니다.

## 왜 Map을 써야 하는가?

Javascript 에서 Map을 Object 대신 사용하면 여러가지 장점이 있습니다.

### Key로 여러가지 Type을 사용 할 수 있습니다.

```javascript
const map = new Map();
const myFunction = () => console.log('I am a function.');
const myNumber = 1234;
const myObject = {
  name: 'plainObjectValue',
  otherKey: 'otherValue'
};

map.set(myFunction, 'function as a key');
map.set(myNumber, 'number as a key');
map.set(myObject, 'object as a key');

console.log(map.get(myFunction)); // key 로 function을 사용한 예
console.log(map.get(myNumber)); // key 로 number를 사용한 예
console.log(map.get(myObject)); // key 로 object를 사용한 예
```

위의 코드를 한번 보겠습니다. map의 key로 function을, number를 , 그리고 Object 를 사용 할 수 있습니다. 만약 Object를 Map 처럼 사용했다면 문자열 밖에 사용 못 했을 텐데 말이죠.

### Map의 크기를 훨씬 빠르고 쉽게 측정 할 수 있습니다.

- 첫번째로, Map 입니다

```javascript
const map = new Map();
map.set('someKey1', 1);
map.set('someKey2', 1);
...
map.set('someKey100', 1);

console.log(map.size) // 100, Runtime: O(1)
```

map의 크기를 O(1) 의 시간복잡도로 금새 구할 수 있습니다. size 호출만으로 바로 쉽게 값을 얻는 것도 덤 입니다.			

- 이번엔  Object 입니다.

```javascript
const plainObjMap = {};
plainObjMap['someKey1'] = 1;
plainObjMap['someKey2'] = 1;
...
plainObjMap['someKey100'] = 1;

console.log(Object.keys(plainObjMap).length) // 100, Runtime: O(n)
```

객체의 key들을 받아온 후, 그 길이를 구해야 합니다. O(n) 의 시간 복잡도가 소요 됩니다.

### 더 나은 성능

Map은 entry 들의 잦은 추가와 제거에 최적화 되어 있으며 그 목적으로 만들어졌습니다. 

게다가 위에서 알아 본 것 처럼 entry 들의 갯수를 구하는데 항상 일정한 O(1) 시간이 듭니다. 반면 Plain Object의 entry를 구하려면 하나씩 카운팅 되어야 해서 O(n)의 시간이 들죠. 1000만개의 entry들이 들어간 맥북 프로에서 Plain Javascript Object의 size를 재는데는 1600ms 가량의 시간이 소요 된 반면, Map 에서는 1ms 이하의 시간이 들었다고 합니다. 또한 어떠한 key 도 string으로 변환해야 할 필요가 없기 때문에 아주 많은 시간을 절약 할 수 있습니다.	

### 직접 Iteration 합니다.

Object는 순회 하기 위해서 key 들을 구해 내고 그것들을 통해 iterating 합니다. 하지만 Map은 어떨까요? Map은 그 자체가 iterable 하기 때문에 바로 사용 할 수 있습니다.

```javascript
const map = new Map();
map.set('someKey1', 1);
map.set('someKey2', 2);
map.set('someKey3', 3);

for (let [key, value] of map) {
  console.log(`${key} = ${value}`);
}
// someKey1 = 1
// someKey2 = 2
// someKey3 = 3

const plainObjMap = {};
plainObjMap['someKey1'] = 1;
plainObjMap['someKey2'] = 2;
plainObjMap['someKey3'] = 3;

for (let key of Object.keys(plainObjMap)) {
  const value = plainObjMap[key];
  console.log(`${key} = ${value}`);
}
// someKey1 = 1
// someKey2 = 2
// someKey3 = 3
```

### 무려 Key에 순서가 있습니다!

ECMAScript 2015 이전에는 Object의 key의 특정 순서가 보장 되지 않았습니다. 하지만 반면에 Map을 통한 Iterating의 경우에는 추가한 순서에 따른 순번을 보장 해 줍니다.

### Key overriding이 없습니다!

Plain Object는 이미 이것의 prototype 때문에 특정 몇몇 key들을 가지고 있습니다. 그것이 때때로는 충돌을 만들 수 있는데요 Map은 생성 직후에 어떠한 key값도 가지고 있지 않습니다.

> ECMAScript 2015 부터는 Object.create(null)을 통해 plain object map을 만들 수 있긴 합니다.

```javascript

const map = new Map();
map.set('someKey1', 1);
map.set('someKey2', 2);
map.set('toString', 3); // Map에서는 문제 없습니다.

const plainObjMap = {};
plainObjMap['someKey1'] = 1;
plainObjMap['someKey2'] = 2;
plainObjMap['toString'] = 3; // toString은 이미 선점되어 있습니다. 문제가 발생합니다.
```

## 결론

HashMap의 구현이 되지 않아서 Object를 사용 해야 했던 시대는 끝났습니다. 

Object가 매우 유용한 것은 사실이지만, 더이상 HashMap 이 필요할 때 최선의 선택은 아니게 되었습니다.

이제 간단히 Map객체의 몇몇 함수들을 정리하며 글 마치겠습니다.

- 생성자 

```javascript
let map = new Map();
```

- Map 크기 

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

