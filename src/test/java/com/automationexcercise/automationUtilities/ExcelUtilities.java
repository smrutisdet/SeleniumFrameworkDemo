package com.automationexcercise.automationUtilities;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

public class ExcelUtilities {
    @DataProvider(name="loginData")
    public Object[][] readExcelSheetData(Method name) throws IOException {
        File excelFilePath=new File(System.getProperty("user.dir")+"/src/test/resources/testData/TestData.xlsx");
        FileInputStream fis= new FileInputStream(excelFilePath);
        Workbook workbook= WorkbookFactory.create(fis);
        Sheet sheet =workbook.getSheet(name.getName());
        //get row count
        int rowsCount=sheet.getLastRowNum();
        System.out.println("Total no of rows are:"+rowsCount);
        //get total no of columns
        Row row =sheet.getRow(0);
        int columnCount=row.getLastCellNum();
        System.out.println("Total no of columns  "+columnCount);
        Object[][]data=new Object[rowsCount][columnCount];
        DataFormatter formatter=new DataFormatter();
        for(int i=1;i<=rowsCount;i++){
            for(int j=0;j<columnCount;j++){
            data[i-1][j]=formatter.formatCellValue(sheet.getRow(i).getCell(j));
                System.out.println("row number "+(i-1)+" Column number "+j+ " is : "+data[i-1][j]);
            }
        }
        return data;
    }
}
