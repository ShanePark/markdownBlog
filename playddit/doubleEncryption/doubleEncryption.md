## Playddit 비밀번호 이중 암호화 적용기

![](main.jpg)

### 🔐 AES256
Playddit에서는 AES256 방식으로 비밀번호를 암호화 하고 있었습니다. 이유는 단 한가지 학원에서 AES256를 이용한 암호화를 배웠기 때문이었습니다.

```
public static String encryptAES256(String str, String key) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
    String iv = key.substring(0,16);
    byte[] keyBytes = new byte[16];
    byte[] b = key.getBytes("UTF-8");
    int len = keyBytes.length;
    System.arraycopy(b, 0, keyBytes, 0, len);
    
    SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
    Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
    
    byte[] ivBytes = new byte[16];
    System.arraycopy(iv.getBytes(), 0, ivBytes, 0, ivBytes.length);

    c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(ivBytes));
    
    byte[] encryped = c.doFinal(str.getBytes("UTF-8"));
    
    String enStr = Base64.getEncoder().encodeToString(encryped);
    
    return enStr;		
}
```

위의 코드를 통해 평문을 AES256 방식으로 암호화 하였습니다.
AES256은 양방향 암호화 알고리즘입니다. 위의 암호화를 배웠을때는 단방향암호라는걸 몰랐을 때였는데, 위의 방식 만으로는 키를 알고 있으면 누구든 비밀번호를 알아 낼 수 있었기 때문에 어떻게든 비밀번호는 복호화가 불가능 하게끔 만들고 싶었습니다. 특히나 Github 상에 코드를 공개할 예정이었기 때문에 더더욱 신경이 쓰였습니다.

```
public static String encryptPass(String id, String password) {
    String encryptedPass = "";
    try {
        encryptedPass = encryptAES256(password, String.format("playddit%s%s", id,password));
    } catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException
            | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
        e.printStackTrace();
    }
    
    return encryptedPass;
}
```

최대한 머리를 쓴다고 만들었던 암호화 알고리즘 입니다. 암호화 키로 사용자의 id와 비밀번호와 프로젝트 이름까지 넣어서 제 이론상으론 아이디, 암호 양쪽을 모두 알지 않는 이상 비밀번호를 복호화 해낼 수 없도록 했습니다. 나름 최선을 취했다고 생각했었는데 어딘가 남아있던 꺼림칙한 느낌은 끝까지 떨쳐낼 수가 없었습니다.  
그러다 오늘 암호화에 대한 보강 수업을 들으며 단방향 암호화 알고리즘이란걸 알게 되었습니다.

### 🔒 Sha 512
단방향 알고리즘을 배웠습니다. 복호화가 불가능한 암호화 알고리즘이라니. 이걸 왜 여태 몰랐을까 싶습니다.  
배우자마자 바로 부랴부랴 배포중인 프로젝트에 적용하려고 하는데, 문제가 있습니다. 이미 가입한 회원들의 암호를 Sha 512 방식으로 새로 암호화 할 수가 없습니다. 
>원래 암호 -> AES256(원래 암호를 포함한 문자열이 key로 들어감)

으로 암호화가 되어 있는데, 
> 원래암호 -> Sha512

로 바꾸려면 그 과정이
> 원래암호 -> AES256(암호포함key) -> 다시 원래 암호 -> Sha512

과정을 거쳐야 Sha512로 암호화 된 데이터를 저장 할 수 있는데, 애초에 복호화를 못하게 하고싶어 만들었던 암호화 과정이라서 원래 암호-> Sha512 암호화 과정을 하나로 만들어 메서드를 만들어보려고 했는데, 할 수가 없습니다.  
방법을 곰곰히 생각해 보다가, 처음부터 의도하지는 않았지만, 이중 암호화를 하기로 결심했습니다.
> 원래암호 -> AES256(암호포함key) -> Sha512암호화

위의 과정으로 암호화를 하면, 기존에 가입되어있는 회원들의 데이터는 이미 암호화 된 비밀번호들만 한번씩 Sha512 암호화를 덧붙여 새로 저장해주면 되겠고, 기존의 암호 알고리즘만 이중 암호화를 적용하게끔 코드를 변경해서 이후 가입하는 회원들은 이중으로 암호화시킨다면 무리 없이 적용이 가능하겠다고 생각되었고, 그대로 테스트 해 보니 문제없이 잘 되었습니다.

아래는 Sha512 암호화 코드입니다.
```
public static String encryptSha512(String plain) throws NoSuchAlgorithmException {
    // 단방향 암호화, 해시값(일정한 길이의 문자열로 출력되는 값)
    MessageDigest md = MessageDigest.getInstance("SHA-512");
    byte[] input = plain.getBytes();
    byte[] encrypted = md.digest(input);
    String encoded = org.apache.commons.codec.binary.Base64.encodeBase64String(encrypted);

    return encoded;
}
```

아래는 최종 비밀번호 생성 코드입니다.
```
public static String encryptPass(String id, String password) {
    String encryptedPass = "";
    try {
        encryptedPass = encryptAES256(password, String.format("playddit%s%s", id,password));
        encryptedPass = encryptSha512(encryptedPass);
    } catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException
            | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
        e.printStackTrace();
    }
    
    return encryptedPass;
	}
```

사용중인 DB에서의 password Column 데이터 크기를 보니 100 byte 였는데, 다행히도 Sha512 암호화 된 데이터는 88자리의 크기를 가지고 있었습니다. 
기존에 AES256으로 암호화 된 암호는 24글자 였는데 3배 이상 늘어난 만큼 보안이 훨씬 튼튼해졌으면 합니다. 
```
기존의 암호화되었던 비밀번호 : 
7mofKfVgkpHr4bWRXQs8IA==

새로운 이중 암호화된 비밀번호 : 
ewf+wJ8JYiu6fahn8rqMMV6t3HNGJoDVD6mI4Tok5QS08Mr0mnFCf8zNk+c8TyiWX5o3kiTa9TbN61lbiR2o0g==
```
  
테스팅을 마치고 바로 동작중이던 서버를 종료 한 뒤에 새로 배포를 해서 서버를 다시 실행했습니다.  

코드와 DB상에 모두 변화가 있었는데, 아무 일도 없었던 것 처럼 그대로 동작하는 사이트를 보니, 참 신기하기도 하고 변화가 없음에도 저는 변화를 알고 있기에 기분이 좋습니다.

