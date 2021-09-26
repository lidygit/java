import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

    public static void main(String[] args) {
        HttpUtil httpUtil = new HttpUtil();
        httpUtil.doGet();
    }

    public void doGet(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("http://localhost:8801");

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