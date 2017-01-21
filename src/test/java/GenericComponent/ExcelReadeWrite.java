package GenericComponent;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelReadeWrite {
	
	public FileInputStream fis;
	public HSSFWorkbook wb;
	public ExcelReadeWrite(String path) throws IOException {
		
		 fis = new FileInputStream(path);
		 wb = new HSSFWorkbook(fis);
		 
	}
	
    
	public HSSFSheet setSheet(String sheetname){
		HSSFSheet sheet1 = wb.getSheet(sheetname);
		return sheet1;
	}
		
	public int getRowCount(HSSFSheet sheetname){
       int rowCount = sheetname.getLastRowNum();
       return rowCount;
       
	}
	
	public int getColCount(HSSFSheet sheetname,int rowindex) {
		int colCount = sheetname.getRow(rowindex).getLastCellNum();
		return colCount;
	}
	
	public String readValue(HSSFSheet sheetname,int rowindex,int colindex) {
		String celltext = null;
	    HSSFCell cell = sheetname.getRow(rowindex).getCell(colindex);
	    if(cell.getCellType()==Cell.CELL_TYPE_STRING) {
	    	celltext = cell.getStringCellValue();
	    }
	    else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC) {
	    	celltext = String.valueOf(cell.getNumericCellValue());
	    }
	    else if(cell.getCellType()==Cell.CELL_TYPE_BLANK) {
	    	celltext ="";
	    }
	    return celltext;
	    
	}
	
	public String readValue(HSSFSheet sheetname,int rowindex, String colname){
		
		int colindex = 0;
		for(int i=0;i<getColCount(sheetname,0);i++) {
			if(sheetname.getRow(0).getCell(i).getStringCellValue().trim().equalsIgnoreCase(colname)) {
				colindex = i;
			}
		}
		return readValue(sheetname,rowindex,colindex);
		
	}
		
		
	public void writeValue(HSSFSheet sheetname,int rowindex,int colindex,String value) {
		
		HSSFCell writecell = sheetname.getRow(rowindex).getCell(colindex);
		if(writecell == null) {
			writecell = sheetname.getRow(rowindex).createCell(colindex);
		}
		writecell.setCellValue(value);
		
	}
	
	public void writeValue(HSSFSheet sheetname,int rowindex,String colname,String value) {
		
		int colindex =0;
		for(int i=0;i<getColCount(sheetname,0);i++) {
			if(sheetname.getRow(0).getCell(i).getStringCellValue().trim().equalsIgnoreCase(colname)) 
			colindex=i;
		}
		
		writeValue(sheetname,rowindex,colindex,value);
	}
		
		
	public void saveExcel(String path) throws IOException  
 {
		
		fis.close();
		FileOutputStream fos = new FileOutputStream(path);
		wb.write(fos);
		fos.close();
	}

}
