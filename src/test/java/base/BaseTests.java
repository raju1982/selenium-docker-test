package base;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import pages.HomePage;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTests {

    private RemoteWebDriver driver;
    protected HomePage homePage;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        DesiredCapabilities dc = DesiredCapabilities.chrome();
        String hub = System.getProperty("HUB_HOST", "selenium-hub");
        System.out.println("hub: " + hub);
        driver = new RemoteWebDriver(new URL("http://" + hub + ":4444/wd/hub"), dc);
        goHome();
    }

    @BeforeMethod
    public void goHome(){
        //driver.get("https://the-internet.herokuapp.com/");
        driver.navigate().to("https://the-internet.herokuapp.com/");
        homePage = new HomePage(driver);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}
