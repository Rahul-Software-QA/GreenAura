package GreenAura;

import java.time.Duration;
import java.util.Random;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CustomerReview {
    WebDriver driver;
    WebDriverWait wait;

    // Define a Random object to generate random data
    Random random = new Random();

    @BeforeClass
    public void setup() {
        // Initialize WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        driver.manage().deleteAllCookies();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Navigate to the website
        driver.get("https://ditdev.net/GreenAura/");
        System.out.println("Navigated to GreenAura website.");
    }

    @Test
    public void testCart() throws InterruptedException {
        try {
            // Close cookie popup
            WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='cookies-popup-close']")));
            closeButton.click();
            System.out.println("Cookie popup closed.");

            // Add a delay between switching views
            Thread.sleep(5000);

            // Scroll down to bring the value selection into view
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");

            // Click on "Body cream" button using JavaScript click
            WebElement LipstickButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='button primary-button'][normalize-space()='Lipstick']")));
            smoothScrollIntoView(LipstickButton);
            clickElementUsingJS(LipstickButton);
            System.out.println("Clicked on Lipstick.");

            // Scroll down to bring the value selection into view
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");

            // Click on "Blossom Charm" button using JavaScript click
            WebElement BlossomCharmButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Blossom Charm Lipstick']")));
            smoothScrollIntoView(BlossomCharmButton);
            clickElementUsingJS(BlossomCharmButton);
            System.out.println("Clicked on BlossomCharm.");

            // Add a delay between switching views
            Thread.sleep(2000);

            // Scroll down to bring the value selection into view
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");

            // Click on "Reviews" button using JavaScript click
            WebElement ReviewsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Reviews (1)']")));
            smoothScrollIntoView(ReviewsButton);
            clickElementUsingJS(ReviewsButton);
            System.out.println("Clicked on Reviews");

            // Add a delay between switching views
            Thread.sleep(2000);

            // Scroll down to bring the value selection into view
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200)");

            // Click on "Reviews" button using JavaScript click
            WebElement yourratingButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='star-4']")));
            smoothScrollIntoView(yourratingButton);
            clickElementUsingJS(yourratingButton);
            System.out.println("Clicked on star");

            // Add a delay between switching views
            Thread.sleep(3000);

            // Your Review
            WebElement yourreviewField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='comment']")));
            yourreviewField.sendKeys(generateRandomReview());
            System.out.println("Entered review text.");

            // Add a delay between switching views
            Thread.sleep(3000);

            // Enter Name
            WebElement NameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='author']")));
            NameField.sendKeys(generateRandomName());
            System.out.println("Entered random name.");

            // Add a delay between switching views
            Thread.sleep(3000);

            // Scroll down to bring the value selection into view
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,400)");

            // Enter Email
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
            emailField.sendKeys(generateRandomEmail());
            System.out.println("Entered random email.");

            // Add a delay between switching views
            Thread.sleep(3000);

            // Save check box
            WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='wp-comment-cookies-consent']")));
            checkbox.click();
            System.out.println("Cookie consent checkbox clicked.");

            // Add a delay between switching views
            Thread.sleep(3000);

            // Submit the review
            WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='submit']")));
            submitButton.click();
            System.out.println("Review submitted.");

            // Add a delay before finishing
            Thread.sleep(8000);

        } catch (Exception e) {
            System.err.println("An error occurred during the test: " + e.getMessage());
        }
    }

    // Method to scroll smoothly to an element
    public void smoothScrollIntoView(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", element);
            System.out.println("Scrolled to element: " + element.getText());
        } catch (Exception e) {
            System.err.println("Failed to scroll to element: " + e.getMessage());
        }
    }

    // Method to click an element using JavaScript
    public void clickElementUsingJS(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
            System.out.println("Element clicked using JavaScript.");
        } catch (Exception e) {
            System.err.println("JavaScript click failed: " + e.getMessage());
        }
    }

    // Generate random review text
    public String generateRandomReview() {
        String[] reviews = {
            "This product is amazing! I love the texture and the scent.",
            "It's a good product, but it could be a little better.",
            "I didn't like the product. It didn't meet my expectations.",
            "Great value for money! I will definitely buy again.",
            "The product is okay, but the color wasn't what I expected."
        };
        return reviews[random.nextInt(reviews.length)];
    }

    // Generate random name
    public String generateRandomName() {
        String[] names = { "John Doe", "Jane Smith", "Foram Shah", "Alex Brown", "Emily White" };
        return names[random.nextInt(names.length)];
    }

    // Generate random email
    public String generateRandomEmail() {
        String[] domains = { "gmail.com", "yahoo.com", "outlook.com" };
        return "user" + random.nextInt(1000) + "@" + domains[random.nextInt(domains.length)];
    }

    @AfterClass
    public void teardown() {
        // Close the browser after test execution
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed successfully.");
        }
    }
}
