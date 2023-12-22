package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditCarPage {
    public static final String URL = "http://localhost:8081/editCar";

    public static final String TITLE = "Update your car:";

    @FindBy(id = "name")
    WebElement name;
    @FindBy(id = "age")
    WebElement age;
    @FindBy(id = "submit")
    WebElement submit;

    WebDriver webDriver;

    public EditCarPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public EditCarPage open() {
        webDriver.get(URL);
        return this;
    }
    public void fillName(String keys){
        name.clear();
        name.sendKeys(keys);
    }
    public void fillAge(String keys){
        age.clear();
        age.sendKeys(keys);
    }
    public void clickSubmit(){
        submit.click();
    }



}
