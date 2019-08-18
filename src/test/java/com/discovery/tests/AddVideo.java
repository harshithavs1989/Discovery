package com.discovery.tests;

import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.discovery.base.Controller;
import com.discovery.pageobjects.HomePage;
import com.discovery.pageobjects.MyvideosPage;
import com.discovery.utils.Context;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AddVideo
{
	
	@SuppressWarnings("unused")
	
	
	private Controller controller;
	private Context context;
	Map<String, Object> testContext = null;
	WebDriver driver;
	HomePage homePage;
	MyvideosPage myvideosPage;
	String firstVideoTitle = null;
	String firstVideoDescription = null;
	String secondVideoTitle = null;
	String secondVideoDescription = null;
	
	
	public AddVideo(Controller controller, Context context) 
	{
		this.context = context;
		this.controller = controller;
		testContext = context.getContext();
		driver = controller.getDriver();
		homePage = new HomePage(driver);
		myvideosPage = new MyvideosPage(driver);
	}
	
    
    @Given("^I am in discovery home page$")
    public void i_am_in_discovery_home_page() throws Throwable
    {
    		boolean homePageTitleMatches = homePage.validateHomePageTitle();
    		Assert.assertEquals(homePageTitleMatches, true);
    }

    @When("^I scroll to recommended video$")
    public void i_scroll_to_recommended_video() throws Throwable {
        homePage.scrollToRecommendedVideo();
    }

    @Then("^Recommended videos should be displayed$")
    public void recommended_videos_should_be_displayed() throws Throwable {
        boolean isRecommendedVideoSectionDisplayed = homePage.validateIfRecommendedSectionVideoDisplayed();
        Assert.assertTrue(isRecommendedVideoSectionDisplayed);
    }
    
    
    @When("^I mouse hover on first video$")
    public void i_mouse_hover_on_first_video() throws Throwable {
        boolean canBeMouseHovered = homePage.mouseHoverOnVideo("1");
        Assert.assertTrue("Please specify number 1-4", canBeMouseHovered);
       
    }

    @Then("^I get the title of the first video$")
    public void i_get_the_title_of_the_first_video() throws Throwable {
    		context.setValue("firstVideoTitle", homePage.getVideoTitle("1"));
    }

    @Then("^I get the description of the first video$")
    public void i_get_the_description_of_the_first_video() throws Throwable {
    		context.setValue("firstVideoDescription", homePage.getVideoDescription("1"));
    }

    @Then("^I click on add to favourite button of first video$")
    public void i_click_on_add_to_favourite_button_of_first_video() throws Throwable {
    		homePage.clickAddToFav("1");
    }
    
    @Then("^I mouse hover on second video$")
    public void i_mouse_hover_on_second_video() throws Throwable {
        homePage.mouseHoverOnVideo("2");
    }

    @Then("^I get the title of the second video$")
    public void i_get_the_title_of_the_second_video() throws Throwable {
        context.setValue("secondVideoTitle", homePage.getVideoTitle("2"));
        
    }
    
    @Then("^I get the description of the second video$")
    public void i_get_the_description_of_the_second_video() throws Throwable {
    		context.setValue("secondVideoDescription", homePage.getVideoDescription("2"));
    }

    @Then("^I click on add to favourite button of second video$")
    public void i_click_on_add_to_favourite_button_of_second_video() throws Throwable {
        homePage.clickAddToFav("2");
    }
    
    @When("^I navigate to myvidoes page$")
    public void i_navigate_to_myvidoes_page() throws Throwable {
    		boolean isMyvidoesPageDisplayed = myvideosPage.navigateToMyVideosPage();
    		Assert.assertTrue(isMyvidoesPageDisplayed);
    }

    @When("^I scroll to Favorite shows section$")
    public void i_scroll_to_Favorite_shows_section() throws Throwable {
        myvideosPage.scrollToFavoriteSection();
    }

    @Then("^Two videos should be displayed in favorite show section$")
    public void two_videos_should_be_displayed_in_favorite_show_section() throws Throwable {
        boolean favVideoListSizeMatches = myvideosPage.isFavouriteShowVideoListSizeCorrect(2);
        Assert.assertTrue(favVideoListSizeMatches);
    }

    @And("^I mouse hover on first video of favorite show section$")
    public void i_mouse_hover_on_first_video_of_favorite_show_section() throws Throwable {
       myvideosPage.mouseHoverOnFavoriteVideo("1");
    }
    
    @Then("^The title of the first video should match with the title of first recommended video of home page$")
    public void the_title_of_the_first_video_should_match_with_the_title_of_first_recommended_video_of_home_page() throws Throwable {
        String titleOfFirstVideoInFavVideoSection = myvideosPage.getTitle("1"); 
        
        Assert.assertEquals(titleOfFirstVideoInFavVideoSection, context.getValue("firstVideoTitle"));
    }

    @Then("^The description of the first video should match with the description of first recommended video of home page$")
    public void the_description_of_the_first_video_should_match_with_the_description_of_first_recommended_video_of_home_page() throws Throwable {
        String descriptionOfFirstVideoInFavVideoSection = myvideosPage.getDescription("1");
        Assert.assertEquals(descriptionOfFirstVideoInFavVideoSection, context.getValue("firstVideoDescription"));
    }

    @And("^I mouse hover on second video of favorite show section$")
    public void i_mouse_hover_on_second_video_of_favorite_show_section() throws Throwable {
       myvideosPage.mouseHoverOnFavoriteVideo("2");
    }
    
    @Then("^The title of the second video should match with the title of second recommended video of home page$")
    public void the_title_of_the_second_video_should_match_with_the_title_of_second_recommended_video_of_home_page() throws Throwable {
        String secondVidoeTitleInFavVideoSection = myvideosPage.getTitle("2");
        
        Assert.assertEquals(secondVidoeTitleInFavVideoSection, context.getValue("secondVideoTitle"));
    }
    
    @Then("^The description of the second video should match with the description of second recommended video of home page$")
    public void the_description_of_the_second_video_should_match_with_the_description_of_second_recommended_video_of_home_page() throws Throwable {
        String descriptionOfSecondVideoInFavVideoSection = myvideosPage.getDescription("2");
        Assert.assertEquals(descriptionOfSecondVideoInFavVideoSection, context.getValue("secondVideoDescription"));
    }
    
    @Given("^User wants to close the browser$")
    public void user_wants_to_close_the_browser() throws Throwable {
        
    }
   
}
