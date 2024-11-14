package GreenAura;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GroupedProduct {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        // Initialize WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().deleteAllCookies();

        // Initialize WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to the website
        driver.get("https://ditdev.net/GreenAura/");
    }

    @Test
    public void TestProductQuantity() {
        // Close cookie popup
        try {
            WebElement closeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='cookies-popup-close']")));
            closeButton.click();
        } catch (Exception e) {
            System.out.println("Cookie popup close button found.");
        }

        // Hover over the "Pure Aura" menu item
        WebElement puraAuraButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='quadmenu-navbar-nav']//span[contains(text(),'Pure Aura')]")));
        Actions actions = new Actions(driver);
        actions.moveToElement(puraAuraButton).perform();

        // Click on "Product Grouped" to navigate to the product page
        WebElement groupedProductButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'product Grouped')]")));
        groupedProductButton.click();

        // Increase quantity for "Charmin" product and verify
        increaseQuantityAndVerify("//tr[@id='product-162']", "2");

        // Increase quantity for "Rose" product and verify
        increaseQuantityAndVerify("//tr[@id='product-218']", "2");

        // Increase quantity for "Lips" product and verify
        increaseQuantityAndVerify("//tr[@id='product-233']", "2");

        // Add to Cart
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='add-to-cart']")));
        addToCartButton.click();
    }

    private void increaseQuantityAndVerify(String productRowXPath, String expectedQuantity) {
        try {
            // Click the increase button
            WebElement increaseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(productRowXPath + "//button[@aria-label='Increase Quantity'][normalize-space()='+']")));
            increaseButton.click();

            // Locate the quantity input field
            WebElement quantityInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(productRowXPath + "//input[@type='number']")));

            // Retrieve and assert the quantity
            String actualQuantity = quantityInput.getAttribute("value");
            System.out.println("Expected quantity: " + expectedQuantity + ", Actual quantity: " + actualQuantity);
            Assert.assertEquals(actualQuantity, expectedQuantity, "Quantity did not increase correctly.");
        } catch (Exception e) {
            System.out.println("Error while increasing quantity for product at: " + productRowXPath);
            e.printStackTrace();
        }
    }

    @AfterClass
    public void teardown() {
        // Close the browser after test execution
        if (driver != null) {
            driver.quit();
        }
    }
}
