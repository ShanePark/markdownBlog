package com.tistory.shanepark.file.csv;

import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvFileWrite {
    final static String folder = System.getProperty("user.home") + "/Documents";

    public static void main(String[] args) {
        File file = new File(folder, "test.csv");
        try (
                FileOutputStream fos = new FileOutputStream(file);
                OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
                CSVWriter writer = new CSVWriter(osw);
        ) {
            List<String[]> list = new ArrayList<>();
            list.add(new String[]{
                    "shane",
                    "10,000원",
                    "ThornHill"
            });
            list.add(new String[]{
                    "Jenny",
                    "10,000,000",
                    "Queenstown"
            });
            writer.writeAll(list, true);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
