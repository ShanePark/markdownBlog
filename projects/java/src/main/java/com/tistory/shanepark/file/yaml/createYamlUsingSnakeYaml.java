package com.tistory.shanepark.file.yaml;

import org.yaml.snakeyaml.Yaml;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class createYamlUsingSnakeYaml {
    final static String folder = System.getProperty("user.home") + "/Documents";

    public static void main(String[] args) throws IOException {
        Map<String, Object> data1 = new LinkedHashMap<>();
        data1.put("name", "shane");
        data1.put("nationality", "korean");
        data1.put("jobs", new String[]{"programmer", "traveler"});
        data1.put("timestamp", new Date());

        Map<String, Object> data2 = new LinkedHashMap<String, Object>();
        data2.put("name", "shane2");
        data2.put("nationality", "english");
        data2.put("jobs", new String[]{"programmer"});

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("data1", data1);
        result.put("data2", data2);

        Yaml yaml = new Yaml();
        FileWriter writer = new FileWriter(folder + "/file.yaml");
        yaml.dump(result, writer);
    }
}
