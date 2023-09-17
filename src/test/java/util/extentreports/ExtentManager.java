package util.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	private static final ExtentReports extentReports = new ExtentReports();

	public synchronized static ExtentReports getExtentReports() {
		ExtentSparkReporter reporter = new ExtentSparkReporter("./extent-reports/extent-report.html");
		reporter.config().setReportName("SauceDemo Report");
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("Version", "0.9.1");
		extentReports.setSystemInfo("Author", "Mohamad Arga");
		return extentReports;
	}
}
