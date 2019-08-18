package com.discovery.base;

import org.openqa.selenium.WebDriver;

import com.discovery.screenshot.ScreenShot;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;



public class Base 
{
	
	
	private Controller controller;
	private static boolean isBrowserCreated = false;
	private static boolean skip=false;
	WebDriver driver;
	
	public Base(Controller controller)
	{
		this.controller = controller;
	}
	
	
	@Before()
	public void setup(Scenario scenario)
	{
		if(skip && scenario.getName().equals("Close the browser"))
		{
			throw new RuntimeException("Since browser is already closed skipping the step");
		}else if(skip)
		{
			throw new RuntimeException("There are failures and skipping the test");
		}
		
		
		
		if(!isBrowserCreated && !skip)
		{
			controller.setupController();
			isBrowserCreated=true;
		}
		
	}

	@After()
	public void shutdown(Scenario scenario)
	{
		System.out.println(scenario.getName());
		if(scenario.isFailed() && !skip)
		{
			new ScreenShot().getScreenshot(controller.getDriver(), scenario);
			controller.teardownController();
			skip=true;
			
		}
		if(scenario.getName().equals("Close the browser"))
		{
			if(!controller.getDriver().toString().contains("null"))
			{
				controller.teardownController();
			}
			skip=false;
			isBrowserCreated=false;
		}
	}
	
}
