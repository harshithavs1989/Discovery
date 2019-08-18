package com.discovery.screenshot;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import cucumber.api.Scenario;


public class ScreenShot implements IScreenShot
{
	Logger log = Logger.getLogger("Logger");
	public void getScreenshot(WebDriver driver, Scenario scenarioname) {
		log.info("Taking screen shot");
		byte[] scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenarioname.embed(scrFile, "image/png");
	}

}
