package com.tistory.shanepark.string;

public class fileName {

    public static void main(String[] args) {
        String netId = netIdProcessor("abc@abcd.net");
        String destinationFileNameOriginal = netId + "@전화번호패턴.txt";
        String suffix = "(2)";
        StringBuffer destinationFileName = new StringBuffer(destinationFileNameOriginal);
        int dotIndex = destinationFileName.lastIndexOf(".");
        destinationFileName.insert(dotIndex, suffix);

        System.out.println(destinationFileName);

        String fileName = "admin@전화번호패턴.txt";
        System.out.println(getFileName(fileName));
    }

    private static String netIdProcessor(String netId) {
        int indexAt = netId.indexOf("@");
        return indexAt < 0 ? netId : netId.substring(0, indexAt);
    }

    private static String getFileName(String str) {
        return str.substring(str.indexOf("@")+1);
    }

}
