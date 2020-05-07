package main.support;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DriverWait {

    private WebDriver driver;
    private int timeOut = 20;

    public DriverWait(WebDriver driver) {
        this.driver = driver;
    }

    private FluentWait<WebDriver> waitElement(){
        waitForJavaScriptCondition().waitForJQueryProcessing();
        return new FluentWait<WebDriver>(driver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(InvalidElementStateException.class);
    }

    private DriverWait waitForJavaScriptCondition() {
        boolean jscondition = false;
        String javaScript = "return (xmlhttp.readyState >= 2 && xmlhttp.status == 200)";
        try{
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            new WebDriverWait(driver, timeOut) {
            }.until(new ExpectedCondition<Boolean>() {

                @Override
                public Boolean apply(WebDriver driverObject) {
                    return (Boolean) ((JavascriptExecutor) driverObject).executeScript(javaScript);
                }
            });
            jscondition =  (Boolean) ((JavascriptExecutor) driver).executeScript(javaScript);
            driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS); //reset implicitlyWait
        } catch (JavascriptException e) { }
        return this;
    }

    private DriverWait waitForJQueryProcessing(){
        boolean jQcondition = false;
        try{
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait()
            new WebDriverWait(driver, timeOut) {
            }.until(new ExpectedCondition<Boolean>() {

                @Override
                public Boolean apply(WebDriver driverObject) {
                    return (Boolean) ((JavascriptExecutor) driverObject).executeScript("return jQuery.active == 0");
                }
            });
            jQcondition = (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0");
            driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS); //reset implicitlyWait
        } catch (Exception e) { }
        return this;
    }

    public WebElement waitVisibleElement(WebElement element){
        return waitElement().until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitClickableElement(WebElement element){
        return waitElement().until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement findElementInList(List<WebElement> elements, String text){
        elementList(elements);
        for(WebElement element : elements){
            if(text.equalsIgnoreCase(element.getText())){
                return waitClickableElement(element);
            }
        }
        return null;
    }

    public int findElementPositionInList(List<WebElement> elements, String text){
        elementList(elements);
        for(int count =0; count < elements.size(); count++){
            if(text.equalsIgnoreCase(elements.get(count).getText())){
                return count;
            }
        }
        return 0;
    }

    public List<WebElement> elementList(List<WebElement> elements){
        try {
            return waitElement().until(ExpectedConditions.visibilityOfAllElements(elements));
        }catch (WebDriverException e) {
            Utils.sleep(3);
            return elements;
        }
    }
}
