package com.discovery.webdriver;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.discovery.utils.ReadPropertiesFile;

public class Driver 
{
	
	private WebDriver driver;
	Logger log = Logger.getLogger("Logger");
	public WebDriver getDriver(String browsername)
	{
		if(browsername.equalsIgnoreCase("chrome"))
		{
			log.info("Creating chrome driver");
			String basePath = System.getProperty("user.dir");
			ReadPropertiesFile.setFilePath(basePath+"/src/main/resources/config.properties");
			if(System.getProperty("os.name").toLowerCase().contains("mac"))
			{
				System.setProperty("webdriver.chrome.driver", basePath+"/src/main/resources/" + "chromedriver");
			}else
			{
				System.setProperty("webdriver.chrome.driver", basePath+"/src/main/resources/" + "chromedriver.exe");
			}
			driver = new ChromeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Integer.valueOf(ReadPropertiesFile.getValue("implicitwait")), TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Integer.valueOf(ReadPropertiesFile.getValue("pageloadwait")), TimeUnit.SECONDS);
		return driver;
	}

}
