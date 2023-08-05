import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class loginTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/nst/Downloads/chromedriver_mac64/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void testLoginWithValidCredential() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/login");
        driver.manage().window().maximize();
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(1000);
        //verification
        WebElement headingElement = driver.findElement(By.id("flash"));
        String headingText = headingElement.getText();
        String expectedTitle="You logged into a secure area!\n" + "×";
        Assert.assertEquals(headingText, expectedTitle, "Correct!");
        Thread.sleep(1000);
        LOGGER.info("Verified OK");
    }

    @Test
    public void testLoginWithInvalidCredential() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/login");
        driver.manage().window().maximize();
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("random");
        Thread.sleep(1000);
        LOGGER.info("Username and Password typed");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(1000);
        //verification
        WebElement headingElement = driver.findElement(By.id("flash"));
        String headingText = headingElement.getText();
        String expectedTitle="Your password is invalid!\n" + "×";
        Assert.assertEquals(headingText, expectedTitle, "Correct!");
        Thread.sleep(1000);
        LOGGER.info("Verified OK");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
