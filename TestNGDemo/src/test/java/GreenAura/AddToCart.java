package GreenAura;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddToCart {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void setup() {
        // Initialize WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().deleteAllCookies();

        // Initialize WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Navigate to the website
        driver.get("https://ditdev.net/GreenAura/");
    }

    @Test
    public void testCart() {
        try {
            // Close cookie popup
            WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("cookies-popup-close")));
            smoothScrollIntoView(closeButton);
            closeButton.click();
            pause(1500); // 1.5 seconds pause
            System.out.println("Cookie popup closed.");

            // Click on "Add to Cart" button for the product
            WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='2 / 7']//a[contains(@aria-label,'Add to cart')]")));
            smoothScrollIntoView(addToCartButton);
            addToCartButton.click();
            pause(2000); // 2 seconds pause
            System.out.println("Product added to cart.");

            // Click on "View Cart" button
            WebElement viewCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='View Cart']")));
            smoothScrollIntoView(viewCartButton);
            viewCartButton.click();
            pause(2000);
            System.out.println("Navigated to Cart page.");

            // Click on "Checkout" button
            WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".checkout-button.button.alt.wc-forward")));
            smoothScrollIntoView(checkoutButton);
            checkoutButton.click();
            pause(2000);
            System.out.println("Navigated to Checkout page.");

            // Fill Billing Details
            fillBillingDetails();

            System.out.println("Billing details filled successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void fillBillingDetails() {
        try {
            // First Name
            WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("billing_first_name")));
            smoothScrollIntoView(firstName);
            firstName.sendKeys("Test");
            pause(1500);

            // Last Name
            WebElement lastName = driver.findElement(By.id("billing_last_name"));
            smoothScrollIntoView(lastName);
            lastName.sendKeys("Phase");
            pause(1500);

            // Country Selection
            WebElement countryButton = driver.findElement(By.id("select2-billing_country-container"));
            smoothScrollIntoView(countryButton);
            countryButton.click();
            pause(1500);

            WebElement countryName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(), 'India')]")));
            smoothScrollIntoView(countryName);
            countryName.click();
            pause(1500);

            // Address
            WebElement streetAddress = driver.findElement(By.id("billing_address_1"));
            smoothScrollIntoView(streetAddress);
            streetAddress.sendKeys("104 Thaltej new road");
            pause(1500);

            // City
            WebElement townCity = driver.findElement(By.id("billing_city"));
            smoothScrollIntoView(townCity);
            townCity.sendKeys("Ahmedabad");
            pause(1500);

            // Postal Code
            WebElement PINCode = driver.findElement(By.id("billing_postcode"));
            smoothScrollIntoView(PINCode);
            PINCode.sendKeys("985632");
            pause(1500);

            // Phone Number
            WebElement phone = driver.findElement(By.id("billing_phone"));
            smoothScrollIntoView(phone);
            phone.sendKeys("8754123256");
            pause(1500);

            // Email
            WebElement emailAddress = driver.findElement(By.id("billing_email"));
            smoothScrollIntoView(emailAddress);
            emailAddress.sendKeys("greenaura022@yopmail.com");
            pause(1500);

//            // Ship to a Different Address Checkbox
//            WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("ship-to-different-address-checkbox")));
//            smoothScrollIntoView(checkbox);
//            checkbox.click();
//            pause(1500);

            // Order Notes
            WebElement orderNotes = driver.findElement(By.id("order_comments"));
            smoothScrollIntoView(orderNotes);
            orderNotes.sendKeys("Test Automation");
            pause(1500);

            // Select Cash on Delivery
            WebElement cashOnDelivery = wait.until(ExpectedConditions.elementToBeClickable(By.id("payment_method_cod")));
            smoothScrollIntoView(cashOnDelivery);
            cashOnDelivery.click();
            pause(1500);

            // Agree to Privacy Policy Checkbox
            WebElement privacyPolicyCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("privacy_policy")));
            smoothScrollIntoView(privacyPolicyCheckbox);
            privacyPolicyCheckbox.click();
            pause(1500);

            // Agree to Terms and Conditions Checkbox
            WebElement termsCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("terms_and_conditions")));
            smoothScrollIntoView(termsCheckbox);
            termsCheckbox.click();
            pause(1500);

            System.out.println("Selected Cash on Delivery and agreed to Privacy Policy and Terms & Conditions.");
        } catch (Exception e) {
            System.out.println("An error occurred in fillBillingDetails: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed successfully.");
        }
    }

    // Method to smoothly scroll to an element
    public void smoothScrollIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        System.out.println("Smoothly scrolled to the element.");
    }

    // Custom pause method
    public void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println("Pause interrupted: " + e.getMessage());
        }
    }
}
