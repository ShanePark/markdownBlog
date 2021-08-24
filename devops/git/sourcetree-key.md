# Sourcetree 에서 잘못된 비밀번호로 저장소 접근 안될때 해결방법

​		

소스트리에서는 한번 입력된 비밀번호가 잘못 되어도 절대 다시 입력하라고 하지 않고, 계속해서 비밀번호가 틀렸다는 메시지만 주구 장창 띄웁니다. 심지어 8월 13일 부로 Github에서는 password authentication을 공식적으로 제거하고는 personal access token 만을 사용하도록 정책을 변경 했는데요. 이미 오래전 부터 경고해왔기 때문에 갑작스러운 일은 아닙니다.

​	

하지만 Sourcetree에서는 인증에 실패하였을때 새로 비밀번호를 입력하게끔 해주지 않기 때문에 대책이 필요합니다. 예전에도 팀원 분중 한분이 소스트리에 Gitub 비밀번호를 잘못 입력했다가 로그인 되지 않아서 지웠는데도 계속해서 비밀번호 입력하라는 창은 뜨지 않고 인증 실패만 나오니까, 구글에 "소스트리 완전 삭제" 등을 검색해서 해결 했었는데요. 보다 간단한 방법으로 해결하는 방법을 알려드리겠습니다.

​	

비밀번호가 잘못 되었을때는 흔하게 볼 수 있는 빨간 에러화면 입니다. 이 글을 쓰게 된 계기는 회사에서 Gitlab과 연결 해 둔 Sourcetree의 인증이 풀리면서 고생 했었기 때문에 글로 한번 남겨봐야겠다 생각을 했는데, 해당 상황을 재현하려고 오래전 사용하던 컴퓨터에서 Fetch를 하다보니 마침 비슷한 오류를 마주할 수 있었습니다. 하나씩 함께 해결 해 보겠습니다.

​	

![image-20210824220841746](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/sourcetree-key.assets/image-20210824220841746.png)

```
Command: git -c diff.mnemonicprefix=false -c core.quotepath=false --no-optional-locks ls-remote https://github.com/Shane-Park/programmers.git
Output: 
Error: remote: Support for password authentication was removed on August 13, 2021. Please use a personal access token instead.
remote: Please see https://github.blog/2020-12-15-token-authentication-requirements-for-git-operations/ for more information.
fatal: Authentication failed for 'https://github.com/Shane-Park/programmers.git/'
```

​	

## 목차

- 1. Personal Access Token 발급받기

- 2. Sourcetree에 저장된 비밀번호 제거하기

  

  ​	

## 1. Personal Access Token 발급받기

>  Github 이나 Gitlab등 모두 Token 을 발급받아 로그인 하는 방식을 지원하고 있습니다. Github을 예를 들어 함께 해 보겠습니다. Gitlab도 사용 방법은 비슷 했습니다.



일단 로그인 후 Settings에 들어갑니다.

![image-20210824221020382](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/sourcetree-key.assets/image-20210824221020382.png)

​	

왼쪽 하단에 보면 Developer settings 라고 있습니다.

![image-20210824221225279](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/sourcetree-key.assets/image-20210824221225279.png)

​	

그러면 제일 아래 있는 Personal access tokens를 클릭해 들어갑니다.

여기에서 Generate new token 을 누르면 됩니다.



![image-20210824221245242](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/sourcetree-key.assets/image-20210824221245242.png)

​	

Note에 토큰 이름을 편하게 지정 합니다. 

그리고 scopes 설정을 해주는데, sourcetree 에서 쓰기에는 repo만 있으면 충분 한 것 같습니다. Expiration은 해당 Token의 유효기간인데, No expiration 의 경우에는 상황에 따라 보안상 위혐이 될 수 있기 때문에 권장하지 않습니다.

![image-20210824221343183](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/sourcetree-key.assets/image-20210824221343183.png)

​	

설정을 마쳤으면 Generate token을 클릭해서 생성해줍니다.

![image-20210824221400153](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/sourcetree-key.assets/image-20210824221400153.png)

​	

아래 보이는 것 처럼 Token이 발급되었습니다!.

>  저는 토큰 발급 해서 블로그 첨부용으로 스샷을 찍고 해당 토큰을 바로 Revoke 했지만, 실제 본인이 사용하는 Token의 경우에는 코드가 유출되지 않도록 주의 해야 합니다. 특히나 You won't be able to see it again! 에서 보이는 것 처럼 다시 보여주지 않으니 잘 카피 하거나 로그인 할 때까지는 해당 창을 닫지 않도록 합니다. 저는 어디 적어두는 것도 보안상 좋지 않다고 생각해서 Sourcetree를 연결 할 때 까지 해당 창을 띄워 두고 따로 복사해두지 않아서 토큰값은 아무도 모르게 됩니다.

![image-20210824221433990](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/sourcetree-key.assets/image-20210824221433990.png)

​	

​	

​	

## 2. Sourcetree에 저장된 비밀번호 제거하기

아래의 경로에 있는 파일을 찾아 삭제해줍니다. 해당 파일이 소스트리가 비밀번호를 저장해두는 파일입니다.

```
C:\Users\[계정이름]\AppData\Local\Atlassian\SourceTree\passwd
```

​	

아래의 파일을 삭제해줍니다.

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/sourcetree-key.assets/img.png)



이제  다시 Fetch 나 Pull 을 시도 해 봅니다. 비밀번호를 입력하라는 화면이 나옵니다!

![image-20210824222112113](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/sourcetree-key.assets/image-20210824222112113.png)



그러면 Github 로그인 비밀번호가 아닌 위에서 발급받은 Token 값을 입력 해 줍니다. 후 Login을 누르면

![image-20210824222135991](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/sourcetree-key.assets/image-20210824222135991.png)

​	

Pull을 정상적으로 해냅니다!

![image-20210824222151815](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/sourcetree-key.assets/image-20210824222151815.png)



SourceTree를 사용하다 보면 여러가지 불편 한 상황이 있는데 그중 가장 짜증 나는 상황이 인증 오류입니다. 이렇게 한번 제대로 인증을 해 둔다면 한동안은 인증 오류로 씨름 할 일이 없을 뿐더러 passwd 만 지우면 손쉽게 일단 왠만한 상황은 해결이 되기 때문에 상황을 잘 기억해 뒀다가 다음에 또 이런 상황을 마주하면 간단하게 해결 하면 되겠습니다.