package com.tistory.shanepark.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Proxy {

    public static void main(String[] args) {

        try {
            StringBuffer sbuf = new StringBuffer();

            System.getProperties().put("proxySet", "true");
            System.getProperties().put("socksProxyHost", "localhost");
            System.getProperties().put("socksProxyPort", "9999");

            URL url = new URL("https://www.ifconfig.me");

            URLConnection urlConn = url.openConnection();
            InputStream is = urlConn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);

            String str;
            while ((str = br.readLine()) != null) {
                sbuf.append(str + "\r\n");
            }

            System.out.println(sbuf.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
