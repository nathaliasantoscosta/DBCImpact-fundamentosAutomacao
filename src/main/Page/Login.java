package main.Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import main.support.DriverWait;


public class Login {

    private DriverWait wait;
    private String email = main.support.Utils.getPropertiesValue("usuario");
    private String senha = main.support.Utils.getPropertiesValue("senha");

    public Login(WebDriver driver){
        wait = new DriverWait(driver);
        PageFactory.initElements(driver, this);
    }

    public Login clickLogin(){
        wait.waitClickableElement(loginbutton).click();
        return this;
    }

    public Login email(){
        wait.waitClickableElement(emailInput).sendKeys(this.email);
        return this;
    }

    public Login senha(){
        wait.waitClickableElement(senhaInput).sendKeys(this.senha);
        return this;
    }

    public Login clickSubmit(){
        wait.waitClickableElement(SubmitButton).click();
        return this;
    }

    @FindBy(className = "login")
    private WebElement loginbutton;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "passwd")
    private WebElement senhaInput;

    @FindBy(id = "SubmitLogin")
    private WebElement SubmitButton;

}
