package pom_pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class POM_SearchPage {
	
	@FindBy(id = "search_src_text")
	public WebElement txt_SearchBox;
	
	@FindBy(id = "search_voice_btn")
	public WebElement btn_VoiceSearch;
	
	@FindBy(id = "google_text_ad_container")
	public WebElement nativeViewAd;
	
	@FindBy(id = "adcontainer1")
	public WebElement webViewAd;
	
	public POM_SearchPage(AndroidDriver<MobileElement> driver) {
		PageFactory.initElements(driver, this);
	}

}
