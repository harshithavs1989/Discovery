Feature: This is for testing adding recommended video to favourite


	
@Scenario1
Scenario: Scroll to recommanded video 
	Given I am in discovery home page
	When I scroll to recommended video
	Then Recommended videos should be displayed

@Scenario2
Scenario: Adding video to favourite list	
	Given I am in discovery home page
	When I mouse hover on first video
	Then I get the title of the first video
	And I get the description of the first video
	And I click on add to favourite button of first video
	And I mouse hover on second video
	And I get the title of the second video
	And I get the description of the second video
	And I click on add to favourite button of second video
	
@Scenario3
Scenario: Validating added video details in myvideos page
	Given I navigate to myvidoes page
	When I scroll to Favorite shows section
	Then Two videos should be displayed in favorite show section
	And I mouse hover on first video of favorite show section
	And The title of the first video should match with the title of first recommended video of home page
	And The description of the first video should match with the description of first recommended video of home page
	And I mouse hover on second video of favorite show section
	And The title of the second video should match with the title of second recommended video of home page
	And The description of the second video should match with the description of second recommended video of home page

@Closebrowser
Scenario: Close the browser
	Given User wants to close the browser	


	
	
	

	
	

	