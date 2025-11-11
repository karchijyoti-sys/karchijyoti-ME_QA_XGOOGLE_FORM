package demo.wrappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
   
    private WebDriver driver;
      public Wrappers(WebDriver driver){
        this.driver = driver;
        
      }
     public void entertext(By locator,String Text){
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try{ 
         WebElement element=driver.findElement(locator);
         wait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(Text);
        }catch(Exception e){
            System.out.println("Element not found to enter text: "+ e.getMessage());
        }
     }

     public void clickElement(WebElement element){
        element.click();
     }

     public void selectradio(By element,String value){
      List<WebElement> listofRadio=driver.findElements(element);
        for(WebElement ele:listofRadio)
        {
           String text=ele.getText();
           System.out.println(text);
           if(text.equalsIgnoreCase(value))
           {
               ele.click(); 
               break;
           }
        }
        
     }

     public void selectcheckbox(String value){
       WebElement element= driver.findElement(By.xpath("//label[contains(@class,'Yri8Nb')]//span[contains(text(),'"+value+"')]"));
        element.click();
     }

     public void dropdownbytext(WebElement element, String text){
        
        
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        try {
         Thread.sleep(1000);
      } catch (InterruptedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
        System.out.println("Dropdown clicked successfully.");
        List<WebElement> dropwoenlist=driver.findElements(By.xpath("//div[@class='OA0qNb ncFHed QXL7Te']//span[not(contains(text(),'Choose'))]"));
        System.out.println(dropwoenlist.size());
       for(int i=0;i<dropwoenlist.size();i++)
        {
         String dropdownvalue=dropwoenlist.get(i).getText();
       
        
         if(dropwoenlist.get(i).getText().equals(text))
         {
            System.out.println("inside if");
             System.out.println(dropdownvalue+"-----------"+text);
           dropwoenlist.get(i).click();
            
         }


        }
      
          
        
    }

     public String getCurrentDateTime(String format){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        LocalDateTime now = LocalDateTime.now().minusDays(7);
        return dtf.format(now);
     }

     
     


}
