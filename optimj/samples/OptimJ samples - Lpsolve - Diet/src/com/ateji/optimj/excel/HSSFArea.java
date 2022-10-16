/****************************
 * Copyright (c) 2007 ATEJI *
 * All rights reserved.     *
 ****************************/
package com.ateji.optimj.excel;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.*;

public class HSSFArea
{
	final HSSFWorkbook wb;
	final HSSFSheet sheet;
	
	final int firstRow;
	final int lastRow;
	final short firstColumn;
	final short lastColumn;
	
	HSSFArea(final HSSFWorkbook wb, final HSSFSheet sheet, final int firstRow, final int lastRow, final short firstColumn, final short lastColumn) {
		this.wb = wb;
		this.sheet = sheet;
		this.firstRow = firstRow;
		this.lastRow = lastRow;
		this.firstColumn = firstColumn;
		this.lastColumn = lastColumn;
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

	public static HSSFArea make(HSSFWorkbook wb, String name)
	{
		HSSFSheet sheet;
		
		int firstRow;
		int lastRow;
		short firstColumn;
		short lastColumn;

		AreaReference areaReference = getAreaForName(wb, name);
		if(areaReference == null) {
			return null;
		} else {
			CellReference[] cellReferences = areaReference.getCells();

			String sheetName = cellReferences[0].getSheetName();
			sheet = wb.getSheet(sheetName);
			firstRow = cellReferences[0].getRow();
			firstColumn = cellReferences[0].getCol();

			if(cellReferences.length > 1) {
				lastRow = cellReferences[1].getRow();
				lastColumn = cellReferences[1].getCol();
			} else {
				lastRow = firstRow;
				lastColumn = firstColumn;
			}
			return new HSSFArea(wb, sheet, firstRow, lastRow, firstColumn, lastColumn);
		}
	}
	
	void display()
	{		
		System.out.println("from " + firstRow + ", " + firstColumn + " to " + lastRow + ", " + lastColumn);
		
		for(int row = firstRow; row <= lastRow; row++) {
			HSSFRow r = sheet.getRow(row);
			for(short column = firstColumn; column <= lastColumn; column++) {
				HSSFCell cell = r.getCell(column);
				
//				System.out.println("  !" + row + ", " + column + ": " + HSSF.getFloat(cell));
				System.out.println("  !" + row + ", " + column + ": " + cell.toString());
			}
		}
	}
	
	public float[] toFloatArray()
	{
		float[] result = new float[(lastRow-firstRow+1)*(lastColumn-firstColumn+1)];
		int index = 0;
		
		for(int row = firstRow; row <= lastRow; row++) {
			HSSFRow r = sheet.getRow(row);
			for(short column = firstColumn; column <= lastColumn; column++) {
				HSSFCell cell = r.getCell(column);
				result[index++] = HSSFUtils.getFloat(cell);
			}
		}
		return result;
	}
	
	public float[][] toFloatArrayArray()
	{
		float[][] result = new float[lastRow-firstRow+1][];
		int rowIndex = 0;
		
		for(int row = firstRow; row <= lastRow; row++) {
			float[] resultRow = new float[lastColumn-firstColumn+1];
			int columnIndex = 0;
			HSSFRow r = sheet.getRow(row);
			for(short column = firstColumn; column <= lastColumn; column++) {
				HSSFCell cell = r.getCell(column);
				resultRow[columnIndex] = HSSFUtils.getFloat(cell);
				columnIndex++;
			}
			result[rowIndex] = resultRow;
			rowIndex++;
		}
		return result;
	}
	
	public static float[][] transpose(float[][] input)
	{
		int rows = input.length;
		int columns = input[0].length;
		
		float[][] output = new float[rows][columns];
		output = new float[columns][rows];
	    for (int iR = 0; iR < rows; iR++) {
	        for (int iC = 0; iC < columns; iC++) {
	        	output[iC][iR] = input[iR][iC];
	        }
	    }

	    return output;
	}
	
	public double[] toDoubleArray()
	{
		double[] result = new double[(lastRow-firstRow+1)*(lastColumn-firstColumn+1)];
		int index = 0;
		
		for(int row = firstRow; row <= lastRow; row++) {
			HSSFRow r = sheet.getRow(row);
			for(short column = firstColumn; column <= lastColumn; column++) {
				HSSFCell cell = r.getCell(column);
				result[index++] = HSSFUtils.getDouble(cell);
			}
		}
		return result;
	}
	
	double[][] toDoubleArrayArray()
	{
		double[][] result = new double[lastRow-firstRow+1][];
		int rowIndex = 0;
		
		for(int row = firstRow; row <= lastRow; row++) {
			double[] resultRow = new double[lastColumn-firstColumn+1];
			int columnIndex = 0;
			HSSFRow r = sheet.getRow(row);
			for(short column = firstColumn; column <= lastColumn; column++) {
				HSSFCell cell = r.getCell(column);
				resultRow[columnIndex] = HSSFUtils.getFloat(cell);
				columnIndex++;
			}
			result[rowIndex] = resultRow;
			rowIndex++;
		}
		return result;
	}

	public String[] toStringArray()
	{
		String[] result = new String[(lastRow-firstRow+1)*(lastColumn-firstColumn+1)];
		int index = 0;
		
		for(int row = firstRow; row <= lastRow; row++) {
			HSSFRow r = sheet.getRow(row);
			for(short column = firstColumn; column <= lastColumn; column++) {
				HSSFCell cell = r.getCell(column);
				result[index++] = cell.toString();
			}
		}
		return result;
	}
	
	String[][] toStringArrayArray()
	{
		String[][] result = new String[lastRow-firstRow+1][];
		int rowIndex = 0;
		
		for(int row = firstRow; row <= lastRow; row++) {
			String[] resultRow = new String[lastColumn-firstColumn+1];
			int columnIndex = 0;
			HSSFRow r = sheet.getRow(row);
			for(short column = firstColumn; column <= lastColumn; column++) {
				HSSFCell cell = r.getCell(column);
				resultRow[columnIndex] = cell.toString();
				columnIndex++;
			}
			result[rowIndex] = resultRow;
			rowIndex++;
		}
		return result;
	}

}
