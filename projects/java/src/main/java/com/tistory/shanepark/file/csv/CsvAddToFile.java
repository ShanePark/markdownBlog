package com.tistory.shanepark.file.csv;

import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvAddToFile {
    final static String folder = System.getProperty("user.home") + "/Documents";

    public static void main(String[] args) {
        File file = new File(folder, "test.csv");
        try (
                FileWriter fileWriter = new FileWriter(file, true);
                CSVWriter writer = new CSVWriter(fileWriter);
        ) {
            List<String[]> list = new ArrayList<>();
            list.add(new String[]{
                    "Michael",
                    "20,000원",
                    "Devonport"
            });
            list.add(new String[]{
                    "Kohei",
                    "20,000,000",
                    "Ogaki"
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
