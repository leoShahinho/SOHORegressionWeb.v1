package SetupsAndPages;

import org.apache.poi.xssf.usermodel.XSSFCell;

public class ReadExcelData {

    public static void main(String[] args) {

        ExcelDataConfig excel = new ExcelDataConfig("C:\\Automation\\ChatBot - Copy\\Treceability and data.xlsx");
        String data = excel.getData("LoginCredentials",15,1);
        System.out.println(data);

    }
}
