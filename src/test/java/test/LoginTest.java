package test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//@Listeners(value={
//		org.tap4j.ext.testng.listener.TapListenerClassYaml.class
//})
public class LoginTest {

	String browser;
	WebDriver driver = null;
	String SUT = "";
	
	public Logger Log = Logger.getLogger(this.getClass());
	
    @Factory(dataProviderClass = TestDataProvider.class, dataProvider = "textDataProvider")
    public LoginTest(String SUT) {
    	this.SUT = SUT;
    }
	
	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		
		driver = new HtmlUnitDriver();
		//((HtmlUnitDriver) driver).setProxy("proxy", 3128);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);		
	}
	
	@Test
	public void testLogin() throws Exception {		
		Log.info("Testing TAP output with " + this.SUT);
		driver.get(this.SUT);			
		if (this.SUT.toLowerCase().contains("google")) {
			assertThat("Title tag with bad text", driver.findElement(By.tagName("title")).getText(), is("Google"));
		}
		else {
			assertThat("Title tag with bad text", driver.findElement(By.tagName("title")).getText(), not(is("Google")));
		}			
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDownBrowserResources(ITestResult result) {
		result.setAttribute("SUT", this.SUT);
		driver.quit();		
	}
}