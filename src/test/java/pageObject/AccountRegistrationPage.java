package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(xpath = "//input[@id='input-firstname']")
    WebElement txtFirstname;

    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement txtLastname;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtEmail;

    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement txtTelephone;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtPassword;

    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement txtConfirmPassword;

    @FindBy(xpath = "//input[@name='agree']")
    WebElement chkPolicy;

    @FindBy(xpath = "//input[@value='Continue']")
    WebElement btnContinue;
    
    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msgConfirmation ;
    
 // Action Methods
    public void setFirstName(String firstName) {
        txtFirstname.clear();
        txtFirstname.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        txtLastname.clear();
        txtLastname.sendKeys(lastName);
    }

    public void setEmail(String email) {
        txtEmail.clear();
        txtEmail.sendKeys(email);
    }

    public void setTelephone(String telephone) {
        txtTelephone.clear();
        txtTelephone.sendKeys(telephone);
    }

    public void setPassword(String password) {
        txtPassword.clear();
        txtPassword.sendKeys(password);
    }

    public void setConfirmPassword(String confirmPassword) {
        txtConfirmPassword.clear();
        txtConfirmPassword.sendKeys(confirmPassword);
    }

    public void acceptPolicy() {
        if (!chkPolicy.isSelected()) {
            chkPolicy.click();
        }
    }

    public void clickContinue() {
        btnContinue.click();
        
     // sol2
     // btnContinue.submit();

     // sol3
     // Actions act = new Actions(driver);
     // act.moveToElement(btnContinue).click().perform();

     // sol4
     // JavascriptExecutor js = (JavascriptExecutor) driver;
     // js.executeScript("arguments[0].click();", btnContinue);

     // sol5
     // btnContinue.sendKeys(Keys.RETURN);

     // sol6
     // WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
     // mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();

    }
    
    public String getConfirmationMSG() {
    try {
    	return msgConfirmation.getText();
    } catch (Exception e) {
    	return (e.getMessage());
    }
}}
