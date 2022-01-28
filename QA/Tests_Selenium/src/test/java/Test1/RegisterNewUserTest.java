package Test1;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class RegisterNewUserTest {

    private static WebDriver driver;
    private static final String TIPO_DRIVER = "webdriver.chrome.driver";
    private static final String PATH_DRIVER="./src/main/resources/WebDriver/chromedriver.exe";
    private static String url= "http://127.0.0.1:3000/";
    //private static String url = "http://www.digitalcar.ml";
    private static Logger logger = LogManager.getLogger(RegisterNewUserTest.class);

    @BeforeClass
    public static void  SetUpBeforeClass(){
        logger.info("TEST STARTED");
        System.setProperty(TIPO_DRIVER,PATH_DRIVER);
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        WebElement element = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/nav/ul/li[3]/div[1]/a/button"));
        Assert.assertTrue(element.isDisplayed());
        logger.info("VISUALIZE REGISTER FORM");
        element.click();
    }



    @Test
    public  void register() throws InterruptedException {

         Thread.sleep(1000);
        driver.findElement(By.id("name")).sendKeys("Rocio");
        logger.info("Input name user: Rocio");
        driver.findElement(By.id("lastName")).sendKeys("Martinez");
        logger.info("Input last name user: Martinez");
        driver.findElement(By.id("email")).sendKeys("romartinez@gmail.com");
        logger.info("Input email user: romartinez@gmail.com");
        driver.findElement(By.id("password")).sendKeys("123456");
        logger.info("Input password user: 123456");
        driver.findElement(By.id("password2")).sendKeys("123456");
        logger.info("Confirm password user: 123456");
        WebElement element1 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/form/div[6]/a/button"));
        logger.info("REGISTER FORM CORRECTLY COMPLETED");
        Assert.assertTrue(element1.isEnabled());
        element1.click();
        Thread.sleep(6000);
        WebElement element2  =driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]"));
        logger.info("** SUCCESS REGISTER **");
        Assert.assertTrue(element2.isDisplayed());
        element2.click();
        Thread.sleep(1300);

    }



    @AfterClass
    public static void tearDownAfterClass(){
        logger.info("TEST FINISHED");
        driver.quit();
    }
    }



