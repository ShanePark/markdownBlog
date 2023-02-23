package com.tistory.shanepark.file.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CreateZipFileWithFoldersAndFiles {
    final static String folder = System.getProperty("user.home") + "/Downloads/fileTest";

    public static void main(String[] args) throws IOException {
        File file1 = new File(folder, "1.txt");
        File file2 = new File(folder, "2.txt");
        File file3 = new File(folder, "folder1");

        List<File> files = new ArrayList<>();
        files.add(file1);
        files.add(file2);
        files.add(file3);

        File zipFile = new File(folder, "압축파일.zip");

        try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile))) {
            for (File file : files) {
                addFile(out, file, "");
            }
        }
        System.out.println("압축 파일 생성 성공");
    }

    private static void addFile(ZipOutputStream out, File file, String path) throws IOException {
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                addFile(out, f, path + file.getName() + File.separator);
            }
            return;
        }
        try (FileInputStream in = new FileInputStream(file)) {
            ZipEntry ze = new ZipEntry(path + file.getName());
            out.putNextEntry(ze);

            int len;
            byte[] buf = new byte[4096];
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            out.closeEntry();
        }
    }
}
