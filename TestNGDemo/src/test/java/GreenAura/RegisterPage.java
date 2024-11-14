package GreenAura;

import java.time.Duration;
import java.time.Instant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class RegisterPage {

    public String baseUrl = "https://ditdev.net/GreenAura/my-account/";
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

        // Start measuring page load time
        Instant start = Instant.now();
        driver.get(baseUrl);

        // Measure page load time
        Instant end = Instant.now();
        long loadTime = Duration.between(start, end).toMillis();
        System.out.println("Page Load Time: " + loadTime + " milliseconds");

        // Set implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        // Initialize explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    public void RegisterTest() {
        try {
            // Scroll to the registration form
            smoothScroll(300);

            // Enter email
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='reg_email']")));
            emailField.sendKeys("greenaura" + System.currentTimeMillis() + "@yopmail.com");

            // Scroll to password field
            smoothScroll(200);

            // Enter password
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='reg_password']")));
            passwordField.sendKeys("Testing@12345");

            // Scroll to register button
            smoothScroll(200);

            // Click the Register button
            WebElement registerButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='register']")));
            registerButton.click();

            // Verify registration success
            WebElement dashboardLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Dashboard']")));
            if (dashboardLink.isDisplayed()) {
                System.out.println("Registration successful!");
            } else {
                System.out.println("Registration failed!");
            }

        } catch (Exception e) {
            System.out.println("An error occurred during the registration test: " + e.getMessage());
        }
    }

    // Method for smooth scrolling
    public void smoothScroll(int pixels) {
        js.executeScript("window.scrollBy({ top: " + pixels + ", behavior: 'smooth' });");
        try {
            Thread.sleep(1000); // Wait for smooth scrolling effect
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000); // Short delay before closing
        if (driver != null) {
            driver.quit();
        }
    }
}
