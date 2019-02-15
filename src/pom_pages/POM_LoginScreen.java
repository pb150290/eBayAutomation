package pom_pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic.BaseClass;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class POM_LoginScreen {
	
	@FindBy(id = "edit_text_username")
	public WebElement txt_Username;
	
	@FindBy(id = "edit_text_password")
	public WebElement txt_Password;
	
	@FindBy(id = "button_sign_in")
	public WebElement btn_loginSignIn;
	
	public POM_LoginScreen(AndroidDriver<MobileElement> driver) {
		PageFactory.initElements(driver, this);
	}

}
