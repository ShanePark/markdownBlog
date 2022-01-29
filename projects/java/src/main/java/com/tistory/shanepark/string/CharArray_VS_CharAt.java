package com.tistory.shanepark.string;

public class CharArray_VS_CharAt {

    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 200_000_000; i++) {
            sb.append('a');
        }

        String str = sb.toString();
        Character c;

        long start;

        start = System.currentTimeMillis();
        char[] chars = str.toCharArray();
        System.out.println("array생성 소요시간: " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = 0; i < chars.length; i++) {
            c = chars[i];
        }
        System.out.println("array 순회 소요시간: " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
        }
        System.out.println("charAt 소요시간: " + (System.currentTimeMillis() - start) + "ms");

    }

}
