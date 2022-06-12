package SetupsAndPages.Pages;

import SetupsAndPages.ExcelDataConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.function.Predicate;

import static org.openqa.selenium.remote.ErrorCodes.TIMEOUT;

public class SOHOPage extends BasePage {

    public String dataFile = System.getProperty("user.dir")+"\\Treceability and data.xlsx";;

    public SOHOPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id=\"ManualLoginComp_btn_submitLogin\"]")
    WebElement submitBotton;
    @FindBy(xpath = "//button[@class=\"footer-content ng-star-inserted\"]")
    WebElement closeErrorBanner;
    @FindBy(xpath = "//button[@id=\"btn_primaryClick\"]")
    WebElement closeErrorBanner2;
    @FindBy(xpath = "//a[@title=\"Conoce tus productos\"]")
    WebElement conoceTusProductos;



    public Object[][] getCredentialsData( int testCaseRow){

        ExcelDataConfig excel = new ExcelDataConfig(dataFile);
        Object[][] data = new Object[1][3];
        data[0][0] = excel.getData("LoginCredentialsSit2",testCaseRow,1);
        data[0][1] = excel.getData("LoginCredentialsSit2",testCaseRow,2);
        data[0][2] = excel.getData("screenshotLocations",testCaseRow,2);
        return data;

    }

    public void CloseErrorBanner() throws InterruptedException {


        Thread.sleep(5000);
        int count = driver.findElements(By.xpath("//button[@id=\"btn_retryClick\"]")).size();
        if (count > 0)
        {
            closeErrorBanner.click();
            Thread.sleep(1000);
            submitBotton.click();
            count=0;
            Thread.sleep(5000);
            count = driver.findElements(By.xpath("//div[contains(text(),'Error')]")).size();
            System.out.println(count);
        }

       int count1 = driver.findElements(By.xpath("//button[@id=\"btn_primaryClick\"]")).size();
        if (count1 > 0)
        {
            closeErrorBanner2.click();
            Thread.sleep(1000);
            submitBotton.click();
            count1=0;
            Thread.sleep(5000);
            count1 = driver.findElements(By.xpath("//div[contains(text(),'Error')]")).size();
            System.out.println(count1);
        }
    }

    public void SwichIFrame() throws InterruptedException{

        WebElement chat_iframe = waitForElementToBeVisible(By.xpath("//*[@id=\"Iframe\"]"));
        String chatURL = chat_iframe.getAttribute("src");
        driver.get(chatURL);
        WebElement liveChat = waitForElementToBeVisible(By.tagName("iframe"));
        driver.switchTo().frame(liveChat);
        Thread.sleep(20000);
        waitForElementToBeVisible_WebElement(conoceTusProductos);
        System.out.println("Chat is opened successfully");

    }

    public void navigateToSoHoDistributiva(String pathToEvidence) throws InterruptedException {

        WebElement distributivaBanner = waitForElementToBeVisible(By.xpath("//*[text()=\"Transforma con nosotros tu negocio\"]"));
        scrollTo(distributivaBanner);
        Thread.sleep(5000);
        takeScreenShot(pathToEvidence+"3 distributiva banner in dashboard.png");
        distributivaBanner.click();
        Assert.assertTrue(waitForElementToBeVisible(By.xpath("//*[text()=\"Promociones para tu negocio\"]")).isDisplayed());
        Thread.sleep(5000);
        takeScreenShot(pathToEvidence+"4 distributiva page.png");

    }

    public void openListadoOneProFromDistributiva(String pathToEvidence) throws InterruptedException{

        WebElement OnePro = waitForElementToBeVisible(By.xpath("//p[text()=\"Servicios One Profesional\"]"));
        Assert.assertTrue(OnePro.isDisplayed());
        scrollTo(OnePro);
        takeScreenShot(pathToEvidence+"\\5 OneProEP.png");
        OnePro.click();
        Assert.assertTrue(waitForElementToBeVisible(By.xpath("//p[text()=\"Servicios One Profesional\"]")).isDisplayed());
        Assert.assertTrue(waitForElementToBeVisible(By.xpath("//p[text()=\" Potencia y protege tu negocio con servicios específicos para empresas \"]")).isDisplayed());
        Thread.sleep(5000);
        takeScreenShot(pathToEvidence+"\\6 OnePro Services List.png");
    }

    public void scrollTo(WebElement webElement){

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", webElement);
    }

    public WebElement waitForElementToBeVisible_WebElement(By WebElement) {
        WebDriverWait wait = new WebDriverWait(driver, 90);
        try {
            return wait.until(

                    ExpectedConditions.visibilityOfElementLocated(WebElement));

        } catch (TimeoutException ex) {
            return null;
        }
    }



}