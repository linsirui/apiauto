import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class passParameter {

    @Parameters({"param1","param2"})
    @Test
    public void parameter(String param1,String param2){
        System.out.println("parameter 1 is "+param1);
        System.out.println("parameter 2 is "+param2);
    }
}
