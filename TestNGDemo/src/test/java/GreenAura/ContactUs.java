package GreenAura;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;

public class ContactUs {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void setup() {
        // Initialize WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().deleteAllCookies();

        // Initialize WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Navigate to the website
        driver.get("https://ditdev.net/GreenAura/");
    }

    @Test
    public void testContactUsForm() {
        try {
            // Close the cookie popup if present
            try {
                WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='cookies-popup-close']")));
                smoothScrollIntoView(closeButton);
                closeButton.click();
                System.out.println("Cookie popup closed.");
            } catch (TimeoutException e) {
                System.out.println("Cookie popup not displayed.");
            }

            pause(1000); // Slow down after closing the popup

            // Click on "Contact Us" button
            WebElement contactUsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class='quadmenu-navbar-nav']//li[@id='menu-item-985']//a")));
            smoothScrollIntoView(contactUsButton);
            contactUsButton.click();

            pause(1000); // Slow down before filling the form

            // Enter Name
            WebElement enterName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Your Name']")));
            smoothScrollIntoView(enterName);
            enterName.sendKeys("Test Phase");
            System.out.println("Name entered.");

            pause(1000); // Slow down before entering email

            // Enter Email
            WebElement emailId = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Your Email']")));
            smoothScrollIntoView(emailId);
            emailId.sendKeys("greenaura102@yopmail.com");
            System.out.println("Email entered.");

            pause(1000); // Slow down before entering message

            // Enter Message
            WebElement enterYourMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@placeholder='Enter Your Message']")));
            smoothScrollIntoView(enterYourMessage);
            enterYourMessage.sendKeys("This is a test message for automation.");
            System.out.println("Message entered.");

            pause(1000); // Slow down before scrolling to submit

            // Scroll to the "Submit" button smoothly before clicking
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Submit']")));
            smoothScrollIntoView(submitButton);

            pause(1000); // Slow down before clicking submit

            // Click the "Submit" button
            submitButton.click();
            System.out.println("Submit button clicked.");

            pause(2000); // Slow down to observe form submission

            // Validate form submission (Check for success message)
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Thank you for contacting us')]")));
            Assert.assertTrue(successMessage.isDisplayed(), "Form submission failed. Success message not displayed.");
            System.out.println("Contact Us form submitted successfully.");

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @AfterTest
    public void tearDown() {
        // Close the browser after test execution
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed successfully.");
        }
    }

    // Custom pause method to control execution speed
    public void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Method to smoothly scroll to an element
    public void smoothScrollIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        System.out.println("Smoothly scrolled to the element.");
    }
}
