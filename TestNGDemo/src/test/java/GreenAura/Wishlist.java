package GreenAura;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class Wishlist {
	
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
            WebElement wishlistButton = driver.findElement(By.xpath("//div[@class='product-wishlist']//a//*[name()='svg']"));
            wishlistButton.click();
            WebElement allProductButton = driver.findElement(By.xpath("//div[@class='empty-wishlist-button']//a[@class='go-shop button'][normalize-space()='Shop all products']"));
            allProductButton.click();
            WebElement productButton = driver.findElement(By.xpath("//div[@class='wdt-col wdt-col-xs-12 wdt-col-sm-12 wdt-col-md-6 wdt-col-qxlg-4 wdt-col-hxlg-4 wdt-col-lg-4 product type-product post-71 status-publish instock product_cat-body-care has-post-thumbnail sale taxable shipping-taxable purchasable product-type-simple']//i[@class='fa-regular fa-heart wishlist-icon-empty']"));
            productButton.click();
            WebElement productButton1 = driver.findElement(By.xpath("//div[@class='wdt-col wdt-col-xs-12 wdt-col-sm-12 wdt-col-md-6 wdt-col-qxlg-4 wdt-col-hxlg-4 wdt-col-lg-4 product type-product post-69 status-publish instock product_cat-body-care product_cat-hair-care has-post-thumbnail sale taxable shipping-taxable purchasable product-type-simple']//i[@class='fa-regular fa-heart wishlist-icon-empty']"));
            productButton1.click();
            WebElement wishlistButton1 = driver.findElement(By.xpath("//div[@class='product-wishlist']//a//*[name()='svg']"));
            wishlistButton1.click();
            WebElement deleteButton = driver.findElement(By.xpath("//tr[@id='row_71']//i[@class='fa fa-trash']"));
            deleteButton.click();
            WebElement addToCartButton = driver.findElement(By.xpath("//a[@aria-label='Add to cart: “Alpine Glow Body Cream”']"));
            addToCartButton.click();
            
            
	}
}