package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	 // DataProvider 1 - Login Data
    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {

        // Path of Excel file
        String path = ".\\testData\\LoginData.xlsx"; // Taking excel file from the user directory test data folder

        // Created object of ExcelUtility
        ExcelUtility xlutil = new ExcelUtility(path);

        // Get row & column count
        int totalrows = xlutil.getRowCount("Sheet1");
        int totalcols = xlutil.getCellCount("Sheet1", 1);// counted columns from the first row

        // Created 2D Array
        String logindata[][] = new String[totalrows][totalcols];

        // Read data from Excel and store in array
        for (int i = 1; i <= totalrows; i++) {
            for (int j = 0; j < totalcols; j++) {
                logindata[i - 1][j] = xlutil.getCellData("Sheet1", i, j);// i - 1 Because Array index will start from zero
            }
        }

        return logindata; // returning 2D array
    }
    
    // Data Provider 2
    // Data Provider 3
    // 

}
