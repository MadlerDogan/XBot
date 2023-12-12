package utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtilities {
    private Workbook workbook;
    private Sheet sheet;
    private String path;

    // create a constructor
    public ExcelUtilities(String path, String sheetName) {

        this.path = path;

        try {
            FileInputStream fis = new FileInputStream(path);
            workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheet(sheetName);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // get text with line and colunm numbers
    public String getCellData(int rowNumber, int columnNumber) {

        Cell cell = sheet.getRow(rowNumber).getCell(columnNumber);
        return cell.toString();
    }

    //get line number
    public int rowCount() {
        return sheet.getLastRowNum();
    }

    //Get column number
    public int columnCount() {
        return sheet.getRow(0).getLastCellNum();
    }

    //Get data with array
    public String[][] getDataArray() {
        String[][] data = new String[rowCount()][columnCount()];
        for (int i = 1; i <=rowCount() ; i++) {//length 4
            for (int j = 0; j < columnCount(); j++) {// length 2
                String value = getCellData(i,j);
           //     System.out.println("Value  "+value);
                data[i-1][j]=value;
            }
        }
        return data;
    }




}
