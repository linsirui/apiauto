package com.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class cityDataUtil {

    @DataProvider(name ="getCityData")
    public static Object[][] getCityData() throws FileNotFoundException {
        JsonElement jsonData = new JsonParser().parse(new FileReader("/Users/jingchen/IdeaProjects/apiauto/src/main/resources/city.json"));
        JsonElement jsonDataSet = jsonData.getAsJsonObject().get("dataSet");
        //String res = jsonDataSet.toString();

        //System.out.println(res);

        List<cityData> cityData = new Gson().fromJson(jsonDataSet,new TypeToken<List<cityData>>(){}.getType());
        Object[][] returnValue = new Object[cityData.size()][1];

        int index =0;
        for(Object[] each:returnValue){
            each[0] = cityData.get(index++);
        }
        return returnValue;

    }
}
