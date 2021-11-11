package com.tistory.shanepark.file.zip;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CreateZipFile {
    final static String folder = "/Users/shane/Downloads/fileTest";

    public static void main(String[] args) throws IOException {
        File file1 = new File(folder, "1.txt");
        File file2 = new File(folder, "2.txt");
        File file3 = new File(folder, "3.txt");

        List<File> files = new ArrayList<>();
        files.add(file1);
        files.add(file2);
        files.add(file3);

        File zipFile = new File(folder, "압축파일.zip");
        byte[] buf = new byte[4096];

        try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile))) {

            for (File file : files) {
                try (FileInputStream in = new FileInputStream(file)) {
                    ZipEntry ze = new ZipEntry(file.getName());
                    out.putNextEntry(ze);

                    int len;
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }

                    out.closeEntry();
                }

            }
        }
        System.out.println("압축 파일 생성 성공");

    }
}
