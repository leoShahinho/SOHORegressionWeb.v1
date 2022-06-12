package RegreSoho.ExtentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    public static  ExtentReports extentReports = new ExtentReports();
    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("./extent-report.html");
        reporter.config().setReportName("ChatBot Report");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Author", "Automtaion Team");
        return extentReports;
    }
}
