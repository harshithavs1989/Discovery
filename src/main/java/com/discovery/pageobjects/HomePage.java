package com.discovery.pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.discovery.actions.DoWebAction;

public class HomePage 
{
	
	WebDriver driver;
	Logger log = Logger.getLogger("Logger");
	DoWebAction action = new DoWebAction();
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(xpath="//div[text()='Recommended']")
	private WebElement recommendedSection;
	
	@FindBy(xpath="//section[h2[div[text()='Recommended']]]/descendant::div[@class='carousel-tile-wrapper carousel__tileWrapper']")
	private List<WebElement> recommendedSectionVideoList;
	
	public boolean validateHomePageTitle()
	{
		log.info("Verifying the title of home page");
		if(driver.getTitle().equals("Discovery - Official Site"))
			return true;
		return false;
	}
	
	public void scrollToRecommendedVideo()
	{
		log.info("Scrolling to recommended video section");
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", recommendedSection);
		Actions action =  new Actions(driver);
		action.moveToElement(recommendedSection).build().perform();
	}
	
	public boolean validateIfRecommendedSectionVideoDisplayed()
	{
		if(recommendedSectionVideoList.size()>=2)
			return true;
		return false;
	}
	
	public boolean mouseHoverOnVideo(String videoNumber)
	{
		log.info("Mouse hovering on the video number " + videoNumber);
		if(Integer.valueOf(videoNumber) > 4)
		{
			return false;
		}else {
			WebElement firstVideo = driver.findElement(By.xpath("//section[h2[div[text()='Recommended']]]/descendant::div[@class='carousel-tile-wrapper carousel__tileWrapper'][" + videoNumber + "]"));
			action.mouseHover(driver, firstVideo,30);
			return true;
		}
	}
	
	public String getVideoTitle(String videoNumber)
	{
		log.info("Getting video title for " + videoNumber);
		String title = driver.findElement(By.xpath("//section[h2[div[text()='Recommended']]]/descendant::div[@class='carousel-tile-wrapper carousel__tileWrapper']["+videoNumber+"]//h3[@class='showTileSquare__title']/div")).getText();
		return title;
	}
	
	public String getVideoDescription(String videoNumber)
	{
		log.info("Getting description of video " + videoNumber);
		String description = driver.findElement(By.xpath("//section[h2[div[text()='Recommended']]]/descendant::div[@class='carousel-tile-wrapper carousel__tileWrapper']["+videoNumber+"]//div[@class='showTileSquare__description']/div")).getText();
		return description;
	}
	
	public void clickAddToFav(String videoNumber)
	{
		log.info("Clicking of add to favourite for video for " + videoNumber);
		WebElement addToFav = driver.findElement(By.xpath("//section[h2[div[text()='Recommended']]]/descendant::div[@class='carousel-tile-wrapper carousel__tileWrapper']["+videoNumber+"]//i[contains(@class,'flipIconCore__icon icon-plus')]"));
		action.webClick(driver, addToFav, 30);
	}

}
