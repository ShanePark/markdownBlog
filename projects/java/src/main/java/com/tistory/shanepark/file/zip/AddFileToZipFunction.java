package com.tistory.shanepark.file.zip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class AddFileToZipFunction {

    private static Logger log = LoggerFactory.getLogger(AddFileToZipFunction.class);

    public static void addFileToZip(File file, File zipFile) throws IOException {
        byte[] buf = new byte[4096];
        File tempFile = File.createTempFile("TMPZIP~" + zipFile.getName(), null);
        // 혹시 모를 중복 대비. delete 안해주면 rename 실패 한다.
        tempFile.delete();

        boolean renameOK = zipFile.renameTo(tempFile);
        if (!renameOK) {
            throw new RuntimeException("파일명 변경 실패 :" + zipFile.getAbsolutePath());
        }

        try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
             ZipInputStream zin = new ZipInputStream(new FileInputStream(tempFile));
             InputStream in = new FileInputStream(file);) {

            ZipEntry entry = zin.getNextEntry();
            while (entry != null) {
                String name = entry.getName();
                boolean notInFiles = true;
                if (file.getName().equals(name)) {
                    notInFiles = false;
                    log.warn("이미 파일이 압축파일 내에 존재함. {}", file.getName());
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

        } finally {
            tempFile.delete();
        }
    }
}
