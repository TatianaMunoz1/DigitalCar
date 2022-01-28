package Test1;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;


public class MyBookingsTest {
    private static WebDriver driver;
    private static final String TIPO_DRIVER = "webdriver.chrome.driver";
    private static final String PATH_DRIVER = "./src/main/resources/WebDriver/chromedriver.exe";
    private static String url = "http://127.0.0.1:3000/";
    //private static String url = "http://www.digitalcar.ml";
    private static Logger logger = LogManager.getLogger(HomeTest.class);

    @BeforeClass
    public static void SetUpBeforeClass() throws InterruptedException {
        logger.info("TEST STARTED");
        System.setProperty(TIPO_DRIVER, PATH_DRIVER);
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        WebElement element = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/nav/ul/li[3]/div[2]/a/button"));
        Assert.assertTrue(element.isDisplayed());
        logger.info("VISUALIZE LOGIN FORM");
        element.click();
        driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);driver.findElement(By.id("email")).sendKeys("mmarquez@gmail.com");
        driver.findElement(By.id("password")).sendKeys("123456");
        WebElement element2 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/form/div[3]/a/button"));
        element2.click();
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]")).isDisplayed());
        logger.info("USER LOGIN SUCCESSFULLY");
    }

    @Test
    public void myBookings() throws InterruptedException {

        WebElement myBookingsButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/nav/ul/li[3]/div[1]/a/button"));
        myBookingsButton.click();
        logger.info("MY BOOKINGS FORM IS DISPLAYED");
        Thread.sleep(2000);
        WebElement booking1 = myBookingsButton.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/section/div/table/tbody/tr[1]"));
        Assert.assertTrue(booking1.isDisplayed());
        booking1.getText();

    }
    @Test
    public void validateUrl() throws InterruptedException {
        Thread.sleep(8000);
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("reservation-by-user"));
        logger.info("URL CHECKED");
    }
    @AfterClass
    public static void tearDownAfterClass(){
        logger.info("TEST FINISHED");
        driver.quit();
    }
}
