package com.discovery.base;

import org.openqa.selenium.WebDriver;

import com.discovery.utils.ReadPropertiesFile;
import com.discovery.webdriver.Driver;

public class Controller 
{
	
	private static WebDriver driver;
	
    public WebDriver getDriver()
    {
        return driver;
    }
    
    public void setDriver(WebDriver driver) 
    {
    		Controller.driver = driver;
    }
    
    public void setupController()
    {
    		String basePath = System.getProperty("user.dir");
		ReadPropertiesFile.setFilePath(basePath+"/src/main/resources/config.properties");
		String driverName = ReadPropertiesFile.getValue("browsername");
		
		if(driverName.equalsIgnoreCase("chrome"))
		{
			driver = new Driver().getDriver("chrome");
			setDriver(driver);
		}else
		{	
			System.exit(0);
			
		}
		driver.get(ReadPropertiesFile.getValue("applicationurl"));
    }
    
    public void teardownController() {
    
        if (driver != null) {
            driver.quit();
        }
    }

    
}
