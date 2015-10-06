package com.liferay.faces.test.showcase; 

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.List;

public class Smoke {

    private String url;

    private WebClient webClient;

    @Before
    public void setUp() {
        // url = System.getProperty("integration.url");
        url = "http://localhost:8080/showcase-webapp-3.0.0-SNAPSHOT/web/guest/showcase/-/component/alloy/inputtext/general";

        webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(true);
    }

    @After
    public void tearDown() {
        webClient.close();
    }

    @Test
    public void smoke() throws Exception {
    	
    	HtmlPage initialPage = webClient.getPage(url);
    	
    	String magic = "1245";
    	String inputXpath = "//input[contains(@id,':text')]";
    	String buttonXpath = "//button[@type='submit']";
    	String modelValueXpath = "//span[contains(@id,':modelValue')]";
    	
    	List<HtmlTextInput> inputList = (List<HtmlTextInput>) initialPage.getByXPath(inputXpath);
    	List<HtmlButton> buttonList = (List<HtmlButton>) initialPage.getByXPath(buttonXpath);
    	List<HtmlSpan> modelValueList = (List<HtmlSpan>) initialPage.getByXPath(modelValueXpath);
        
    	if (inputList != null) {
    		inputList.get(0).type(magic);
    		System.out.println("inputList.get(0).getValueAttribute() = " + inputList.get(0).getValueAttribute());
    	}
    	
        if (buttonList != null) {
        	HtmlPage pageAfterSubmit = (HtmlPage) buttonList.get(0).click();
        	webClient.waitForBackgroundJavaScriptStartingBefore(100);
        	
        	System.out.println("pagey.isHtmlPage() = " + pageAfterSubmit.isHtmlPage());
        	modelValueList = (List<HtmlSpan>) pageAfterSubmit.getByXPath(modelValueXpath);
        	if (modelValueList != null) {
        		System.out.println("modelValueList.get(0).getTextContent() = " + modelValueList.get(0).getTextContent());
        	}
        }
        
        assertTrue(modelValueList.get(0).getTextContent().contains(magic));
        
//        String xml = page.asXml();
//        System.out.println("xml = " + xml);

    }

}
