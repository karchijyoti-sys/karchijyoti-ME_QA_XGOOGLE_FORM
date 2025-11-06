package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.logging.Level;

import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */
     @Test
     public void testCase01() throws InterruptedException {
        // Step 1: Launch the Google Form
      System.out.println("Launching Google Form...");
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");
      
        Wrappers obj = new Wrappers(driver);
        

        System.out.println("Filling basic details...");
       // Step 2: Fill in the basic details
        obj.entertext(driver.findElement(By.xpath("(//input[@type='text'])[1]")), "Crio Learner");
        long epoch = System.currentTimeMillis()/1000;
        obj.entertext(driver.findElement(By.xpath("//span[text()='Why are you practicing Automation?']/parent::div/parent::div/parent::div/parent::div//textarea")), "I want to be the best QA Engineer! "+Long.toString(epoch));
        obj.selectradio(driver.findElement(By.xpath("//div[@data-value='6 - 10']")));
       // Selecting multiple checkboxes
        obj.selectcheckbox("Java");
        obj.selectcheckbox("Selenium");
        obj.selectcheckbox("TestNG");
        // Step 3: Handle dropdown selection
        obj.dropdownbytext(driver.findElement(By.xpath("//span[text()='Choose']")), "Mrs");
        // Step 4: Enter date and time
        obj.entertext(driver.findElement(By.xpath("//input[@type='date']")), obj.getCurrentDateTime("dd-MM-yyyy").toString()); 
        obj.entertext(driver.findElement(By.xpath("//div[text()='Time']/parent::div/parent::div/parent::div//input[1]")), "07");
        obj.entertext(driver.findElement(By.xpath("(//div[text()='Time']/parent::div/parent::div/parent::div//input)[2]")), "30");
        // Step 5: Submit the form and validate submission
        obj.clickElement(driver.findElement(By.xpath("//span[text()='Submit']")));
        String ActualText="Thanks for your response, Automation Wizard!";
        String ExpectedText=driver.findElement(By.xpath("//div[contains(text(),'Thanks for your response')]")).getText();
        if(ActualText.toLowerCase().equals(ExpectedText.toLowerCase())){
            Reporter.log("Test Case Passed: Form Submitted Successfully", true);
        }else{
            Reporter.log("Test Case Failed: Form not Submitted", true); 
        }        
    
        System.out.println("Form submitted successfully!");
    }  
     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

       

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(50));
    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}