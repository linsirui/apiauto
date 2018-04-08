import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.util.cityData;
import org.testng.annotations.Test;
import com.util.httpClientUtil;
import com.util.cityDataUtil;

public class queryCityWeatherProvider {



    @Test(dataProvider ="getCityData",dataProviderClass = cityDataUtil.class)

    public void queryMultiCity(cityData cityDataTest) throws IOException{

        //JSONObject jsonObject = new JSONObject();
        String testcaseName = cityDataTest.getTestCase();
        String apiURL = cityDataTest.getApiURL();

        System.out.println("this is test case :"+testcaseName);
        httpClientUtil httpClientUtil = new httpClientUtil();

        CloseableHttpResponse res = httpClientUtil.httpDoGet(apiURL,null);

        String result = httpClientUtil.getJsonResponseData(res, "code");

        Assert.assertEquals(result, "200"); //通过断言来判断用例是否通过


    }
}
