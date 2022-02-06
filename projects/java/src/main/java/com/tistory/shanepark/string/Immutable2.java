package com.tistory.shanepark.string;

public class Immutable2 {
    public static void main(String[] args) {
        String account="451234-56-789012";
        int amount = 10000;
        transferMoney(account, amount);
    }

    private static void transferMoney(String account, int amount) {
        System.out.println(account + "계좌에 대한 validaton 시작");
        // validation 코드
        System.out.println(account + "계좌에 대한 validaton 완료");

        // 취약 구간

        System.out.println(account + "계좌로 " + amount + "원 입금 시작");
        // 입금 코드
        System.out.println(account + "계좌로 " + amount + "원 입금 완료");

    }


}
