package main.support;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Browsers {

    private static WebDriver getChromeDriver(){
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    public static WebDriver openBrowser(WebDriver driver, String url) {
        String browser = Utils.getPropertiesValue("browser");
        driver = getChromeDriver();
        driver.get(url);
        return driver;
    }
}
