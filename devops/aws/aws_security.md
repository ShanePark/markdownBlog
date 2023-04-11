# AWS 특정 ip 에서만 접속 될때 해결방법
Amazon Web Service 프리티어 서버를 열때 public으로 연다고 연 것 같은데 서버를 생성한 장소에서의 DB에 접속은 문제가 없었는데  다른 곳에서 접속을 하니 접속을 거부 당하는 상황이 있었습니다.  

>localhost가 아니니 외부에서도 접속이 되어야 하는게 당연한데    
특정 ip에서만 접속이 된다면 서버 보안상 특정 ip에서만의 접속만을 허용했을 가능성이 높습니다.

일단 접속정보나 아이디/비밀번호를 잘못 입력했는지 확실하게 확인 후, 특정 아이피에서만 접속된다는게 확실시 될 경우에는 아래의 과정을 통해 문제를 해결하실 수 있습니다.

​	

### 지금부터 AWS 보안 설정을 변경해, 허용 ip 를 추가 하거나, 어느 ip 에서도 접속이 가능하도록 허용하는 방법에 대해 알아보겠습니다.

#### 1 AWS 로그인을 한 뒤, AWS Management Console 창으로 들어가줍니다.

<img src="https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/aws/aws_security.assets/1.webp" width=750 height=470 alt=first>

​	

#### 2 DB instance로 들어 갑니다

![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/aws/aws_security.assets/2.webp)

​	

#### 3 본인의 RDS 인스턴스에 들어가시면 Security 설정을 할 수 있는 곳이 있습니다.

![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/aws/aws_security.assets/3.webp)

​	

#### 4 Security group ID에 들어가면 보안 설정을 하실 수 있습니다.

![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/aws/aws_security.assets/4.webp)

​	

#### 5 inbound rules를 수정해주시면 됩니다.   

맨 위에 제가 처음 에 쓰던 집 컴퓨터의 ip주소가 있습니다. 이후 학원 아이피를 아래에 추가해서 쓰다가 지금은 전체 공개를 위해 0.0.0.0 아이피를 추가해서 사용하고 있습니다.    
Oracle-RDS로 Type 설정을 해주는걸 잊지 마세요.
![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/aws/aws_security.assets/5.webp)

​	

#### 6 Edit inbound rules를 눌러주셨으면 들어오는 화면입니다.

여기에서 수정을 한 뒤에 save rules를 잊지 말고 눌러주세요. 
![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/aws/aws_security.assets/6.webp)

설정을 모두 마치고 나면 문제 없이 접속 되는걸 확인 하실 수 있습니다.