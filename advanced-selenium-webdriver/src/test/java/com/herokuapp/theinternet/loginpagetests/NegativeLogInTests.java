package com.herokuapp.theinternet.loginpagetests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.CsvDataProviders;
import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.LoginPage;
import com.herokuapp.theinternet.pages.WelcomePage;

public class NegativeLogInTests extends TestUtilities {

//	@Parameters({ "username", "password", "expectedMessage" })
	@Test(priority = 1, dataProvider = "csvReader", dataProviderClass = CsvDataProviders.class)
	public void negativeLogInTest(Map<String, String> testData) {

		// Data 
		String no = testData.get("no");
		String username = testData.get("username");
		String password = testData.get("password");
		String expectedErrorMessage = testData.get("expectedMessage");
		String description = testData.get("description");

		log.info("Starting negativeLogInTest #" + no + " for " + description);

		// open main page
//		String url = "http://the-internet.herokuapp.com/";
//		driver.get(url);
//		log.info("Main page is opened.");

		WelcomePage welcomePage = new WelcomePage(driver, log);
		welcomePage.openPage();

		// Click on Form Authentication link
//		driver.findElement(By.linkText("Form Authentication")).click();

		LoginPage loginPage = welcomePage.clickFormAuthenticationLink();

		// enter username and password (execute negative login)
//		driver.findElement(By.id("username")).sendKeys(username);
//		driver.findElement(By.id("password")).sendKeys(password);
		loginPage.negativeLogIn(username, password);

		// push log in button
//		driver.findElement(By.className("radius")).click();

		// wait for error message
		loginPage.waitForErrorMessage();
		String message = loginPage.getErrorMessageText();

		// Verification
//		String actualErrorMessage = driver.findElement(By.id("flash")).getText();
//		Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
//				"actualErrorMessage does not contain expectedErrorMessage\nexpectedErrorMessage: "
//						+ expectedErrorMessage + "\nactualErrorMessage: " + actualErrorMessage);

		Assert.assertTrue(message.contains(expectedErrorMessage), "Message doesn't contain expected text.");
	}

}
