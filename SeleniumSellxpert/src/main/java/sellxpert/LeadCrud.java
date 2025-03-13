package sellxpert;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LeadCrud {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		try {
			WebDriver driver = new ChromeDriver();
		driver.get("https://sellxpert.in/Home/Dashboard");
		driver.findElement(By.id("btn_login")).click();
		
		driver.findElement(By.id("email_id")).sendKeys("vinay@gmail.com");
		driver.findElement(By.id("password")).sendKeys("12345");
		driver.findElement(By.id("btn_login")).click();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement Customer = driver.findElement(By.linkText("Customer"));
	        Customer.click();
    /*WebElement LeadWidget = driver.findElement(By.xpath("/html/body/aside/div/div[1]/div[2]/div/div[2]/div[1]/div/div/div[1]/div[4]/div/a[1]/h6"));
    LeadWidget.click();
    WebElement LeadAdd = driver.findElement(By.xpath("//*[@id=\"list\"]/div/div[1]/div/div[2]/div/div[1]/a"));
    LeadAdd.click();*/
   
WebElement customeradd =  driver.findElement(By.xpath("/html/body/aside/div/div[1]/div[2]/div/div[2]/div/div/div[1]/div/div[2]/a[1]"));
customeradd.click();
driver.findElement(By.name("full_name")).sendKeys("test lead");
driver.findElement(By.name("phone1")).sendKeys("9087654326");
JavascriptExecutor js = (JavascriptExecutor) driver;
js.executeScript("window.scrollBy(0,500)"); // Scroll down by 500 pixels

WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"quickForm_customer\"]/div/div[2]/div[6]/div[2]/div/div/button")));
button.click();
driver.navigate().back();

driver.findElement(By.xpath("//*[@id=\"associateCard\"]/div[2]/div/div/div[2]/div[2]/div/div[2]/a")).click();
WebElement dropdown = driver.findElement(By.className("select2-selection"));
dropdown.click();
Thread.sleep(2000);
WebElement option = driver.findElement(By.xpath("//li[contains(text(),'Google')]"));
option.click();
WebElement selectedValue = driver.findElement(By.className("select2-selection__rendered"));
System.out.println("Selected Value: " + selectedValue.getText());



    
       

        // Wait for the button to be clickable
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"quickForm\"]/div[2]/button")));

        // Scroll into view if necessary
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveButton);

        // Click the button
        saveButton.click();

    } catch (Exception e) {
        e.printStackTrace();
    
}

	}
}




	


