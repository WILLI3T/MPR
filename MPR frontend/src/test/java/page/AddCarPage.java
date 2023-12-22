package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCarPage {
    public static final String URL = "http://localhost:8081/addCar";

    public static final String TITLE = "Add new car:";

    @FindBy(id = "name")
    WebElement name;
    @FindBy(id = "age")
    WebElement age;
    @FindBy(id = "submit")
    WebElement submit;

    WebDriver webDriver;

    public AddCarPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public AddCarPage open() {
        webDriver.get(URL);
        return this;
    }
    public void fillName(String keys){
        name.sendKeys(keys);
    }
    public void fillAge(String keys){
        age.sendKeys(keys);
    }
    public void clickSubmit(){
        submit.click();
    }

}
