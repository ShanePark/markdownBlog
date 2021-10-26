# proxy 설정하기



IntelliJ IDEA

![image-20211026145103818](/home/shane/Documents/git/markdownBlog/backend/java/proxy.assets/image-20211026145103818.png)

![image-20211026145153972](/home/shane/Documents/git/markdownBlog/backend/java/proxy.assets/image-20211026145153972.png)

Run/Debug Configurations > Add VM options`Alt+V`

![image-20211026145208614](/home/shane/Documents/git/markdownBlog/backend/java/proxy.assets/image-20211026145208614.png)

VM options 블록이 생김. 아래의 내용 입력

```
-DproxySet=true -DsocksProxyHost=localhost -DsocksProxyPort=9999
```



![image-20211026145341253](/home/shane/Documents/git/markdownBlog/backend/java/proxy.assets/image-20211026145341253.png)



## Eclipse

조금 다름

![image-20211026144951353](/home/shane/Documents/git/markdownBlog/backend/java/proxy.assets/image-20211026144951353.png)





























