package Demo;

//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
//import org.testng.*;

public class DataProviderDemo {

	@Test(dataProvider="TestData",dataProviderClass=SeparateDataProviderClass.class)
	void add(int num1, int num2)	
	{
		int result = num1+ num2;
		System.out.println("sum of two numbers:" + result);

	}



}