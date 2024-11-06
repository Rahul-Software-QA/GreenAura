package GreenAura;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class SearchBar {
	
	@Test
	public void Testcart()
	{
	WebDriver driver = new ChromeDriver();
	driver.get("https://ditdev.net/GreenAura/");
	//Maximize window
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			driver.manage().deleteAllCookies();
			
			WebElement closeButton = driver.findElement(By.xpath("//a[@id='cookies-popup-close']"));
		   closeButton.click();
		   WebElement searchBarButton = driver.findElement(By.xpath("//div[@class='header_search_icon header_icon']//a[@id='search-icon']//*[name()='svg']"));
            searchBarButton.click();
            WebElement searchBar = driver.findElement(By.xpath("//input[@id='search-input']"));
            searchBar.sendKeys("Face cream");
            WebElement searchIconButton = driver.findElement(By.xpath("//i[@class='fa fa-search']"));
            searchIconButton.click();
	}
}
