# Oracle Database가 갑자기 안될 때 TNS-12541, TNS-12560, TNS-00511 에러 해결



### 방금 까지만 해도 잘 되던 Oracle Dabase가 재부팅 이후에 갑자기 안됩니다.

딱히 건든 게 없는 것 같은데 정말 멘붕이 옵니다. 하나씩 이유를 찾아 해결 해 보도록 합시다.

​				

일단 리스너의 상태를 확인 해 봅니다. 

```
lsnrctl status
```

​	

![image-20210906214022328](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/listener.ora.assets/image-20210906214022328.png)

에러가 세개나 나옵니다.

​			

```
TNS-12541: TNS:no listener
 TNS-12560: TNS:protocol adapter error
  TNS-00511: No listener
   64-bit Windows Error: 2: No such file or directory
```

​	

그런데 Connecting to 쪽이 뭔가 의심이 갑니다. HOST=DESKTOP-어쩌구 라고 써있는데, 저는 방금 그걸 알기 쉽게 변경했었거든요. 이게 문제였다는게 어느 정도 감이 옵니다. 이제 해결하면 되겠습니다.

HOST 정보를 변경 해야 합니다.

​				

### listener.ora 파일을 찾습니다.

![image-20210906214342369](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/listener.ora.assets/image-20210906214342369.png)

​		

사실 정확히 어디에 있는지 모르고 검색을 하면 시간만 엄청 오래 잡아먹고 못 찾을 수도 있습니다. oracle이 설치 되어 있을 만한 경로를 뒤져서 좁히는게 좀 더 빠른데요, 저의 경우에는 C:\oraclexe 경로에 오라클이 설치 되어 있었습니다.

​	

### listener.ora의 경로를 찾아 갑니다.

![image-20210906214630073](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/listener.ora.assets/image-20210906214630073.png)	

경로가 굉장히 복잡하네요. 저의 경우에는

```
C:\oraclexe\app\oracle\product\11.2.0\server\network\ADMIN
```

에서 찾았습니다.

​	

이제 파일을 열어 수정할 것 입니다.

​	![image-20210906214740479](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/listener.ora.assets/image-20210906214740479.png)

​	

HOST = 에 적혀 있는 것을 보면, 제가 컴퓨터 이름을 변경 하기 전의 이름이 적혀 있습니다. 



​	새로 변경한 이름을 확인 해서

![image-20210906221320061](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/listener.ora.assets/image-20210906221320061.png)

​	

### HOST 를 새로 변경한 이름으로 바꿔줍니다.

![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/listener.ora.assets/image-20210906221914249.png)	

​		

### 그 다음으로는 Oracle service 들을 재시작 해 줘야 합니다.

윈도우키 + R 을 눌러 아래 내용을 입력 해 줍니다.

```
services.msc
```

​	

​	<img src="https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/listener.ora.assets/image-20210906215038222.png" alt="image-20210906215038222" style="zoom:50%;" />

​		

그러고는 Oracle 관련된 서비스들을 재시작 해줍니다.

![image-20210906215135923](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/listener.ora.assets/image-20210906215135923.png)

​	

![image-20210906215243915](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/listener.ora.assets/image-20210906215243915.png)	

그러고 나서 이제 다시 리스너의 상태를 확인 해 봅니다.

```
lsnrctl status
```

​	

![image-20210906215327685](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/listener.ora.assets/image-20210906215327685.png)

리스너가 잘 작동 되고 있습니다만 xe 가 안보이네요.

​	

### ora-12514, tns:listener does not currently know of service requested in connect descriptor

![image-20210906215706158](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/listener.ora.assets/image-20210906215706158.png)

여전히 해당 에러를 내며 되지 않습니다. xe가 없어서 그렇습니다.

​	

### 아까 listener.ora 가 있던 동일 폴더의 tnsnames.ora 파일을 열어 보면

![image-20210906220154699](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/listener.ora.assets/image-20210906220154699.png)

XE 라는 이름에 HOST 가 바뀌지 않아서 XE 를 못 찾고 있었나 봅니다.

​	

### XE 의 HOST는 localhost로 변경 해 줍니다.	

![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/listener.ora.assets/image-20210906222029422.png)

​		

### 이제 아까처럼 서비스들을 재시작 해줍니다.

> 사실 리스너 서비스만 재 시작 해도 충분 할 것 같습니다.

![image-20210906220355457](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/listener.ora.assets/image-20210906220355457.png)

​			

혹은 이렇게 리스너를 재시작 할 수 있습니다.

```
lsnrctl stop
```

​		

![image-20210906220525266](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/listener.ora.assets/image-20210906220525266.png)

참고로 관리자 권한으로 실행하지 않으면 위와 같이 The user is not authorized to execute the requested listener command 에러가 발생 합니다.

​	

관리자 권한으로 stop 후 start 까지 다시 해줍니다.

```
lsnrctl start
```

​		

![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/listener.ora.assets/image-20210906221745053.png)

"xe" 를 Listening 하기 시작하네요.

​	

![image-20210906221717173](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/listener.ora.assets/image-20210906221717173.png)

SQL Developer에서 확인해 보니 이제 정상적으로 작동 합니다.

컴퓨터 이름 한번 바꿨다가 좋은 거 배워갑니다 ! 이상입니다.