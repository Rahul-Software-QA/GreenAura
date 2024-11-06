package GreenAura;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;


public class LoginPage {
	
	public String baseUrl = "https://ditdev.net/GreenAura/my-account/";
	public ChromeDriver driver;	
	
	@BeforeTest
	public void setup()
	{
		System.out.println("Before Test executed");
		// TODO Auto-generated method stub
		driver=new ChromeDriver();
		//Maximize window
		driver.manage().window().maximize();
		
		//open url
		driver.get(baseUrl);
		//timer i kept as 60 you can keep 40
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	}
     @Test
	public void loginTest() 
     {
    	 //find username 
    	 driver.findElement(By.xpath("//input[@id='username']")).sendKeys("greenaura@yopmail.com");
    	 
    	//find password
    	 driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Testing@12345");
    	 
    	//find button click 
    	 driver.findElement(By.xpath("//button[@name='login']")).submit();
    	 
    	 // Verify if the login was successful by checking the page title or a specific element 
    	 String pageTitle = driver.getTitle();
    	 if (pageTitle.equals("GreenAura")) {
    		 System.out.println("Login successful!");
    	 } else {
    		 System.out.println("Login failed!");
    	 }
    	 
    	 //find logout
    	 driver.findElement(By.xpath("//main[@id='primary']//h3[1]//a[1]")).sendKeys("Logout");
	}
     @AfterTest
     public void tearDown() throws InterruptedException
     {
    	 Thread.sleep(5000);//wait for 5 secs before quit
    	 
    	 driver.close();
    	 driver.quit();
     }
}
