package  DataProviderComponent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.testng.annotations.DataProvider;

import GenericComponent.ExcelReadeWrite;

public class DP_Search {
	
	@DataProvider(name="dp_validsearch")
	public static Iterator<Object[]> getValidXlData() throws IOException {
		
		List<Object[]> xlData = getXlData("InvalidSearch");
		return xlData.iterator();
		
	}
	
	@DataProvider(name="dp_invalidsearch")
	public static Iterator<Object[]> getInvalidXlData() throws IOException {
		
		List<Object[]> xlData = getXlData("ValidSearch");
		return xlData.iterator();
		
		
	}
	
	
	public static List<Object[]> getXlData(String scriptname) throws IOException {
		
		ExcelReadeWrite xl = new ExcelReadeWrite("D:\\TestData.xls");
		HSSFSheet Sheet1 = xl.setSheet("Sheet1");
		int rowCount = xl.getRowCount(Sheet1);
		int colCount = xl.getColCount(Sheet1, 0);
		
		List<Object[]> ls_search = new ArrayList<Object[]>();
		for(int i=1;i<=rowCount;i++) {
			
			String Execute_Flag = xl.readValue(Sheet1, i, "Execute_Flag");
			String Script_name = xl.readValue(Sheet1, i, "Script_name");
			
			if(Execute_Flag.equals("Y") && Script_name.equalsIgnoreCase(scriptname) ) {
				
				Map<String,String> dsMap = new HashMap<String,String>();
				for(int j=0;j<colCount;j++) {
					
					String key = xl.readValue(Sheet1, 0, j);
					String value = xl.readValue(Sheet1, i, j);
					dsMap.put(key, value);
				}//end of for loop for column
				
				Object[] obj = new Object[1];
				obj[0] = dsMap;
				ls_search.add(obj);
				
				
			}
			
			
			
		}//end of forloop for rowcount
		return ls_search;
		
		
		
	}

}
