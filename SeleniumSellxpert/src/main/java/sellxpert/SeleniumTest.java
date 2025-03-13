package sellxpert;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.List;
public class SeleniumTest {

	public static void main(String[] args) throws IOException {
		
		        // Load JSON file
		        ObjectMapper objectMapper = new ObjectMapper();
		        userlist  usersList = objectMapper.readValue(new File("E://users.json"), userlist.class);

		        // Set up WebDriver
		        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe"); // Set correct path
		        WebDriver driver = new ChromeDriver();

		        // Open the form page (update the URL)
		        driver.get("https://sellxpert.in/Home/Dashboard");

		        // Loop through each user and fill the form
		        for (User user : usersList.users) {
		            WebElement nameField = driver.findElement(By.id("full_name")); // Update with actual field ID
		            WebElement mobileField = driver.findElement(By.id("phone1")); // Update with actual field ID
		            WebElement submitButton = driver.findElement(By.id("submit")); // Update with actual field ID

		            // Fill the form with user data
		            nameField.clear();
		            nameField.sendKeys(user.getName());

		            mobileField.clear();
		            mobileField.sendKeys(user.getMobile());

		            // Submit the form
		            submitButton.click();

		            // Wait before the next iteration (optional)
		            try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
		        }

		        // Close browser
		        driver.quit();
		    }
		

	}


