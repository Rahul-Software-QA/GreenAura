package GreenAura;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Wishlist {

    @Test
    public void Testcart() {
        WebDriver driver = null;
        try {
            // Initialize WebDriver
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();

            // Open URL
            driver.get("https://ditdev.net/GreenAura/");

            // Initialize WebDriverWait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Close cookie popup if present
            try {
                WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='cookies-popup-close']")));
                closeButton.click();
            } catch (TimeoutException e) {
                System.out.println("Cookie popup not displayed.");
            }

            // Click on the Wishlist button
            WebElement wishlistButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='product-wishlist']//a//*[name()='svg']")));
            wishlistButton.click();

            // Click on "Shop All Products" button
            WebElement allProductButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='empty-wishlist-button']//a[@class='go-shop button'][normalize-space()='Shop all products']")));
            allProductButton.click();

            // Wait for the "All Products" page to load completely
            waitForPageLoad(js);
            System.out.println("All Products page loaded successfully.");

            // Scroll smoothly and interact with products
            smoothScroll(js, 300);

            // Add the first product to wishlist
            WebElement productButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'post-71')]//i[@class='fa-regular fa-heart wishlist-icon-empty']")));
            smoothScrollIntoView(js, productButton);
            productButton.click();

            // Add the second product to wishlist
            WebElement productButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'post-69')]//i[@class='fa-regular fa-heart wishlist-icon-empty']")));
            smoothScrollIntoView(js, productButton1);
            productButton1.click();

            // Open Wishlist page again
            WebElement wishlistButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='product-wishlist']//a//*[name()='svg']")));
            wishlistButton1.click();

            // Delete the first item from wishlist
            WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[@id='row_71']//i[@class='fa fa-trash']")));
            smoothScrollIntoView(js, deleteButton);
            deleteButton.click();

            // Add the item to cart after deleting from wishlist
            WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='Add to cart: “Alpine Glow Body Cream”']")));
            smoothScrollIntoView(js, addToCartButton);
            addToCartButton.click();

            // Verify the item is added to the cart
            WebElement cart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='cart-contents']")));
            String cartText = cart.getText();
            Assert.assertTrue(cartText.contains("1 Item"), "Item was not added to the cart!");

            System.out.println("Wishlist test completed successfully!");

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Ensure the browser is closed after the test
            if (driver != null) {
                driver.quit();
                System.out.println("Browser closed successfully.");
            }
        }
    }

    // Smooth scroll by pixel value
    public void smoothScroll(JavascriptExecutor js, int pixels) {
        js.executeScript("window.scrollBy({ top: " + pixels + ", behavior: 'smooth' });");
        try {
            Thread.sleep(1000); // Allow time for smooth scrolling
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Smooth scroll to bring the element into view
    public void smoothScrollIntoView(JavascriptExecutor js, WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        try {
            Thread.sleep(1000); // Allow time for smooth scrolling
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Wait for the page to load completely
    public void waitForPageLoad(JavascriptExecutor js) {
        new WebDriverWait((WebDriver) js, Duration.ofSeconds(30)).until(
            webDriver -> js.executeScript("return document.readyState").equals("complete")
        );
        System.out.println("Page load complete."); 
    }
}
