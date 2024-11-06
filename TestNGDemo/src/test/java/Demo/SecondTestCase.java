package Demo;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SecondTestCase {
	
      @Test
	public void testMethod1()
	{
    	  System.out.println("This is test method 1");
	}
      
      @Test
  	public void testMethod2()
  	{
  		System.out.println("This is test method 2");
  		Assert.assertEquals(1,1);
  	}
      
      @Test
  	public void testMethod3()
  	{
    	  System.out.println("This is test method 3");
  	}
}
