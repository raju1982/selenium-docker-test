package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HorizontalSliderPage {

    private WebDriver driver;
    private By sliderContainer = By.className("sliderContainer");
    private By result = By.id("range");
    private By slider = By.tagName("input");
    WebElement parent = null;

    public HorizontalSliderPage(WebDriver driver){
        this.driver=driver;
        parent = this.driver.findElement(sliderContainer);
    }

    public void moveSlider(Keys text){
        parent.findElement(slider).sendKeys(text);
    }

    public String getResult(){
        return parent.findElement(result).getText();
    }

}
