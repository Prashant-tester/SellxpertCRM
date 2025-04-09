package sellxpert;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class NewLead {
	public static void main(String[] args)throws InterruptedException {	
		System.out.println("Hello");
		//Initialize web driver
		//Read excel sheet
		//Run test case
		try {
			WebDriver driver = initializeWebDriver();
			Sheet sheet = loadExcelSheet();
			if(sheet != null) {
				runTestCases(sheet,driver);
			} else {
				System.out.println("Excel sheet not found");
			}
		} catch (Exception e) {
			
		}
    }

	private static void runTestCases(Sheet sheet,WebDriver driver) {
		boolean isFirstRow = true;
		for (Row row: sheet){
			if (isFirstRow) {
				isFirstRow = false;
				continue;
				
			}
			
			String ID = getCellValue(row.getCell(0));
			String Description	 = getCellValue(row.getCell(1));
			String Expectedresult = getCellValue(row.getCell(2));
		}
	}

	
	

	private static String getCellValue(Cell cell) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Sheet loadExcelSheet() {
		try {
			String filePath = System.getenv().getOrDefault("file.path", "C:\\Users\\HP\\Documents\\test.xlsx");
			FileInputStream file = new FileInputStream(new File(filePath));
			Workbook workbook = new XSSFWorkbook(file);
			return workbook.getSheetAt(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	private static WebDriver initializeWebDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-gpu"); // Sometimes needed for headless mode
		options.addArguments("--remote-allow-origins=*");
        return new ChromeDriver();
	}
}	

	




		
		




