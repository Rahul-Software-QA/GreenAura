package GreenAura;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class AddToCart {
	
	@Test
	public void Testcart()
	{
	WebDriver driver = new ChromeDriver();
	driver.get("https://ditdev.net/GreenAura/");
	//Maximize window
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.manage().deleteAllCookies();
			
			WebElement closeButton = driver.findElement(By.xpath("//a[@id='cookies-popup-close']"));
		   closeButton.click();
		   WebElement AddToCartButton = driver.findElement(By.xpath("//div[@aria-label='2 / 7']//a[@aria-label='Add to cart: “Pure Harmony Body Cream”'][normalize-space()='Add to cart']"));
		   AddToCartButton.click();
		   WebElement ViewCartButton = driver.findElement(By.xpath("//span[normalize-space()='View Cart']"));
		   ViewCartButton.click();
		   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		   WebElement CheckoutButton = driver.findElement(By.xpath("//a[@class='checkout-button button alt wc-forward']"));
		   CheckoutButton.click();
		   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		   WebElement FirstName = driver.findElement(By.xpath("//input[@id='billing_first_name']"));
		   FirstName.sendKeys("Test");
		   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
           WebElement LastName = driver.findElement(By.xpath("//input[@id='billing_last_name']"));
           LastName.sendKeys("Phase");
           driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
           WebElement CountryButton = driver.findElement(By.xpath("//span[@id='select2-billing_country-container']"));
           CountryButton.click();
           WebElement CountryName = driver.findElement(By.xpath("//li[@id='select2-billing_country-result-tbvi-IN']"));
           CountryName.click();
           WebElement StreetAddress = driver.findElement(By.xpath("//input[@id='billing_address_1']"));
           StreetAddress.sendKeys("104 Thaltej new road ");
           WebElement TownCity = driver.findElement(By.xpath("//input[@id='billing_city']"));
           TownCity.sendKeys("Ahmedabad");
         
	}
}