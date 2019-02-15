package pom_pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class POM_LaunchHomeScreen {
	
	@FindBy(id = "home")
	public WebElement homeIcon;
	
	@FindBy(id = "button_sign_in")
	public WebElement btn_homeSignIn;
	
	public POM_LaunchHomeScreen(AndroidDriver<MobileElement> driver) {
		PageFactory.initElements(driver, this);
	}
	

}
