package utilities;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataReader {

    public static HashMap<String, String> storeValues = new HashMap();

    public static List<HashMap<String, String>> data(String filepath, String sheetName) {

        List<HashMap<String, String>> excelData = new ArrayList<>();

        try {
            FileInputStream fs = new FileInputStream(filepath);
            XSSFWorkbook workbook = new XSSFWorkbook(fs);
            XSSFSheet sheet = workbook.getSheet(sheetName);
            Row HeaderRow = sheet.getRow(0);
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                if (sheet.getRow(i) != null) {
                    Row currentRow = sheet.getRow(i);
                    LinkedHashMap<String, String> currentHash = new LinkedHashMap<String, String>();
                    for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++) {
                        Cell currentCell = currentRow.getCell(j);
                        //System.out.println("value of Current cell >>>>" + currentCell);
                        switch (currentCell.getCellType()) {
                            case STRING:
                                currentHash.put(HeaderRow.getCell(j).getStringCellValue(), currentCell.getStringCellValue());
                                break;
                            case NUMERIC:
                                //System.out.println("Cell Type >>>>>>"+HeaderRow.getCell(j).getCellType());
                                currentHash.put(HeaderRow.getCell(j).getStringCellValue(), String.valueOf((int)currentCell.getNumericCellValue()));
                                break;
                        }
                    }
                    excelData.add(currentHash);
                }
                fs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return excelData;
    }
}
