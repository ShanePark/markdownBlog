package com.tistory.shanepark.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * vm option
 * java -DproxySet=true -DsocksProxyHost=localhost -DsocksProxyPort=9999
 * IntelliJ IDEA 에서는 vm 옵션에 -DproxySet=true -DsocksProxyHost=localhost -DsocksProxyPort=9999 만 입력 하면 됨.
 */
public class ProxyWithArgument {

    public static void main(String[] args) throws IOException {
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
