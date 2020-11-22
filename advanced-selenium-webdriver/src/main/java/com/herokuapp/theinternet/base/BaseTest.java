package com.herokuapp.theinternet.base;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@Listeners({ com.herokuapp.theinternet.base.TestListener.class })

public class BaseTest {
	protected WebDriver driver;
	protected Logger log;

	protected String testSuiteName;
	protected String testName;
	protected String testMethodName;

//	  if different profiles (plus more code needed) "chromeProfile", "deviceName" 
	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	public void setUp(Method method, @Optional("chrome") String browser, ITestContext ctx) {
		String testName = ctx.getCurrentXmlTest().getName();
		log = LogManager.getLogger(testName);

		BrowserDriverFactory factory = new BrowserDriverFactory(browser, log);
//		if (profile != null) {
//			driver = factory.createChromeWithProfile(profile);
//		} else if (deviceName != null) {
//			driver = factory.createChromeWithMobileEmulation(deviceName);
//		} else {
//			driver = factory.createDriver();
//		}
		driver = factory.createDriver();

		driver.manage().window().maximize();

		this.testSuiteName = ctx.getSuite().getName();
		this.testName = testName;
		this.testMethodName = method.getName();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		log.info("Close driver");
		// Close browser
		driver.quit();
	}
}
