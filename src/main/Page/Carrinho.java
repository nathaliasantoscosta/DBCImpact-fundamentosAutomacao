package main.Page;

import main.support.DriverWait;
import main.support.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Carrinho{

    private DriverWait wait;
    private WebDriver driver;

    public Carrinho(WebDriver driver){
        this.driver = driver;
        wait = new DriverWait(driver);
        PageFactory.initElements(driver, this);
    }

    public Carrinho clickCheckout(){
        wait.waitClickableElement(checkoutButton).click();
        Utils.scrollUp(driver);
        return this;
    }

    public Carrinho clickCheckoutAddress(){
        wait.waitClickableElement(checkoutAddressButon).click();
        Utils.scrollUp(driver);
        return this;
    }

    public Carrinho clickAgreeTerms(){
        wait.waitClickableElement(agreeTerms).click();
        Utils.scrollUp(driver);
        return this;
    }

    public Carrinho clickCheckoutShipping(){
        wait.waitClickableElement(checkoutShippingButton).click();
        Utils.scrollUp(driver);
        return this;
    }

    public Carrinho clickTransferenciaBancaria(){
        wait.waitClickableElement(bankwire).click();
        Utils.scrollUp(driver);
        return this;
    }

    public Carrinho clickConfirmarOrder(){
        wait.waitClickableElement(confirmOrderButtom).click();
        Utils.scrollUp(driver);
        return this;
    }

    public String validaMensagem(){
        String mensagem = wait.waitVisibleElement(mensagemSucesso).getText();
        return mensagem;
    }

    public Carrinho clickRemoverItem(){
        wait.waitClickableElement(removerItem).click();
        Utils.scrollUp(driver);
        return this;
    }

    public String validaMensagemCarrinhoVazio(){
        String mensagem = wait.waitVisibleElement(mensagemSucesso).getText();
        return mensagem;
    }

    @FindBy(xpath = "//*[@id=\"center_column\"]/p[2]/a[1]/span")
    private WebElement checkoutButton;

    @FindBy(name = "processAddress")
    private WebElement checkoutAddressButon;

    @FindBy(id = "uniform-cgv")
    private WebElement agreeTerms;

    @FindBy(name = "processCarrier")
    private WebElement checkoutShippingButton;

    @FindBy(className = "bankwire")
    private WebElement bankwire;

    @FindBy(xpath = "//*[@id=\"cart_navigation\"]/button/span")
    private WebElement confirmOrderButtom;

    @FindBy(xpath = "//*[@id=\"center_column\"]/div/p/strong")
    private WebElement mensagemSucesso;

    @FindBy(className = "icon-trash")
    private WebElement removerItem;

    @FindBy(xpath = "//*[@id=\"center_column\"]/p")
    private WebElement mensagemCarrinhoVazio;


}
