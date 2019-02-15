package pom_pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class POM_AfterLoginScreen {
	
	@FindBy(id = "button_google_deny")
	public WebElement btn_NoThanks;
	
	@FindBy(id = "button_google_allow")
	public WebElement btn_Allow;
	
	@FindBy(id = "text_google_greeting_enroll")
	public WebElement lbl_greeting;
	
	public POM_AfterLoginScreen(AndroidDriver<MobileElement> driver) {
		PageFactory.initElements(driver, this);
	}

}
