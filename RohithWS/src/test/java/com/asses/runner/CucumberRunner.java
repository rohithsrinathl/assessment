package com.asses.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
    
@CucumberOptions(tags = "", features = {"src/test/resources/features"}, glue = {"com.assess.definitions"},plugin = {})    
public class CucumberRunner extends AbstractTestNGCucumberTests {
    
}