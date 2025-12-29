package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC3_LoginDDTest extends BaseClass {
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class,groups= "Datadriven")// getting data provider Method from different package and class
	public void verify_login(String email, String pwd, String exp) 
	{
	
	logger.info("*****Starting TC3_loginDDTest****** ");
		
		try {
		// Home Page
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		
		hp.clickLogin();
		
		
		// LoginPage
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		// MyAccountPage
		
		
		
		
		MyAccountPage myacc=new MyAccountPage(driver);
		boolean targetpage=myacc.isMyaccountPageExist();
		
		/*Data is valid -- Login success- test pass -logout
		 * 					Login Failed--test fail
		 *Data is Invalid-- login success test fail-- logout
		 *					login Failed test pass
		 */
		
		if(exp.equalsIgnoreCase("Valid")) 
		{
			if(targetpage==true) 
			{
				myacc.clickLogout();
				Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(false);
			}
		}
		if(exp.equalsIgnoreCase("Invalid")) 
		{
			if(targetpage==true) 
			{
				myacc.clickLogout();
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);
			}
		
	}
		
			
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("*****Finished TC3_loginDDTest****** ");
}}
