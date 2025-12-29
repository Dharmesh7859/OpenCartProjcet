package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	// Constructor
    public HomePage(WebDriver driver) {
        super(driver);
    }

    // My Account link
    @FindBy(xpath = "//span[normalize-space()='My Account']")
    WebElement lnkMyAccount;

    // Register link
    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement lnkRegister;

    // Login link
    @FindBy(xpath = "//a[normalize-space()='Login']")
    WebElement lnkLogin;

    // Click on My Account
    public void clickMyAccount() {
        lnkMyAccount.click();
    }

    // Click on Register
    public void clickRegister() {
        lnkRegister.click();
    }

    // Click on Login
    public void clickLogin() {
        lnkLogin.click();
    }
	
	

}
