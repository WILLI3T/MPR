package page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class IndexPage {
    public static final String URL = "http://localhost:8081/index";
    public static final String TITLE = "Car list:";
    WebDriver webDriver;
    @FindBy(tagName = "h1")
    WebElement header;
    @FindBy(id = "add-new-car")
    WebElement addNewCarButton;

    public IndexPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public IndexPage open() {
        webDriver.get(URL);
        return this;
    }

    public void clickAddNewCarButton() {
        addNewCarButton.click();
    }
    public void clickEditButton() {
        List<WebElement> editButtons = webDriver.findElements(By.cssSelector("a[id^='edit-']"));
        WebElement elementToEdit = editButtons.stream()
                .min(Comparator.comparingInt(e -> Integer.parseInt(e.getAttribute("id").split("-")[1])))
                .orElseThrow(() -> new NoSuchElementException("No edit button found"));

        elementToEdit.click();
    }
    public void clickDeleteButton() {
        List<WebElement> deleteButtons = webDriver.findElements(By.cssSelector("a[id^='delete-']"));
        WebElement elementToDelete = deleteButtons.stream()
                .min(Comparator.comparingInt(e -> Integer.parseInt(e.getAttribute("id").split("-")[1])))
                .orElseThrow(() -> new NoSuchElementException("No delete button found"));

        elementToDelete.click();
        Alert alert = webDriver.switchTo().alert();
        alert.accept();
    }

    public String getHeader() {
        return header.getText();
    }


}
