package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.AccountRegistrationPage;
import pageObject.HomePage;
import testBase.BaseClass;


public class TC1_AccountRegistrationTest extends BaseClass
{
	
	
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration() 
	{
		logger.info("****Starting TC1_AccountRegistrationTest****");
		try {
		
		HomePage hp=new HomePage(driver);
		
		hp.clickMyAccount();
		logger.info("Clicked on My Account...");
		
		hp.clickRegister();
		logger.info("Clicked on Register page...");
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		logger.info("Providing Customer Details...");
		regpage.setFirstName(rendomString().toUpperCase());
		regpage.setLastName(rendomString().toUpperCase());
		regpage.setEmail(rendomString()+"@gmail.com");
		regpage.setTelephone(rendomNumbers());
		
		String password=rendomAlphaNumeric();
		regpage.setPassword(password);
		
		
		regpage.setConfirmPassword(password);
		regpage.acceptPolicy();
		regpage.clickContinue();
		
		logger.info("Validating expected Message");
		String confm=regpage.getConfirmationMSG();
		if(confm.equals("Your Account Has Been Created!")) 
		{
			Assert.assertTrue(true);
		}
		else 
		{
			logger.error("Test Failed....");
			logger.debug("Debug logs....");
			Assert.assertTrue(false);
		}
		
		}
		catch(Exception e)
		{
			
			Assert.fail();
		}
		
		logger.info("****Finished TC1_AccountRegistrationTest****");
			
	}
	
	

}
