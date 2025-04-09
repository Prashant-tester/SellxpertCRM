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



public class Lead {
	public static void main(String[] args)throws InterruptedException{
	
	
		    try {
        		ObjectMapper objectMapper = new ObjectMapper();
        		
        		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        		ChromeOptions options = new ChromeOptions();
        		options.addArguments("--disable-gpu"); // Sometimes needed for headless mode
        		options.addArguments("--remote-allow-origins=*");
                WebDriver driver = new ChromeDriver();
                JavascriptExecutor js = (JavascriptExecutor) driver;
                
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
                    driver.get("https://sellxpert.in/Users");
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    
                    // Login (Login page )
                    driver.findElement(By.id("btn_login")).click();
                    driver.findElement(By.id("email_id")).sendKeys("vinay07@gmail.com");
                    driver.findElement(By.id("password")).sendKeys("12345");
                    driver.findElement(By.id("btn_login")).click();
        
                    
                    // Navigate to Lead Form
                    WebElement leadWidget = driver.findElement(By.xpath("/html/body/aside/div/div[1]/div[2]/div/div[2]/div[1]/div/div/div[1]/div[4]/div/a[1]/h6"));
                    leadWidget.click();
                    
                    WebElement leadAdd = driver.findElement(By.xpath("//*[@id='list']/div/div[1]/div/div[2]/div/div[1]/a"));
                    leadAdd.click();
                    // Test case 1 (Direct Save button per click krake check karna Required field)
                 
                    WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='quickForm']/div[2]/button")));
                    
                    // Scroll into view & Click
                    js.executeScript("arguments[0].scrollIntoView(true);", saveButton);
                    Thread.sleep(1000); // Allow time for any UI transitions
        
                    // Click the button using JavaScript (if needed)
                    js.executeScript("arguments[0].click();", saveButton);
                  
                    System.out.println("Please fill Required field!");
                    // Test Case 2 (Customer ka data json file se lena and save krana )
                    User[] users = objectMapper.readValue(new File("C:\\users.json"), User[].class);
                    int userIndex = 0;
                    boolean customerSaved = false;
                         while (userIndex < users.length && !customerSaved) {
                        User currentUser = users[userIndex];
                    User firstUser = users[0]; // Pehla user uthana
                    WebElement Customeradd = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-target=\"#modal-customer\"]")));
             	   js.executeScript("arguments[0].scrollIntoView(true);", Customeradd);
                    Thread.sleep(1000);
                    js.executeScript("arguments[0].click();", Customeradd);
                    
                    
                    // Locate elements inside loop for each iteration
                    WebElement nameField = driver.findElement(By.name("full_name"));
                   
                    nameField.sendKeys(firstUser.getName());
        
                    WebElement phoneField = driver.findElement(By.name("phone1"));
                 
                    phoneField.sendKeys(firstUser.getMobile());
                    
                    WebElement addressField = driver.findElement(By.name("address"));
                 
                    addressField.sendKeys(firstUser.getaddress());
                    
                    WebElement adharField = driver.findElement(By.name("Aadhar"));
                 
                    adharField.sendKeys(firstUser.getadhar());
                    
                    WebElement panField = driver.findElement(By.name("pan_no"));
                   
                    panField.sendKeys(firstUser.getpan());
                    
                    
                    
        
                    driver.findElement(By.xpath("//*[@id='quickForm_customer']/div/div[2]/div[6]/div[2]/div/div/button")).click();
                    System.out.println("Customer Saved !");
        	            try {
        	                WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(3));
        	
        	                // Wait for SweetAlert to appear
        	                WebElement sweetAlert = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal2-popup")));
        	                
        	                // Extract message (optional)
        	                String alertText = sweetAlert.getText();
        	                System.out.println("SweetAlert Message: " + alertText);
        	
        	                // Click the "OK" or "Confirm" button (adjust selector if needed)
        	                WebElement okButton = wait1.until(ExpectedConditions.elementToBeClickable(By.className("swal2-confirm")));
        	                okButton.click();
        	                
        	                if (alertText.toLowerCase().contains("already exists") || alertText.toLowerCase().contains("mobile number")) {
        	                    userIndex++; // Move to next user
        	                    System.out.println("Duplicate user found, trying next...");
        	                    	
        	                }else
        	                    {
        	                    	customerSaved = true;
        	                    }
        	                
        	            }catch (TimeoutException e){
        	            	customerSaved = true;
        	            }
        			
        	            
        	            // Test Case 3 (Customer save karne ke baad with out source ke sath)
        	               WebDriverWait wait12 = new WebDriverWait(driver, Duration.ofSeconds(10));
        	               WebElement saveButton11 = wait12.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='quickForm']/div[2]/button")));
        	               js.executeScript("arguments[0].scrollIntoView(true);", saveButton11);
        	               Thread.sleep(1000); // Allow time for any UI transitions
        	
        	               saveButton11.click();
        	              System.out.println("Condition check Without Source!");
        	            // Test Case4 (Select Source From Dropdown Option)
        	            WebElement SelectSource = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"select2-source_id-container\"]")));
        	            js.executeScript("arguments[0].scrollIntoView(true);", SelectSource);
        	            Thread.sleep(1000);
        	            
        	            js.executeScript("arguments[0].click();", SelectSource);
        	            
        	            WebElement dropdown = driver.findElement(By.className("select2-selection"));
        	            dropdown.click();
        	            Thread.sleep(2000);
        	            WebElement option = driver.findElement(By.xpath("//li[contains(text(),'FACEBOOK')]"));
        	            option.click();
        	            WebElement selectedValue = driver.findElement(By.className("select2-selection__rendered"));
        	            System.out.println("Selected Value: " + selectedValue.getText());
        	            /*User[] users1 = objectMapper.readValue(new File("C:\\users.json"), User[].class);
        	            
        	         // Add Customers from JSON Data
        	            for (User user : users1) {
        	                WebElement customerAdd = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-target=\"#modal-customer\"]")));
        	                customerAdd.click();
        	
        	                // Locate elements inside loop for each iteration
        	                WebElement nameField1 = driver.findElement(By.name("full_name"));
        	                nameField1.clear();
        	                nameField1.sendKeys(user.getName());
        	
        	                WebElement phoneField1 = driver.findElement(By.name("phone1"));
        	                phoneField1.clear();
        	                phoneField1.sendKeys(user.getMobile());
        	                
        	                WebElement addressField1 = driver.findElement(By.name("address"));
        	                addressField1.clear();
        	                addressField1.sendKeys(user.getaddress());
        	                
        	                WebElement adharField1 = driver.findElement(By.name("Aadhar"));
        	                adharField1.clear();
        	                adharField1.sendKeys(user.getadhar());
        	                
        	                WebElement panField1 = driver.findElement(By.name("pan_no"));
        	                panField1.clear();
        	                panField1.sendKeys(user.getpan());
        	                
        	                
        	                
        	
        	                driver.findElement(By.xpath("//*[@id='quickForm_customer']/div/div[2]/div[6]/div[2]/div/div/button")).click();
        	
        	                // Handle mobile number validation
        	               try {
        	                    WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(3));
        	
        	                    // Wait for SweetAlert to appear
        	                    WebElement sweetAlert = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal2-popup")));
        	                    
        	                    // Extract message (optional)
        	                    String alertText = sweetAlert.getText();
        	                    System.out.println("SweetAlert Message: " + alertText);
        	
        	                    // Click the "OK" or "Confirm" button (adjust selector if needed)
        	                    WebElement okButton = wait1.until(ExpectedConditions.elementToBeClickable(By.className("swal2-confirm")));
        	                    okButton.click();
        	
        	                    System.out.println("SweetAlert handled successfully.");
        	                } catch (TimeoutException e) {
        	                    System.out.println("SweetAlert did not appear.");
        	                }
        	
        	                // Wait for modal to close before continuing
        	                  // Delay for UI update
        	                WebElement customerAdd1 = driver.findElement(By.xpath("//a[@data-target=\"#modal-customer\"]"));
        	                customerAdd1.click();
        	                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='modal-content']")));
        	                Thread.sleep(1000);*/
        	                
        	
        	             // Scroll Down
        	            //js.executeScript("window.scrollBy(0,1000)");
        	
        	            // Wait for Save Button
        	            WebElement saveButton2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='quickForm']/div[2]/button")));
        	            
        	            // Scroll into view & Click
        	            js.executeScript("arguments[0].scrollIntoView(true);", saveButton2);
        	            Thread.sleep(1000); // Allow time for any UI transitions
        	
        	            // Click the button using JavaScript (if needed)
        	            js.executeScript("arguments[0].click();", saveButton2);
        	
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
        	            WebElement DeleteButton= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"lead_table\"]/tbody/tr[1]/td/div/div/div/div[2]/div[7]/div[2]/div/div[3]/a/p")));
        	            js.executeScript("arguments[0].scrollIntoView(true);", DeleteButton);
        	            Thread.sleep(1000); // Allow time for any UI transitions
        	            js.executeScript("arguments[0].click();", DeleteButton);
        	
        	            
        	            
        	            System.out.println("Delete Button!");
        	            driver.quit();
                    
                    	
                    
                    
                    }
            
            }
            catch(Exception e){            	           
            e.printStackTrace();
            }
        	finally {
            	
            }           		

			
		}
	}
