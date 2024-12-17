import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TestDEMO {
    public static void main(String[] args) throws IOException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeySpecException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        URL url = new URL("https://jwgl.suse.edu.cn/xtgl/login_slogin.html");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.setRequestMethod("GET");
        //请求头
        connection.addRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36 Edg/131.0.0.0");
        String JSESSIONID = connection.getHeaderField(7).split(";")[0];
        String route = connection.getHeaderField(13).split(";")[0];

        String Cookie = route + ";" + JSESSIONID;

        BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
        StringBuffer sb = new StringBuffer();

        byte[] bytes = new byte[1024];
        int len;
        while ((len = bis.read(bytes)) != -1) {
            sb.append(new String(bytes));
        }
        bis.close();
        String csrfToken = sb.toString().split("id=\"csrftoken\" name=\"csrftoken\" value=\"")[1].split("\"/")[0];

        URL u = new URL("https://jwgl.suse.edu.cn/xtgl/login_getPublicKey.html?time=" + System.currentTimeMillis() + "&_=" + (System.currentTimeMillis() - 100));
        HttpURLConnection uCon = (HttpURLConnection) u.openConnection();
        uCon.setDoInput(true);
        uCon.setRequestMethod("GET");
        uCon.setRequestProperty("Cookie", Cookie);
        uCon.setRequestProperty("referer", "https://jwgl.suse.edu.cn/xtgl/login_slogin.html");
        uCon.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36 Edg/131.0.0.0");
        StringBuffer sb2 = new StringBuffer();
        BufferedInputStream bis2 = new BufferedInputStream(uCon.getInputStream());

        byte[] bytes2 = new byte[64];
        int len2;
        while ((len2 = bis2.read(bytes2)) != -1) {
            sb2.append(new String(bytes2));
        }
        bis2.close();
        String M = sb2.toString().split("\"")[3];

        String replace = M.replace("\\", "");

        String E = "AQAB";
        RSA key = new RSA(replace, E);
        String PWD = key.encrypt("99Lhlismygirl.");
        System.out.println(PWD);


//      //退出登录
//       URL logout = new URL("https://jwgl.suse.edu.cn/xtgl/login_logoutAccount.html");
//       HttpURLConnection con = (HttpURLConnection) logout.openConnection();
//       con.setDoOutput(true);
//       con.setRequestMethod("POST");
//       con.setRequestProperty("cookie",Cookie);
//
//
//
//        //请求登录后的cookie
//        URL login = new URL("https://jwgl.suse.edu.cn/xtgl/login_slogin.html?time=" + System.currentTimeMillis());
//
//        HttpURLConnection connection1 = (HttpURLConnection) login.openConnection();
//        connection1.setRequestMethod("POST");
//        connection1.setDoInput(true);
//        connection1.setDoOutput(true);
//
//        HashMap<Object, Object> data = new HashMap<>();
//        data.put("csrftoken", csrfToken);
//        data.put("language", "zh_CN");
//        data.put("yhm", "22101020118");
//        data.put("mm", PWD);
//
//
//        // 设置请求头Content-Type为application/x-www-form-urlencoded
//        connection1.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//
//
//        // 将表单参数编码为application/x-www-form-urlencoded格式
//        String encodedParams = data.entrySet().stream()
//                .map(entry -> entry.getKey() + "=" + entry.getValue())
//                .collect(Collectors.joining("&"));
//
//        // 发送请求体
//        try (OutputStream os = connection1.getOutputStream()) {
//            byte[] input = encodedParams.getBytes(StandardCharsets.UTF_8);
//            os.write(input, 0, input.length);
//        } catch (Exception e) {
//            System.out.println(e
//            );
//        }
//
////        for(int i=0;;i++){
////            if(connection1.getHeaderField(i)==null){
////                break;
////            }
////            System.out.println(connection1.getHeaderField(i));
////        }
//
//        System.out.println("------------------");
//        BufferedInputStream bis1=new BufferedInputStream(connection1.getInputStream());
//        byte[] bytes3=new byte[1024];
//        int len3;
//        while ((len3=bis1.read(bytes3))!=-1){
//            System.out.println(new String(bytes3));
//        }
//        bis1.close();

    }
}
