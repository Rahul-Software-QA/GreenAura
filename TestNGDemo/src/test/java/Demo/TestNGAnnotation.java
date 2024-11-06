package Demo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNGAnnotation {
	
	@BeforeMethod
	void setup()
	{
		System.out.println("This is setup method");
	}

	@Test
	void testMethod1()
	{
		System.out.println("This is test method 1");	
	}
	
	@Test
	void testMethod2()
	{
		System.out.println("This is test method 2");
	}
}