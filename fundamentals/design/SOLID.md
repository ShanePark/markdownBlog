# 객체지향설계 5대원칙 SOLID

![](https://github.com/Shane-Park/markdownBlog/raw/master/fundamentals/design/SOLID.assets/img.png)

> 학원 수업에서 선생님이 항상 강조하셨던 SOLID 원칙이 잘 이해가 되지 않아서 개인적으로 공부하기 위해 여러가지 검색을 해보고 많은 글들을 읽어보았습니다. 그러다 우연히 Youtube에서 41분짜리 영상을 접하게 되었는데, 앞부분만 잠깐 보려고 했던게 결국 영상을 마지막까지 보게 되었고 도움이 제법 많이 되었습니다.

​	

https://www.youtube.com/watch?v=rtmFCcjEgEw

> 북 마케도니아 출신 개발자 Katerina Trajchevska가 2018년 암스테르담에서 열린 컨퍼런스에서 SOLID를 주제로 강연을 했던 내용인데요, 본인의 경험을 예로 들어가며 설명을 잘 해줘서 이해하기가 좋았습니다.



#### SOLID는 Robert. C. Martin (Uncle Bob 이라고 불립니다) 이 처음으로 제안했고, Michael Feathers에 의해 이름 붙여진 5대 원칙입니다. 

## <center> SOLID Principle </center>
|Abbr|Full name|Explanation|
|----|----|---|
|SRP|[single Responsibility Principle](#one)|A class should have one, and only one reason to change|
|OCP|[Open Closed Princicle](#two)|You should be able to extend a classes behavior, without modifying it.|
|LSP|[Liskov Substitution Princicle](#three)|Derived classes must be substitutable for their base classes.|
|ISP|[Interface Segragation Princicle](#four)|Make fine grained interfaces that are client specific.|
|DIP|[Dependency Inversion Princicle](#five)|Depend on abstractions, not on concretions|

​	

​	

### <a id="one"></a>1. Single Reponsibility Principle(단일 책임의 원칙)
- A class should be reponsible for onething.
> 하나의 클래스는 오직 하나의 책임을 가진다.
​	

- There's a place for everything and everything is in its place.
> 모든 클래스들은 자신의 위치가 있으며, 모든 클래스들은 자기 자신의 위치에 머문다.
​	

- Find one reason to change and take everything out of the class
> 어떤 변화에 의해 클래스를 변경해야 하는 이유는 오직 하나뿐이어야 합니다.
​	

- Very precise names for many small classes > generic names for large classes.
> 작은 클래스에는 매우 세분화된 이름을 붙이고, 덩치가 큰 클래스에는 포괄적인 이름이 붙여져야 합니다.

​	

​	        

### <a id="two"></a>2. Open Closed Princicle(개방폐쇄의 원칙)

​	

- An entity should be open for extension, closed for modification
> 확장에는 열려있고, 변경에는 닫혀있어야 합니다.
​	

- Extend functionality by adding new code instead of changing existing code
> 기능적인 확장을 할때는 기존의 코드를 변경하는게 아닌 새로운 코드를 추가함으로써 이루어 져야 합니다.
​	

- Separate the behaviors, so the system can easily be extended, but never broken.
> 행동들을 나누어 두어서 시스템이 쉽게 확장되지만 절대 고장나지 않도록 합니다.
​	

- Goal : get to the point where you can never break the core of your system
- Solution : Factory Pattern    

변화를 막을 수는 없습니다. 다만, 변화가 일어날 가능성에 대해 미리 예측하고 잘 준비해둔다면 적절하게 대응하는게 가능합니다.

​        	

​	

### <a id="three"></a>3. Liskov Substitution Princicle(리스코브 치환의 원칙)
> 리스코브라는 이름은 데이터 추상화, 분산 컴퓨팅등으로 2008년 튜링상을 받은 Barbara Liskov의 last name에서 따왔습니다. MIT 교수님인데, 2021년 지금 기준으로 무려 80대 할머니이십니다. 튜링상을 받은 시점을 기준으로도 70대 였는데, 정말 대단한 분입니다.    
> &nbsp;아래 링크는 본인이 직접 리스코브 치환원칙에 대해 설명해주시는 4분짜리 유투브 클립입니다. 90년대에 해당 원칙에 대한 질문 이메일 하나를 받으셨다는데, 그 전까진 이런 이름의 원칙이 있는지도 모르셨다고 웃으십니다. "Subtype should behave like a super type as far as you can tell by using the super type methods"    
> &nbsp;https://www.youtube.com/watch?v=-Z-17h3jG0A
- Any derived class should be able to substitute its parent class wiout the consumer knowing it
> 모든 파생 클래스는 사용자가 알지도 못할 정도로 그 부모 클래스를 대신할 수 있어야 합니다.
​	

- Every class that implements an interface, must be able to substitute any reference throughout the code that implements that same interface
> 모든 클래스는 같은 인터페이스를 구현한 다른 구현체를 대신할 수 있어야 합니다.
​	

- Every part of the code should get the expected result no matter what instance of a class you send to it, given it implements the same interface.
> 같은 인터페이스를 구현한 모든 인스턴스들은 코드의 어떤 부분에서든 동일하게, 예상된 결과를 기대할 수 있어야 합니다.    

​	

![](https://github.com/Shane-Park/markdownBlog/raw/master/fundamentals/design/SOLID.assets/1.jpeg)
오리가 있고 RubberDuck이 있습니다. 둘다 꽥 소리를 내고, 둘다 오리처럼 생겼습니다. 그렇다면,  
class RubberDuck extends Duck{} 이 가능할까요? 그건 불가능합니다. 단적인 예로 오리는 혼자 움직일 수도, 때론 날라갈 수도 있지만 Rubber duck은 배터리 없이는 어떤 동작도 할 수 없습니다.

​	

​	        

### <a id="four"></a>4. Interface Segragation Princicle(인터페이스 분리의 원칙) 
SRP가 클래스의 단일 책임에 대한 내용이라면, ISP는 인터페이스의 단일 책임에 대한 내용입니다.

​	

- A client should never be forced to depend on methods it doesn't use.
> client는 사용하지 않는 메서드에 의존해서는 안됩니다.
​	

- Or client should never depend on anything more than the method it's calling.
> 혹은 client는 본인이 호출하는 메서드 외의 어떤 메서드에도 의존해서는 안됩니다.
​	

- Changing one method in a class shoudln't affect classes that don't depend on it.
> 클래스에서 한가지 메서드를 변경할때는, 이에 의존하지 않는 어느 클래스에도 영향을 줘서는 안됩니다.
​	

- Replace fat interfaces with many small, specific interfaces.
> 덩치가 큰 인터페이스를 구체적이고 작은 여러개의 인터페이스로 쪼개야 합니다.

​	

학생들은 교수님들에게 가르침을 받습니다. 청소, 설거지, 빨래, 요리 등을 요구하지 않습니다. 우리는 가르침이라는 메서드를 가진 [교수님] 이라는 인터페이스 만을 사용하지, 같은 구현체의 [엄마] 혹은 [친구]라는 인터페이스가 가진 메서드들을 사용하지 않습니다.

​        

​	

### <a id="five"></a>5. Dependency Inversion Princicle(의존성 역전의 원칙)

​	

- Highlevel modules should not depend on low-level modules. Both should depend on Abstractions.
> 고레벨 모듈들은 저레벨 모듈에 의존해서는 안됩니다. 둘 모두 추상화에 의존해야 합니다.
​	

- Never depend on anything concrete, only depend on abstractions
> 구현체에 의존해서는 안됩니다. 인터페이스에 의존해야합니다.
​	

- Able to change an implementation easily without altering the high level code.
> 고수준의 코드를 변경하지 않고도 쉽게 구현을 변경할 수 있어야 합니다.

​    	

### Don't get trapped by SOLID. don't try to archieve SOILD, use SOLID to achieve maintainability.
> 마지막으로 Katerina Trajchevska가 당부했던 내용입니다. 원칙은 원칙일 뿐, 그 원칙을 지키는데 목표를 두지 말고, SOLID원칙을 이용해서 유지보수에서의 이점을 달성하라고 합니다.   
&nbsp;아무리 멋진 디자인의 제품이어도 요구사항이 충족되지 않으면 아무 의미 없습니다.

​        
### 참고자료
- https://www.youtube.com/watch?v=rtmFCcjEgEw    
- https://en.wikipedia.org/wiki/SOLID    
- https://www.nextree.co.kr/p6960/