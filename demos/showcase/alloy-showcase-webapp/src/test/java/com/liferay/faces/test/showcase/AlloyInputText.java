package com.liferay.faces.test.showcase; 

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class AlloyInputText {

    private String inputTextUrl = "http://localhost:8080/alloy-showcase-webapp-2.0.0-SNAPSHOT/web/guest/showcase/-/component/alloy/inputtext";
    private String url;

    private WebClient webClient;

    @Before
    public void setUp() {
        webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(true);
    }

    @After
    public void tearDown() {
        webClient.close();
    }

    @Test
    public void alloyInputTextGeneral() throws Exception {

      url = inputTextUrl + "/general";
    	HtmlPage initialPage = webClient.getPage(url);

    	String magic = "Hello World!";
    	String inputXpath = "//input[contains(@id,':text')]";
    	String buttonXpath = "//button[@type='submit']";
    	String modelValueXpath = "//span[contains(@id,':modelValue')]";

    	HtmlTextInput input = initialPage.getFirstByXPath(inputXpath);
    	HtmlButton submit = initialPage.getFirstByXPath(buttonXpath);
    	HtmlSpan modelValueSpan = initialPage.getFirstByXPath(modelValueXpath);

    	if (input != null) {
    		input.type(magic);
    		System.out.println("inputList.getValueAttribute() = " + input.getValueAttribute());
    	}

        if (submit != null) {
        	HtmlPage pageAfterSubmit = (HtmlPage) submit.click();
        	webClient.waitForBackgroundJavaScriptStartingBefore(100);
        	
        	System.out.println("pagey.isHtmlPage() = " + pageAfterSubmit.isHtmlPage());
        	modelValueSpan = pageAfterSubmit.getFirstByXPath(modelValueXpath);
        	if (modelValueSpan != null) {
        		System.out.println("modelValueList.getTextContent() = " + modelValueSpan.getTextContent());
        	}
        }
        
        assertTrue(modelValueSpan.getTextContent().contains(magic));
        
//        String xml = page.asXml();
//        System.out.println("xml = " + xml);

    }
    
    @Test
    public void alloyInputTextConversion() throws Exception {
    	
      url = inputTextUrl + "/conversion";
    	HtmlPage initialPage = webClient.getPage(url);
    	
    	String magic = "Hello World!";
    	String inputXpath = "//input[contains(@id,':text')]";
    	String buttonXpath = "//button[@type='submit']";
    	String modelValueXpath = "//span[contains(@id,':modelValue')]";
    	
    }
    
    @Test
    public void alloyInputTextImmediate() throws Exception {
    	
      url = inputTextUrl + "/immediate";
    	HtmlPage initialPage = webClient.getPage(url);
    	
    }
    
    @Test
    public void alloyInputTextValidation() throws Exception {
    	
      url = inputTextUrl + "/validation";
    	HtmlPage initialPage = webClient.getPage(url);
    	
    }

}
