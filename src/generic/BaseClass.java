package generic;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import pom_pages.POM_LaunchHomeScreen;

public class BaseClass {
	
	public static AndroidDriver<MobileElement> driver;

	@BeforeTest
	public void setUp() throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		AppiumServer appiumServer = new AppiumServer();
		
		int port = 4723;
		if(!appiumServer.checkIfServerIsRunnning(port)) {
			System.out.println("Port No.: " + port + " is available.");

			appiumServer.startServer();
			
		} else {
			System.out.println("Appium Server already running on Port - " + port);
		}
		
		File appPath = new File("./app/eBay.apk");
		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability(MobileCapabilityType.APP, appPath.getAbsolutePath());
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "MI");
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
//		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION,"9");
		
		cap.setCapability("unicodeKeyboard", "true");                                     
		cap.setCapability("resetKeyboard", "true");
		
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, DeviceVersionFinder.getDeviceVersion());
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		cap.setCapability("--session-override", false);
		
		cap.setCapability("setWebContentsDebuggingEnabled", true);
		
		driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), cap);

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		POM_LaunchHomeScreen homeScreen = new POM_LaunchHomeScreen(driver);

		if(homeScreen.btn_homeSignIn.isDisplayed()) {
			
			System.out.println("App has successfully loaded within the time provided!");
			
		}

		
//		MobileElement element = driver.findElement(By.id("recycler_view_main"));
		
//		TouchActions action = new TouchAction();
//		action.scroll(element, 10, 100);
//		action.perform();
		
		

		System.out.println("Setup successful!");


	}
	
	@AfterTest
	public void shutDown() {
		
		driver.quit();
	}

}
