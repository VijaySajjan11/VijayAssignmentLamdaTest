package Assignment.AssignmentLamdaTest;

import java.net.URL;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class desktopWebApp {
	public String userName= "tejaswigarrepally20";
	public String accessKey="s19ATIMnQUrJhidSDHZxKk8sPdGDdfVkJTR3qHASifkV3PHGua";
	public String gridURL= "hub.lambdatest.com";
	
	
	
	public RemoteWebDriver driver;
	
	@org.testng.annotations.Parameters(value = {"browser", "version", "platform"})
	@BeforeTest
	public void setUp(String browser, String version, String platform)throws Exception {
		
		
		
		try {
			DesiredCapabilities caps=new DesiredCapabilities();
			caps.setCapability("build", "Web Automation");
			caps.setCapability("name", "demo");
			caps.setCapability("platform", platform);
			caps.setCapability("browserName", browser);
			caps.setCapability("version", version);
			caps.setCapability("visual", true);
			caps.setCapability("network", true);
			caps.setCapability("console", true);
			caps.setCapability("performance", true);
			caps.setCapability("tunnel", false);
			
			
			
			driver = new RemoteWebDriver(new URL("https://"+userName+":"+accessKey+"@"+gridURL+"/wd/hub"), caps);
			driver.setFileDetector(new LocalFileDetector());
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
	@Test
	public void desktopWebAppScript() throws InterruptedException {

		driver.get("https://www.lambdatest.com");
		Thread.sleep(5000);
		
		WebDriverWait wait= new WebDriverWait(driver,30);
		WebElement element1= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Logo']")));
		//Scroll till 'SEE ALL INTERACTIONS'
		WebElement element2 = driver.findElement(By.xpath("//a[text()='See All Integrations']"));
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,5700)", "");
		Thread.sleep(5000);
		//Click on 'SEE All INTERACTIONS LINK
		element2.click();
		Thread.sleep(2000);
		//Save the window handles in a list and print window handles
		String parent=driver.getWindowHandle();
		
		Set<String>s=driver.getWindowHandles();
		
		System.out.println(s);
				
		//get current url and verify
		
		String currentUrl= driver.getCurrentUrl();
		
		Assert.assertEquals(currentUrl, "https://www.lambdatest.com/integrations");
		
		driver.close();
		}
		

	@AfterTest
	public void quit() {
		driver.quit();
	}

}
