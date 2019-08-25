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

public class MLabLoginFeature {

    WebDriver driver;

    @Given("^I am on mLab login page$")
    public void i_am_on_mLab_login_page() throws Throwable {
        Class<? extends WebDriver> driverClass = FirefoxDriver.class;
        WebDriverManager.getInstance(driverClass).setup();
        driver = driverClass.newInstance();
        driver.get("https://asd-demo-app.herokuapp.com/mLab.jsp");
        //driver.navigate().to(driver.getCurrentUrl());
    }

    @When("^I fill in \"([^\"]*)\" with \"([^\"]*)\"$")
    public void i_fill_in_with(String arg1, String arg2) throws Throwable {
        driver.get("https://asd-demo-app.herokuapp.com/mLab.jsp");
        driver.navigate().to(driver.getCurrentUrl());
        driver.findElement(By.id("adminemail")).sendKeys(arg1);
        driver.findElement(By.id("adminpassword")).sendKeys(arg2);
        driver.findElement(By.id("mLab_login")).click();
    }

    @Then("^I should see \"([^\"]*)\" green$")
    public void i_should_see_green(String arg1) throws Throwable {
        System.out.println("mLab connection successful.");
    }

    @Then("^I should see \"([^\"]*)\" red$")
    public void i_should_see_red(String arg1) throws Throwable {
        System.out.println("mLab connection unsuccessful!");
    }    
}
