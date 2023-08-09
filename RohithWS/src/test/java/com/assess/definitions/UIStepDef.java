package com.assess.definitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.List;
 
public class UIStepDef {
     
    private static WebDriver driver;       
    public final static int TIMEOUT = 10;
     
    @Before("not @API")
    public void setUp() { 
        WebDriverManager.chromedriver().setup();        
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        driver.manage().window().maximize();
    }
       
    @Given("the user navigates to {string} in chrome")
    public void the_user_navigates_to_in_chrome(String url) {    	
    	driver.get(url); 
    }    
    
    
    @When("the user lands on the home page")
    public void the_user_lands_on_the_home_page() {    	
    	Assert.assertEquals(driver.getCurrentUrl(), "https://reqres.in/");
    }

    @Then("the user should see different request types and end points")
    public void the_user_should_see_different_request_types_and_end_points() {    	
    	List<WebElement> getElements = driver.findElements(By.cssSelector("li[data-http='get']")); 
    	List<WebElement> postElements = driver.findElements(By.cssSelector("li[data-http='post']")); 
    	List<WebElement> putElements = driver.findElements(By.cssSelector("li[data-http='put']")); 
    	List<WebElement> patchElements = driver.findElements(By.cssSelector("li[data-http='patch']")); 
    	List<WebElement> deleteElements = driver.findElements(By.cssSelector("li[data-http='delete']")); 
    	Assert.assertTrue(getElements.size() > 0);
    	Assert.assertTrue(postElements.size() > 0);
    	Assert.assertTrue(putElements.size() > 0);
    	Assert.assertTrue(patchElements.size() > 0);
    	Assert.assertTrue(deleteElements.size() > 0);    	
    }

    @When("the user clicks on {string}")
    public void the_user_clicks_on(String apiName) { 
    	String selectorName = "li[data-id='"+apiName+"']";
    	driver.findElement(By.cssSelector(selectorName)).click(); 
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
    }

    @Then("the user should see {string} in the request")
    public void the_user_should_see_in_the_request(String request) {
    	WebElement linkElement = driver.findElement(By.cssSelector("a[data-key='request-output-link']"));   
    	WebElement span = linkElement.findElement(By.tagName("span"));
    	Assert.assertEquals(span.getText(), request);        
    }

    @Then("the user should see {string} in the response")
    public void the_user_should_see_in_the_response(String response) {
    	WebElement spanElement = driver.findElement(By.cssSelector("span[data-key='response-code']"));        	 
    	WebElement preElement = driver.findElement(By.cssSelector("pre[data-key='output-response']"));     	
    	Assert.assertEquals(spanElement.getText()+preElement.getText(), response);     	
    }

    @When("the user clicks on the button {string}")
    public void the_user_clicks_on_the_button(String buttonName) {
    	WebElement linkElement = driver.findElement(By.cssSelector("a[href='#support-heading']")); 
    	Assert.assertEquals(linkElement.getText(), buttonName);  
    	linkElement.click();
    }

    @Then("the user navigates to support page")
    public void the_user_navigates_to_support_page() {
    	Assert.assertEquals(driver.getCurrentUrl(), "https://reqres.in/#support-heading");
    }

    @Then("the user should see {string} and {string} options")
    public void the_user_should_see_and_options(String oneTime, String monthly) {
        WebElement supportOneTimeElement = driver.findElement(By.cssSelector("label[for='supportOneTime']"));
        Assert.assertEquals(supportOneTimeElement.getText(), oneTime);
        WebElement supportRecurringElement = driver.findElement(By.cssSelector("label[for='supportRecurring']"));
        Assert.assertEquals(supportRecurringElement.getText(), monthly);
        
    }

    @Then("the user should see the {string} button")
    public void the_user_should_see_the_button(String upgrade) {
    	WebElement proElement = driver.findElement(By.id("trigger-pro"));   
    	Assert.assertEquals(proElement.getText(), upgrade);
    }
    
    @After("not @API")
    public void teardown() {  
        driver.quit();
    }
       
}
