package SetupsAndPages;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;


import java.io.File;
import java.io.FileInputStream;

public class ExcelDataConfig {

    XSSFWorkbook wb;
    XSSFSheet sheet1;
    public XSSFCell cell;


    public ExcelDataConfig(String excelPath) {


        try {
            File src = new File(excelPath);
            FileInputStream fis = new FileInputStream(src);
            wb = new XSSFWorkbook(fis);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    public String getData(String sheetName, int row, int column) {

        String dataRestored;
        sheet1 = wb.getSheet(sheetName);
        cell = sheet1.getRow(row).getCell(column);
        cell.getCellType();

        if (cell.getCellType() == CellType.STRING) {
            dataRestored = cell.getStringCellValue();
            return dataRestored;
        }
        else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
            int convert = (int) cell.getNumericCellValue();
            dataRestored = String.valueOf(convert);
            return dataRestored;
        }
        else if (cell.getCellType() == CellType.BLANK) {
            return "Blank";
        }
        else {
            return String.valueOf(cell.getBooleanCellValue());}
    }

    }









