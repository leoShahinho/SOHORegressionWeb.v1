package RegreSoho;

import SetupsAndPages.BaseTest;
import SetupsAndPages.Pages.SOHOPage;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static RegreSoho.ExtentReport.ExtentTestManager.startTest;

public class ContratarOnePro extends BaseTest {

    @Test(dataProvider = "SeguridadDigital")
    public void SeguridadDigital(String userName, String password, String pathToEvidence) throws InterruptedException {

        String EPSeguridadDigital = "//*[text()=\"Seguridad Digital\"]";
        String queEsTrySeccionAzul = "//div[@class=\"card-bg ng-star-inserted\"]";
        String queIncluyeSegDig = "//h3[text()=\"¿Qué incluye Seguridad Digital?\"]";
        String ventajasSegDig = "//h3[text()=\"Ventajas de Seguridad Digital\"]";
        String preguntasFrecuentesSegDig = "//h3[text()=\"Preguntas Frecuentes\"]";
        String pastillaInferior = "(//div[text()=\"Seguridad Digital\"])[last()]";

        startTest("TC01.Contratación Seguridad Digital", "En esta prueba validamos la contratación de Seguridad Digital por cliente RS Micro");
        Login(userName,password,pathToEvidence);
        SOHOPage Soho = new SOHOPage(driver);
        Soho.navigateToSoHoDistributiva(pathToEvidence);
        Soho.openListadoOneProFromDistributiva(pathToEvidence);

        waitForElementToBeVisible(By.xpath(EPSeguridadDigital)).click();

        checkThisTextinPage("Seguridad Digital");
        checkThisTextinPage("Protegemos tu negocio, invertir en prevención es ahorrar costes en reacción");
        checkThisTextinPage("¿Qué es Seguridad Digital?");
        Thread.sleep(5000);
        takeScreenShot(pathToEvidence+"z1.png");

        checkAndCaptureThisElementInPage(queEsTrySeccionAzul,pathToEvidence+"z2.png");
        checkAndCaptureThisElementInPage(queIncluyeSegDig,pathToEvidence+"z3.png");
        checkAndCaptureThisElementInPage(ventajasSegDig,pathToEvidence+"z4.png");
        checkAndCaptureThisElementInPage(preguntasFrecuentesSegDig,pathToEvidence+"z5.png");
        checkAndCaptureThisElementInPage(pastillaInferior,pathToEvidence+"z6.png");

    }

    @DataProvider(name = "SeguridadDigital")
    public Object[][] credentials_GestLaboral() {

        SOHOPage Soho = new SOHOPage(driver);
        Object[][] loginData = Soho.getCredentialsData(1);
        return loginData;

    }

}