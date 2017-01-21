package PageObjectComponent;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;

public class OR_Search {
	
	public AppiumDriver driver;
	
	//findby elements
	
	@FindBy(id="com.bigbasket.mobileapp:id/action_search")
	public WebElement search_btn;
	
	@FindBy(id="com.bigbasket.mobileapp:id/searchView")
	public WebElement enter_search;
	
	@FindBy(id="com.bigbasket.mobileapp:id/txtProductCount")
	public WebElement search_value;
	
	@FindBy(id="com.bigbasket.mobileapp:id/txtEmptyMsg1")
	public WebElement invalid_msg;
	
	//initate page facory
	public OR_Search(AppiumDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	
	public void click_search_btn(){
		search_btn.click();
		
	}
	
	public void enter_search_text(String text) {
		enter_search.sendKeys(text+ "\n");
	}
	
	public String get_ValidMsg() {
		
		return search_value.getText();
		
	}
	
	public String get_InvalidMsg() {
		
		return invalid_msg.getText();
	}

}
