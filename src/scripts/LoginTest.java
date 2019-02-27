package scripts;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseClass;
import generic.ConstantItems;
import generic.GenericFunctions;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import pom_pages.POM_AfterLoginScreen;
import pom_pages.POM_HomeScreenAfterLogin;
import pom_pages.POM_LaunchHomeScreen;
import pom_pages.POM_LoginScreen;
import pom_pages.POM_SearchPage;

public class LoginTest extends BaseClass implements ConstantItems {

	@Test
	public void checkLoginToEbay() throws InterruptedException, EncryptedDocumentException, InvalidFormatException,
			FileNotFoundException, IOException {

		// Clicking on Element
		POM_LaunchHomeScreen homeScreen = new POM_LaunchHomeScreen(driver);
		homeScreen.btn_homeSignIn.click();

		POM_LoginScreen loginScreen = new POM_LoginScreen(driver);
		POM_AfterLoginScreen afterLogin = new POM_AfterLoginScreen(driver);

		GenericFunctions.WaitExplicitly(loginScreen.txt_Username);
		String username = GenericFunctions.getCellValue(EXCEL_PATH, "LoginDetails", 1, 0);
		loginScreen.txt_Username.sendKeys(username);

		String password = GenericFunctions.getCellValue(EXCEL_PATH, "LoginDetails", 1, 1);
		loginScreen.txt_Password.sendKeys(password);

		if (loginScreen.btn_loginSignIn.isEnabled() == true) {
			System.out.println("Sign In button is Enabled. Now clicking it...");
			loginScreen.btn_loginSignIn.click();
			GenericFunctions.WaitExplicitly(afterLogin.lbl_greeting);

			if (afterLogin.lbl_greeting.isDisplayed() == true) {
				System.out.println("Successfully logged In With Username and Password");
				Assert.assertTrue(GenericFunctions.elementIsPresent(afterLogin.lbl_greeting));

				afterLogin.btn_NoThanks.click();
			} else {

				System.out.println("Greeting not displayed. Error occurred here...");
				Assert.fail("Error. Greeting is not seen.");
			}

		} else {
			System.out.println("Sign In button is not enabled. Some error occurred.");
		}

	}

	@Test

	public void searchForItem() throws InterruptedException, EncryptedDocumentException, InvalidFormatException,
			FileNotFoundException, IOException {

		POM_HomeScreenAfterLogin homeAfterlogin = new POM_HomeScreenAfterLogin(driver);
		GenericFunctions.WaitExplicitly(homeAfterlogin.txt_Search);

		homeAfterlogin.txt_Search.click();

		POM_SearchPage searchPage = new POM_SearchPage(driver);
		String searchValue = GenericFunctions.getCellValue(EXCEL_PATH, "SearchData", 1, 0);
		searchPage.txt_SearchBox.sendKeys(searchValue);
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		GenericFunctions.WaitExplicitly(searchPage.nativeViewAd);

		// Code to switch Context since search results are shown in WebView. However, it
		// does not switch.
		// Please check that the app has set "setWebContentsDebuggingEnabled" o true
		// while development
		Set<String> contextName = driver.getContextHandles();
		for (String context : contextName) {
			System.out.println(contextName);
			if (!context.equals("NATIVE_APP")) {
				driver.context(context);
				System.out.println("Context switched to WebView");
				break;
			}
		}
		System.out.println("Context is still NATIVE");
		Assert.assertFalse(GenericFunctions.elementIsPresent(searchPage.webViewAd));
		Thread.sleep(5000);

	}

	@Test
	public void handleScreenRotation() {
		if(driver.getOrientation()==ScreenOrientation.PORTRAIT) {
			System.out.println("App is currently in portrait mode. Now switching to Landscape");
			driver.rotate(ScreenOrientation.LANDSCAPE);
			Assert.assertEquals(ScreenOrientation.LANDSCAPE, driver.getOrientation());
		}
		else {
			System.out.println("App is currently in landscape mode. Now switching to portrait");
			driver.rotate(ScreenOrientation.PORTRAIT);
			Assert.assertEquals(ScreenOrientation.PORTRAIT, driver.getOrientation());
		}
	}

	@Test
	public void handleScreenResolution() {

		Dimension screen_dimension = driver.manage().window().getSize();

		System.out.println("The screen size is: Width - " + screen_dimension.getWidth() + "\n" + "Height - "
				+ screen_dimension.getHeight());
		Assert.assertTrue(true, "Width: "+ screen_dimension.getWidth()+ "Height: "+screen_dimension.getHeight());
	}

}
