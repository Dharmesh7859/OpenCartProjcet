package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;

public class TC2_LoginTest extends BaseClass
{
	@Test(groups= {"Sanity","Master"})
	public void verify_login() 
	{
		logger.info("*****Starting TC2_LoginTest ****** ");
		
		try {
			// HomePage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//LoginPage
		LoginPage lp =new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		// MyAccountPage
		MyAccountPage myacc=new MyAccountPage(driver);
		boolean targetpage=myacc.isMyaccountPageExist();
		
		Assert.assertEquals(targetpage, true,"Login failed...");
		//Assert.assertTrue(targetpage);
		}catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("*****Finished TC2_LoginTest ****** ");
	}
	

}
