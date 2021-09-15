# IRODS ) How to enforce Quota limit

​		

## change default setting on core.re

> reference https://groups.google.com/g/irod-chat/c/2Fu71_sWv7Q/m/pL6D9yZVUW0J

​	

### Connect to irods via docker

![image-20210915174759987](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/IRODS/quota.assets/image-20210915174759987.png)

​		

### find core.re

```bash
find / -name core.re
```

![image-20210915174956647](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/IRODS/quota.assets/image-20210915174956647.png)

​	

### edit core.re

```bash
vi /etc/irods/core.re
```

![image-20210915175138688](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/IRODS/quota.assets/image-20210915175138688.png)

​	

### find quota rule name (acRescQuotaPolicy) on vim

```
:/acRescQuotaPolicy
```

![image-20210915175323371](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/IRODS/quota.assets/image-20210915175323371.png)

​	

### change the setting to "on" and save

![image-20210915175353639](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/IRODS/quota.assets/image-20210915175353639.png)

​	

### make it sure all the Quota are set correcly

```bash
iadmin lq
```

![image-20210915174721844](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/IRODS/quota.assets/image-20210915174721844.png)

>  as we can see user "admin" is using over quota.

​		

### see current user's global quota 

```bash
iquota
```

![image-20210915175622038](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/IRODS/quota.assets/image-20210915175622038.png)

​		

​	

That's it. but it never work until you put 

> cu (calulate usage (for quotas))

```bash
iadmin cu
```

![image-20210915181546366](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/IRODS/quota.assets/image-20210915181546366.png)

it looks nothing, but after uploading file you have to do this otherwise it never knows your quota is exceed.

​	

### The error message from jargon

![image-20210915181027881](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/IRODS/quota.assets/image-20210915181027881.png)

​	

and there was no delay after changing Quota.

I just changed the quota generously, tried again and it worked immediately. but still in some cases it didn't work so I had to put

```bash
iadmin cu
```

 but anyway iRODS do it periodically itself i guess so if you want to make it strict you sould run the command all the time before delete/upload file or you can rely on auto caculate. it is kinda trade-off.

