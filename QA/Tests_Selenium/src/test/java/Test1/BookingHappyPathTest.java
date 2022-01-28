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


public class BookingHappyPathTest {
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
    public void happyPath() throws InterruptedException {

        Actions action = new Actions(driver);
        WebElement about = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/section[1]/div/div/div[1]/div"));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView();",about);
        about.click();
        Thread.sleep(3000);
        WebElement element = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/section/div[1]/div/div[1]"));
        jse.executeScript("arguments[0].scrollIntoView();",element);
        WebElement element2 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/section/div[1]/div/div[1]/div[2]/a/button"));
        logger.info(element.getText());
        Assert.assertTrue(element.isDisplayed());
        element2.click();

        logger.info("Clickeamos en Ver más");

        Thread.sleep(3000);

        WebElement element8 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[3]"));
        jse.executeScript("arguments[0].scrollIntoView();",element8);
        logger.info("IMAGE GALLERY CHECKED");
        Thread.sleep(2000);
        WebElement element9  = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[4]"));
        jse.executeScript("arguments[0].scrollIntoView();",element9);
        logger.info("PRODUCT DETAIL CHECKED");
        logger.info(element9.getText());
        Thread.sleep(2000);
        WebElement element10  = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[6]"));
        jse.executeScript("arguments[0].scrollIntoView();",element10);
        logger.info("VEHICLE´S LOCATION CHECKED");
        Thread.sleep(2000);
        WebElement element11  = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[7]/div"));
        jse.executeScript("arguments[0].scrollIntoView();",element11);
        logger.info("PRODUCT POLITICS CHECKED");
        logger.info(element11.getText());
        Thread.sleep(2000);

        WebElement element12 = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[5]/div"));
        jse.executeScript("arguments[0].scrollIntoView();", element12);
        Thread.sleep(2000);
        logger.info("CLICK ON 'INICIAR RESERVA'");
        action.moveToElement(driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[5]/div/div[2]/div/a/button"))).click().build().perform();
        //element12.click();
        Thread.sleep(2000);


        WebElement element13 = driver.findElement(By.xpath("/html/body/div/div/section/div/div/div[1]/div[1]/div"));
        jse.executeScript("arguments[0].scrollIntoView();",element13);
        WebElement element14 = driver.findElement(By.id("city"));
        element14.sendKeys("Rosario");
        WebElement element1 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/section/div[2]/div/div[1]/div[1]/div/form/div[2]/div/label/span"));
        element1.click();
        Thread.sleep(2000);

        WebElement element15 = driver.findElement(By.xpath("/html/body/div/div/section/div/div/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div[4]/div[5]"));
        WebElement element17 = driver.findElement(By.xpath("/html/body/div/div/section/div/div/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]"));
        jse.executeScript("arguments[0].scrollIntoView();",element17);
        element15.click();

        WebElement element16 = driver.findElement(By.xpath("/html/body/div/div/section/div/div/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div[4]/div[6]"));
        element16.click();

        Thread.sleep(2000);

        WebElement element18 = driver.findElement(By.xpath("/html/body/div/div/section/div/div/div[1]/div[3]/h1"));
        jse.executeScript("arguments[0].scrollIntoView();",element18);
        WebElement element19 = driver.findElement(By.xpath("/html/body/div/div/section/div/div/div[1]/div[3]/div/div/div[1]/div/input"));
        element19.click();

        WebElement element20 = driver.findElement(By.xpath("/html/body/div/div/section/div/div/div[1]/div[3]/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div/ul/li[12]"));
        element20.click();
        Thread.sleep(2000);

        WebElement element21 = driver.findElement(By.xpath("/html/body/div/div/section/div/div/div[2]/div"));
        jse.executeScript("arguments[0].scrollIntoView();",element21);
        Thread.sleep(1000);
        WebElement element22 = driver.findElement(By.xpath("/html/body/div/div/section/div/div/div[2]/div/div/form/div[2]/a/button"));
        jse.executeScript("arguments[0].scrollIntoView();",element18);
        Thread.sleep(1000);

        Assert.assertTrue(element22.isDisplayed());
        element22.click();
        Thread.sleep(5000);

        WebElement element3 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/a/button"));
        Assert.assertTrue(element3.isDisplayed());
        element3.click();
        logger.info("SUCCESSFUL BOOKING");



    }

    @AfterClass
    public static void tearDownAfterClass(){
        logger.info("TEST FINISHED");
        driver.quit();
    }
}
