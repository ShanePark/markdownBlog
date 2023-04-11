# javax.mail 이용해 이메일 보내기

play ddit에서 웹사이트에 회원가입시 저희는 '이메일'을 회원 아이디로 할 예정입니다.

> 이메일을 통해 대부분의 확인을 할 예정이기때문에 회원가입시 입력한 이메일이 본인의 것이라는게 확인이 되어야만 합니다.

​	

백엔드 서버를 Servlet으로 구현할 예정이기 때문에, 자바에서 입력한 이메일로 본인의 이메일임을 확인 할 수 있는 코드를 보낼 예정입니다.

>해당 코드는 랜덤으로 생성한 난수를 보낼 것이며, 정확히 입력시 본인의 이메일임을 증명할 수 있습니다.
​	



시작에 앞서 본인이 사용하실 이메일의 SMTP 설정을 확인해주세요. 저는 gmail을 기준으로 설명하겠습니다.

​	

<img src=https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/javaMail.assets/1.webp width=750 height=500 alt=1>



☐ 전체코드를 먼저 한번에 올린 뒤 파트별로 따로 설명해드리겠습니다.



```java
package users.main.signUp;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class VerifyEmail {
	
	public static void main(String[] args) {
		String recipient = "psh40963@naver.com";
		String code = "abc";
		
		// 1. 발신자의 메일 계정과 비밀번호 설정
		final String user = "ddit302@gmail.com";
		final String password = "********";
		
		// 2. Property에 SMTP 서버 정보 설정
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		// 3. SMTP 서버정보와 사용자 정보를 기반으로 Session 클래스의 인스턴스 생성
		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		
		// 4. Message 클래스의 객체를 사용하여 수신자와 내용, 제목의 메시지를 작성한다.
		// 5. Transport 클래스를 사용하여 작성한 메세지를 전달한다.
		
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(user));
			
			// 수신자 메일 주소
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			
			// Subject
			message.setSubject("PLAYDDIT verification code");
			
			// Text
			message.setText("Welcome to playddit. your code is ["+code+"]");
			
			Transport.send(message);	// send message
			
			
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}

```

​	

​	

1. 저는 이용자에 따라 달라지는 변수인 수신자와, 난수로 설정할 값을 코드 제일 위에 선언했습니다.

```java

String recipient = "psh40963@naver.com";
String code = "abc";
```



2. 발신자의 메일 계정과 비밀번호를 입력해줍니다. 제가 **로 적어뒀지만, 구현하실때는 본인의 비밀번호를 꼭 입력해주세요.

```java
final String user = "ddit302@gmail.com";
final String password = "********";
```

​	

3. Property에 SMTP 서버의 정보를 설정해줍니다.    

>  사용하시는 이메일 계정에서 SMTP 사용에 대한 허용도 꼭 해주셔야 합니다.

```java
Properties prop = new Properties();
prop.put("mail.smtp.host", "smtp.gmail.com");
prop.put("mail.smtp.port", 465);
prop.put("mail.smtp.auth", "true");
prop.put("mail.smtp.ssl.enable", "true");
prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
```



4. SMTP 서버정보와 사용자 정보를 기반으로 Session 클래스의 인스턴스 생성해주기  

   > 괄호가 여러개 있으니 신중하게 구조를 봐주세요.  

```java
Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(user, password);
	}
});
```

​	

5. Message 클래스의 객체를 사용하여 수신자와 내용, 제목의 메시지를 작성한 뒤, transport 클래스를 사용하여 작성한 메세지를 전달합니다.

```java
MimeMessage message = new MimeMessage(session);
try {
	message.setFrom(new InternetAddress(user));
	
	// 수신자 메일 주소
	message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			
	// Subject
	message.setSubject("PLAYDDIT verification code");
			
	// Text
	message.setText("Welcome to playddit. your code is ["+code+"]");
			
	Transport.send(message);	// send message

} catch (AddressException e) {
	e.printStackTrace();
} catch (MessagingException e) {
		e.printStackTrace();
}
```



위 코드를 실행하면 아래와 같이 이메일이 몇 초 내에 도착하는걸 확인할 수 있습니다.

​	

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/javaMail.assets/2.webp)	

