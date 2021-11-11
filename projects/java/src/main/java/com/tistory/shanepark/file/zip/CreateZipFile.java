package com.tistory.shanepark.file.zip;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CreateZipFile {
    public static void main(String[] args) throws IOException {
        String folder = "/home/shane/Downloads/fileTest";
        File file1 = new File(folder, "전화번호패턴.txt");
        File file2 = new File(folder, "주민등록패턴.txt");
        File file3 = new File(folder, "주민번호.docx");
        List<File> files = new ArrayList<>();
        files.add(file1);
        files.add(file2);

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

    }
}
