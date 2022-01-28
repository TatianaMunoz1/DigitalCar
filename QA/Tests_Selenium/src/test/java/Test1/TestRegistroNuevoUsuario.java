package Test1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class TestRegistroNuevoUsuario {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver","C:\\Users\\Juan Carlos Aguilar\\Documents\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://127.0.0.1:8080/");
        System.out.println("Ingresamos al sitio de Digital Car");
        WebElement element = driver.findElement(By.xpath("//button[contains(@class, 'btn') and contains(@class, 'medium')]"));
        System.out.println("Ingresamos al formulario de registro");
        element.click();
        driver.findElement(By.id("usuario")).sendKeys("Marcelo");
        System.out.println("Ingresamos el nombre del nuevo usuario: Marcelo");
        driver.findElement(By.id("apellido")).sendKeys("Marquez");
        System.out.println("Ingresamos el apellido del nuevo usuario: Marquez");
        driver.findElement(By.id("correo")).sendKeys("mmarquez@gmail.com");
        System.out.println("Ingresamos el correo electrónico del nuevo usuario: mmarquez@gmail.com");
        driver.findElement(By.id("password1")).sendKeys("123456");
        System.out.println("Ingresamos el password del nuevo usuario: 123456");
        driver.findElement(By.id("password2")).sendKeys("123456");
        System.out.println("Reingresamos el password 123456");
        WebElement element1 = driver.findElement(By.xpath("//button[contains(@class, 'btn--full')]"));
        System.out.println("Hacemos click en Registro");
        element1.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement element2  =driver.findElement(By.xpath("//button[contains(@class, 'swal2-confirm')]"));
        System.out.println("Registro exitoso");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        element2.click();
        WebElement  element3 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/nav/ul/li[3]/div[1]/p"));
        System.out.println(element3.getText());
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/nav/ul/li[3]/div[1]/input")).click();
        System.out.println("Sesión cerrada");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        System.out.println("Cerramos el navegador");



        System.out.println("");
        System.out.println("******************************************************");
        System.out.println("********  T E S T       F I N A L I Z A D O   ********");
        System.out.println("********       C O N          E X I T O       ********");
        System.out.println("******************************************************");

        
        driver.close();







    }
}
