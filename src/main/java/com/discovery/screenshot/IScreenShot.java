package com.discovery.screenshot;
import org.openqa.selenium.WebDriver;


import cucumber.api.Scenario;

/**
 * 
 * @author vinay.bp
 *
 */
public interface IScreenShot 
{
	
	
	
	public void getScreenshot(WebDriver driver, Scenario scenario);

}
