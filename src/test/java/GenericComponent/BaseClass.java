package GenericComponent;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class BaseClass {
	
	public static Process process;
	public static AppiumDriver driver;

	public static void Start_Sever() throws IOException, InterruptedException {
		
		String start_server = "C:\\Appium\\node.exe  C:\\Appium\\node_modules\\appium\\bin\\appium.js";
		process = Runtime.getRuntime().exec(start_server);
		if(process!=null){
			System.out.println("Server Started");
		}
		else {
			System.out.println("server not started");
		}
		Thread.sleep(19000);
			
	}
	
	public static void initapp() throws MalformedURLException, InterruptedException {
		
		DesiredCapabilities capbilities = new DesiredCapabilities();
		
		capbilities.setCapability("deviceName", "Moto G Play");
		capbilities.setCapability("platformName", "android");
		capbilities.setCapability("platformVersion", "6.0.1");
		
		capbilities.setCapability("appPackage", "com.bigbasket.mobileapp");
		capbilities.setCapability("appActivity", "com.bigbasket.mobileapp.activity.SplashActivity");
		
		
//		capbilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Moto G Play");
//		capbilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
//		capbilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0.1");
		
//		capbilities.setCapability(MobileCapabilityType.APP_PACKAGE, "com.bigbasket.mobileapp");
//		capbilities.setCapability(MobileCapabilityType.APP_ACTIVITY, "com.bigbasket.mobileapp.activity.SplashActivity");
//		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capbilities);
		
		Thread.sleep(10000);
		
		
	} 
	public static void Snapshot(String TCID,String OrderID) throws IOException{
		
		Date date = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		File file = new File(sd.format(date)+".png");
		
		TakesScreenshot tsh  = (TakesScreenshot)driver;
		File screenshot1 = tsh.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot1, new File("D:\\Screenshot\\"+TCID+"-"+OrderID+"-"+file));	
		
	}
	
	
	public  void Explicit_Wait(WebElement ele,long T1) {
		
		WebDriverWait wait = new WebDriverWait(driver,T1);
		wait.until(ExpectedConditions.visibilityOf(ele)).isDisplayed();
		
		
	}
	public static void stop_server() {
		if(process!=null) {
			process.destroy();
			System.out.println("server stopped");
		}
		
	}

}
