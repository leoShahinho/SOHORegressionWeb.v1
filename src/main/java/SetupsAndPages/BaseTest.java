package SetupsAndPages;

import SetupsAndPages.Pages.SOHOPage;
import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BaseTest {

	public String MIVF10_URL_PPRD1 = "http://assets-es-pprd.dxlpreprod.local.vodafone.es/";
	public String MIVF10_URL_Sit2 = "https://assets-es-sit2.dxlpreprod.local.vodafone.es/mves/login";
	public WebDriver driver; //Object from the Webdriver to use in the script
	public String resourcesFile = System.getProperty("user.dir")+"\\Treceability and data.xlsx";
	public String usersSheet = "LoginCredentials";
	public String screenshotLocations = "ScreenshotLocations";


	@BeforeMethod
	public void prepareChrome() {
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Webdrivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(capabilities);
		driver.get(MIVF10_URL_Sit2);
		driver.manage().window().maximize();

	}


	public WebElement waitForElementToBeVisible(By selector) {
		WebDriverWait wait = new WebDriverWait(driver, 45);
		try {
			return wait.until(
					ExpectedConditions.visibilityOfElementLocated(selector));
		} catch (TimeoutException ex) {
			return null;
		}
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

	//@AfterMethod
	public void teardown() throws IOException {
		driver.quit();
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

	public void Login(String user, String pw, String path) {

		String userNameFieldPath = "//input[@id='ManualLoginComp_txt_username']";
		String passwordFieldPath = "//input[@id=\"ManualLoginComp_txt_password\"]";
		String accederButtonPath = "//*[@id=\"ManualLoginComp_btn_submitLogin\"]";
		String chatBubblePath = "//img[@class=\"bubble-tray__icon\"]";

		WebElement username = waitForElementToBeVisible(By.xpath(userNameFieldPath));
		username.sendKeys(user);
		WebElement password = waitForElementToBeVisible(By.xpath(passwordFieldPath));
		password.sendKeys(pw);
		takeScreenShot(path+"1 User.png");
		WebElement submitBotton = waitForElementToBeVisible(By.xpath(accederButtonPath));
		submitBotton.click();
		Assert.assertTrue(waitForElementToBeVisible(By.xpath(chatBubblePath)).isDisplayed());
		takeScreenShot(path+"2 Dashboard.png");
		System.out.println(user+" logged in successfully");

	}

	public Boolean checkThisTextinPage(String text){
		Assert.assertTrue(waitForElementToBeVisible(By.xpath("//*[text()='"+text+"']")).isDisplayed());
		System.out.println(text);
		return true;
	}

	public Boolean checkAndCaptureThisElementInPage(String locator, String pathToSaveCapture){

		WebElement element = waitForElementToBeVisible(By.xpath(locator));
		Assert.assertTrue(element.isDisplayed());
		scrollTo(element);
		takeScreenShot(pathToSaveCapture);
		return true;
	}

	public void scrollTo(WebElement webElement){

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", webElement);
	}

}