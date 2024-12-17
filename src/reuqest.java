
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.stream.Collectors;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


public class reuqest {


    public static void main(String[] args) throws IOException {
        //负载数据
        HashMap<Object,Object> data=new HashMap<>();
        data.put("xnm",2024);
        data.put("xqm",3);
        data.put("xsdm","");




        URL url=new URL("https://jwgl.suse.edu.cn/kbcx/xskbcx_cxXsgrkb.html?gnmkdm=N2151");

        //打开向网站的通道流
        HttpURLConnection connection=(HttpURLConnection) url.openConnection();

        //连接方式为get
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");

        //设置请求头
        connection.addRequestProperty("cookie","route=9b151084fd1fa838caca087e9ed8f751; JSESSIONID=83ED68AAE6DB6F107A6BA756D1E4A9C3");

        // 设置请求头Content-Type为application/x-www-form-urlencoded
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");


        // 将表单参数编码为application/x-www-form-urlencoded格式
        String encodedParams = data.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));

        // 发送请求体
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = encodedParams.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);

            // 获取响应码
            int responseCode = connection.getResponseCode();

            //读取网页传递过来的数据
            BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer sb=new StringBuffer();
            String response;
            while ((response=br.readLine())!=null){
                sb.append(response);
            }
            br.close();

            //转换为json数据
            Gson gson=new Gson();
            JsonObject jsonObject = gson.fromJson(sb.toString(), JsonObject.class);
            //System.out.println(jsonObject);

            //将json文件写到文件里面去
            BufferedWriter bw=new BufferedWriter(new FileWriter("D:\\course_java.json"));
            bw.write(jsonObject.toString());
            bw.close();





            System.out.println("Response Code: " + responseCode);
        }catch (Exception e){
            System.out.println(e);
        }


    }
}
