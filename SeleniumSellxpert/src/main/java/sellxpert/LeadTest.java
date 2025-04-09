package sellxpert;

import java.io.File;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LeadTest {
	private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private Actions actions;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
        actions = new Actions(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Open website
        driver.get("https://sellxpert.in/Users");
    }

    @Test(priority = 1)
    public void login() {
        driver.findElement(By.id("btn_login")).click();
        driver.findElement(By.id("email_id")).sendKeys("vinay@gmail.com");
        driver.findElement(By.id("password")).sendKeys("12345");
        driver.findElement(By.id("btn_login")).click();
        System.out.println("Login successful");
    }

    @Test(priority = 2)
    public void navigateToLeadForm() {
        WebElement leadWidget = driver.findElement(By.xpath("/html/body/aside/div/div[1]/div[2]/div/div[2]/div[1]/div/div/div[1]/div[4]/div/a[1]/h6"));
        leadWidget.click();
        WebElement leadAdd = driver.findElement(By.xpath("//*[@id='list']/div/div[1]/div/div[2]/div/div[1]/a"));
        leadAdd.click();
        System.out.println("Navigated to Lead Form");
    }

    @Test(priority = 3)
    public void selectDropdownOption() throws InterruptedException {
        WebElement dropdown = driver.findElement(By.className("select2-selection"));
        dropdown.click();
        Thread.sleep(2000);
        WebElement option = driver.findElement(By.xpath("//li[contains(text(),'FACEBOOK')]"));
        option.click();
        WebElement selectedValue = driver.findElement(By.className("select2-selection__rendered"));
        System.out.println("Selected Value: " + selectedValue.getText());
    }

    @Test(priority = 4, dataProvider = "userData")
    public void addCustomer(User user) throws InterruptedException {
        WebElement customerAdd = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-target=\"#modal-customer\"]")));
        customerAdd.click();

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("full_name")));
        nameField.clear();
        nameField.sendKeys(user.getName());

        WebElement phoneField = driver.findElement(By.name("phone1"));
        phoneField.clear();
        phoneField.sendKeys(user.getMobile());

        driver.findElement(By.xpath("//*[@id='quickForm_customer']/div/div[2]/div[6]/div[2]/div/div/button")).click();

        try {
            WebElement sweetAlert = driver.findElement(By.className("swal2-popup"));
            String alertText = sweetAlert.getText();
            System.out.println("SweetAlert Message: " + alertText);
            WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("swal2-confirm")));
            okButton.click();
            System.out.println("SweetAlert handled successfully.");
        } catch (TimeoutException e) {
            System.out.println("SweetAlert did not appear.");
        }

        Thread.sleep(2000);
    }

    @Test(priority = 5)
    public void saveLead() throws InterruptedException {
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='quickForm']/div[2]/button")));
        js.executeScript("arguments[0].scrollIntoView(true);", saveButton);
        Thread.sleep(1000);
        js.executeScript("arguments[0].click();", saveButton);
        System.out.println("Lead saved successfully!");
    }

    @Test(priority = 6)
    public void editLead() throws InterruptedException {
        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Edit']")));
        js.executeScript("arguments[0].scrollIntoView(true);", editButton);
        Thread.sleep(1000);
        js.executeScript("arguments[0].click();", editButton);
        System.out.println("Edit button clicked successfully!");

        WebElement afterEditSaveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"quickForm\"]/div[2]/button")));
        js.executeScript("arguments[0].scrollIntoView(true);", afterEditSaveButton);
        Thread.sleep(1000);
        js.executeScript("arguments[0].click();", afterEditSaveButton);
        System.out.println("Lead updated successfully!");
    }

    @Test(priority = 7)
    public void deleteLead() throws InterruptedException {
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"lead_table\"]/tbody/tr[1]/td/div/div/div/div[2]/div[8]/div[2]/div/div[3]/a/p")));
        js.executeScript("arguments[0].scrollIntoView(true);", deleteButton);
        Thread.sleep(1000);
        js.executeScript("arguments[0].click();", deleteButton);
        System.out.println("Lead deleted successfully!");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        System.out.println("Browser closed.");
    }

    @DataProvider(name = "userData")
    public Object[][] getUserData() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        User[] users = objectMapper.readValue(new File("C:\\users.json"), User[].class);

        Object[][] data = new Object[users.length][1];
        for (int i = 0; i < users.length; i++) {
            data[i][0] = users[i];
        }
        return data;
      
    }
    }

