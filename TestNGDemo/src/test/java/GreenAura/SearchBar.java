package GreenAura;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SearchBar {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void setup() {
        // Initialize WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().deleteAllCookies();

        // Navigate to the website
        driver.get("https://ditdev.net/GreenAura/");
        System.out.println("Navigated to GreenAura website.");

        // Initialize WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void testSearchBar() {
        try {
            // Close cookie popup
            WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='cookies-popup-close']")));
            closeButton.click();
            System.out.println("Cookie popup closed.");

            // Click on the search bar icon
            WebElement searchBarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='header_search_icon header_icon']//a[@id='search-icon']//*[name()='svg']")));
            searchBarButton.click();
            System.out.println("Search icon clicked.");

            // Enter search text
            WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='search-input']")));
            searchBar.sendKeys("Face cream");
            System.out.println("Search text entered.");

            // Click on the search button
            WebElement searchIconButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='fa fa-search']")));
            searchIconButton.click();
            System.out.println("Search button clicked.");

            // Wait for search results to appear
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Search Results')]")));
            System.out.println("Search results displayed successfully.");

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    @AfterTest
    public void teardown() {
        // Close the browser after the test execution
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed successfully.");
        }
    }
}
