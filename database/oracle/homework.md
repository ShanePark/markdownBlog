# Oracle 데이터 베이스 10개 문제 풀어보기

​	

## 1. 남녀 성별 인원수 조회

```sql
SELECT *
FROM
    (select COUNT(*) AS MEN
       from member
      where SUBSTR(mem_regno2,1,1) = '1' OR SUBSTR(mem_regno2,1,1) ='3'
    ),
    (select COUNT(*) AS WOMEN
       from member
      where SUBSTR(mem_regno2,1,1) = '2' OR SUBSTR(mem_regno2,1,1) ='4'
    )

```

>![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/homework.assets/1.png)

​	          

## 2. 지금까지 한번도 상품을 구매한 적이 없는 회원의 인적사항 조회(아이디, 이름, 이메일)
```
select mem_id, mem_name, mem_mail
from (
    select mem_id, mem_name, mem_mail, count(cart_member) as orders
    from member
        left outer join cart on (mem_id = cart_member)
    group by mem_id, mem_name, mem_mail)
where orders = 0
```
>![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/homework.assets/2.png)

​          	

## 3. 등록된 상품이 한건도 없는 상품분류 조회(상품분류코드, 분류명)
```
select lprod_gu, lprod_nm
from(
    select lprod_gu, lprod_nm, count(prod_lgu) cnt
    from lprod
            left outer join prod on( lprod_gu = prod_lgu)
    group by lprod_gu, lprod_nm)
where cnt = 0
```
![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/homework.assets/3.png)

​          

## 4. 지금까지 가장 많이 팔린 상품 조회(상품코드, 상품명, 상품분류명, 거래처명, 마일리지)
## 5. 지금까지 가장 적게 팔린 상품 조회(상품코드, 상품명, 상품분류명, 거래처명, 마일리지)
```
select prod_id, prod_name,lprod_nm,prod_mileage, buyer_name,
        nvl(sum(cart_qty), 0) as sales
from prod
    left outer join cart on (prod_id = cart_prod)
    left outer join lprod on (prod_lgu = lprod_gu)
    left outer join buyer on (prod_buyer = buyer_id)
group by prod_id, prod_name,lprod_nm,prod_mileage, buyer_name
order by sales desc
```
![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/homework.assets/4.png)

>가장 많이 팔린 상품    

```
select a.*
from(
    select prod_id, prod_name,lprod_nm,nvl(prod_mileage,0) prod_mileage, buyer_name,
            nvl(sum(cart_qty), 0) as sales
    from prod
        left outer join cart on (prod_id = cart_prod)
        left outer join lprod on (prod_lgu = lprod_gu)
        left outer join buyer on (prod_buyer = buyer_id)
    group by prod_id, prod_name,lprod_nm,prod_mileage, buyer_name
    order by sales desc) a
where rownum = 1;
```
![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/homework.assets/5.png)
>가장 적게 팔린 상품    

```
select a.*
from(
    select prod_id, prod_name,lprod_nm,nvl(prod_mileage,0) prod_mileage, buyer_name,
            nvl(sum(cart_qty), 0) as sales
    from prod
        left outer join cart on (prod_id = cart_prod)
        left outer join lprod on (prod_lgu = lprod_gu)
        left outer join buyer on (prod_buyer = buyer_id)
    group by prod_id, prod_name,lprod_nm,prod_mileage, buyer_name
    order by sales asc) a
where rownum = 1;
```
>![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/homework.assets/6.png)

​          

## 6. 거래처 중 거래 품목 수가 가장 많은 거래처 조회(거래처코드, 거래처명, 거래처분류명, 담당자명)
## 7. 거래처 중 거래 품목 수가 가장 적은 거래처 조회(거래처코드, 거래처명, 거래처분류명, 담당자명)
```
select buyer_id, buyer_name, lprod_nm, count(prod_id) cnt
from buyer
    left outer join prod on (buyer_id = prod_buyer)
    left outer join lprod on (buyer_lgu = lprod.lprod_gu)
group by buyer_id, buyer_name,lprod_nm
order by cnt desc; 
```
![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/homework.assets/7.png)
> 가장 많은 거래처
```
select a.*
from(
    select buyer_id, buyer_name, lprod_nm, count(prod_id) cnt
    from buyer
        left outer join prod on (buyer_id = prod_buyer)
        left outer join lprod on (buyer_lgu = lprod.lprod_gu)
    group by buyer_id, buyer_name,lprod_nm
    order by cnt desc)a
where rownum = 1;
```
![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/homework.assets/8.png)
> 가장 적은 거래처
```
select a.*
from(
    select buyer_id, buyer_name, lprod_nm, count(prod_id) cnt
    from buyer
        left outer join prod on (buyer_id = prod_buyer)
        left outer join lprod on (buyer_lgu = lprod.lprod_gu)
    group by buyer_id, buyer_name,lprod_nm
    order by cnt asc)a
where rownum = 1;
```
![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/homework.assets/9.png)

​          

## 8. 남녀 성별 각각 구매율이 높은 상품 조회(성별, 상품코드, 상품명, 분류명)
```
select '남성'as gender, cart_prod, prod_name, lprod_nm
from (
    select cart_prod, prod_name,lprod_nm, sum(cart_qty)
    from member
        inner join cart on (mem_id = cart_member)
        inner join prod on (cart_prod = prod_id)
        inner join lprod on (prod_lgu = lprod_gu)
    where SUBSTR(mem_regno2,0,1) = '1' OR SUBSTR(mem_regno2,0,1) ='3'
    group by cart_prod, prod_name, lprod_nm
    order by sum(cart_qty) desc)
where rownum=1 
union all
select '여성'as gender, cart_prod, prod_name, lprod_nm
from (
    select cart_prod, prod_name,lprod_nm, sum(cart_qty)
    from member
        inner join cart on (mem_id = cart_member)
        inner join prod on (cart_prod = prod_id)
        inner join lprod on (prod_lgu = lprod_gu)
    where SUBSTR(mem_regno2,0,1) = '2' OR SUBSTR(mem_regno2,0,1) ='4'
    group by cart_prod, prod_name, lprod_nm
    order by sum(cart_qty) desc)
where rownum=1
```
![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/homework.assets/10.png)

​          

## 9. 각 회원별 구매 총액 조회(회원아이디, 회원명, 구매총액)
```
select mem_id, mem_name, nvl(sum(prod_price * cart_qty),0) amount
from member
    left outer join cart on (mem_id = cart_member)
    left outer join prod on (cart_prod = prod_id)
group by mem_id, mem_name
order by amount desc
```
![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/homework.assets/11.png)
          

## 10. 각 상품별 구매왕 조회(구매왕아이디, 회원명, 구매총액)
```
select prod_name, mem_id, mem_name, amount
from(
    select prod_name, mem_id, mem_name, prod_price*cart_qty as amount, rank() over(partition by prod_name order by prod_price*cart_qty desc ,mem_id)as rank
    from prod
        left outer join cart on (prod_id = cart_prod)
        left outer join member on (cart_member = mem_id)
    order by prod_name)
where rank=1
```

![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/homework.assets/12.png)