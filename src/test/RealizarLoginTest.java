package test;

import main.Page.Login;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import main.support.Browsers;
import java.util.concurrent.TimeUnit;


public class RealizarLoginTest {
    private String url;
    public WebDriver driver;

    @Test
    public void realizarLogin(){
        url = main.support.Utils.getPropertiesValue("url");

        driver = Browsers.openBrowser(driver, url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Login login = new Login (driver);
        login.clickLogin()
                .email()
                .senha()
                .clickSubmit();

//        driver.quit();
    }

}
