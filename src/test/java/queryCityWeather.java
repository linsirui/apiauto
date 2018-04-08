
import com.util.httpClientUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class queryCityWeather {

    @DataProvider(name ="getCity")
    public static Object[][] getCity() throws IOException {

        List<Object[]> records=new ArrayList<Object[]>();
        String record;


        BufferedReader file=new BufferedReader(new InputStreamReader(new FileInputStream("city.csv"),"UTF-8"));
        file.readLine();

        while((record=file.readLine())!=null){
            String fields[] =record.split(",");
            System.out.println(fields);
            records.add(fields);
        }


        file.close();//关闭文件对象

        Object[][] results=new Object[records.size()][];

        for(int i=0;i<records.size();i++){
            results[i]=records.get(i);
        }
        return results;
    }


       @Test(dataProvider = "getCity")
       public void queryCityWeather(Object[][] results) throws IOException {

           System.out.println("field is "+ results[0][0]);
           System.out.println("city is "+ results[0][1]);
           httpClientUtil httprequest = new httpClientUtil(); //新建一个httpClientUtil对象
           CloseableHttpResponse closeableHttpResponse = null;
           String url = "https://www.apiopen.top/weatherApi?city="+results[1];
           //System.out.println("city is "+City);
           closeableHttpResponse = httprequest.httpDoGet(url,null);

           //System.out.println("the api returned message is:" + closeableHttpResponse); //将返回的信息打印出来
           //通过getJsonResponseData方法，传入json结点名称，即可得到对应结点的值
           String result = httprequest.getJsonResponseData(closeableHttpResponse, "code");

           Assert.assertEquals(result, "200"); //通过断言来判断用例是否通过
       }

}
