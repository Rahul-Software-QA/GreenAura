package Demo;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class LoginLogout {

    WebDriver driver;

    @BeforeClass
    void setup() {
        System.out.println("Setup method executed");

        // Use WebDriverManager to manage ChromeDriver
        driver = new ChromeDriver();

        // Open URL
        driver.get("https://ditdev.net/GreenAura/my-account/");

        // Maximize browser
        driver.manage().window().maximize();
    }

    @AfterClass
    void tearDown() {
        System.out.println("Teardown method executed");
        // Quit the browser to close all sessions
        driver.quit();
    }

    @Test(dataProvider = "UserData")
    void VerifyLogin(String username, String password) {
        // Find the username and password input fields
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));

        // Enter the login credentials
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);

        // Click the login button (verify the ID or use other locator if needed)
        WebElement loginButton = driver.findElement(By.name("login"));
        loginButton.click();

        // Wait for login to complete and the account menu to appear
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        //Go to categories section 
        WebElement PureAura = driver.findElement(By.xpath("//a[text()='Pure Aura']//a"));
        PureAura.click();

        // Click the logout button
        WebElement logoutButton = driver.findElement(By.xpath("//a[contains(text(),'Log out')], 'Logout')]"));
        logoutButton.click();

    }

    @DataProvider(name = "UserData")
    public Object[][] dataProviderMethod() {
        return new Object[][]{
            {"greenaura@yopmail.com", "Testing@12345"},
            {"locked_out_user", "wrong_password"}
        };
    }
}
