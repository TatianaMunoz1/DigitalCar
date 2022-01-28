package Test1;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomeTest {

    private static WebDriver driver;
    private static final String TIPO_DRIVER = "webdriver.chrome.driver";
    private static final String PATH_DRIVER="./src/main/resources/WebDriver/chromedriver.exe";
    private static String url= "http://127.0.0.1:3000/";
    //private static String url = "http://www.digitalcar.ml";
    private static Logger logger = LogManager.getLogger(HomeTest.class);

    @BeforeClass
    public static void  SetUpBeforeClass(){
        logger.info("TEST STARTED");
        System.setProperty(TIPO_DRIVER,PATH_DRIVER);
        driver = new ChromeDriver();
        driver.get(url);
    }



    @Test
    public  void testTitle(){

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        String title = driver.getTitle();
        Boolean condition = title.contains("Digital Car");
        if (condition){
            Assert.assertTrue(condition);
            logger.info("TITLE CORRECTLY CHECKED");
        }else {
            Assert.assertTrue(condition);
            logger.error("WRONG TITLE");
        }

    }

    @Test
    public void hasLogo(){
        WebElement logo = driver.findElement(By.xpath("/html/body/div/div/div[1]/a/img"));
        Boolean condition = logo.isDisplayed();
        if (condition){
            Assert.assertTrue("Check logo",logo.isDisplayed());
            logger.info("LOGO CORRECTLY CHECKED");
        }else {
            Assert.assertTrue("Check logo", logo.isDisplayed());
            logger.error("LOGO NOT FOUND");
        }
    }

    @Test
    public void hasRegisterButton (){
        WebElement registerButton = driver.findElement(By.xpath("//*[text()='Crear cuenta']"));
        Assert.assertTrue("Register button is present", registerButton.isDisplayed());
        Boolean condition = registerButton.isDisplayed();
        if (condition) {
            logger.info("REGISTER BUTTON IS PRESENT");
        }else{
            logger.error("REGISTER BUTTON NOT FOUND");
        }
    }

    @Test
    public void hasLoginButton(){
        WebElement loginButton = driver.findElement(By.xpath("//*[text()='Iniciar sesión']"));
        Assert.assertTrue("Login button is present", loginButton.isDisplayed());
        Boolean condition = loginButton.isDisplayed();
        if (condition) {
            logger.info("LOGIN BUTTON IS PRESENT");
        }else{
            logger.error("LOGIN BUTTON NOT FOUND");
        }
    }

    @Test
    public void hasCitiesSelect(){
        WebElement citiesSelect = driver.findElement(By.xpath("//*[@id=\"input_search_city\"]"));
        Assert.assertTrue("Cities´s select is present", citiesSelect.isDisplayed());
        if (citiesSelect.isDisplayed()){
            logger.info("CITIES´S SELECT IS PRESENT");
        }else{
            logger.error("CITIES´S SELECT NOT FOUND");
        }
    }

    @Test
    public void hasDatePicker(){
        WebElement datePicker = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/form/div[2]/div[2]/div/div/div/div/div/input"));
        Assert.assertTrue("Date picker is present",datePicker.isDisplayed());
        if(datePicker.isDisplayed()){
            logger.info("DATE PICKER IS PRESENT");
        }else{
            logger.error("DATE PICKER NOT FOUND");
        }
    }

    @Test
    public void hasSearchButton(){
        WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/form/div[3]/a/button"));
        Assert.assertTrue("Search button is present",searchButton.isDisplayed());
        if(searchButton.isDisplayed()){
            logger.info("SEARCH BUTTON IS PRESENT");
        }else{
            logger.error("SEARCH BUTTON NOT FOUND");
        }
    }

    @Test
    public void hasFourCategoryImages() {
        WebElement categoryCards = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/section[1]/div/div"));
        List<WebElement> imagenes = categoryCards.findElements(By.tagName("img"));
        Assert.assertTrue(imagenes.size() == 4);
        if (imagenes.size() == 4) {
            logger.info("4 CATEGORIES´S IMAGES CHECKED");
        } else {
            logger.error("WRONG NUMBER OF CATEGORIES´S IMAGES");
        }
    }

    @Test
    public void hasFourCategoryTitles(){
        WebElement categoryCards = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/section[1]/div/div"));
        List<WebElement> titles = categoryCards.findElements(By.tagName("h3"));
        Assert.assertTrue(titles.size() == 4);
        if (titles.size()==4){
            logger.info("4 CATEGORIES´S TITLES CHECKED");
        }else{
            logger.error("WRONG NUMER OF CATEGORIES´S TITLES");
        }
    }

    @Test
    public void hasFourCategoryDescription(){
        WebElement categoryCards = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/section[1]/div/div"));
        List<WebElement> description = categoryCards.findElements(By.tagName("p"));
        Assert.assertTrue(description.size() == 4);
        if(description.size()==4){
            logger.info("4 CATEGORIES´S DESCRIPTION CHECKED");
        }else{
            logger.error("WRONG NUMBER OF CATEGORIES´S DESCRIPTION");
        }
    }


    @Test
    public void hasFooter() {
        WebElement footer = driver.findElement(By.xpath("//*[@id=\"root\"]/div/footer"));
        Boolean condition = footer.isDisplayed();
        if (condition) {
            Assert.assertTrue("Check footer", footer.isDisplayed());
            logger.info("FOOTER CORRECTLY DISPLAYED");
        } else {
            Assert.assertTrue("Check footer", footer.isDisplayed());
            logger.error("FOOTER NOT FOUND");
        }
    }

    @Test
    public void hasCopyright() {
        WebElement copyright = driver.findElement(By.xpath("//*[@id=\"root\"]/div/footer/p"));
        Boolean condition = copyright.isDisplayed();
        if (condition) {
            Assert.assertTrue("Check copyright", copyright.isDisplayed());
            logger.info("COPYRIGHT CORRECTLY DISPLAYED");
        } else {
            Assert.assertTrue("Check copyright", copyright.isDisplayed());
            logger.error("COPYRIGHT NOT FOUND");
        }
    }

    @Test
    public void hasCopyrightText() {
        WebElement copyright = driver.findElement(By.xpath("//*[@id=\"root\"]/div/footer/p"));
        Boolean condition = copyright.getText().contains("2021 Digital Booking");
        if (condition) {
            Assert.assertTrue(condition);
            logger.info("CORRECT COPYRIGHT");
        } else {
            Assert.assertTrue(condition);
            logger.error("WRONG COPYRIGHT");
        }
    }

    @Test
    public void hasFacebookLogo() {
        WebElement facebookLogo = driver.findElement(By.xpath("//*[@id=\"root\"]/div/footer/div/div/a[1]"));
        Boolean condition = facebookLogo.isDisplayed();
        if (condition) {
            Assert.assertTrue(condition);
            logger.info("FACEBOOK LOGO CORRECTLY DISPLAYED");
        } else {
            Assert.assertTrue(condition);
            logger.error("FACEBOOK LOGO NOT FOUND");
        }
    }

    @Test
    public void hasLinkedinLogo() {
        WebElement linkedinLogo = driver.findElement(By.xpath("//*[@id=\"root\"]/div/footer/div/div/a[2]"));
        Boolean condition = linkedinLogo.isDisplayed();
        if (condition) {
            Assert.assertTrue(condition);
            logger.info("LINKEDIN LOGO CORRECTLY DISPLAYED");
        } else {
            Assert.assertTrue(condition);
            logger.error("LINKEDIN LOGO NOT FOUND");
        }
    }

    @Test
    public void hasTwitterLogo() {
        WebElement twitterLogo = driver.findElement(By.xpath("//*[@id=\"root\"]/div/footer/div/div/a[3]"));
        Boolean condition = twitterLogo.isDisplayed();
        if (condition) {
            Assert.assertTrue(condition);
            logger.info("TWITTER LOGO CORRECTLY DISPLAYED");
        } else {
            Assert.assertTrue(condition);
            logger.error("TWITTER LOGO NOT FOUND");
        }
    }

    @Test
    public void hasInstagramLogo() {
        WebElement instagramLogo = driver.findElement(By.xpath("//*[@id=\"root\"]/div/footer/div/div/a[4]"));
        Boolean condition = instagramLogo.isDisplayed();
        if (condition) {
            Assert.assertTrue(condition);
            logger.info("INSTAGRAM LOGO CORRECTLY DISPLAYED");
        } else {
            Assert.assertTrue(condition);
            logger.error("INSTAGRAM LOGO NOT FOUND");
        }
    }



    @AfterClass
    public static void tearDownAfterClass(){
        logger.info("TEST FINISHED");
        driver.quit();
    }


}
