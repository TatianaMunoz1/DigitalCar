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

import java.util.concurrent.TimeUnit;

public class LoginFormTest {
    private static WebDriver driver;
    private static final String TIPO_DRIVER = "webdriver.chrome.driver";
    private static final String PATH_DRIVER = "./src/main/resources/WebDriver/chromedriver.exe";
    private static String url = "http://127.0.0.1:3000/";
    //private static String url = "http://www.digitalcar.ml";
    private static Logger logger = LogManager.getLogger(HomeTest.class);

    @BeforeClass
    public static void SetUpBeforeClass() {
        logger.info("TEST STARTED");
        System.setProperty(TIPO_DRIVER, PATH_DRIVER);
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        WebElement element = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/nav/ul/li[3]/div[2]/a/button"));
        Assert.assertTrue(element.isDisplayed());
        logger.info("VISUALIZE LOGIN FORM");
        element.click();
        driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);
    }

    @Test
    public void hasLogo() {
        WebElement logo = driver.findElement(By.xpath("/html/body/div/div/div[1]/a/img"));
        Boolean condition = logo.isDisplayed();
        if (condition) {
            Assert.assertTrue("Check logo", true);
            logger.info("LOGO CORRECTLY CHECKED");
        } else {
            Assert.assertTrue("Check logo", false);
            logger.error("LOGO NOT FOUND");
        }
    }

    @Test
    public void hasFooter() {
        WebElement footer = driver.findElement(By.xpath("//*[@id=\"root\"]/div/footer"));
        Boolean condition = footer.isDisplayed();
        if (condition) {
            Assert.assertTrue("Check footer", true);
            logger.info("FOOTER CORRECTLY DISPLAYED");
        } else {
            Assert.assertTrue("Check footer", false);
            logger.error("FOOTER NOT FOUND");
        }
    }

    @Test
    public void hasCopyright() {
        WebElement copyright = driver.findElement(By.xpath("//*[@id=\"root\"]/div/footer/p"));
        Boolean condition = copyright.isDisplayed();
        if (condition) {
            Assert.assertTrue("Check copyright", true);
            logger.info("COPYRIGHT CORRECTLY DISPLAYED");
        } else {
            Assert.assertTrue("Check copyright", false);
            logger.error("COPYRIGHT NOT FOUND");
        }
    }

    @Test
    public void hasCopyrightText() {
        WebElement copyright = driver.findElement(By.xpath("//*[@id=\"root\"]/div/footer/p"));
        Boolean condition = copyright.getText().contains("2021 Digital Booking");
        if (condition) {
            Assert.assertTrue(true);
            logger.info("CORRECT COPYRIGHT");
        } else {
            Assert.assertTrue(false);
            logger.error("WRONG COPYRIGHT");
        }
    }

    @Test
    public void hasFacebookLogo() {
        WebElement facebookLogo = driver.findElement(By.xpath("//*[@id=\"root\"]/div/footer/div/div/a[1]"));
        Boolean condition = facebookLogo.isDisplayed();
        if (condition) {
            Assert.assertTrue(true);
            logger.info("FACEBOOK LOGO CORRECTLY DISPLAYED");
        } else {
            Assert.assertTrue(false);
            logger.error("FACEBOOK LOGO NOT FOUND");
        }
    }

    @Test
    public void hasLinkedinLogo() {
        WebElement linkedinLogo = driver.findElement(By.xpath("//*[@id=\"root\"]/div/footer/div/div/a[2]"));
        Boolean condition = linkedinLogo.isDisplayed();
        if (condition) {
            Assert.assertTrue(true);
            logger.info("LINKEDIN LOGO CORRECTLY DISPLAYED");
        } else {
            Assert.assertTrue(false);
            logger.error("LINKEDIN LOGO NOT FOUND");
        }
    }

    @Test
    public void hasTwitterLogo() {
        WebElement twitterLogo = driver.findElement(By.xpath("//*[@id=\"root\"]/div/footer/div/div/a[3]"));
        Boolean condition = twitterLogo.isDisplayed();
        if (condition) {
            Assert.assertTrue(true);
            logger.info("TWITTER LOGO CORRECTLY DISPLAYED");
        } else {
            Assert.assertTrue(false);
            logger.error("TWITTER LOGO NOT FOUND");
        }
    }

    @Test
    public void hasInstagramLogo() {
        WebElement instagramLogo = driver.findElement(By.xpath("//*[@id=\"root\"]/div/footer/div/div/a[4]"));
        Boolean condition = instagramLogo.isDisplayed();
        if (condition) {
            Assert.assertTrue(true);
            logger.info("INSTAGRAM LOGO CORRECTLY DISPLAYED");
        } else {
            Assert.assertTrue(false);
            logger.error("INSTAGRAM LOGO NOT FOUND");
        }
    }
    @Test
    public void hasFormH1() {
        WebElement formH1 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/form/h1"));
        Boolean condition = formH1.isDisplayed();
        Boolean condition2 = formH1.getText().equalsIgnoreCase("Iniciar sesión");
        if (condition && condition2) {
            Assert.assertTrue(true);
            logger.info("FORM TITLE CORRECTLY DISPLAYED");

        } else {
            Assert.assertTrue(false);
            logger.error("FORM TITLE WRONGLY DISPLAYED");
        }
    }

    @Test
    public void hasEmailInput() {
        WebElement emailInput = driver.findElement(By.id("email"));
        WebElement emailLabel = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/form/div[1]/label"));
        Boolean condition = emailInput.isDisplayed();
        Boolean condition2 = emailLabel.getText().equalsIgnoreCase("Email");
        Boolean condition3 = emailLabel.isDisplayed();
        if (condition  && condition3 && condition2) {
            Assert.assertTrue(true);
            logger.info("EMAIL INPUT CORRECTLY DISPLAYED");
        } else {
            Assert.assertTrue(false);
            logger.error("WRONG EMAIL INPUT");
        }


    }

    @Test
    public void hasPasswordInput() {
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement passwordLabel = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/form/div[2]/label"));
        Boolean condition = passwordInput.isDisplayed();
        Boolean condition2 = passwordLabel.getText().equalsIgnoreCase("Contraseña");
        Boolean condition3 = passwordLabel.isDisplayed();
        if (condition  && condition3 && condition2) {
            Assert.assertTrue(true);
            logger.info("PASSWORD INPUT CORRECTLY DISPLAYED");
        } else {
            Assert.assertTrue(false);
            logger.error("WRONG PASSWORD INPUT");
        }


    }

    @Test
    public void hasLoginButton(){
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/form/div[3]/a/button"));
        String textButton = loginButton.getText();
        Boolean condition = loginButton.isDisplayed();
        Boolean condition1= loginButton.getAttribute("type").equalsIgnoreCase("submit");
        Boolean condition2 = textButton.equalsIgnoreCase("Enviar");
        if (condition&&condition1&&condition2){
            Assert.assertTrue(true);
            logger.info("LOGIN BUTTON CORRECTLY DISPLAYED");
        }else {
            Assert.assertTrue(false);
            logger.error("LOGIN BUTTON WRONG DISPLAYED");
        }
    }
    @Test
    public void hasLoginLink(){
        WebElement loginLink = driver.findElement(By.linkText("Regístrate"));
        WebElement loginText = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/form/div[3]/p"));
        Boolean condition1= loginText.getText().contains("Aun no tienes una cuenta");
        Boolean condition = loginLink.isDisplayed();
        String hrefLogin = loginLink.getAttribute("href");
        Boolean condition2 = hrefLogin.contains("/register");
        if(condition && condition2) {
            Assert.assertTrue(true);
            logger.info("LOGIN LINK IS CORRECTLY DISPLAYED");
        }else {
            Assert.assertTrue(false);
            logger.error("LOGIN LINK IS WRONGLY DISPLAYED");
        }
    }
    @Test
    public void hasRegisterButton(){
        WebElement registerButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/nav/ul/li[3]/div[1]/a/button"));
        Boolean condition = registerButton.isDisplayed();
        Boolean condition2 = registerButton.getText().equalsIgnoreCase("Crear cuenta");
        if(condition&&condition2){
            Assert.assertTrue(true);
            logger.info("REGISTER BUTTON CORRECTLY DISPLAYED");
        }else {
            Assert.assertTrue(false);
            logger.error("REGISTER BUTTON WRONGLY DISPLAYED");
        }

    }







    @AfterClass
    public static void tearDownAfterClass(){
        logger.info("TEST FINISHED");
        driver.quit();
    }
}
