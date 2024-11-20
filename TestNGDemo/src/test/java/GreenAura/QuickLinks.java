package GreenAura;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class QuickLinks {

    public String baseUrl = "https://ditdev.net/GreenAura/";
    public ChromeDriver driver;
    public WebDriverWait wait;
    public JavascriptExecutor js;

    @BeforeTest
    public void setup() {
        System.out.println("Before Test executed");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Initialize JavaScript Executor
        js = (JavascriptExecutor) driver;

        // Navigate to the base URL
        driver.get(baseUrl);

        // Set implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        // Initialize explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    public void CustomerPage() {
        try {
        	
        	// Close cookie popup
            WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("cookies-popup-close")));
            closeButton.click();
            System.out.println("Cookie popup closed.");
        	
        	// Click on the Shop
            WebElement ShopLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='menu-item-379']//a[normalize-space()='Shop']")));
            ShopLink.click();
            System.out.println("Shop Page opened.");
            
            // Navigate and scroll Shop page
            navigateAndScroll("//li[@id='menu-item-379']//a[normalize-space()='Shop']", "Shop");

            // Navigate and scroll About Us page
            navigateAndScroll("//li[@id='menu-item-380']//a[normalize-space()='About Us']", "About Us");

            // Navigate and scroll Blog page
            navigateAndScroll("//a[normalize-space()='Blog']", "Blog");

            // Navigate and scroll Contact Us page
            navigateAndScroll("//li[@id='menu-item-382']//a[normalize-space()='Contact Us']", "Contact Us");
            
            // Navigate and scroll FAQs page
            navigateAndScroll("//li[@id='menu-item-1253']//a[normalize-space()='FAQs']", "FAQs");
            
            // Navigate and scroll Review page
            navigateAndScroll("//li[@id='menu-item-1254']//a[normalize-space()='Review']", "Review");

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    // Method to navigate to a page and perform continuous scrolling
    public void navigateAndScroll(String linkXPath, String pageName) {
        try {
            System.out.println("Navigating to " + pageName + "...");

            // Click on the page link
            WebElement pageLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(linkXPath)));
            pageLink.click();
            System.out.println(pageName + " page opened.");

            // Perform continuous scrolling
            continuousScroll(pageName);

            // Navigate back to the previous page
            driver.navigate().back();
            System.out.println("Navigated back from " + pageName + " page.");
        } catch (Exception e) {
            System.err.println("Error navigating to " + pageName + ": " + e.getMessage());
        }
    }

    // Method to perform continuous scrolling
    public void continuousScroll(String pageName) {
        try {
            System.out.println("Starting continuous scroll on " + pageName + "...");

            // Get the total page height
            long pageHeight = (long) js.executeScript("return document.body.scrollHeight;");
            long currentScrollPosition = 0; // Start from the top
            int scrollStep = 200; // Step in pixels for each scroll
            int delayBetweenScrolls = 600; // Delay in milliseconds for moderate speed

            // Scroll down in steps until reaching the bottom
            while (currentScrollPosition < pageHeight) {
                js.executeScript("window.scrollBy(0, " + scrollStep + ");");
                currentScrollPosition += scrollStep;

                System.out.println("Scrolled to position: " + currentScrollPosition);
                Thread.sleep(delayBetweenScrolls); // Wait between scrolls for moderate speed

                // Update the page height dynamically in case the page loads additional content
                pageHeight = (long) js.executeScript("return document.body.scrollHeight;");
            }

            System.out.println("Reached the bottom of " + pageName + " page.");
        } catch (Exception e) {
            System.err.println("Error during scrolling on " + pageName + ": " + e.getMessage());
        }
    }

    @AfterTest
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000); // Short delay before closing
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed.");
        }
    }
}
