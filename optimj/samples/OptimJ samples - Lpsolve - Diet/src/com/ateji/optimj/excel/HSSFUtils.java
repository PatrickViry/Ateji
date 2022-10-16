/****************************
 * Copyright (c) 2007 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.optimj.excel;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.*;

public class HSSFUtils
{
	// Cell types
//	static int	CELL_TYPE_NUMERIC = 0;
//	static int	CELL_TYPE_STRING = 1;
//	static int	CELL_TYPE_FORMULA = 2;
//	static int	CELL_TYPE_BLANK = 3;
//	static int	CELL_TYPE_BOOLEAN =4;
//	static int	CELL_TYPE_ERROR = 5;

	static String[] cellTypes = {
		"CELL_TYPE_NUMERIC",
		"CELL_TYPE_STRING",
		"CELL_TYPE_FORMULA",
		"CELL_TYPE_BLANK",
		"CELL_TYPE_BOOLEAN",
		"CELL_TYPE_ERROR"
	};

	static String[] dietProblemNames = {
		"Foods",
		"Cost",
		"FMin",
		"FMax",
		"Amount",
		"Solution",
		"Vitamins",
		"VMin",
		"VMax"
	};
		
	static float getFloat(HSSFCell cell)
	{
		float defaultValue = (float)0.0;
		
		if(cell == null) {
			return defaultValue;
		} else {
			int cellType = cell.getCellType();
			switch(cellType) {
			case HSSFCell.CELL_TYPE_NUMERIC:
				return(Float.parseFloat(cell.toString()));
			case HSSFCell.CELL_TYPE_STRING:
				return defaultValue;
			case HSSFCell.CELL_TYPE_FORMULA:
				return defaultValue;
			case HSSFCell.CELL_TYPE_BLANK:
				return defaultValue;
			case HSSFCell.CELL_TYPE_BOOLEAN:
				return defaultValue;
			case HSSFCell.CELL_TYPE_ERROR:
				return defaultValue;
			default:
				return defaultValue;
			}
		}		
	}
	
	static double getDouble(HSSFCell cell)
		throws NumberFormatException
	{
		double defaultValue = 0.0;
		
		if(cell == null) {
			return defaultValue;
		} else {
			int cellType = cell.getCellType();
			switch(cellType) {
			case HSSFCell.CELL_TYPE_NUMERIC:
				return(Double.parseDouble(cell.toString()));
			case HSSFCell.CELL_TYPE_STRING:
				return defaultValue;
			case HSSFCell.CELL_TYPE_FORMULA:
				return defaultValue;
			case HSSFCell.CELL_TYPE_BLANK:
				return defaultValue;
			case HSSFCell.CELL_TYPE_BOOLEAN:
				return defaultValue;
			case HSSFCell.CELL_TYPE_ERROR:
				return defaultValue;
			default:
				return defaultValue;
			}
		}		
	}
	
	static int getInteger(HSSFCell cell)
	throws NumberFormatException
{
	int defaultValue = 0;
	
	if(cell == null) {
		return defaultValue;
	} else {
		int cellType = cell.getCellType();
		switch(cellType) {
		case HSSFCell.CELL_TYPE_NUMERIC:
			return(Integer.parseInt(cell.toString()));
		case HSSFCell.CELL_TYPE_STRING:
			return defaultValue;
		case HSSFCell.CELL_TYPE_FORMULA:
			return defaultValue;
		case HSSFCell.CELL_TYPE_BLANK:
			return defaultValue;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			return defaultValue;
		case HSSFCell.CELL_TYPE_ERROR:
			return defaultValue;
		default:
			return defaultValue;
		}
	}		
}

	static void displayCell(HSSFCell cell)
	{
		// extract the cell contents based on cell type etc.
		if(cell == null) {
			System.out.println("       null");
		} else {
			int cellType = cell.getCellType();
			System.out.println("       type = " + cellType + " (" + cellTypes[cellType] + ")");
			switch(cellType) {
			case HSSFCell.CELL_TYPE_NUMERIC:
				System.out.println("       numeric = " + cell.getCellNum() + " " +  cell.toString());
				break;
			case HSSFCell.CELL_TYPE_STRING:
				System.out.println("       string = " + cell.toString());
				break;
			case HSSFCell.CELL_TYPE_FORMULA:
				System.out.println("       formula = " + cell.getCellFormula() + " " +  cell.toString());
				break;
			case HSSFCell.CELL_TYPE_BLANK:
				System.out.println("       blank");
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN:
				System.out.println("       boolean = " + cell.getCellNum() + " " +  cell.toString());
				break;
			case HSSFCell.CELL_TYPE_ERROR:
				System.out.println("       error");
				break;
			default:
				System.out.println("       unknown type " + cellType + " " +  cell.toString());
			}
		}
	}

	static AreaReference getAreaForName(HSSFWorkbook wb, String name)
	{
		int namedRangeIndex = wb.getNameIndex(name);
		if(namedRangeIndex == -1) return null;
		
		HSSFName namedRange = wb.getNameAt(namedRangeIndex);
		String reference = namedRange.getReference();    
		
	    AreaReference areaReference = new AreaReference(reference);
	    return areaReference;
	}
}
