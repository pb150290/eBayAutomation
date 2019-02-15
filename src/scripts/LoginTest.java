package scripts;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import generic.BaseClass;
import generic.ConstantItems;
import generic.GenericFunctions;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import pom_pages.POM_AfterLoginScreen;
import pom_pages.POM_HomeScreenAfterLogin;
import pom_pages.POM_LaunchHomeScreen;
import pom_pages.POM_LoginScreen;
import pom_pages.POM_SearchPage;

public class LoginTest extends BaseClass implements ConstantItems{
	
	@Test
	public void checkLoginToEbay() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, FileNotFoundException, IOException {
		
		//Clicking on Element
		POM_LaunchHomeScreen homeScreen = new POM_LaunchHomeScreen(driver);
		homeScreen.btn_homeSignIn.click();
		Thread.sleep(20000);
		
		POM_LoginScreen loginScreen = new POM_LoginScreen(driver);
		POM_AfterLoginScreen afterLogin = new POM_AfterLoginScreen(driver);
		String username = GenericFunctions.getCellValue(EXCEL_PATH, "LoginDetails", 1, 0);
		loginScreen.txt_Username.sendKeys(username);
		
		String password = GenericFunctions.getCellValue(EXCEL_PATH, "LoginDetails", 1, 1);
		loginScreen.txt_Password.sendKeys(password);
		
		if(loginScreen.btn_loginSignIn.isEnabled() == true) {
			System.out.println("Sign In button is Enabled. Now clicking it...");
			loginScreen.btn_loginSignIn.click();
			Thread.sleep(15000);
			
			if(afterLogin.lbl_greeting.isDisplayed() ==true) {
				System.out.println("Successfully logged In With Username and Password");
				
				afterLogin.btn_NoThanks.click();
			}
			else {
				
				System.out.println("Greeting not displayed. Error occurred here...");
			}
			
		}
		else {
			System.out.println("Sign In button is not enabled. Some error occurred.");
		}
		
	}
	
	@Test
	
	public void searchForItem() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, FileNotFoundException, IOException {
		
		Thread.sleep(5000);
		POM_HomeScreenAfterLogin homeAfterlogin = new POM_HomeScreenAfterLogin(driver);
		homeAfterlogin.txt_Search.click();
		Thread.sleep(5000);
		
		POM_SearchPage searchPage = new POM_SearchPage(driver);
		
		String searchValue = GenericFunctions.getCellValue(EXCEL_PATH, "SearchData", 1, 0);
		searchPage.txt_SearchBox.sendKeys(searchValue);
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		
//		searchPage.txt_SearchBox.sendKeys(Keys.ENTER);
		Thread.sleep(25000);
		
	}
	

}
