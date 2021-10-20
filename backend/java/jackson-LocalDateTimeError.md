# Jackson 으로 LocalDateTime serialize 에러

​	

> ref : https://stackoverflow.com/questions/45863678/json-parse-error-can-not-construct-instance-of-java-time-localdate-no-string-a

Entity에 LocalDateTime을 하나 추가했더니 로그인 시 아래와 같은 에러가 발생했습니다.

```
JSON parse error: Cannot construct instance of `java.time.LocalDateTime` (no Creators, like default construct, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
```

​	

## 이유

LocalDateTime은 Java 8 에서 추가 된 기능이지만 Jackson Mapper 라이브러리가 그 이전의 버전이기 때문에 발생 했습니다.

​	

## 해결

```xml
<dependency>
    <groupId>com.fasterxml.jackson.datatype</groupId>
    <artifactId>jackson-datatype-jsr310</artifactId>
</dependency>

```



