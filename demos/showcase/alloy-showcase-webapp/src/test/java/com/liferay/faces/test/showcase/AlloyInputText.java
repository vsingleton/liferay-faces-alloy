package com.liferay.faces.test.showcase;
//J-

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlloyInputText {

	private static final String inputXpath = "//input[contains(@id,':text')]";
	private static final String submitButtonXpath = "//button[contains(text(),'Submit')]";
	private static final String modelValueXpath = "//span[contains(@id,':modelValue')]";

	private String inputTextUrl = "http://localhost:8080/alloy-showcase-webapp-3.0.0-SNAPSHOT/web/guest/showcase/-/component/alloy/inputtext";
	private String url;

	private WebElement input;
	
	private WebElement submitButton;
	
	private WebElement modelValue;

	WebDriver browser = new FirefoxDriver();
//	WebDriver browser = new PhantomJSDriver();
//	WebDriver browser = new ChromeDriver();
//	WebDriver browser = new HtmlUnitDriver();

	@Before
	public void setUp() {
		Options options = browser.manage();
		options.deleteAllCookies();
	}

	@After
	public void tearDown() {
		browser.close();
	}

	@Test
	public void alloyInputTextGeneral() throws Exception {
		url = inputTextUrl + "/general";

		browser.navigate().to(url);

		String magic = "Hello World!";

		input = browser.findElement(By.xpath(inputXpath));
		input.sendKeys(magic);
		waitForElement(browser, inputXpath);
		System.out.println("input.getAttribute('value') = " + input.getAttribute("value"));


		submitButton = browser.findElement(By.xpath(submitButtonXpath));
		submitButton.click();

		waitForElement(browser, modelValueXpath);

		modelValue = browser.findElement(By.xpath(modelValueXpath));
		System.out.println("modelValue.getText() = " + modelValue.getText());

		assertTrue(modelValue.getText().contains(magic));

	}

	public void waitForElement(WebDriver browser, String xpath) {
		WebDriverWait wait = new WebDriverWait(browser, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.ByXPath.xpath(xpath)));
	}

//	public boolean isThere(WebDriver browser, String xpath) {
//		boolean isThere = false;
//		int count = 0;
//		count = browser.findElements(By.xpath(xpath)).size();
//
//		if (count == 0) {
//			isThere = false;
//		}
//
//		if (count > 0) {
//			isThere = true;
//		}
//
//		if (count > 1) {
//			System.err.println(
//				"The method 'isThere(xpath)' found " + count + 
//				" matches using xpath = " + xpath + 
//				" ... the word 'is' implies singluar, or 1, not " + count
//			);
//		}
//
//		return isThere;
//	}

}
// J+
