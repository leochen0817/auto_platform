package com.forte.auto.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class ExcelProcess {
	private String getValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(hssfCell.getNumericCellValue());
		} else {
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}
	
	public void createExcelModule(String downLoadPath, String f_parameters , String f_name , String f_desc) {
		String[] arr = f_parameters.split("\\|");
		HSSFWorkbook wb = new HSSFWorkbook();

		HSSFSheet sheet = wb.createSheet("sheet1");

		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		style.setBottomBorderColor(HSSFColor.RED.index);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);

		HSSFRow top = sheet.createRow(0);
		top.createCell(0).setCellValue("接口名称");
		top.createCell(1).setCellValue("接口描述");
		int i = 0;
		if (arr.length == 1 && ("".equals(arr[0]) || arr[0] == null)) {
			top.createCell(2 + i).setCellValue("预期结果");
		}else{
			for (i = 0; i < arr.length; i++) {
				top.createCell(2 + i).setCellValue(arr[i]);
			}
			top.createCell(2 + i).setCellValue("预期结果");
		}
		HSSFRow second = sheet.createRow(1);
		second.createCell(0).setCellValue(f_name);
		second.createCell(1).setCellValue(f_desc);
		int j = 0;
		if (arr.length == 1 && ("".equals(arr[0]) || arr[0] == null)) {
			second.createCell(2 + j).setCellValue("预期结果");
		}else{
			for (j = 0; j < arr.length; j++) {
				second.createCell(2 + j).setCellValue("请输入参数，使用文本格式，数字请使用文本保存");
			}
			second.createCell(2 + j).setCellValue("请输入预期结果");
		}
		
		FileOutputStream os;
		try {
			os = new FileOutputStream(downLoadPath);
			wb.write(os);
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public void readAndExecutePatch(String fullPath, int idf_interface, String f_url, String f_method) {
		InputStream is;
		try {
			is = new FileInputStream(fullPath);
			int submitCount = 0;
			String patch = "patch" + new Date().getTime();
			HSSFWorkbook workbook = new HSSFWorkbook(is);
			HSSFSheet sheet = workbook.getSheetAt(0);
			HSSFRow top = sheet.getRow(0);
			int length = top.getLastCellNum();
			String last = top.getCell(4).toString();
			for(int i=1;i<=sheet.getLastRowNum();i++){
				System.out.println("第"+i+"行");
				submitCount++;
				HSSFRow row = sheet.getRow(i);
				String name = getValue(row.getCell(0));
				String desc = getValue(row.getCell(1));
				String var1 = row.getCell(2).toString();
				String var2 = getValue(row.getCell(3));
				System.out.println(name);
				System.out.println(desc);
				System.out.println(var1);
				System.out.println(var2);
			}
			System.out.println(submitCount);
			System.out.println(patch);
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getRowCount(String fullPath) {
		int rowCount = 0;
		InputStream is;
		try {
			is = new FileInputStream(fullPath);
			HSSFWorkbook workbook = new HSSFWorkbook(is);
			HSSFSheet sheet = workbook.getSheetAt(0);
			rowCount = sheet.getLastRowNum();
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rowCount + 1;
	}

	public void readFirstRow(String fullPath) {
		InputStream is;
		try {
			is = new FileInputStream(fullPath);
			HSSFWorkbook workbook = new HSSFWorkbook(is);
			HSSFSheet sheet = workbook.getSheetAt(0);
			HSSFRow top = sheet.getRow(0);
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String[] readRow(String fullPath , int rowNum , int length) {
		String[] values = new String[length];
		InputStream is;
		try {
			is = new FileInputStream(fullPath);
			HSSFWorkbook workbook = new HSSFWorkbook(is);
			HSSFSheet sheet = workbook.getSheetAt(0);
			HSSFRow row = sheet.getRow(rowNum);
			for(int i=0;i<length;i++){
				values[i] = getValue(row.getCell(2+i));
			}
			if (values.length == 1 && ("".equals(values[0]) || values[0] == null)) {
				values[0] = "";
			}
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return values;
	}

	public String readCellValue(String fullPath, int rowNum, int index) {
		String value = "";
		InputStream is;
		try {
			is = new FileInputStream(fullPath);
			HSSFWorkbook workbook = new HSSFWorkbook(is);
			HSSFSheet sheet = workbook.getSheetAt(0);
			HSSFRow row = sheet.getRow(rowNum);
			value = getValue(row.getCell(index));
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}
}
