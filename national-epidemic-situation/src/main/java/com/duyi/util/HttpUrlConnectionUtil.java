package com.duyi.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**使用java代码来访问url并过去当中的数据
 * @author cr
 */
public class HttpUrlConnectionUtil {
    public static String doGit(String urlString){
        HttpURLConnection conn;
        InputStream is = null;
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            URL url = new URL(urlString);
            //通过url打开一个远程连接
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            //连接和读取时间
            //连接时间 ：发送请求 连接到 url目标地址的时间
            //         受到距离长短和网络的影响
            //读取时间 : 值连接成功后 获取数据的时间
            //          受到数据量和服务器处理速度的影响
            conn.setConnectTimeout(15000);
            conn.setReadTimeout(60000);
            //设定请求参数的方式，如制定接受json数据 服务端的key值为content-type
            //conn.setRequestProperty("Accept","application/json")
            //发送请求
            conn.connect();
            if (200 != conn.getResponseCode()){
                return "error";
            }
            is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String line;
            //逐行读取，不会空就继续
            while ((line = reader.readLine()) != null){
                //将读取到的数据凭借到builder中
                builder.append(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.toString();
    }

}
