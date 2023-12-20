import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.AddCarPage;
import page.EditCarPage;
import page.IndexPage;

import java.time.Duration;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IndexPageTest {
    WebDriver webDriver ;
    @BeforeEach
    public void setUp(){
        webDriver = new EdgeDriver();
    }
    @AfterAll
    public void tearDown(){
        webDriver.quit();
    }

    @Test
    public void open(){
        IndexPage indexPage = new IndexPage(webDriver);
        indexPage.open();
        assertEquals("http://localhost:8080/index", webDriver.getCurrentUrl());
        assertEquals(IndexPage.TITLE, indexPage.getHeader());
    }

    @Test
    public void visitAddCarPage(){
        IndexPage indexPage = new IndexPage(webDriver);
        indexPage.open();
        indexPage.clickAddNewCarButton();
        assertEquals(AddCarPage.URL, webDriver.getCurrentUrl());
        assertEquals(AddCarPage.TITLE, webDriver.getTitle());
    }
    @Test
    public void addNewCar(){
        IndexPage indexPage = new IndexPage(webDriver);
        indexPage.open();
        indexPage.clickAddNewCarButton();
        AddCarPage addCarPage = new AddCarPage(webDriver);
        addCarPage.fillName("testSelenium");
        addCarPage.fillAge("1");
        addCarPage.clickSubmit();
    }
    @Test
    public void visitEditCarPage(){
        IndexPage indexPage = new IndexPage(webDriver);
        indexPage.open();
        indexPage.clickEditButton();
        assertEquals(EditCarPage.URL, webDriver.getCurrentUrl());
        assertEquals(EditCarPage.TITLE, webDriver.getTitle());
    }
    @Test
    public void EditCar(){
        IndexPage indexPage = new IndexPage(webDriver);
        indexPage.open();
        try {
            indexPage.clickEditButton();
        } catch (NoSuchElementException e) {
            indexPage.clickAddNewCarButton();
            AddCarPage addCarPage = new AddCarPage(webDriver);
            addCarPage.fillName("testSelenium");
            addCarPage.fillAge("1");
            addCarPage.clickSubmit();
            indexPage.open();
            indexPage.clickEditButton();
        }
        EditCarPage editCarPage = new EditCarPage(webDriver);
        editCarPage.fillName("editedSelenium");
        editCarPage.fillAge("7");
        editCarPage.clickSubmit();
        String currentUrl = webDriver.getCurrentUrl().split(";")[0];
        assertEquals(IndexPage.URL, currentUrl);
    }
    @Test
    public void deleteCar() {
        IndexPage indexPage = new IndexPage(webDriver);
        indexPage.open();

        try {
            indexPage.clickDeleteButton();
        } catch (NoSuchElementException e) {
            indexPage.clickAddNewCarButton();
            AddCarPage addCarPage = new AddCarPage(webDriver);
            addCarPage.fillName("testSelenium");
            addCarPage.fillAge("1");
            addCarPage.clickSubmit();
            indexPage.open();
            indexPage.clickDeleteButton();
        }

        assertEquals(IndexPage.URL, webDriver.getCurrentUrl());
    }
}
