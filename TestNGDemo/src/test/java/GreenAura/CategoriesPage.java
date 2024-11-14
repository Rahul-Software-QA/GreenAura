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

public class CategoriesPage {
    WebDriver driver;
    WebDriverWait wait;

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

            // Hover over the "Shop" menu item
            WebElement shopButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='quadmenu-navbar-nav']//span[normalize-space()='Shop']")));
            Actions actions = new Actions(driver);
            actions.moveToElement(shopButton).perform();
            System.out.println("Hovered over the Shop menu.");

            // Click on "All Products" button using JavaScript click
            WebElement allProductButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='menu-item-1076']//a[normalize-space()='All Products']")));
            smoothScrollIntoView(allProductButton);
            clickElementUsingJS(allProductButton);
            System.out.println("Clicked on All Products.");

            // Scroll down to bring list and grid view buttons into view
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200)");

            // Switch to list view
            WebElement listButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@data-display='list']//*[name()='svg']")));
            listButton.click();
            System.out.println("Switched to list view.");

            // Add a delay between switching views
            Thread.sleep(2000);

            // Switch to grid view
            WebElement gridButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@data-display='grid']//*[name()='svg']")));
            gridButton.click();
            System.out.println("Switched to grid view.");

            // Scroll down to bring the Order By dropdown into view
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");

            // Interact with the "Order By" dropdown
            WebElement orderByButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='orderby']")));
            orderByButton.click();
            System.out.println("Clicked on Order By dropdown.");

            // Interact with "Filter by price"
            WebElement filterByPriceButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Filter by price']")));
            smoothScrollIntoView(filterByPriceButton);
            filterByPriceButton.click();
            System.out.println("Clicked on Filter by Price.");

            // Scroll down to bring the value selection into view
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");

            // Select a price range value using JavaScript click
            WebElement valueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='76500']")));
            clickElementUsingJS(valueButton);
            System.out.println("Selected price range value.");

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

    @AfterClass
    public void teardown() {
        // Close the browser after test execution
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed successfully.");
        }
    }
}
