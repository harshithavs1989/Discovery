package com.discovery.actions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * 
 * @author vinay
 *
 */

public class DoWebAction implements IDoAction
{
	Logger log = Logger.getLogger("Logger");
	
	public DoWebAction(){}
	
	/**
	 * Use this method to type into a text box using send keys method.
	 * @param driver
	 * @param element edit box object
	 * @param text content to type
	 * @param waitTime
	 */
	public void webType(WebDriver driver, WebElement element, String text, int waitTime)
	{
		
		try
		{
			log.info("Typing into" + element.toString().split(":")[2].replace("]", ""));
		}catch(ArrayIndexOutOfBoundsException a){}
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.visibilityOf(element));
		}catch(TimeoutException t)
		{
			forceStopWindowLoad(driver);
		}
		
		try
		{
			element.clear();
			element.sendKeys(text);
		}catch(UnhandledAlertException u)
		{
			log.error("Caught a unhandled alert. Will try to dismiss this");
			driver.switchTo().alert().dismiss();
			element.clear();
			element.sendKeys(text);
		}
	}
	
	/**
	 * Use this to click on an element
	 * @param diver
	 * @param element
	 * @param waitTime
	 */
	public void webClick(WebDriver driver, WebElement element, int waitTime)
	{
		try{
			log.info("Clicking" + element.toString().split("->")[1].replace("]", ""));
		}catch(ArrayIndexOutOfBoundsException a){}
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		}catch(TimeoutException t)
		{
			forceStopWindowLoad(driver);
		}
		
		try
		{
			element.click();
		}catch(UnhandledAlertException u)
		{
			log.error("Caught a unhandled alert. Will try to dismiss this");
			driver.switchTo().alert().dismiss();
			element.click();
		}
		
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = (String) cap.getCapability("browser");
		if(browserName!=null && browserName.contains("iphone"))
		{
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	/** 
	 * This is used to select value by string from a drop down. Pass the text to be selected as an argument.
	 * @param element
	 * @param valueToSelect
	 */
	
	public  void selectInDropDownUsingValue(WebDriver driver, WebElement element, String valueToSelect, int waitTime)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.visibilityOf(element));
		}catch(TimeoutException t)
		{
			forceStopWindowLoad(driver);
		}
		
		try 
		{
			log.info("Selecting "+ valueToSelect + " from " + element.toString().split(":")[2].replace("]", ""));
		}catch(ArrayIndexOutOfBoundsException a){}
		
		try
		{
			Select select = new Select(element);
			select.selectByVisibleText(valueToSelect);
		}catch(UnhandledAlertException u)
		{
			log.error("Caught a unhandled alert. Will try to dismiss this");
			driver.switchTo().alert().dismiss();
			Select select = new Select(element);
			select.selectByVisibleText(valueToSelect);
		}
	}

	/**
	 * This is used to select value in table drop down based on the index position rather than text. Use this if you are not sure of the 
	 * future availability of the text in the drop down. 
	 * @param dropDown
	 * @param dropDownValueToSelect
	 */
	
	public void selectInDropDownUsingIndex(WebDriver driver, WebElement dropDown, int dropDownValueToSelect, int waitTime)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.visibilityOf(dropDown));
		}catch(TimeoutException t)
		{
			forceStopWindowLoad(driver);
		}
		
		try 
		{
			log.info("Selecting value from " + dropDown.toString().split(":")[2].replace("]", ""));
		}catch(ArrayIndexOutOfBoundsException a){}
		
		try
		{
			Select select = new Select(dropDown);
			select.selectByIndex(dropDownValueToSelect);
		}catch(UnhandledAlertException u)
		{
			log.error("Caught a unhandled alert. Will try to dismiss this");
			driver.switchTo().alert().dismiss();
			Select select = new Select(dropDown);
			select.selectByIndex(dropDownValueToSelect);
		}
	}
	
	
	/** 
	 * Used to perform mouse hover operation on an element.
	 * @param driver
	 * @param element
	 * @param waitTime
	 */
	
	public void mouseHoverUsingJavaScriptAndClick(WebDriver driver, WebElement element, int waitTime)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.visibilityOf(element));
		}catch(TimeoutException t)
		{
			forceStopWindowLoad(driver);
		}
		
		try 
		{
			log.info("Hovering on " + element.toString().split(":")[2].replace("]", ""));
		}catch(ArrayIndexOutOfBoundsException a){}
		String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject){ arguments[0].fireEvent('onmouseover');}";
		
		((JavascriptExecutor)driver).executeScript(mouseOverScript, element);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		element.click();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void mouseHoverUsingJavaScript(WebDriver driver, WebElement element, int waitTime)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.visibilityOf(element));
		}catch(TimeoutException t)
		{
			forceStopWindowLoad(driver);
		}catch(UnhandledAlertException u)
		{
			log.error("Caught a unhandled alert. Will try to dismiss this");
			driver.switchTo().alert().dismiss();
		}
		
		try 
		{
			log.info("Hovering on " + element.toString().split(":")[2].replace("]", ""));
		}catch(ArrayIndexOutOfBoundsException a){}
		String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject){ arguments[0].fireEvent('onmouseover');}";
		
		((JavascriptExecutor)driver).executeScript(mouseOverScript, element);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}	
		
	}
	
	/** 
	 * Used to perform mouse hover operation and click.
	 * @param driver
	 * @param element
	 * @param waitTime
	 */
	
	public void mouseHoverAndClick(WebDriver driver, WebElement element, int waitTime)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.visibilityOf(element));
		}catch(TimeoutException t)
		{
			forceStopWindowLoad(driver);
		}
		
		try
		{
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			action.click(element);
		}catch(UnhandledAlertException u)
		{
			log.error("Caught a unhandled alert. Will try to dismiss this");
			driver.switchTo().alert().dismiss();
			
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			action.click(element);
		}
	}
	
	
	/** 
	 * This is used to check a check box.
	 * @param element
	 */
	
	public void checkCheckBox(WebDriver driver, WebElement element, int waitTime)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.visibilityOf(element));
		}catch(TimeoutException t)
		{
			forceStopWindowLoad(driver);
		}
		
		try
		{
			boolean selected = element.isSelected();
			if(!selected)
			{
				try {
				log.info("Checking the checkbox of element " + element.toString().split(":")[2].replace("]", "") + " ");
				}catch(ArrayIndexOutOfBoundsException a){}
				element.click();
			}else
			{
				try {
				log.info("Checkbox of " + element.toString().split(":")[2].replace("]", "") + " is already checked");
				}catch(ArrayIndexOutOfBoundsException a){}
			}
		}catch(UnhandledAlertException u)
		{
			log.error("Caught a unhandled alert. Will try to dismiss this");
			driver.switchTo().alert().dismiss();
			
			boolean selected = element.isSelected();
			if(!selected)
			{
				try {
				log.info("Checking the checkbox of element " + element.toString().split(":")[2].replace("]", "") + " ");
				}catch(ArrayIndexOutOfBoundsException a){}
				element.click();
			}else
			{
				try {
				log.info("Checkbox of " + element.toString().split(":")[2].replace("]", "") + " is already checked");
				}catch(ArrayIndexOutOfBoundsException a){}
			}
		}
	}
	
	/** 
	 * This is used to uncheck a check box.
	 * @param element
	 */
	
	public void uncheckCheckBox(WebDriver driver, WebElement element, int waitTime)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.visibilityOf(element));
		}catch(TimeoutException t)
		{
			forceStopWindowLoad(driver);
		}
		
		try
		{
			boolean selected = element.isSelected();
			if(selected)
			{
				try {
				log.info("Unchecking the chekcbox of " + element.toString().split(":")[2].replace("]", ""));
				}catch(ArrayIndexOutOfBoundsException a){}
				element.click();
			}else
			{
				try {
				log.info("Checkbox of " + element.toString().split(":")[2].replace("]", "") + "is already unchekced");
				}catch(ArrayIndexOutOfBoundsException a){}
			}
		}catch(UnhandledAlertException u)
		{
			log.error("Caught a unhandled alert. Will try to dismiss this");
			driver.switchTo().alert().dismiss();
			
			boolean selected = element.isSelected();
			if(selected)
			{
				try {
				log.info("Unchecking the chekcbox of " + element.toString().split(":")[2].replace("]", ""));
				}catch(ArrayIndexOutOfBoundsException a){}
				element.click();
			}else
			{
				try {
				log.info("Checkbox of " + element.toString().split(":")[2].replace("]", "") + "is already unchekced");
				}catch(ArrayIndexOutOfBoundsException a){}
			}
		}
	}
	
	/**
	 * This is used to click ok on java script alert box.
	 */
	
	public void clickAlertOK(WebDriver driver, int waitTime)
	{
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.alertIsPresent());
		log.debug("Clicking ok in the java scrip alert");
		driver.switchTo().alert().accept();
	}
	
	
	/**
	 * This is used to click cancel on java script alert box.
	 */
	
	public void clickAlertCancel(WebDriver driver, int waitTime)
	{
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.alertIsPresent());
		log.debug("Clicking cancel in the java scrip alert");
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * This is used for getting the text of alert box.
	 * @return
	 */
	
	public  String getAlertText(WebDriver driver, int waitTime)
	{
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.alertIsPresent());
		log.debug("Getting text from java script alert box");
		return driver.switchTo().alert().getText();
	}
	
	/**
	 * This method should be used in case you want to stop window loading before performing an action. This was developed for Safari browser as this browser
	 * took more time to load and had to forcefully stop the load. The catch block is introduced to ensure if there is a java script error it is caught. This was 
	 * done for IE browser
	 * @param driver
	 */
	public void forceStopWindowLoad(WebDriver driver)
	{
		log.warn("Forcing window to stop load");
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = cap.getBrowserName().toLowerCase();
		
		try
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
    	    		js.executeScript("window.stop();"); 
		}catch(JavascriptException j) {
			log.info("Catching java script error for stopping window load " + browserName);
		};
	}

	public void mouseHover(WebDriver driver, WebElement element, int waitTime)
	{
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.ignoring(StaleElementReferenceException.class);
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}
}
