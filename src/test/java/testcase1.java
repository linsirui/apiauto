
import com.google.gson.JsonObject;
import com.util.httpClientUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.Parameters;

import java.io.IOException;

public class testcase1 {

    @Parameters({"param1"})
    @org.testng.annotations.Test
    public void tc1(String param1) throws IOException {


        httpClientUtil httprequest = new httpClientUtil(); //新建一个httpClientUtil对象
        CloseableHttpResponse closeableHttpResponse = null;
        String url = "https://www.apiopen.top/weatherApi?city="+param1;
        closeableHttpResponse = httprequest.httpDoGet(url,null);

        //System.out.println("the api returned message is:" + closeableHttpResponse); //将返回的信息打印出来
        //通过getJsonResponseData方法，传入json结点名称，即可得到对应结点的值
        String result = httprequest.getJsonResponseData(closeableHttpResponse, "code");

        Assert.assertEquals(result, "200"); //通过断言来判断用例是否通过

    }

}
