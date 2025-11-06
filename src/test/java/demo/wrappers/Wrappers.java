package demo.wrappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
   
    private WebDriver driver;
      public Wrappers(WebDriver driver){
        this.driver = driver;
        
      }
     public void entertext(WebElement element,String Text){
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try{ wait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(Text);
        }catch(Exception e){
            System.out.println("Element not found to enter text: "+ e.getMessage());
        }
     }

     public void clickElement(WebElement element){
        element.click();
     }

     public void selectradio(WebElement element){
        element.click();
     }

     public void selectcheckbox(String value){
       WebElement element= driver.findElement(By.xpath("//span[text()='"+value+"']/parent::div/parent::div/preceding-sibling::div"));
        element.click();
     }

     public void dropdownbytext(WebElement element, String text){
        
        
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        System.out.println("Dropdown clicked successfully.");
       
        By optionLocator = By.xpath("//div[@class='OA0qNb ncFHed QXL7Te']//span[text()='"+text+"']");
         wait.until(ExpectedConditions.visibilityOfElementLocated(optionLocator));
         WebElement option=driver.findElement(optionLocator);
          ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
          wait.until(ExpectedConditions.elementToBeClickable(option)).click();
          System.out.println("Option selected successfully: "+text);
          
        
    }

     public String getCurrentDateTime(String format){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        LocalDateTime now = LocalDateTime.now().minusDays(7);
        return dtf.format(now);
     }

     
     


}
