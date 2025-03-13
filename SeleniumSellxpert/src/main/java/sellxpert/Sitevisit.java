package sellxpert;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Sitevisit {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://sellxpert.in/Home/Dashboard");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // Login
            driver.findElement(By.id("btn_login")).click();
            driver.findElement(By.id("email_id")).sendKeys("vinay@gmail.com");
            driver.findElement(By.id("password")).sendKeys("12345");
            driver.findElement(By.id("btn_login")).click();

            // Navigate to Site visit Form
            WebElement SitevisitWidget = driver.findElement(By.xpath("/html/body/aside/div/div[1]/div[2]/div/div[2]/div[1]/div/div/div[1]/div[5]/div/a[1]/h6"));
            SitevisitWidget.click();
            WebElement SitevisitAdd = driver.findElement(By.xpath("//*[@id=\"list\"]/div/div[1]/div/div[2]/div/a[1]"));
            SitevisitAdd.click();

            // Select Dropdown Option
            WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"select2-source_id-container\"]"));
            dropdown.click();
            Thread.sleep(2000);
            WebElement option = driver.findElement(By.xpath("//li[contains(text(),'Google')]"));
            option.click();
            WebElement selectedValue = driver.findElement(By.className("select2-selection__rendered"));
            System.out.println("Selected Value: " + selectedValue.getText());

            // Add Customer
            WebElement customerAdd = driver.findElement(By.xpath("//*[@id=\"custom-tabs-four-personaldetail\"]/div/div[5]/div/div/div/a"));
            customerAdd.click();
            driver.findElement(By.name("full_name")).sendKeys("test");
            driver.findElement(By.xpath("//*[@id=\"phone1\"]")).sendKeys("9087657890");
            driver.findElement(By.xpath("//*[@id=\"quickForm_customer\"]/div/div[2]/div[6]/div[2]/div/div/button")).click();

            // Scroll Down
            //js.executeScript("window.scrollBy(0,1000)");

            // Wait for Save Button
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='quickForm']/div[2]/button")));
            
            // Scroll into view & Click
            js.executeScript("arguments[0].scrollIntoView(true);", saveButton);
            Thread.sleep(1000); // Allow time for any UI transitions

            // Click the button using JavaScript (if needed)
            js.executeScript("arguments[0].click();", saveButton);

            System.out.println("Save button clicked successfully!");
            //Click Lead Edit Button
            WebElement EditButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Edit']")));
            js.executeScript("arguments[0].scrollIntoView(true);", EditButton);
            Thread.sleep(1000); // Allow time for any UI transitions
            js.executeScript("arguments[0].click();", EditButton);

            System.out.println("Edit button clicked successfully!");
            //Click Update Button inedit form
            WebElement AfterEditSaveButton= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"quickForm\"]/div[2]/button")));
            js.executeScript("arguments[0].scrollIntoView(true);", AfterEditSaveButton);
            Thread.sleep(1000); // Allow time for any UI transitions
            js.executeScript("arguments[0].click();", AfterEditSaveButton);
            
            System.out.println("AfterEditSaveButton!");
            //Click Delete Button
            WebElement DeleteButton= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"lead_table\"]/tbody/tr[1]/td/div/div/div/div[2]/div[8]/div[2]/div/div[3]/a/p")));
            js.executeScript("arguments[0].scrollIntoView(true);", DeleteButton);
            Thread.sleep(1000); // Allow time for any UI transitions
            js.executeScript("arguments[0].click();", DeleteButton);

            System.out.println("Delete Button!");
            
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            
        	 
           
        }
    }
	
		
		

	}


