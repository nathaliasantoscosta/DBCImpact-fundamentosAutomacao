package main.Page;

import main.support.DriverWait;
import main.support.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home {

    private DriverWait wait;
    private WebDriver driver;

    public Home(WebDriver driver){
        this.driver = driver;
        wait = new DriverWait(driver);
        PageFactory.initElements(driver, this);
    }

    public Home clickCatalogo(){
        wait.waitClickableElement(catalogo).click();
        Utils.scrollUp(driver);
        return this;
    }

    public Home clickItemTShirt(){
        wait.waitClickableElement(itemTShirt).click();
        Utils.scrollUp(driver);
        return this;
    }

    public Home clickAdicionarCarrinho(){
        wait.waitClickableElement(addCartButton).click();
        Utils.scrollUp(driver);
        return this;
    }


    public Home clickComprar(){
        wait.waitClickableElement(comprarButton).click();
        Utils.scrollUp(driver);
        return this;
    }

    @FindBy(xpath = "//*[@id=\"header_logo\"]/a/img")
    private WebElement catalogo;

    @FindBy(xpath = "//*[@id=\"homefeatured\"]/li[1]/div/div[2]/h5/a")
    private WebElement itemTShirt;

    @FindBy(xpath = "//*[@id=\"add_to_cart\"]/button/span")
    private WebElement addCartButton;

    @FindBy(xpath = "//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")
    private WebElement comprarButton;



}
