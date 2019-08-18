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
import com.discovery.utils.ReadPropertiesFile;

public class MyvideosPage 
{
	
	WebDriver driver;
	Logger log = Logger.getLogger("Logger");
	DoWebAction action = new DoWebAction();
	public MyvideosPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean navigateToMyVideosPage()
	{
		log.info("Navigating to my videos page and validating my videos page");
		String basePath = System.getProperty("user.dir");
		ReadPropertiesFile.setFilePath(basePath + "/src/main/resources/config.properties");
		driver.get(ReadPropertiesFile.getValue("myvideosurl"));
		if(driver.getTitle().equals("My Videos | Discovery"))
			return true;
		return false;
	}
	
	@FindBy(xpath="//h2[text()='Favorite Shows']")
	private WebElement favoriteShowsSection;
	
	@FindBy(xpath="//section[contains(@class,'layout-section FavoriteShowsCarousel layoutSection__main')]//div[@class='carousel-tile-wrapper carousel__tileWrapper']")
	private List<WebElement> favoriteShowVideoList; 
	
	public void scrollToFavoriteSection()
	{
		log.info("Scrolling to favourite section");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", favoriteShowsSection);
	}
	
	public boolean isFavouriteShowVideoListSizeCorrect(int expectedSize)
	{
		if(favoriteShowVideoList.size() == expectedSize)
			return true;
		return false;			
	}
	
	public void mouseHoverOnFavoriteVideo(String videoNumber)
	{
		WebElement favVideo = driver.findElement(By.xpath("//section[contains(@class,'layout-section FavoriteShowsCarousel layoutSection__main')]//div[@class='carousel-tile-wrapper carousel__tileWrapper']["+ videoNumber+"]"));
		action.mouseHover(driver, favVideo, 30);
	}
	
	public String getTitle(String vidoeNumber)
	{
		log.info("Getting title of vidoe " + vidoeNumber);
		String titleInMyVideos = driver.findElement(By.xpath("//section[contains(@class,'layout-section FavoriteShowsCarousel layoutSection__main')]//div[@class='carousel-tile-wrapper carousel__tileWrapper']["+vidoeNumber+"]//h3/div")).getText();
		return titleInMyVideos;
	}
	
	public String getDescription(String videoNumber)
	{
		log.info("Getting description of video " + videoNumber);
		String descriptionInMyVideos = driver.findElement(By.xpath("//section[contains(@class,'layout-section FavoriteShowsCarousel layoutSection__main')]//div[@class='carousel-tile-wrapper carousel__tileWrapper']["+videoNumber+"]//div[@class='showTileSquare__description']/div")).getText();
		return descriptionInMyVideos;
	}
}
