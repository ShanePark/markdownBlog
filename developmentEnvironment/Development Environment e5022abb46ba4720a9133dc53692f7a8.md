# Development Environment

## Tech Stacks I chose this time

- MacBook Air M1 (Big Sur 11.2.3)
- JDK 1.8
- Eclipse 2020-06
- Apache Tomcat 8.5.63

1. JDK 1.8 

I already have JDK 1.8 installed on my computer so I'll pass this step.

2. Eclipse 2020-06

- Why 2020-06 ?

![Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled.png](Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled.png)

- [https://www.egovframe.go.kr/eng/ntt/nttRead.do?pagerOffset=0&searchKey=&searchValue=&menuNo=21&bbsId=101&nttId=1852](https://www.egovframe.go.kr/eng/ntt/nttRead.do?pagerOffset=0&searchKey=&searchValue=&menuNo=21&bbsId=101&nttId=1852)
- By visiting above link, you can check Development Environment for eGovFrame , and it says they support Eclipse 2020-06 as the latest version same as windows.

download link is below. you should choose fastest mirror by clicking "Select Another Mirror" not the default one

- [https://www.eclipse.org/downloads/packages/release/2020-06/r](https://www.eclipse.org/downloads/packages/release/2020-06/r)
- in case you have "Failed to create the Java Virtual Machine" Error, follow instructions on this link : [https://shanepark.tistory.com/32](https://shanepark.tistory.com/32)

![Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%201.png](Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%201.png)

Now I successfully launched Eclipse 2020-06.

It's time to setup workspace preferences

 

![Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%202.png](Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%202.png)

You need to change some encoding types to utf-8, on windows you should changed most of it but on mac most of them are already "utf-8" so do not need to worry.

![Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%203.png](Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%203.png)

change all Text to utf-8

![Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%204.png](Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%204.png)

but only Java Properties File should stay IOS-8859-1

![Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%205.png](Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%205.png)

Disable All Validations, and then 

![Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%206.png](Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%206.png)

activate DTD Validator for ibatis XML code assistance.

![Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%207.png](Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%207.png)

we'll make JSP files so check them 

![Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%208.png](Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%208.png)

they are for validation check of XML, XSL

![Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%209.png](Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%209.png)

don't need Spelling check as well.

![Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%2010.png](Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%2010.png)

disable auto activation to reduce Eclipse's resource 

3. Apache Tomcat 8.5.63

![Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%2011.png](Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%2011.png)

download Apache Tomcat.

if you are on Windows, click zip. mine is mac OS so i chose tar.gz

![Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%2012.png](Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%2012.png)

leave it somewhere you want 

### now come back to Eclipse

![Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%2013.png](Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%2013.png)

No servers are available. Click it!

![Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%2014.png](Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%2014.png)

choose what you have

![Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%2015.png](Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%2015.png)

hit "Browse" button

![Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%2016.png](Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%2016.png)

find where it is.

![Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%2017.png](Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%2017.png)

don't forget to choose JRE as well.

![Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%2018.png](Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%2018.png)

![Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%2019.png](Development%20Environment%20e5022abb46ba4720a9133dc53692f7a8/Untitled%2019.png)

Add any projects you want 

It's all done ! enjoy.

if your server port number is 80 you don't need to type it but anything else then

- if your port number is 8080

[localhost:8080/](http://localhost:8080/)