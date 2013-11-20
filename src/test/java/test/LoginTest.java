package test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class LoginTest {

	String browser;
	WebDriver driver = null;
	String SUT = "";
	String platform = "";
	
	public Logger Log = Logger.getLogger(this.getClass());
	
    @Factory(dataProviderClass = SUT_PlatformDataProvider.class, dataProvider = "textDataProvider")    
    public LoginTest(String SUT, String platform) {
    	Log.info("Testing on " + SUT + " for platform " + platform);
    	this.SUT = SUT;
    	this.platform = platform;   
    }
	
	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		
		driver = new HtmlUnitDriver();
		//((HtmlUnitDriver) driver).setProxy("proxy", 3128);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);		
	}
	
	@Test
	public void testLogin() throws Exception {		
		driver.get(this.SUT);			

		assertThat("Title tag with bad text", driver.findElement(By.tagName("title")).getText(), equalTo("Google"));		
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDownBrowserResources(ITestResult result) {
		
		Map<String, Object> testlink = new HashMap<String, Object>();
		testlink.put("Platform", this.platform);
		testlink.put("Notes", "Browsing to " + this.SUT);
		
		result.setAttribute("TestLink", testlink);
		
		Map<String, Object> file = new HashMap<String, Object>();
		Map<String, Object> filedetail = new HashMap<String, Object>();
		
		filedetail.put("File-Title", this.platform + '-' + this.SUT.substring(7) );
		filedetail.put("File-Description", "Main page");
		filedetail.put("File-Size", "13483");
		filedetail.put("File-Name", this.platform + '-' + this.SUT.substring(7) ); 
		filedetail.put("File-Type", "image/png");
		filedetail.put("File-Content", "iVBORw0KGgoAAAANSUhEUgAAAF8AAAApCAMAAABk6QUbAAABgFBMVEX///8Al+dYxpD++rbKkkokFgjTnnLXnF8Am/T/uZb46rP5pHSyfkQAAAAAxvXi05K4kmttWUbgsX+whV51WzFVUC6LbVPv37ffpYIAuP7sTV2KbDpAKRQeLDtvalzovYq2r4NzZYU6IzLr6thKvoTRfSvYcBwJExuwpqXi05La2NbdlneEeI2zcTR6aTo2tXQAl+cAl+c2tXQvqWIAl+cArfCLbVNNTFNaPh0ft2y2r4O1qr2whV4Al+f/+dr/45Xx1W/fllGBDxXvYHAvqWKLbVOsxq+EeI344LLR27PxzoBau9fjmDXyc4KUfohvalzUybkAxvUkFggArfBVUC7x8fDuxSL2lqT1jJmPqK9TmqA1eIRYTF5KPTdCFgoAEDHr6tgAl+cAl+cAl+fv37eRmpwAl+cAl+cAl+cAxvX0xa3Yu7isxq+RmpyJnHa1kGPVgEqmb1pfbZFFQxdrIyz/4lpYTF7a2Nbr6tjvYHCsxq/a2Nb44LLa2NYAm/TsTV1By/KpAAAAgHRSTlMA////////////////////////////iP////////+q////////////d5n//////yJVd4jM3f////8iZrvd7v//////qt3d7u7//////////0RVzN3u//////////////8iIjNEVVVmmard//////////////8RETNERGZ3maqqqlkdB8AAAAaUSURBVBjT7c/nVxpZGAbwO06l995xqQKCoYUuxo4yiqIIKqgIiGIHR4r/+t4hkuCe7Ek+bD5tngNz5t6Z+T3vBeBP/gQA2mit0ABULy6qky3m7Y3++NICm38Bao1CIYkghdHOztRu3bi7O+zHQM8hsAqs4EVKEITr66NEVpvVVqeJzwtfZmZ+7PuTSHIUCASaIaFQ2Jo0vKw4BFGBoZ1Q7jkkvLDDmeCzccJH/dLJk1ebXfrIfJqZmf8R30CQ8/fbnRZsCIDnYrHoGt445Bt8/MYsl4fnOLx7H18nl8v5OhrUj/JPT/qsdutX/FoQaU6XCVvzrmIxU8yU8jqSxLDtG5GJB4Px5Tpc4NPplnEBrn98kszJlusfpC8/9JtIcHq504LTZ1AuN3MjW7wnZXe3s6lUGvrSvShPxicxPS73RWHFvVinw/H6z3w/gpxPrxeMSmUmpeLOisi8jsfHyHDHYIg4JKr9hFeG8fikXrJmIsI+fRQjeVgUp3/iB6fGp5095Qku0CvWuek17sZ2hCRv1x8EArlP4ais7avvMOwuIVeLCZM0LYkuYiRJ4sof+gzzfhNAkMbk+SuO63EcD6clElS05t683pYetFU34XQmnTE4tt37Kj6/3b8goS+WynWLGIbZTsqB0ajZHBWCZ998ZmlpaWtri604CyJIYOIbBcY9XEJs3BL5dVS1snK9rdJqVTI+uSGT+QSGiGd/vz27P4wTi3Mbom1+DuPbdcgofr1JIYWdc3b+v1ifOTxkYMe4AI6PXH7360q+1ETcSs0w2Mp+qRN99D3iesWpQyHBBQLDejqTWSl3Nkw3Rx6JeNHupYQucnFjkwqxCPQ/A0AfHIy1Q7aG9c8nfi+qpGUc3qLULJZJIpKw+waH0ev1UX3YZCLCDoE8w0Z0Uy4b9NbI9j2iOTp5VMiloVCoNvEPDuix5mJvGtPzgwEmzefNV5183nB6emq4EWejj1GFIhrN8tRqNScs8KEoF01nIqdWGMfxA6Upn2DYiXwzxB7gE+szh2N/6fBwiQFjv/HNBy+DsOMx/wXyhuNjaySsF6OiNa5JLpbKpGJCiptRLpeLZo6tEolCovCWNZojO0YuRssaKvDuL21tMQy8LI3ByySCBMF0lI+PCoPc67V5c8cRVITCiddVszI1QRCkbE3EReHPTIgJtdiki2g0m9AnbR0N5Z/4Wi30mXet1oQHOPuGXxZaLbhRttttNvvd8THERaI0KnqUc8S3UoLgoioVl5u6gr5UzOFXCiGNzI5huRLFDvk+v5aZGtcPueZkcTYaNVpCocZgJu3eu7oBx31welS0p1y+v5Vt32t50EdTQ4InFYvVhDRR1mgQvt1OhqjaxGe2tt6mfBCEBf6pdUwoDJ2W1mz23LPR2DWqN0wmNSe+WqlUHiz3WvXVmpqu+yT3pGzOZ90zb4Yo5MGDFFh+4ms/HOAS+sHa9/WOUNgyJoo5L1aMgSqHN3fH47QtFo/Fsrr64Gm33W3tm8Oq43t1uqyXt2gubQop6uv3Yx+8aT8WBGABcvbBB7mcPYdmiq4Bj8eB8ayuejweS/31etbt3p/L6uw283Xnds5uN11dpTap0PnEnweA0U4KGOZ7QeC7r2nFijmbzZtYynE4Y3+1u2qBRzACyLtn29ork1KUWu9Uq2r3LBf6FOWHX85/mfnr08LnrwVvMJNj+JOwgGr4z2u1nZ0WnN/mcmafTrL0IM0ZZ1CvQN9idKZmYdyD1DDWUZl9fXDBj5vdwxByxM73eWFh4dPCPDv427iCmcwMGskkBTuShVZrVIvZ3sByFkYJ+nMwavWgbvF4LJV8/Pq6dH29Ur2oJhQ+nzwO6g/BzopovdFBqACYDsMw4GNqZ/7z13osRg9c8NET62cBSNi9dq+tV49UlpcVy6/lcrxUXh0OwGokUrEmdqj1lZgrPQBOhEJq4KdBB8/0QBRzDevZ7NPTU7YLXFgigZFVoFAou4pl59EReC0f9S/ASzseb8eTm+nBcyzW516cU8jlz/3+S487jD27UBoY6t1duFNNATAcgq5E0oUdnlIJ9B8e+nB/+6UvG1GFVxq4Yv00FzSRAPiF9FSvsWdQB11Btyuog/pevgqUeeWuHpYpd3edL3CI+BAA5161Z0Q0BfiJK0bTg0ET8f+KD2gAffi9E/5pUKXpKuj1et1/vlZlL02EgujzeK5kEPznOUPGBWxGSA38hoIkbBiNGiMq+Dt4mMsmrAgG/eBP/o/5G5fNcrAxDsSbAAAAAElFTkSuQmCC");
		
		file.put(this.platform + '-' + this.SUT.substring(7) + ".png" , filedetail);
		
		result.setAttribute("Files", file);
		
		driver.quit();		
	}
}