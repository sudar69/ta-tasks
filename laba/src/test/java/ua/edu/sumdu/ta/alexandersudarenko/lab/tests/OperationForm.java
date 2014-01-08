package ua.edu.sumdu.ta.alexandersudarenko.lab.tests;

import java.io.File;
import java.io.IOException;

import java.util.Set; 
import java.util.List; 
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
 

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Keys;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeDriver;


public class OperationForm {
	
	private static WebDriver driver;
	
	public OperationForm(WebDriver driver) {
		this.driver = driver;
	}
    
    public WebElement setInput(String formName, String fieldName, String value) {
		WebElement inputElement = driver.findElement(By.xpath("//*[@name=\"" + 
          formName + "\"]//*[@name=\"" + fieldName + "\"]"));
        if (inputElement.getAttribute("readonly") != null) {
            String originalWindow = driver.getWindowHandle();
            final Set<String> oldWindowsSet = driver.getWindowHandles();
            
            WebElement select = driver.findElement(By.xpath("//*[@id=\"" + formName + "\"]//*[@id=\"" + fieldName + "\"]/parent::td/a"));
            select.click();
            //driver.findElement(By.linkText("select")).click();
     
            String newWindow = (new WebDriverWait(driver, 10))
                .until(new ExpectedCondition<String>() {
                    public String apply(WebDriver driver) {
                        Set<String> newWindowsSet = driver.getWindowHandles();
                        newWindowsSet.removeAll(oldWindowsSet);
                        return newWindowsSet.size() > 0 ? 
                                     newWindowsSet.iterator().next() : null;
                      }
                    }
                );
            driver.switchTo().window(newWindow);
            Navigation navigation = new Navigation(driver);
            navigation.setNavigation(new String[] {value});
            driver.findElement(By.id("OK")).click();     
            driver.switchTo().window(originalWindow);            
        } else if (inputElement.getTagName().equals("select")){
            Select realSelect = new Select(inputElement);
            realSelect.selectByValue(value);
        } else {
            inputElement.clear();
            inputElement.sendKeys(value);
        }
        return inputElement;
    }
    
    public WebElement setInput(String formName, String[] fieldName, String[] value) {
        WebElement inputElement = null;
        for (int i = 0; i < value.length; i++) {
            inputElement = setInput(formName, fieldName[i], value[i]);
        }
        return inputElement;
    }
	
	public boolean validInput(String formName, String fieldName, String value) {
        setInput(formName, fieldName, value).submit();
        if (driver.findElements(By.xpath("//*[@id=\"" + formName + "\"]//*[@id=\"" + fieldName + 
          "\"]/parent::td/parent::tr/td/span[@class=\"error\"]")).size() > 0) return false;
        return true;
	}
	
	public boolean validInput(String formName, String[] fieldName, String[] value) {
		if (fieldName.length <= 0 || value.length <= 0 || fieldName.length != value.length) return false;
        setInput(formName, fieldName, value).submit();        
        if (driver.findElements(By.xpath("//*[@id=\"" + formName + "\"]//*[@id=\"" + fieldName[0] + 
          "\"]/parent::td/parent::tr/td/span[@class=\"error\"]")).size() > 0) return false;
        return true;
	}

    public boolean loginToServer(String formName, String[] fieldName, String[] value) {
        setInput(formName, fieldName, value);
        driver.findElement(By.name("submit")).click();
        if (!driver.getTitle().equals("Top")) return false;
        //if (driver.findElements(By.xpath("//div[@class=\"errorblock\"]")).size() > 0) return false;
        return true;
    }
    
}
