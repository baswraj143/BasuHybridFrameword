package com.accenture.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ExcelReader {
	XSSFWorkbook wb;
	public ExcelReader(){
		
		try {
		File f1=new File(System.getProperty("user.dir")+"/TestData/inputData.xlsx");
		FileInputStream fis= new FileInputStream(f1);
		 wb= new XSSFWorkbook(fis);
		System.out.println("LOG : INFO excel is ready to use" );
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public String getCellData(int sheetIndex,int row, int col) {
	String data = "";
	
	XSSFCell cell=wb.getSheetAt(sheetIndex).getRow(row).getCell(col);
	if(cell.getCellType()==CellType.STRING) {
		data=cell.getStringCellValue();
	}else if(cell.getCellType()==CellType.NUMERIC) {
		data=String.valueOf(cell.getNumericCellValue());
	}else if(cell.getCellType()==CellType.BLANK) {
		data="";
	}
	
	return data;

	}
	
	public int getNnumberOfColoumn(String sheetName) {
		
		return wb.getSheet(sheetName).getRow(0).getPhysicalNumberOfCells();
	}
	
	public int getNumberOfColumn(int sheetIndex)
	{
		return wb.getSheetAt(sheetIndex).getRow(0).getPhysicalNumberOfCells();
	}
	
	/*
	 * public int getNumberOfRows(String sheetName) { return
	 * wb.getSheet(sheetName).getFirstRowNum(); }
	 */
	
	public int getNumberOfRows(int sheetIndex)
	{
		return wb.getSheetAt(sheetIndex).getPhysicalNumberOfRows();
	}

}
