package com.tistory.shanepark.regex;

import java.util.regex.Pattern;

public class SimpleRegex {

    public static void main(String[] args) {
        regexPass("AAAAA1", 6, true, true, true);
    }

    private static boolean regexPass(String password, int lengthPolicy, boolean numberPolicy, boolean specialCharacterPolicy,
                                     boolean capitalPolicy) {

        // 길이 검증
        if (password.length() < lengthPolicy) {
            System.out.println("길이검증 실패");
            return false;
        }

        // 숫자 포함 여부 검증
        if (numberPolicy) {
            String pattern = ".*[0-9].*";
            if (!Pattern.matches(pattern, password)){
                System.out.println("숫자 검증 실패");
                return false;
            }
        }

        // 특수 문자 포함 여부 검증
        if (specialCharacterPolicy) {
            String pattern = ".*[!@#$%^&*?_-].*";
            if (!Pattern.matches(pattern, password)) {
                System.out.println("특수문자 검증 실패");
                return false;
            }
        }

        // 대문자 포함 여부 검증
        if (capitalPolicy) {
            String pattern = ".*[A-Z].*";
            if (!Pattern.matches(pattern, password)) {
                System.out.println("대문자 검증 실패");
                return false;
            }
        }

        return true;

    }
}
