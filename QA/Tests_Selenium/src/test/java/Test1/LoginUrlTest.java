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


public class LoginUrlTest {

    private static WebDriver driver;
    private static final String TIPO_DRIVER = "webdriver.chrome.driver";
    private static final String PATH_DRIVER = "./src/main/resources/WebDriver/chromedriver.exe";
    private static String url = "http://127.0.0.1:3000/";
    //private static String url = "http://www.digitalcar.ml";
    private static Logger logger = LogManager.getLogger(RegisterNewUserTest.class);

    @BeforeClass
    public static void SetUpBeforeClass() {
        logger.info("TEST STARTED");
        System.setProperty(TIPO_DRIVER, PATH_DRIVER);
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }


    @Test
    public void urlRegistro() throws InterruptedException {

        WebElement element = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/nav/ul/li[3]/div[2]/a/button"));
        Assert.assertTrue(element.isDisplayed());
        logger.info("VISUALIZE LOGIN FORM");
        element.click();
        Thread.sleep(2000);
        String urlLogin = driver.getCurrentUrl();
        Boolean condition = urlLogin.equals("http://127.0.0.1:3000/login");
        //Boolean condition= urlRegistro.equals("http://www.digitalcar.ml/login");
        if (condition) {
            Assert.assertTrue(true);
            logger.info("LOGIN FORM URL CORRECTLY CHECKED");
        } else {
            logger.error("WRONG LOGIN FORM URL");
        }

    }
    @AfterClass
    public static void tearDownAfterClass(){
        logger.info("TEST FINISHED");
        driver.quit();
    }
}
