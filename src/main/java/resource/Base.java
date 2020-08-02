package resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Base {
	public WebDriver driver;
	public Properties prop;
	
	public WebDriver intializationDriver() throws IOException 
	{
		 prop= new Properties();
		 FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resource\\data.properties");
		
		 prop.load(fis);
		
		 String browserName=prop.getProperty("browser");
		 System.out.println(browserName);
		 
		 if (browserName.equalsIgnoreCase("chrome"))
		 {
			
			 System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\main\\java\\resource\\chromedriver.exe");
			
		
			driver= new ChromeDriver();
			 
		 }
		 else if (browserName.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\src\\main\\java\\resource\\geckodriver.exe");
				driver = new FirefoxDriver();
			}
		 else if (browserName.equalsIgnoreCase("Edge"))
			{
				System.setProperty("webdriver.edge.driver",System.getProperty("user.dir")+"\\src\\main\\java\\resource\\msedgedriver.exe");
			 driver = new EdgeDriver();
			}
		 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		 return driver;	 
	}
		
	
	public String getScreenShotPath(String testcasename, WebDriver driver) throws IOException
	{
	TakesScreenshot	ts = (TakesScreenshot) driver;
	System.out.println(testcasename);
	File source = ts.getScreenshotAs(OutputType.FILE);
	String destinationFile = System.getProperty("user.dir")+"\\reports"+testcasename+".png";
	FileUtils.copyFile(source,new File(destinationFile));
	return destinationFile;
	
	}
}
