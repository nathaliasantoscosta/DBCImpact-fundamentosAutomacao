package test;

import main.Page.Home;
import main.Page.Login;
import main.support.Browsers;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import main.Page.Carrinho;

import java.util.concurrent.TimeUnit;

public class RealizarCompraTest{

    private String url;
    public WebDriver driver;

    @Test
    public void realizarCompra(){
        url = main.support.Utils.getPropertiesValue("url");

        driver = Browsers.openBrowser(driver, url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Login login = new Login (driver);
        login.clickLogin().email().senha().clickSubmit();

        Home home = new Home(driver);
        home.clickCatalogo()
                .clickItemTShirt()
                .clickAdicionarCarrinho()
                .clickComprar();

        Carrinho carrinho = new Carrinho(driver);
        carrinho.clickCheckout()
                .clickCheckoutAddress()
                .clickAgreeTerms()
                .clickCheckoutShipping()
                .clickTransferenciaBancaria()
                .clickConfirmarOrder();

        String validaMensagem = new Carrinho(driver).validaMensagem();
        Assert.assertTrue(validaMensagem.equals("Your order on My Store is complete."));

        driver.quit();

    }
}
