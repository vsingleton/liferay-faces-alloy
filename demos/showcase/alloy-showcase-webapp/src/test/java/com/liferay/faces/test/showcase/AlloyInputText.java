package com.liferay.faces.test.showcase;
//J-

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.openqa.selenium.By;
// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebElement;
// import org.openqa.selenium.firefox.FirefoxDriver;
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

//	WebDriver browser = new FirefoxDriver();
//	WebDriver browser = new PhantomJSDriver();
//	WebDriver browser = new ChromeDriver();
//	WebDriver browser = new HtmlUnitDriver();

	private static String PHANTOMJS_BINARY;

	@Before
	public void setUp() {
//		PHANTOMJS_BINARY = System.getProperty("phantomjs.binary");
		PHANTOMJS_BINARY = "/usr/local/bin/phantomjs";

//		assertNotNull(PHANTOMJS_BINARY);
		assertTrue(
			PHANTOMJS_BINARY + "does not exist.",
			new File(PHANTOMJS_BINARY).exists()
		);
	}

	@After
	public void tearDown() {
		// browser.close();
	}

	@Test
	public void alloyInputTextGeneral() throws Exception {

		final DesiredCapabilities capabilities = new DesiredCapabilities();

		// Configure our WebDriver to support JavaScript and be able to find the PhantomJS binary
		capabilities.setJavascriptEnabled(true);
		capabilities.setCapability("takesScreenshot", true);
		capabilities.setCapability(
			PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
			PHANTOMJS_BINARY
		);

		final WebDriver browser = new PhantomJSDriver(capabilities);

		url = inputTextUrl + "/general";

		// driver.navigate().to(baseURL + "/index.html");
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

		assertTrue(
			"modelValue should contain '" + magic + "', but it contains '" + modelValue.getText() + "'.",
			modelValue.getText().contains(magic)
		);

	}
	
	public void waitForElement(WebDriver browser, String xpath) {
        WebDriverWait wait = new WebDriverWait(browser, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.ByXPath.xpath(xpath)));
	}

}
// J+
