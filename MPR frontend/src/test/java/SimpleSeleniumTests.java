import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SimpleSeleniumTests {
            WebDriver webDriver ;
            @BeforeEach
            public void setUp(){
                webDriver = new EdgeDriver();
            }
            @AfterAll
            public void tearDown(){
                webDriver.quit();
            }
    private void addNewCar() {
        webDriver.findElement(By.id("add-new-car")).click();
        WebElement inputName = webDriver.findElement(By.id("name"));
        WebElement inputAge = webDriver.findElement(By.id("age"));
        inputName.sendKeys("testSelenium");
        inputAge.sendKeys("1");
        webDriver.findElement(By.id("submit")).click();
    }
            @Test
            public void addingCarTest(){
                webDriver.get("http://localhost:8080/index");
                addNewCar();
            }
            @Test
            public void editingCarTest(){
                webDriver.get("http://localhost:8080/index");
                WebElement element = webDriver.findElement(By.id("edit-1"));
                element.click();
                WebElement inputName = webDriver.findElement(By.id("name"));
                WebElement inputAge = webDriver.findElement(By.id("age"));
                inputName.clear();
                inputAge.clear();
                inputName.sendKeys("editedSelenium");
                inputAge.sendKeys("7");
                WebElement button = webDriver.findElement(By.id("submit"));
                button.click();
            }
    @Test
    public void DeleteCarTest() {
        webDriver.get("http://localhost:8080/index");

        List<WebElement> deleteButtons = webDriver.findElements(By.cssSelector("a[id^='delete-']"));

        if (deleteButtons.isEmpty()) {
            addNewCar();
        }

        deleteButtons = webDriver.findElements(By.cssSelector("a[id^='delete-']"));
        WebElement elementToDelete = deleteButtons.stream()
                .min(Comparator.comparingInt(e -> Integer.parseInt(e.getAttribute("id").split("-")[1])))
                .orElseThrow(() -> new NoSuchElementException("No delete button found"));

        elementToDelete.click();
        Alert alert = webDriver.switchTo().alert();
        alert.accept();
    }
}
