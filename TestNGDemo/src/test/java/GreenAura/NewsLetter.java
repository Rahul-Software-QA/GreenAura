package GreenAura;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class NewsLetter {
	
	@Test
	public void Testcart()
	{
	WebDriver driver = new ChromeDriver();
	driver.get("https://ditdev.net/GreenAura/");
	//Maximize window
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
			driver.manage().deleteAllCookies();
			
            WebElement emailId = driver.findElement(By.xpath("//div[@id='wpcf7-f570-o1']//input[@placeholder='Enter Your Email']"));
            emailId.sendKeys("greenaura25@yopmail.com");
            WebElement sumbitButton = driver.findElement(By.xpath("//input[@value='Submit']"));
            sumbitButton.click();
          
	}
}
