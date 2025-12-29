package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass 
{
	public Logger logger;
	
	//public static WebDriver driver;
	public WebDriver driver;
	public Properties p;
	
	
	@Parameters({"os","browser"})
	@BeforeClass(groups={"Sanity","Regression","Master"})
	public void setup(String os,String br) throws IOException 
	
	{
		if (driver instanceof RemoteWebDriver) {
		    logger.info("REMOTE EXECUTION: " +
		        ((RemoteWebDriver) driver).getCapabilities());
		}
		
		// Loading properties file
		FileInputStream file =new FileInputStream(System.getProperty("user.dir")+"\\src/test/resources\\config.properties");
		p=new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());
		
		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {

		    // ------------ CHROME ------------
		    if (br.equalsIgnoreCase("chrome")) {

		        ChromeOptions options = new ChromeOptions();
		        options.setPlatformName(os.toUpperCase());

		        driver = new RemoteWebDriver(
		                new URL("http://localhost:4444/wd/hub"),
		                options
		        );

		    // ------------ EDGE ------------
		    } else if (br.equalsIgnoreCase("edge")) {

		        EdgeOptions options = new EdgeOptions();
		        options.setPlatformName(os.toUpperCase());

		        driver = new RemoteWebDriver(
		                new URL("http://localhost:4444/wd/hub"),
		                options
		        );

		    // ------------ FIREFOX ------------
		    } else if (br.equalsIgnoreCase("firefox")) {

		        FirefoxOptions options = new FirefoxOptions();
		        options.setPlatformName(os.toUpperCase());

		        driver = new RemoteWebDriver(
		                new URL("http://localhost:4444/wd/hub"),
		                options
		        );

		    } else {
		        System.out.println("No matching browser found for remote execution");
		        return;
		    }
		}
		else if(p.getProperty("execution_env").equalsIgnoreCase("local")) 
		{
			switch(br.toLowerCase()) 
			{
			case "chrome": driver=new ChromeDriver();break;
			case "edge"	:	driver=new EdgeDriver();break;
			case "firefox" : driver=new FirefoxDriver();break;
			default:System.out.println("Invalid Browser name...");return;
			
			}
		}
		
		
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL1"));// reading Url from properties file
		driver.manage().window().maximize();
	}
	@AfterClass(groups={"Sanity","Regression","Master"})
	public void tearDown() 
	{
		driver.quit();
	}
	
	/*@AfterSuite(alwaysRun = true)
	public void tearDown() {
	    if (driver != null) {
	        driver.quit();
	    }
	}*/
	
	public String rendomString() 
	{
		String genratestr=RandomStringUtils.randomAlphabetic(5);
		return genratestr;
		
	}
	public String rendomNumbers() 
	{
		String genratenum=RandomStringUtils.randomNumeric(10);
		return genratenum;
		
	}
	public String rendomAlphaNumeric() 
	{
		String genratestr1=RandomStringUtils.randomAlphabetic(5);
		String genratenum1=RandomStringUtils.randomNumeric(3);
		
		return (genratestr1+"@#"+genratenum1);
		
	}
	
	
	
public String captureScreen(String tname) throws IOException {

	String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

	TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

	String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" 
			+ tname + "_" + timeStamp + ".png";

	File targetFile = new File(targetFilePath);

	sourceFile.renameTo(targetFile);

	return targetFilePath;
}
}
