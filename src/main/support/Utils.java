package main.support;

import org.openqa.selenium.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Properties;

public class Utils {


    public static String getPropertiesValue(String variable){
        Properties props = new Properties();
        try {
            FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/config.properties");
            props.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props.getProperty(variable);
    }

    public static void sleep(int time){
        try {
            Thread.sleep(time * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String captureEvidence(WebDriver driver){
        TakesScreenshot newScreen = (TakesScreenshot) driver;
        String scnShot = newScreen.getScreenshotAs(OutputType.BASE64);
        return "data:image/jpg;base64, " + scnShot ;
    }


    public static String getDataHora(){
        GregorianCalendar calendar = new GregorianCalendar();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy_hh.mm");

        return formatter.format(calendar.getTime());
    }

    public static String getDataHora(String format){
        GregorianCalendar calendar = new GregorianCalendar();
        SimpleDateFormat formatter = new SimpleDateFormat(format);

        return formatter.format(calendar.getTime());
    }


    public static String getValueInXml(String xml, String propertie){
        int startReplace = 0;
        int endReplace = 0;

        String[] lines = xml.split("\n");
        for(String line : lines) {
            if(line.contains(propertie)) {
                startReplace = line.indexOf("CDATA");
                endReplace = line.lastIndexOf("]]");
                return line.substring(startReplace+6, endReplace);
            }
        }
        return "propertie nao encontrada";
    }

    public static void scrollUp(WebDriver driver){
        sleep(2);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -500)");
    }

    public static void scrollView(WebDriver driver, String coordinate){
        sleep(2);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, "+coordinate+")");
    }

    public static void clickJSExecutor(WebDriver driver, WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public static void scrollToElement(WebDriver driver, WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
}