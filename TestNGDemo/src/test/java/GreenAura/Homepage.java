
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

public class HomePage {
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

//            // Click on "collection" button using JavaScript click
//            WebElement collectionButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='1 / 3']//span[@class='circle']")));
//            smoothScrollIntoView(collectionButton);
//            clickElementUsingJS(collectionButton);
//            System.out.println("Clicked on collection.");
            
            // Add a delay between switching views
            Thread.sleep(5000);
            
            // Hover over the "Shop" menu item
            WebElement collectionButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='1 / 3']//span[@class='circle']")));
            Actions actions = new Actions(driver);
            actions.moveToElement(collectionButton).perform();
            clickElementUsingJS(collectionButton);
            System.out.println("Hovered over the collectionButton.");
            
            // Add a delay between switching views
            Thread.sleep(2000);

            // Scroll down to bring list and grid view buttons into view
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200)");
            
            // Navigate back to the previous page
            driver.navigate().back();

            // Switch to right slider Button
            WebElement rightsliderButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='Next slide']")));
            Actions actions1 = new Actions(driver);
            actions.moveToElement(rightsliderButton).perform();
            clickElementUsingJS(rightsliderButton);
            System.out.println("Switched to rightslider.");

            // Add a delay between switching views
            Thread.sleep(2000);

            // Switch to left slider Button
            WebElement leftliderButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='Previous slide']")));
            Actions actions2 = new Actions(driver);
            actions.moveToElement(leftliderButton).perform();
            clickElementUsingJS(leftliderButton);
            System.out.println("Switched to leftslider.");

            // Add a delay between switching views
            Thread.sleep(2000);

            // Scroll down to bring the Order By dropdown into view
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");


            // Interact with "Serums Button"
            WebElement SerumsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='button primary-button'][normalize-space()='Serums']")));
            SerumsButton.click();
            System.out.println("Clicked on Serums Button.");
            
            // Add a delay between switching views
            Thread.sleep(2000);
             
            // Navigate back to the previous page
            driver.navigate().back();
            
            
            // Interact with "haircare"
            WebElement haircareButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='button primary-button'][normalize-space()='Hair Care']")));
            haircareButton.click();
            System.out.println("Clicked on haircare Button.");
            
            // Add a delay between switching views
            Thread.sleep(3000);
             
             // Navigate back to the previous page
             driver.navigate().back();
            
            // Scroll down to bring list and grid view buttons into view
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200)");
            
            // Interact with "lipstick Button"
            WebElement lipstickButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='button primary-button'][normalize-space()='Lipstick']")));
            lipstickButton.click();
            System.out.println("Clicked on lipstickButton.");
            
            // Add a delay between switching views
            Thread.sleep(3000);
             
             // Navigate back to the previous page
             driver.navigate().back();
            
             // Interact with "bodycream Button"
             WebElement bodycreamButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='button primary-button'][normalize-space()='Body Cream']")));
             bodycreamButton.click();
             System.out.println("Clicked on bodycreamButton.");
             
              // Add a delay between switching views
              Thread.sleep(3000);
              
              // Navigate back to the previous page
              driver.navigate().back();
            
              // Scroll down to bring list and grid view buttons into view
              ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200)");
              
              // Interact with "bodycream Button"
              WebElement explorecollectionButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Explore Collection')]")));
              explorecollectionButton.click();
              System.out.println("Clicked on bodycreamButton.");
              
               // Add a delay between switching views
               Thread.sleep(3000);
               
               
               // Scroll down to bring list and grid view buttons into view
               ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,600)");
               
               // Interact with "buynow Button"
               WebElement buynowButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Buy Now']]")));
               buynowButton.click();
               System.out.println("Clicked on buynow Button.");
               
                // Add a delay between switching views
                Thread.sleep(3000);
                
                // Scroll down to bring list and grid view buttons into view
                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,600)");
              
                // Interact with "buynow Button"
                WebElement viewallButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://ditdev.net/GreenAura/shop/'][normalize-space()='View All']")));
                viewallButton.click();
                System.out.println("Clicked on viewall Button."); 
                
                 // Add a delay between switching views
                 Thread.sleep(3000); 
                 
                 // Navigate back to the previous page
                 driver.navigate().back();
                 
                 // Scroll down to bring list and grid view buttons into view
                 ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
                 
                 // Interact with "buynow Button"
                 WebElement shopnowButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Shop Now']")));
                 shopnowButton.click();
                 System.out.println("Clicked on viewall Button."); 
                 
                  // Add a delay between switching views
                  Thread.sleep(3000); 
                  
                  // Navigate back to the previous page
                  driver.navigate().back();
                  
                  // Scroll down to bring list and grid view buttons into view
                  ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
              
              // Add a delay between switching views
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

    @AfterClass
    public void teardown() {
        // Close the browser after test execution
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed successfully.");
        }
    }
}
