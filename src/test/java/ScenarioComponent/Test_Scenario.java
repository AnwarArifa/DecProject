package ScenarioComponent;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import GenericComponent.BaseClass;
import PageObjectComponent.OR_Search;

public class Test_Scenario extends BaseClass {
	
	public static Logger log = Logger.getLogger(Test_Scenario.class);
	SoftAssert sAssrt = new SoftAssert();
	
	@Test(dataProvider="dp_validsearch",dataProviderClass=DataProviderComponent.DP_Search.class,groups={"smoke"})
	
	public void test_Scenario(Map search) throws IOException, InterruptedException{
		
		String TC_ID = search.get("TC_ID").toString();
		String Order = search.get("Order").toString();
		String Search_Item = search.get("Search_item").toString();
		String Exp_Result = search.get("Exp_Result").toString();
		
		Start_Sever();
		log.info("execution started"+TC_ID+"order"+Order);
		Thread.sleep(8000);
		System.out.println("app started");
		initapp();
		System.out.println("app ended");
		OR_Search or1 = new OR_Search(driver);
		Explicit_Wait(or1.search_btn,25);
		or1.click_search_btn();
		
		Explicit_Wait(or1.enter_search,35);
		or1.enter_search_text(Search_Item);
		
		Explicit_Wait(or1.invalid_msg,20);
		String actual_result = or1.get_InvalidMsg();
		
		if(actual_result.contains(Exp_Result)) {
		   log.info("Actualresult"+actual_result+"---"+"ExpResult"+Exp_Result);
		}
		else {
			
			Snapshot(TC_ID,Order);
			log.info("Actualresult"+actual_result+"---"+"ExpResult"+Exp_Result);
			sAssrt.fail("failed"+"actual result"+actual_result+"exp_result"+Exp_Result);
		}
		
		stop_server();
		sAssrt.assertAll();
		
	}

}
