package utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Dnslog {

    public static Map<String, String> GetDnslogDomain() throws Exception {
        Map<String, String> map = new HashMap<>();
        HttpURLConnection httpURLConnection = null;

        String url = "http://dnslog.cn/getdomain.php";
        URL u = new URL(url);
        httpURLConnection = (HttpURLConnection) u.openConnection();

        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36");
        httpURLConnection.setRequestProperty("Accept", "*/*");
        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
        httpURLConnection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
        httpURLConnection.setRequestProperty("Connection", "close");
        httpURLConnection.setRequestProperty("Upgrade-Insecure-Requests", "1");

        httpURLConnection.connect();
        if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
            String cookie_value = httpURLConnection.getHeaderField("Set-Cookie");
            InputStream inputStream = httpURLConnection.getInputStream();
            String domain = is2String(inputStream);
            map.put("cookie", cookie_value);
            map.put("domain", domain);
        }
        return map;
    }

    public static boolean GetDnslogRecords(String cookie) throws Exception {
        String records = "";
        boolean flag =false;
        HttpURLConnection httpURLConnection = null;

        String url = "http://dnslog.cn/getrecords.php";
        URL u = new URL(url);
        httpURLConnection = (HttpURLConnection) u.openConnection();

        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36");
        httpURLConnection.setRequestProperty("Accept", "*/*");
        httpURLConnection.setRequestProperty("Cookie", cookie);
        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
        httpURLConnection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
        httpURLConnection.setRequestProperty("Connection", "close");
        httpURLConnection.setRequestProperty("Upgrade-Insecure-Requests", "1");

        httpURLConnection.connect();
        if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
            InputStream inputStream = httpURLConnection.getInputStream();
            records = is2String(inputStream);
        }
        if (records.contains("dnslog.cn")){
            flag = true;
        }
        return flag;
    }

    public static String is2String(InputStream inputStream){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            byte[] b = new byte[10240];
            int n;
            while ((n = inputStream.read(b)) != -1) {
                outputStream.write(b, 0, n);
            }
        } catch (Exception e) {
            try {
                inputStream.close();
                outputStream.close();
            } catch (Exception e1) {
            }
        }
        return outputStream.toString();
    }
}
