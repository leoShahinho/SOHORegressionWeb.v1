package SetupsAndPages.Pages;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;

public class BasePage {
    public WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver= driver;
        PageFactory.initElements(this.driver, this);
    }

    public WebElement waitForElementToBeVisible_WebElement(WebElement webelement) {
        WebDriverWait wait = new WebDriverWait(driver, 45);
        try {
            return wait.until(

                    ExpectedConditions.visibilityOf(webelement));

        } catch (TimeoutException ex) {
            return null;
        }
    }

    public WebElement waitForElementToBeVisible(By selector) {
        WebDriverWait wait = new WebDriverWait(driver, 90);
        try{
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(selector));
        }catch(TimeoutException ex){
            return null;
        }

    }

    public void takeScreenShot(String path){

        File Source_File = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(Source_File, new File(path));
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }


}

