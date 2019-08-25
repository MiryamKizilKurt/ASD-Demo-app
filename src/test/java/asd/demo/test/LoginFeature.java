/*
 * Test the Login function using selenium and cucumber acceptance testing 
 */
package asd.demo.test;

/**
 *
 * @author George
 */
import org.openqa.selenium.By;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginFeature {

    WebDriver driver;

    @Given("^I am on the \"([^\"]*)\" page on URL \"([^\"]*)\"$")
    public void i_am_on_the_page_on_URL(String arg1, String arg2) throws Throwable {
        Class<? extends WebDriver> driverClass = FirefoxDriver.class;        
        WebDriverManager.getInstance(driverClass).setup();
        driver = driverClass.newInstance();
        driver.get("https://asd-demo-app.herokuapp.com/");
        driver.navigate().to(driver.getCurrentUrl());
    }

    @When("^I fill in \"([^\"]*)\" with \"([^\"]*)\"$")
    public void i_fill_in_with(String arg1, String arg2) throws Throwable {
        driver.findElement(By.id("adminemail")).sendKeys(arg1); 
        driver.findElement(By.id("adminpassword")).sendKeys(arg2); 
        driver.findElement(By.id("mLab_login")).click();
    }

    @Then("^I should see \"([^\"]*)\" green$")
    public void i_should_see_green(String arg1) throws Throwable {
       System.out.println("mLab connection successful.");        
    }

    @When("^I click on the \"([^\"]*)\" button$")
    public void i_click_on_the_button(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        
    }
   

    @Then("^I should see \"([^\"]*)\" Button$")
    public void i_should_see_Button(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        
    }

    @Then("^I should see the \"([^\"]*)\" button$")
    public void i_should_see_the_button(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        
    }

    @Then("^I should see \"([^\"]*)\" message$")
    public void i_should_see_message(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        
    }

}
