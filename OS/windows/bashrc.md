# Windows 에서 bashrc 어쩌구 Vim vi alias 어쩌구 



```bash
vi
```



![image-20210903132847205](bashrc.assets/image-20210903132847205.png)



```bash
vim
```

![image-20210903132927441](bashrc.assets/image-20210903132927441.png)





```bash
Get-Alias
```

![image-20210903133314493](bashrc.assets/image-20210903133314493.png)



```bash
Set-Alias vi vim
```



![image-20210903133343308](bashrc.assets/image-20210903133343308.png)

![image-20210903133351351](bashrc.assets/image-20210903133351351.png)



Terminal / PowerShell 을 껐다 다시 켜면

![image-20210903133422168](bashrc.assets/image-20210903133422168.png)



설정 영구 저장



```
$profile
```



![image-20210903133208987](bashrc.assets/image-20210903133208987.png)



![](bashrc.assets/image-20210903133541927.png)

![image-20210903133601793](bashrc.assets/image-20210903133601793.png)



저장 하고 껐다 켜면



![image-20210903133631639](bashrc.assets/image-20210903133631639.png)

오히려 더 에러가 남 스크립트를 시행 할 수 없음

​	

```bash
get-help ExecutionPolicy
```



![image-20210903133850991](bashrc.assets/image-20210903133850991.png)



```bash
Get-ExecutionPolicy
```



![image-20210903133919334](bashrc.assets/image-20210903133919334.png)

Restricted 라고 나오네요. ps1 스크립트 파일을 로드하여 실행 할 수 없는 정책 입니다.





```bash
get-help Set-ExecutionPolicy
```

![](bashrc.assets/image-20210903133936561.png)



```bash
Set-ExecutionPolicy remoteSigned
```



![image-20210903134117081](bashrc.assets/image-20210903134117081.png)



![image-20210903134140167](bashrc.assets/image-20210903134140167.png)

```bash
Set-ExecutionPolicy remoteSigned
```



![](bashrc.assets/image-20210903134351779.png)



새로 켭니다 관리자 모드로 켤 필요 없습니다.



![image-20210903134447648](bashrc.assets/image-20210903134447648.png)



![image-20210903134456087](bashrc.assets/image-20210903134456087.png)