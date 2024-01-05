package com.tistory.shanepark.string;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

public class CarriageReturn {
    final static String POSIX_STYLE_FILENAME = "posix_style.txt";
    final static String WINDOW_STYLE_FILENAME = "window_style.txt";

    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        compareRemoveLineFeed(POSIX_STYLE_FILENAME);
        compareRemoveLineFeed(WINDOW_STYLE_FILENAME);
        System.err.println("임차인100명\r임대인");

    }

    private static void compareRemoveLineFeed(String fileName) throws URISyntaxException {
        Class<CarriageReturn> curClass = CarriageReturn.class;
        URL resource = curClass.getResource("./" + curClass.getSimpleName());
        File resourceDir = new File(resource.toURI());
        File file = new File(resourceDir, fileName);

        String textOriginal = readFileToString(file);
        String textAfterReplace = textOriginal.replaceAll("\n", "");

        System.out.printf("== 변경 전 텍스트 (%s, 길이:%d) ==\n%s\n==================================\n\n", file.getName(), textOriginal.length(), textOriginal);
        System.out.printf("== 변경 후 텍스트 (%s, 길이:%d) ==\n%s\n==================================\n\n", file.getName(), textAfterReplace.length(), textAfterReplace);
    }

    private static String readFileToString(File posixStyleText) {
        StringBuilder sb = new StringBuilder();
        try (FileInputStream fis = new FileInputStream(posixStyleText);
             BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        ) {
            int read = br.read();
            while (read != -1) {
//                System.out.printf("read = %d, char = %c\n", read, (char) read);
                sb.append((char) read);
                read = br.read();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

}
