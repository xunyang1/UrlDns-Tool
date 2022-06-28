import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import okhttp3.*;
import utils.RequestBodyUtil;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws Exception {
//        HttpURLConnection httpURLConnection = null;
//
//        String url = "http://www.dnslog.cn/getdomain.php";
//        URL u = new URL(url);
//        httpURLConnection = (HttpURLConnection) u.openConnection();
//
//        httpURLConnection.setRequestMethod("GET");
//        httpURLConnection.setConnectTimeout(5000);
//        httpURLConnection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36");
//        httpURLConnection.setRequestProperty("Accept", "*/*");
//        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
//        httpURLConnection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
//        httpURLConnection.setRequestProperty("Connection", "close");
//        httpURLConnection.setRequestProperty("Upgrade-Insecure-Requests", "1");
//
//        httpURLConnection.connect();
//        if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
//            String cookie_value = httpURLConnection.getHeaderField("Set-Cookie");
//            InputStream inputStream = httpURLConnection.getInputStream();
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            try {
//                byte[] b = new byte[10240];
//                int n;
//                while ((n = inputStream.read(b)) != -1) {
//                    outputStream.write(b, 0, n);
//                }
//            } catch (Exception e) {
//                try {
//                    inputStream.close();
//                    outputStream.close();
//                } catch (Exception e1) {
//                }
//            }
//            System.out.println(outputStream.toString());

        //}
        // 1.开启Http连接
//        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 11000));
//        String url = "http://localhost:8080/urldns";
//        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection(proxy);
//        conn.setConnectTimeout(10*1000);
//        conn.setDoOutput(true); // 允许输出

        // 2.Http请求行/头
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("Content-Type", "application/java-serialized-object");
//        conn.connect();

        // 3.Http请求体
//        InputStream in = new FileInputStream("payload.ser");
//        OutputStream outputStream = conn.getOutputStream();
//        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
//        System.out.println(in);
//        byte[] b = new byte[1024];
//        int l = 0;
//        while((l = in.read(b)) != -1) out.write(b,0,l); // 写入文件
//        out.flush();
//        out.close();
//        in.close();
//

//        int length = -1;
//        byte[] bytes = new byte[1024];
//        while ((length = in.read(bytes)) != -1){
//            outputStream.write(bytes,0,length);//写的具体操作
//        }
//        in.close();
//        outputStream.close();
//
//        int responseCode = conn.getResponseCode();
//        if(responseCode == HttpURLConnection.HTTP_OK){
//            InputStream inputStream = conn.getInputStream();
//            String result = is2String(inputStream);
//            System.out.println(result);
//        }

//        InputStream in = new FileInputStream("payload.ser");
//        String in2string = is2String(in);
//
//
//        String url = "http://localhost:8080/urldns";
//        String[] split = url.split("/");
//        System.out.println(split[3]);
//        StringBuffer content = new StringBuffer("GET /"+split[3]+" HTTP/1.1\r\n");
//        content.append("Host: "+split[2]+"\r\n");
//        content.append("Content-Type: application/java-serialized-object");
//        content.append("Content-Length: "+in2string.length()+"\r\n");
//        content.append("Connection: close\r\n");
//        content.append("\r\n");
//        content.append(in2string);
//        System.out.println(content.toString());
//
//        String[] split1 = split[2].split(":");
//        System.out.println(split1[0]);
//        System.out.println(split1[1]);
//        try(Socket socket = new Socket(split1[0], Integer.parseInt(split1[1]))){
//            OutputStream os = socket.getOutputStream();
//            os.write(content.toString().getBytes());
//            os.flush();
//            InputStream is = socket.getInputStream();
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//            while (br.readLine() != null){
//                System.out.println(br.readLine());
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }


//        String url = "http://localhost:8080/urldns";
//        String[] split = url.split("/");
//        String path = split[3];
//        String domain_port = split[2];
//        String domain = domain_port.split(":")[0];
//        Integer port = Integer.valueOf(domain_port.split(":")[1]);
//
//
//        Socket socket = new Socket(domain, port);
//        StringBuffer buffer = new StringBuffer();
//        //要上传的文件
//        File file = new File("payload.ser");
//        long length = file.length();
//        System.out.println(length);
//
//
//        buffer.append("GET /"+path+" HTTP/1.1\r\n");
//        buffer.append("Host: "+domain_port+"\r\n");
//        buffer.append("Content-Type: application/java-serialized-object\r\n");
//        buffer.append("Content-Length: "+length+"\r\n");
//        buffer.append("Connection: close\r\n");
//        buffer.append("\r\n");
//        System.out.println(buffer.toString());
//
//        socket.getOutputStream().write(buffer.toString().getBytes());
//        FileInputStream fis = new FileInputStream(file);
//        byte[] bs = new byte[512];
//        int len = -1;
//        while ((len = fis.read(bs)) != -1) {
//            socket.getOutputStream().write(bs,0,len);
//        }
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(
//                socket.getInputStream()));
//        String line = null;
//        while ((line = reader.readLine()) != null) {
//            System.out.println(line);
//        }


//        String url = "http://localhost:8080/urldns";
//
//        HttpURLConnection httpURLConnection = null;
//        HttpURLConnection.setFollowRedirects(false);
//        URL u = new URL(url);
//        httpURLConnection = (HttpURLConnection) u.openConnection();
//        httpURLConnection.setRequestMethod("POST");
//        httpURLConnection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");
//        httpURLConnection.setRequestProperty("content-type","application/java-serialized-object");
//        httpURLConnection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
//        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
//        httpURLConnection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
//        httpURLConnection.setRequestProperty("Connection", "close");

         //1.开启Http连接
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 11000));
        String url = "http://localhost:8080/urldns";
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection(proxy);
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


//        int length = -1;
//        byte[] bytes = new byte[1024];
//        while ((length = in.read(bytes)) != -1){
//            outputStream.write(bytes,0,length);//写的具体操作
//        }
//        in.close();
//        outputStream.close();

        int responseCode = conn.getResponseCode();

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
