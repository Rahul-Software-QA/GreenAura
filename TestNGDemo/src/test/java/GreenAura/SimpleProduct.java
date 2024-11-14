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

public class SimpleProduct {
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
        wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        // Navigate to the website
        driver.get("https://ditdev.net/GreenAura/");
    }

    @Test
    public void testProductQuantity() {
        try {
            // Close cookie popup
            WebElement closeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='cookies-popup-close']")));
            closeButton.click();
            System.out.println("Cookie popup closed.");

            // Hover over the "Pure Aura" menu item
            WebElement PureAuraButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='quadmenu-navbar-nav']//span[normalize-space()='Pure Aura']")));
            Actions actions = new Actions(driver);
            actions.moveToElement(PureAuraButton).perform();
            System.out.println("Hovered over 'Pure Aura' menu.");

            // Click on "Product Simple" to navigate to the product page
            WebElement simpleProductButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Product Simple']")));
            simpleProductButton.click();
            System.out.println("Navigated to 'Product Simple' page.");

            // Locate the quantity input field
            WebElement quantityInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='qty']")));

            // Get the initial quantity
            String initialQuantity = quantityInput.getAttribute("value");
            System.out.println("Initial quantity: " + initialQuantity);

            // Increase quantity using JavaScript click
            WebElement increaseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='increase-quantity-button']")));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", increaseButton);
            System.out.println("Clicked increase button.");

            // Wait for the quantity to update
            WebDriverWait quantityWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            quantityWait.until(ExpectedConditions.attributeToBe(quantityInput, "value", "2"));
            String increasedQuantity = quantityInput.getAttribute("value");
            System.out.println("Increased quantity: " + increasedQuantity);
            Assert.assertEquals(increasedQuantity, "2", "Quantity did not increase correctly.");

            // Decrease quantity using JavaScript click
            WebElement decreaseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='decrease-quantity-button']")));
            js.executeScript("arguments[0].click();", decreaseButton);
            System.out.println("Clicked decrease button.");

            // Wait for the quantity to update
            quantityWait.until(ExpectedConditions.attributeToBe(quantityInput, "value", "1"));
            String decreasedQuantity = quantityInput.getAttribute("value");
            System.out.println("Decreased quantity: " + decreasedQuantity);
            Assert.assertEquals(decreasedQuantity, "1", "Quantity did not decrease correctly.");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed due to an exception: " + e.getMessage());
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
