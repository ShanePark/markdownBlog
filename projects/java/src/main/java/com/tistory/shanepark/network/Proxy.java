package com.tistory.shanepark.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Proxy {

    public static void main(String[] args) throws IOException {

        ifConfig();

        System.getProperties().put("proxySet", "true");
        System.getProperties().put("socksProxyHost", "localhost");
        System.getProperties().put("socksProxyPort", "9999");

        ifConfig();

    }

    static void ifConfig() throws IOException {
        URL url = new URL("https://www.ifconfig.me");
        StringBuffer sb = new StringBuffer();
        URLConnection urlConn = url.openConnection();
        try (InputStream is = urlConn.getInputStream();
             InputStreamReader isr = new InputStreamReader(is, "UTF-8");
             BufferedReader br = new BufferedReader(isr);
        ) {
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str + "\r\n");
            }
            System.out.println(sb.toString());
        }

    }
}
