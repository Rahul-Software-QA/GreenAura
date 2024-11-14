package GreenAura;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BlogPages {

    WebDriver driver;

    @BeforeTest
    public void setup() {
        // Initialize WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        driver.manage().deleteAllCookies();
        driver.get("https://ditdev.net/GreenAura/");
        System.out.println("Navigated to GreenAura website.");
    }

    @Test
    public void testBlogPages() throws InterruptedException {
        try {
            // Close cookie popup
            clickElement(By.xpath("//a[@id='cookies-popup-close']"), "Cookie Popup Close Button");

            // Hover over the "Shop" menu item
            WebElement newsButton = driver.findElement(By.xpath("//ul[@class='quadmenu-navbar-nav']//a[@title='News']"));
            Actions actions = new Actions(driver);
            actions.moveToElement(newsButton).perform();
            System.out.println("Hovered over the News button.");

            // Click on "All Products" button
            clickElement(By.xpath("//ul[@class='quadmenu-row ps-container ps-theme-default']//li[@id='menu-item-1288']//div[@id='dropdown-1288']//ul//li[@id='menu-item-1297']//a"), "All Products Button");

            // Scroll the blog page slowly
            slowScrollDown();

            // Click on "All Categories" button
            clickElement(By.xpath("//main[@id='primary']//li[1]//a[1]"), "All Categories Button");

            // Click on "Elevate" button
            clickElement(By.xpath("/html/body/div[2]/main/section/div/div/div/article/div/div[2]/div/div[2]/h2/a"), "Elevate Button");

            // Scroll the blog page slowly
            slowScrollDown();

        } catch (Exception e) {
            System.err.println("Test encountered an error: " + e.getMessage());
        }
    }

    // Method for slow scrolling
    public void slowScrollDown() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        long pageHeight = (long) js.executeScript("return document.body.scrollHeight");

        System.out.println("Starting slow scroll down the page.");

        // Scroll in increments of 200 pixels with a pause
        for (int i = 0; i < pageHeight; i += 200) {
            js.executeScript("window.scrollBy(0, 200)");
            Thread.sleep(500); // Pause for 500 milliseconds (adjust as needed)
        }

        // Scroll back to the top of the page
        js.executeScript("window.scrollTo(0, 0)");
        System.out.println("Scrolled back to the top of the page.");
    }

    // Utility method to click elements
    public void clickElement(By locator, String elementName) {
        try {
            WebElement element = driver.findElement(locator);
            element.click();
            System.out.println("Clicked on: " + elementName);
        } catch (NoSuchElementException e) {
            System.err.println("Element not found: " + elementName + " - " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error clicking on element: " + elementName + " - " + e.getMessage());
        }
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed successfully.");
        }
    }
}
