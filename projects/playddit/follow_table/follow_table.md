# follow 테이블 설계

> follow table은 유저들간의 follow를 구현하기 위해 만든 테이블 입니다.

users 테이블에 있는 각각의  유저들은 서로를 follow 할 수 있습니다.

해당 기능을 위해서는 유저들간의 관계를 맺어주는 테이블이 필요합니다.

follow 관계에 대한 정보를 가지고 있는 follow 테이블을 생성했습니다.

![img](table.png)

FOLLOW 테이블은 어떤 유저가 어떤 유저를 follow 하고 있는지에 대한 정보를 담고 있습니다.

follower 는 'follow'를 하는 유저

followee 는 follower 에게 follow를 받는 유저를 뜻합니다.

follower 와 followee 모두, users 테이블에서 Foreign key 로 연관되어 있습니다.

> ID Column은 number값을 갖습니다. follow 테이블에서 pk가 필요하다 보니 억지로 들어간 셈인데, follower 와 followee 복합키로 pk를 만들어도 괜찮을 것 같습니다.

아직은 users 테이블도 임시 테이블일 뿐이니, 단지 follow를 데이터베이스 상에서 어떤 식으로 구현을 할 예정인지를 설명하는 정도로 이해해주세요.

특정 유저가 follow 하고 있는 유저들을 조회한다 했을때, follow 하고 있는 유저들의 아이디만 조회한다면 follow 테이블에서 해결이 되지만, follow 중인 회원들의 정보가 필요하다면, 테이블의 조인이 필요합니다.

특히, users 테이블의 column 값들을 똑같이 받아온다면, usersVO를 재활용 할 수 있으니 더욱 좋겠죠.

```jsx
select followee
from follow
where follower = 'a001';
```

위의 쿼리를 통해 a001 회원이 팔로우하고 있는 회원들이 아이디 목록을 볼 수 있습니다.

쿼리 결과는 다음과 같습니다.

![img](1.png)

```jsx
select *
from users
where user_id = 'a004';
```

위의 쿼리를 통해서는 a004 회원의 정보를 불러올 수 있습니다. 쿼리 결과는 다음과 같습니다.

![img](2.png)

위의 두 쿼리들을 서브쿼리로 연결해서 테이블간 조인을 해준다면, 특정회원이 팔로우 하고 있는 회원들의 정보를 조회할 수 있습니다.

```jsx
select *
from users
where user_id in (select followee 
		    from follow
		   where follower='a001');
```

where 절에서 = 이 아닌 in 이 쓰인것에 주목해주세요. 서브쿼리 안의 쿼리에서 불러오는 값이 여러개고, 해당 아이디들의 정보를 모두 불러와야 하니 in 을 사용합니다.

위 쿼리의 결과는 다음과 같습니다.

![img](3.png)

users 의 컬럼값들을 동일하게 받으니 UsersVO 를 동일하게 사용 할 수 있습니다.

List<UsersVO> 로 결과값을 받아오는 메서드를 작성하면 특정 회원이 follow 중인 회원들의 정보를 확인 할 수 있습니다. 

반대로 특정 회원을 follow 하는 회원들의 정보를 받아오는 쿼리도 필요합니다.

위의 쿼리에서 select 를 follower 로, where 절을 followee= '기준되는 회원 아이디' 로 변경하면 간단하게 조회 할 수 있습니다.

해당 테이블에 대해 질문이 있다면 댓글로 달아주세요! 감사합니다.
