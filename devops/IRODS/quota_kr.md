# IRODS ) Quota 제한 넘기지 못하도록 강제하기

> reference https://groups.google.com/g/irod-chat/c/2Fu71_sWv7Q/m/pL6D9yZVUW0J

​	

## 1. core.re 설정변경

​	

### docker를 통해 irods에 접속합니다.

![image-20210915174759987](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/IRODS/quota_kr.assets/image-20210915174759987.png)

​		

### core.re 파일을 찾습니다.

```bash
find / -name core.re
```

![image-20210915174956647](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/IRODS/quota_kr.assets/image-20210915174956647.png)

​	

### core.re 파일을 수정합니다.

```bash
vi /etc/irods/core.re
```

![image-20210915175138688](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/IRODS/quota_kr.assets/image-20210915175138688.png)

​	

### vim 에서 acRescQuotaPolicy 를 검색합니다.

```
:/acRescQuotaPolicy
```

![image-20210915175323371](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/IRODS/quota_kr.assets/image-20210915175323371.png)

​	

### 해당 설정을 on 으로 변경합니다.

![image-20210915175353639](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/IRODS/quota_kr.assets/image-20210915175353639.png)

​	

## 2. 변경사항 확인

## 		

### iRODS 에 설정된 Quota의 목록들을 확인해봅니다.

```bash
iadmin lq
```

![image-20210915174721844](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/IRODS/quota_kr.assets/image-20210915174721844.png)

> admin 유저가 할당된 quota 이상으로 사용하고 있는게 확인됩니다.

​		

접속중인 유저의 quota를 확인하는 명령어는

```bash
iquota
```

![image-20210915175622038](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/IRODS/quota_kr.assets/image-20210915175622038.png)

​		

​	

설정은 이게 전부 입니다. 하지만 iadmin cu 를 입력해 quota를 계산 해줘야만 합니다.

> cu (calulate usage (for quotas))

```bash
iadmin cu
```

![image-20210915181546366](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/IRODS/quota_kr.assets/image-20210915181546366.png)

​	quota를 계산 해주지 않으면 설정을 on 으로 변경 했음에도 불구하고 계속해서 업로드 되는 상황이 있었습니다.



### Quota 초과시 Jaron이 던지는 에러 메시지는 아래와 같습니다.

![image-20210915181027881](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/IRODS/quota_kr.assets/image-20210915181027881.png)

​	

처음에는 Quota를 변경 했을 때 즉각해서 해당 사항이 적용 되었습니다. 다만, 여러번의 시도를 한 결과 몇번씩은 Quota를 넉넉하게 변경 해 줬음에도 같은 에러를 똑같이 뱉는 상황이 있었습니다. 반대로 Quota를 줄였는데도 불구하고 추가 업로드가 가능할 때도 있었습니다.

그럴때에는 

```bash
iadmin cu
```

를 수행할 경우 정상적으로 작동 하는 것으로 봐서, calculate usage를 iRODS가 주기적으로 하기는 하지만 그 텀이 충분히 짧지는 않은 것 같습니다.

용량 제한을 확실하게 하기 위해 서는 매번 파일 삭제/업로드 전에 iadmin cu 명령 수행이 필요해 보입니다.

​		

iRODS가 던진 exception code -110000 을 활용해 예외 처리를 하면 되겠습니다.

