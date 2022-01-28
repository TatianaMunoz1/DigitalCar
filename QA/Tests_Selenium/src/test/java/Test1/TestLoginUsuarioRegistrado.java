package Test1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class TestLoginUsuarioRegistrado {

    public static void main(String[] args) throws AWTException {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Juan Carlos Aguilar\\Documents\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://127.0.0.1:8080/");
        System.out.println("Ingresamos al sitio de Digital Car");


        WebElement element1 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/nav/ul/li[3]/div[2]/a/button"));
      /*  Integer xElem1 = element1.getLocation().getX();
        Integer yElem1 = element1.getLocation().getY();
        Robot robot = new Robot();
        robot.mouseMove(xElem1,yElem1);*/
        element1.click();


        System.out.println("Ingresamos al formulario de Login");
        driver.findElement(By.id("correo")).sendKeys("mmarquez@gmail.com");
        System.out.println("Ingresamos el correo electrónico del nuevo usuario: mmarquez@gmail.com");
        driver.findElement(By.id("password")).sendKeys("123456");
        System.out.println("Ingresamos el password del nuevo usuario: 123456");



        WebElement element2 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/form/div[3]/a/button"));
        element2.click();
        System.out.println("Clickeamos en Enviar");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


        WebElement element3 = driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/button[1]"));
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(element3));
        element3.click();
        System.out.println("Login exitoso!");



        WebElement  element4 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/nav/ul/li[3]/div[1]/p"));
        System.out.println(element4.getText());
        wait.until(ExpectedConditions.elementToBeClickable(element4));



        WebElement element5= driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/nav/ul/li[3]/div[1]/input"));
        wait.until(ExpectedConditions.elementToBeClickable(element5));
        element5.click();
        System.out.println("Sesión cerrada");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);




        System.out.println("Cerramos el navegador");


        System.out.println("");
        System.out.println("******************************************************");
        System.out.println("********  T E S T       F I N A L I Z A D O   ********");
        System.out.println("********       C O N          E X I T O       ********");
        System.out.println("******************************************************");


        driver.close();


    }

}


