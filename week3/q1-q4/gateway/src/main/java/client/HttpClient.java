package client;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
/**
 * @Description: java类作用描述
 * @Author: l
 * @CreateDate: 2021/10/10 17:45
 * @需求:
 * @思路说明:
 */
public class HttpClient {
    public static void main(String[] args) {
//        HttpClient httpClient = new HttpClient();
//        String url = "http://localhost:8801";
//        httpClient.doGet(url);

        // 走网关
        HttpClient httpClient = new HttpClient();
        String url = "http://localhost:8888";
        httpClient.doGet(url);
    }

    public void doGet(String url){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);

            HttpEntity entity = response.getEntity();
            if (entity != null){
                System.out.println("响应长度：" + entity.getContentLength());
                System.out.println("响应内容：" + EntityUtils.toString(entity));
            }

        }catch (Exception e){

        }finally {

            try{
                if (httpClient != null){
                    httpClient.close();
                }
            }catch (Exception e){

            }
            try{
                if (response != null){
                    response.close();
                }
            }catch (Exception e){

            }

        }


    }
}
