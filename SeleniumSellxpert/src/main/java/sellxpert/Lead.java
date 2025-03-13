package sellxpert;
import java.time.Duration;
import java.util.List;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

import java.io.IOException;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

public class Lead {
	public static void main(String[] args)throws InterruptedException{
	
		try {
		ObjectMapper objectMapper = new ObjectMapper();
		
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       
        
        
       
            driver.get("https://sellxpert.in/Users");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            
            // Login
            driver.findElement(By.id("btn_login")).click();
            driver.findElement(By.id("email_id")).sendKeys("vinay@gmail.com");
            driver.findElement(By.id("password")).sendKeys("12345");
            driver.findElement(By.id("btn_login")).click();

            
            // Navigate to Lead Form
            WebElement leadWidget = driver.findElement(By.xpath("/html/body/aside/div/div[1]/div[2]/div/div[2]/div[1]/div/div/div[1]/div[4]/div/a[1]/h6"));
            leadWidget.click();
            WebElement leadAdd = driver.findElement(By.xpath("//*[@id='list']/div/div[1]/div/div[2]/div/div[1]/a"));
            leadAdd.click();

            // Select Dropdown Option
            WebElement dropdown = driver.findElement(By.className("select2-selection"));
            dropdown.click();
            Thread.sleep(2000);
            WebElement option = driver.findElement(By.xpath("//li[contains(text(),'FACEBOOK')]"));
            option.click();
            WebElement selectedValue = driver.findElement(By.className("select2-selection__rendered"));
            System.out.println("Selected Value: " + selectedValue.getText());
           
            // Add Customer
            //WebElement customerAdd = driver.findElement(By.xpath("//a[@data-target=\"#modal-customer\"]"));
            //customerAdd.click();
            
            User[] users = objectMapper.readValue(new File("C:\\users.json"), User[].class);
           
         // Add Customers from JSON Data
            for (User user : users) {
                WebElement customerAdd = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-target=\"#modal-customer\"]")));
                customerAdd.click();

                // Locate elements inside loop for each iteration
                WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("full_name")));
                nameField.clear();
                nameField.sendKeys(user.getName());

                WebElement phoneField = driver.findElement(By.name("phone1"));
                phoneField.clear();
                phoneField.sendKeys(user.getMobile());

                driver.findElement(By.xpath("//*[@id='quickForm_customer']/div/div[2]/div[6]/div[2]/div/div/button")).click();

                // Handle mobile number validation
                try {
                    WebElement errorMsg = driver.findElement(By.xpath("//*[@id=\"quickForm_customer\"]/div/div[2]/div[6]/div[2]/div/div/button")); // Replace with actual error message locator
                    if (errorMsg.isDisplayed()) {
                        System.out.println("Validation Failed for Mobile Number: " + user.getMobile() + " - Error: " + errorMsg.getText());
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Successfully Added Customer: " + user.getName() + " - " + user.getMobile());
                }
                // Wait for modal to close before continuing
                  // Delay for UI update
                //WebElement customerAdd1 = driver.findElement(By.xpath("//a[@data-target=\"#modal-customer\"]"));
                //customerAdd1.click();
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='modal-content']")));
                Thread.sleep(5000);
                }

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
            driver.quit();
            
            } catch (Exception e) {
            e.printStackTrace();
        } finally {
            
        	 
           
        }
    }
	
	}	

	




		
		




