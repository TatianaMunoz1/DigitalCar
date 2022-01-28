package Test1;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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


public class TestBusquedaPorCategorias {
    private static WebDriver driver;
    private static final String TIPO_DRIVER = "webdriver.chrome.driver";
    private static final String PATH_DRIVER = "./src/main/resources/WebDriver/chromedriver.exe";
    private static String url = "http://127.0.0.1:8080/";
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
        WebElement element2 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/form/div[3]/button"));
        element2.click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]")).isDisplayed());
        logger.info("USER LOGIN SUCCESSFULLY");
    }






    @Test
    public void happyPath() throws InterruptedException {

        WebElement  element4 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/nav/ul/li[3]/div[1]/p"));
        WebElement about = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/section[1]/div"));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView();",about);
        Thread.sleep(3000);
        WebElement element = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/section[1]/div/div"));
        logger.info(element.getText());

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement element5 = driver.findElement(By.xpath("/html/body/div/div/div[2]/section[1]/div/div/div[1]/div[2]"));
        jse.executeScript("arguments[0].scrollIntoView();",element5);
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/section[1]/div/div/div[1]/div[1]/img"))).click().build().perform();

        //element5.click();
        System.out.println("Seleccionamos SUV");
       Thread.sleep(3000);



        WebElement element6 = driver.findElement(By.xpath("/html/body/div/div/div[2]/section[2]/div/div/div[2]/div[2]"));
        jse.executeScript("arguments[0].scrollIntoView();",element6);
        Thread.sleep(2000);

        System.out.println("Seleccionamos Renault Duster");

        //WebElement element2 = driver.findElement(By.xpath());

        System.out.println(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/section[2]/div/div/div[1]/div[2]/div[2]/div/p")).getText());


        WebElement element7 = driver.findElement(By.xpath("/html/body/div/div/div[2]/section[2]/div/div/div[2]/div[1]/img"));
        jse.executeScript("arguments[0].scrollIntoView();",element7);
        Thread.sleep(2000);
        System.out.println("Clickeamos en Ver más");

        action.moveToElement(element7).moveToElement(driver.findElement(By.xpath("/html/body/div/div/div[2]/section[2]/div/div/div[2]/div[2]/a/button"))).click().build().perform();

        WebElement element8 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[3]"));
        jse.executeScript("arguments[0].scrollIntoView();",element8);
        System.out.println("Visualizamos la galería de imágenes");
        Thread.sleep(2000);
        WebElement element9  = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[4]"));
        jse.executeScript("arguments[0].scrollIntoView();",element9);
        System.out.println("Visualizamos el detalle del producto");
        System.out.println(element9.getText());
        Thread.sleep(2000);
        WebElement element10  = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[6]"));
        jse.executeScript("arguments[0].scrollIntoView();",element10);
        System.out.println("Visualizamos la ubicación del vehículo");
        Thread.sleep(2000);
        WebElement element11  = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[7]/div"));
        jse.executeScript("arguments[0].scrollIntoView();",element11);
        System.out.println("Visualizamos las políticas del vehículo");
        System.out.println(element11.getText());
        Thread.sleep(2000);

        WebElement element12 = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[5]/div"));
        jse.executeScript("arguments[0].scrollIntoView();", element12);
        Thread.sleep(2000);
        System.out.println("Hacemos click en Iniciar Reserva");
        action.moveToElement(driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[5]/div/div[2]/div/a/button"))).click().build().perform();
        //element12.click();
        Thread.sleep(2000);


        WebElement element13 = driver.findElement(By.xpath("/html/body/div/div/section/div/div/div[1]/div[1]/div"));
        jse.executeScript("arguments[0].scrollIntoView();",element13);
        WebElement element14 = driver.findElement(By.xpath("/html/body/div/div/section/div/div/div[1]/div[1]/div/form/div/div[2]/div[2]/input"));
        element14.sendKeys("Rosario");
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
        Thread.sleep(2000);







    }

}
