package GreenAura;

import java.time.Duration;
import java.time.Instant;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class LoginPage {
    public String baseUrl = "https://ditdev.net/GreenAura/my-account/";
    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeTest
    public void setup() {
        System.out.println("Setting up the WebDriver and navigating to the login page.");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().deleteAllCookies();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Measure loading time
        Instant startTime = Instant.now();
        driver.get(baseUrl);
        Instant endTime = Instant.now();
        System.out.println("Page Load Time: " + Duration.between(startTime, endTime).toMillis() + " milliseconds");

        // Slow down page animations
        ((JavascriptExecutor) driver).executeScript("document.getElementsByTagName('html')[0].style.animationDuration = '5s';");
    }

    @Test
    public void loginTest() throws InterruptedException {
        // Scroll with custom speed
        smoothScrollBy(0, 200);

        // Enter username
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='username']")));
        usernameField.sendKeys("greenaura@yopmail.com");

        // Enter password
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='password']")));
        passwordField.sendKeys("Testing@12345");

        // Click the login button
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='login']")));
        loginButton.click();

        // Wait for Dashboard button and click
        WebElement dashboardButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Dashboard']")));
        dashboardButton.click();

        // Navigate to Orders section
        smoothScrollBy(0, 200);
        WebElement orderButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--orders']//a[normalize-space()='Orders']")));
        orderButton.click();

        // Click "View" button for an order
        smoothScrollBy(0, 200);
        WebElement orderViewButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='View']")));
        orderViewButton.click();

        // Click "Downloads" button
        smoothScrollBy(0, 200);
        WebElement downloadButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Downloads']")));
        downloadButton.click();

        // Click "Browse Products" button
        smoothScrollBy(0, 200);
        WebElement browseProductsButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='button wc-forward']")));
        browseProductsButton.click();

        // Navigate back to the previous page
        driver.navigate().back();
        
        // Navigate to Address section
        smoothScrollBy(0, 200);
        WebElement addressButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Addresses']")));
        addressButton.click();

        // Edit Billing Address
        WebElement editBillingAddressButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Edit Billing address']")));
        editBillingAddressButton.click();
        smoothScrollBy(0, 200);

        WebElement saveBillingAddressButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='save_address']")));
        saveBillingAddressButton.click();

        // Edit Shipping Address
        WebElement editShippingAddressButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Edit Shipping address']")));
        editShippingAddressButton.click();
        smoothScrollBy(0, 200);

        WebElement saveShippingAddressButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='save_address']")));
        saveShippingAddressButton.click();

        // Account Details
        WebElement accountDetailsButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Account details']")));
        accountDetailsButton.click();
        smoothScrollBy(0, 200);

        WebElement saveChangesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='save_account_details']")));
        saveChangesButton.click();

        // Log Out
        WebElement logOutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--customer-logout']//a[contains(text(),'Log out')]")));
        logOutButton.click();

        // Add a delay between switching views
        Thread.sleep(5000);
    }

    @AfterTest
    public void teardown() {
        System.out.println("Closing the browser.");
        if (driver != null) {
            driver.quit();
        }
    }

    // Custom method for smooth scrolling
    public void smoothScrollBy(int x, int y) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy({top: arguments[1], left: arguments[0], behavior: 'smooth'});", x, y);
        try {
            Thread.sleep(1000); // Adjust scroll speed by changing the sleep duration
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
