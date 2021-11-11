package com.tistory.shanepark.file.zip;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class AddFileToZip {
    public static void main(String[] args) {
        String folder = "/home/shane/Downloads/fileTest";
        File file = new File(folder, "주민번호.docx");
        File zipFile = new File(folder, "압축파일.zip");
        byte[] buf = new byte[4096];

        File tempFile = null;
        try {
            tempFile = File.createTempFile(zipFile.getName(), null);
        } catch (IOException e) {
            System.err.println("임시파일 생성 실패");
        }
        tempFile.delete();

        boolean renameOK = zipFile.renameTo(tempFile);
        if (!renameOK) {
            throw new RuntimeException("couldn't rename the file :" + zipFile.getAbsolutePath());
        }

        try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
             ZipInputStream zin = new ZipInputStream(new FileInputStream(tempFile));
             InputStream in = new FileInputStream(file);
        ) {

            ZipEntry entry = zin.getNextEntry();
            while (entry != null) {
                String name = entry.getName();
                boolean notInFiles = true;
                if (file.getName().equals(name)) {
                    notInFiles = false;
                    System.err.println("이미 파일이 있음.");
                    return;
                }
                if (notInFiles) {
                    out.putNextEntry(new ZipEntry(name));
                    int len;
                    while ((len = zin.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                }
                entry = zin.getNextEntry();
            }

            out.putNextEntry(new ZipEntry(file.getName()));
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

        } catch (IOException e) {

        } finally {
            tempFile.delete();
        }

    }
}
