package GreenAura;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PostComment {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void setup() {
        // Initialize WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        driver.manage().deleteAllCookies();
        driver.get("https://ditdev.net/GreenAura/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        System.out.println("Navigated to GreenAura website.");
    }

    @Test
    public void testPostComment() throws InterruptedException {
        try {
            // Close cookie popup
            WebElement closeButton = driver.findElement(By.xpath("//a[@id='cookies-popup-close']"));
            closeButton.click();
            System.out.println("Cookie popup closed.");

            // Hover over the "Shop" menu item
            WebElement newsButton = driver.findElement(By.xpath("//ul[@class='quadmenu-navbar-nav']//a[@title='News']"));
            Actions actions = new Actions(driver);
            actions.moveToElement(newsButton).perform();
            System.out.println("Hovered over the News button.");

            // Click on "All Products" button
            WebElement essentialButton = driver.findElement(By.xpath("//ul[@class='quadmenu-row ps-container ps-theme-default']//li[@id='menu-item-1288']//div[@id='dropdown-1288']//ul//li[@id='menu-item-1297']//a"));
            essentialButton.click();
            System.out.println("Clicked on All Products.");

            // Fill the comment form
            fillCommentForm();

        } catch (Exception e) {
            System.err.println("An error occurred during the test: " + e.getMessage());
        }
    }

    // Method to fill the comment form
    public void fillCommentForm() throws InterruptedException {
        // Comment
        WebElement comment = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("comment")));
        smoothScrollIntoView(comment);
        comment.sendKeys("Test phase for Green Aura");
        pause(1500);
        System.out.println("Comment entered.");

        // Name
        WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("author")));
        smoothScrollIntoView(name);
        name.sendKeys("Test");
        pause(1500);
        System.out.println("Name entered.");

        // Email
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        smoothScrollIntoView(email);
        email.sendKeys("greenaura022@yopmail.com");
        pause(1500);
        System.out.println("Email entered.");

        // Website URL
        WebElement websiteURL = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("url")));
        smoothScrollIntoView(websiteURL);
        websiteURL.sendKeys("https://ditdev.net/GreenAura/the-essential-skincare-routine-for-glowing-skin/");
        pause(1500);
        System.out.println("Website URL entered.");

        // Save my name, email, and website in this browser for the next time I comment
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("wp-comment-cookies-consent")));
        smoothScrollIntoView(checkbox);
        checkbox.click();
        pause(1500);
        System.out.println("Checkbox clicked.");

        // Post the comment
        WebElement postCommentButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
        smoothScrollIntoView(postCommentButton);
        postCommentButton.click();
        pause(1500);
        System.out.println("Comment submitted.");
    }

    // Method for smooth scrolling to an element
    public void smoothScrollIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", element);
        System.out.println("Scrolled to element: " + element.getAttribute("id"));
    }

    // Method to pause execution
    public void pause(long milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed successfully.");
        }
    }
}
