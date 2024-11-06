package GreenAura;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class ContactUs {
	
	@Test
	public void Testcart()
	{
	WebDriver driver = new ChromeDriver();
	driver.get("https://ditdev.net/GreenAura/");
	//Maximize window
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
			driver.manage().deleteAllCookies();
			
			WebElement closeButton = driver.findElement(By.xpath("//a[@id='cookies-popup-close']"));
			   closeButton.click();
            WebElement contactUsButton = driver.findElement(By.xpath("//ul[@class='quadmenu-navbar-nav']//li[@id='menu-item-985']//a"));
            contactUsButton.click();
            WebElement enterName = driver.findElement(By.xpath("//input[@placeholder='Enter Your Name']"));
            enterName.sendKeys("Test Phase");
            WebElement  emailId = driver.findElement(By.xpath("//div[@class='contact_us_form_field']//input[@placeholder='Enter Your Email']"));
            emailId.sendKeys("greenaura102@yopmail.com");
            WebElement enterYourMessage = driver.findElement(By.xpath("//textarea[@placeholder='Enter Your Message']"));
            enterYourMessage.sendKeys("Test Message");
            WebElement submitButton = driver.findElement(By.xpath("//input[@value='Submit']"));
            submitButton.click();
            
	}
}