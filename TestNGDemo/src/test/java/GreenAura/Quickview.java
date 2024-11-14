package GreenAura;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Quickview {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private JavascriptExecutor js;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().deleteAllCookies();

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;

        driver.get("https://ditdev.net/GreenAura/");
    }

    @Test
    public void testQuickView() throws InterruptedException {
        // Close cookie popup if present
        try {
            WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='cookies-popup-close']")));
            closeButton.click();
        } catch (TimeoutException e) {
            System.out.println("Cookie popup not displayed.");
        }

        // Hover over the "Shop" menu item
        WebElement shopButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Shop']")));
        actions.moveToElement(shopButton).perform();
        Thread.sleep(1000); // Wait for hover effect

        // Click on "All Products" button
        WebElement allProductButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='menu-item-1325']//a")));
        allProductButton.click();

        // Scroll down to bring the product view button into view
        smoothScroll(200);

        // Hover over the specific product (Aloe Vera)
        WebElement productElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'product') and contains(@class, 'post-71')]")));
        retryHover(productElement);

        // Click on the Quick View button (eye icon)
        WebElement quickViewButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='wdt-col wdt-col-xs-12 wdt-col-sm-12 wdt-col-md-6 wdt-col-qxlg-4 wdt-col-hxlg-4 wdt-col-lg-4 product type-product post-71 status-publish instock product_cat-body-care has-post-thumbnail sale taxable shipping-taxable purchasable product-type-simple']//i[@class='fa-regular fa-eye']")));
        smoothScrollIntoView(quickViewButton);
        quickViewButton.click();

        System.out.println("Quick View button clicked successfully!");

//        // Close the Quick View modal
//        WebElement closeModalButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='close-modal quick-view-close']")));
//        closeModalButton.click();
        
      //click on view full Details button
        WebElement fullDetailsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='button view-full-details']")));
        fullDetailsButton.click();
        
        
    }

    // Method for smooth scrolling by pixels
    public void smoothScroll(int pixels) {
        js.executeScript("window.scrollBy({ top: " + pixels + ", behavior: 'smooth' });");
        try {
            Thread.sleep(1000); // Allow time for smooth scrolling
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Method for smooth scrolling into view of an element
    public void smoothScrollIntoView(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", element);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Retry hover action to ensure it performs correctly
    public void retryHover(WebElement element) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                actions.moveToElement(element).perform();
                Thread.sleep(500); // Short wait for hover effect
                return;
            } catch (Exception e) {
                System.out.println("Hover attempt " + (attempts + 1) + " failed. Retrying...");
                attempts++;
            }
        }
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
