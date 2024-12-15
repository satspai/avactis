package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class DataProviders {
	@DataProvider(name = "dataFromXLS")
	public Object[][] provideDataFromXLS() {
		Object[][] dataFromXLS = readDataFromXLS("src/test/resources/data/dataxls1.xls", "login", "Marker");
		return dataFromXLS;
	}

	@DataProvider(name = "dataFromXLSX")
	public Object[][] provideDataFromXLSX() throws IOException {
		Object[][] dataFromXLS = readDataFromXLSX("src/test/resources/data/dataxls.xlsx");
		return dataFromXLS;
	}

	@DataProvider(name = "dataFromCSV")
	public Iterator providesDataFromCSV() throws IOException {
		Collection<String[]> returnData = getTestDataFromCSV("src/test/resources/data/datacsv.csv");
		return returnData.iterator();
	}

	@DataProvider(name = "dataFromOpenCSV")
	public Iterator providesDataFromOpenCSV() throws IOException, CsvException {
		Collection<String[]> returnData = getTestDataFromOpenCsv("src/test/resources/data/datacsv.csv");
		return returnData.iterator();
	}

	@DataProvider(name = "dataFromDB")
	public Iterator providesDataFromDB() throws Exception {
		Collection<String[]> returnData = getTestDataFromDB("Select * from customer_details");
		return returnData.iterator();
	}


	public static Collection<String[]> getTestDataFromCSV(String fileName) throws IOException {
		List<String[]> records = new ArrayList<>(); // arraylist of array of string is two dim arraylist

		String record;
		// FileReader is meant for reading streams of characters(reading text files)
		// FileReader myReader = new FileReader(fileName);
		// A BufferedReader object takes a FileReader object as
		// an input which contains all the necessary information
		// about the text file that needs to be read.
		BufferedReader file = new BufferedReader(new FileReader(fileName));
		while ((record = file.readLine()) != null) {// record = "niche,thyself"
			String fields[] = record.split(",");// split() method is inside String class
			// fields[0] = "niche", and fields[1] = "thyself"
			records.add(fields);
		}
		file.close();
		return records;
	}

	/*
	 * Logic
	 * - reading the file - Workbook
	 * - Reading a particular sheet - Search for the first occurence of marker "NTStartEnd". Note down the rows and
	 * columns(1,2)
	 * - Search for the next offcurenence of marker "NTStartEnd". Note down the rows and columns (4,7)
	 * - subtract second occured row from the first - (4-1-1, 7-2-1) = (2,4)
	 * - Create two dim array of String which is of size [4,2]
	 * - Now read one cell at a time from XLS and copy into the array.
	 * b[0][0] = "stc123", b[0][1] = "12345"
	 * b[1][0] = testing, b[1][1] = "testing"
	 * ....
	 *
	 * - Return the array to data provider
	 *
	 */
	public String[][] readDataFromXLS(String xlFilePath, String sheetName, String marker) {
		String[][] tabArray = null;
		try {
			Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
			// Workbook class is provied by jxl.jar
			// WebDriver provided by Selenium
			// File is class provided by Java to read a physical file
			Sheet sheet = workbook.getSheet(sheetName);
			Cell tableStart = sheet.findCell(marker);

			int startRow, startCol, endRow, endCol, ci, cj;

			startRow = tableStart.getRow();// 2
			startCol = tableStart.getColumn();// 1

			Cell tableEnd = sheet.findCell(marker, startCol + 1, startRow + 1, 100, 64000, false);

			endRow = tableEnd.getRow();// 7
			endCol = tableEnd.getColumn();// 5
			System.out.println("startRow=" + startRow + ", endRow=" + endRow + ", " + "startCol=" + startCol
					+ ", endCol=" + endCol);
			tabArray = new String[endRow - startRow - 1][endCol - startCol - 1];// 4, 3
			ci = 0; // array row
			// ci=0,i=3, j=3,cj=1
			for (int i = startRow + 1; i < endRow; i++, ci++) {// i represents
																// xls row
				cj = 0;// array column
				for (int j = startCol + 1; j < endCol; j++, cj++) {// j
																	// represents
																	// xls
																	// column
					tabArray[ci][cj] = sheet.getCell(j, i).getContents();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("The file you specified does not exist");
		} catch (Exception e) {
			System.out.println("Please check if file path, sheet name and tag name are correct");
			e.toString();
		}
		return (tabArray);
	}

	public static Collection<String[]> getTestDataFromDB(String sqlQuery) throws Exception {
		ArrayList<String[]> records = new ArrayList<>();

		/*
		 * Windows - You need to install the MySQL DB - Login credentials - You need to
		 * know - create a DB - You need to create tables in your DB
		 *
		 * Java ( - You need DB driver - jar file - Load the DB driver - Connect to the
		 * DB
		 *
		 *
		 * Common for all the DBs - Fire the SQL query - Read the result and pass it to
		 * Data provder in an two dim array
		 */

		// Connection, DriverManager,Statement, ResultSet, ResultSetMetaData

		/*
		 * Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");//Driver for MSAccess String
		 * String myDB = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=" + mdbFile;
		 * //Database String Connection conn = DriverManager.getConnection(myDB, "", "");
		 */

		Class.forName("com.mysql.jdbc.Driver"); // This similar to providing chromedriver.exe, geckodriver.exe to your
												// Selenium code.
		String myDB = "jdbc:mysql://localhost:3306/test"; // DB Connection String
		// String myDB = "jdbc:mysql://localhost:3306/paratus";//paratus is a DB name
		// which one needs to create
		// conn1=DriverManager.getConnection(myDB,"mysql","mysql");
		Connection conn = DriverManager.getConnection(myDB, "root", "");

		Statement stmt = null; // SQL Statements

		ResultSet rs = null; // DB data are returned inside ResultSet object

		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		//5
		rs = stmt.executeQuery(sqlQuery);// 5, 2
		ResultSetMetaData rsMetaData = rs.getMetaData();

		int cols = rsMetaData.getColumnCount();

		while (rs.next()) {

			String fields[] = new String[cols]; // creating array of columns
			int col = 0;
			for (int colIdx = 1; colIdx <= cols; colIdx++) {
				fields[col] = rs.getString(colIdx);
				col++;
			}
			records.add(fields);
		}
		rs.close();
		stmt.close();
		conn.close();

		return records;
	}

	public  String[][] readDataFromXLSX(String filePath) throws IOException {
        // Create a FileInputStream to read the Excel file
        FileInputStream file = new FileInputStream(new File(filePath));

        // Create a Workbook object to hold the Excel file data
        org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(file);

        // Get the first sheet (index 0)
        org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);

        // Get the number of rows and columns in the sheet
        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells(); // Assuming the first row has the maximum number of columns

        // Create a 2D array to store the data
        String[][] data = new String[rowCount][colCount];

        // Iterate over the rows and cells, and fill the array
        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i);

            // Iterate over the cells of the row
            for (int j = 0; j < colCount; j++) {
                org.apache.poi.ss.usermodel.Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                // Handle the cell value based on the type
                switch (cell.getCellType()) {
                    case STRING:
                        data[i][j] = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        data[i][j] = String.valueOf(cell.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        data[i][j] = String.valueOf(cell.getBooleanCellValue());
                        break;
                    default:
                        data[i][j] = "";
                }
            }
        }

        // Close the workbook and file input stream
        workbook.close();
        file.close();

        return data;
    }


	public static List<String[]> getTestDataFromOpenCsv(String filePath) throws CsvException {
        List<String[]> csvData = null;
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            csvData = reader.readAll(); // Read all rows from the CSV file
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvData;
    }

}
