package com.tistory.shanepark.string;

public class fileName {

    public static void main(String[] args) {
        String destinationFileNameOriginal = "admin@전화번호패턴.txt";
        String suffix = "(2)";
        StringBuffer destinationFileName = new StringBuffer(destinationFileNameOriginal);
        int dotIndex = destinationFileName.indexOf(".");
        destinationFileName.insert(dotIndex, suffix);

        System.out.println(destinationFileName);
    }

}
