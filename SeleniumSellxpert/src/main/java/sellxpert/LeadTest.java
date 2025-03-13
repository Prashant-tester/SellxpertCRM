package sellxpert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class LeadTest {
    WebDriver driver;
    JavascriptExecutor js;
    WebDriverWait wait;
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("TestReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testLeadCreation() throws InterruptedException {
        test = extent.createTest("Lead Creation Test").assignAuthor("QA Team").assignCategory("Regression Test");

        // Step 1: Login
        driver.get("https://sellxpert.in/Home/Dashboard");
        driver.findElement(By.id("btn_login")).click();
        driver.findElement(By.id("email_id")).sendKeys("vinay@gmail.com");
        driver.findElement(By.id("password")).sendKeys("12345");
        driver.findElement(By.id("btn_login")).click();
        test.pass("Login successful");
        Thread.sleep(500);

        // Step 2: Navigate to Leads
        WebElement leadWidget = driver.findElement(By.xpath("/html/body/aside/div/div[1]/div[2]/div/div[2]/div[1]/div/div/div[1]/div[4]/div/a[1]/h6"));
        leadWidget.click();
        Thread.sleep(500);
        test.pass("Navigated to Lead Widget");
        

        WebElement leadAdd = driver.findElement(By.xpath("//*[@id='list']/div/div[1]/div/div[2]/div/div[1]/a"));
        leadAdd.click();
        Thread.sleep(500);
        test.pass("Clicked on Add Lead");

        // Step 3: Select Dropdown Option
        WebElement dropdown = driver.findElement(By.className("select2-selection"));
        dropdown.click();
        Thread.sleep(2000);
        WebElement option = driver.findElement(By.xpath("//li[contains(text(),'Google')]"));
        option.click();
        WebElement selectedValue = driver.findElement(By.className("select2-selection__rendered"));
        Assert.assertEquals(selectedValue.getText(), "Google");
        test.pass("Dropdown selection verified");
        Thread.sleep(500);
        // Step 4: Add Customer
        WebElement customerAdd = driver.findElement(By.xpath("//a[@data-target=\"#modal-customer\"]"));
        customerAdd.click();
        driver.findElement(By.name("full_name")).sendKeys("pras");
        driver.findElement(By.name("phone1")).sendKeys("8768938897");
        driver.findElement(By.xpath("//*[@id='quickForm_customer']/div/div[2]/div[6]/div[2]/div/div/button")).click();
        test.pass("Customer added successfully");
        Thread.sleep(500);
        // Step 5: Scroll and Click Save
        js.executeScript("window.scrollBy(0,1000)");

        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='quickForm']/div[2]/button")));
        js.executeScript("arguments[0].scrollIntoView(true);", saveButton);
        Thread.sleep(1000);
        js.executeScript("arguments[0].click();", saveButton);
        test.pass("Clicked Save button");
        Thread.sleep(500);
        System.out.println("Test Passed: Lead Created Successfully!");
    }
    @Test
    //Lead Edit
    public void testLeadEdit() throws InterruptedException {
    	test = extent.createTest("Lead Creation Test").assignAuthor("QA Team").assignCategory("Regression Test");
    WebElement EditButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Edit']")));
    js.executeScript("arguments[0].scrollIntoView(true);", EditButton);
    Thread.sleep(1000); // Allow time for any UI transitions
    js.executeScript("arguments[0].click();", EditButton);
    Thread.sleep(500);
    System.out.println("Edit button clicked successfully!");
    
    
    	
    	WebElement AfterEditSaveButton= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"quickForm\"]/div[2]/button")));
        js.executeScript("arguments[0].scrollIntoView(true);", AfterEditSaveButton);
        Thread.sleep(1000); // Allow time for any UI transitions
        js.executeScript("arguments[0].click();", AfterEditSaveButton);
        Thread.sleep(500);
        System.out.println("AfterEditSaveButton!");
    }
    @AfterMethod
    public void captureFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                File destination = new File("screenshots/" + result.getName() + ".png");
                FileUtils.copyFile(screenshot, destination);
                test.fail("Test Failed: " + result.getThrowable()).addScreenCaptureFromPath(destination.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @AfterSuite
    public void generateReport() {
        extent.flush();
    }
}
