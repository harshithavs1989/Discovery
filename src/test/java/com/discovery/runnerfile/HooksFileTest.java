package com.discovery.runnerfile;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/feature/AddVideo.feature" },
plugin = {"pretty", "json:target/cucumber.json"},
glue = {"com.discovery.base", "com.discovery.tests"},
tags = {"@Scenario1,@Scenario2,@Scenario3,@Closebrowser"},
monochrome = true
)
public class HooksFileTest 
{
	
	
}
