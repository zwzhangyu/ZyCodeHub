package com.zy.test.baidu.ocr;

import cn.hutool.core.io.FileUtil;
import okhttp3.*;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.List;

/*******************************************************
 * Created by ZhangYu on 2024/4/21
 * Description : 百度道路运输证测试
 * History   :
 *******************************************************/
public class Roadtransportcertificate {
    public static final String API_KEY = "tZvzF6cKqYvHmQevsSd7pC1h";
    public static final String SECRET_KEY = "rjUn6pNYjB23D9GcCykkofMnsf2q98nQ";
    public static final String BASE_PATH = "/Users/zhangyu/code/test_data/rtc/";

    static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();

    public static void main(String[] args) throws IOException {
        List<String> fileList = FileUtil.listFileNames(BASE_PATH);
        for (String file : fileList) {
            String filePath = BASE_PATH + file;
            byte[] bytes = FileUtil.readBytes(new File(filePath));
            String base64Data = Base64.getEncoder().encodeToString(bytes);
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            String urlEncodedData = URLEncoder.encode(base64Data, "UTF-8");
            RequestBody body = RequestBody.create(mediaType, "image=" + urlEncodedData);
            Request request = new Request.Builder()
                    .url("https://aip.baidubce.com/rest/2.0/ocr/v1/road_transport_certificate?access_token=" + getAccessToken())
                    .method("POST", body)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("Accept", "application/json")
                    .build();
            Response response = HTTP_CLIENT.newCall(request).execute();
            System.out.println(response.body().string());
        }
    }


    /**
     * 从用户的AK，SK生成鉴权签名（Access Token）
     *
     * @return 鉴权签名（Access Token）
     * @throws IOException IO异常
     */
    static String getAccessToken() throws IOException {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "grant_type=client_credentials&client_id=" + API_KEY
                + "&client_secret=" + SECRET_KEY);
        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/oauth/2.0/token")
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        return new JSONObject(response.body().string()).getString("access_token");
    }
}
