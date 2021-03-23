package in.at.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import in.at.tests.DBConnection;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;

	public FileInputStream fis = null;
	public Properties config = null;
	
	public ExtentSparkReporter extentSparkReporter;
	public ExtentReports extentReports;
	public ExtentTest extentTest;
	
	String projectDirectory;
	String browser;
	String appUrl;
	
	@BeforeSuite
	public void config() throws IOException {
		projectDirectory = System.getProperty("user.dir");
		
		System.out.println("Project Directory: " + projectDirectory);
		fis = new FileInputStream(projectDirectory + "\\src\\test\\resources\\config.properties");
		config = new Properties();
		config.load(fis);
		
		browser = config.getProperty("browser");
		appUrl = config.getProperty("appUrl");
		
		if(extentReports == null) {
			extentSparkReporter = new ExtentSparkReporter(projectDirectory + "/src/main/resources/HMReport.html");
			extentReports = new ExtentReports();
			extentReports.attachReporter(extentSparkReporter);
			
			extentReports.setSystemInfo("OS", "Windows10");
			extentReports.setSystemInfo("Environment", appUrl);
			extentReports.setSystemInfo("Browser", browser);
			extentReports.setSystemInfo("Tester","Krishna");
			
			extentSparkReporter.config().setReportName("Hospital Management Automation Report");
			extentSparkReporter.config().setDocumentTitle("My Own Report");
			extentSparkReporter.config().setTheme(Theme.DARK);
		}
	}

	@BeforeMethod
	public void init() {
		
		//if (driver == null) {
			
			if (browser.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}
		//}
		driver.manage().window().maximize();
		driver.get(appUrl);// QA URL or // staging url
	}

	@AfterMethod
	public void getResult(ITestResult result) throws InterruptedException, IOException, SQLException {
		
		if(result.getStatus() == ITestResult.FAILURE)
		{
			extentTest.fail("Test Failed");
			extentTest.fail(result.getThrowable());
			String path = captureScreenshot(driver, "screenshotName");
			extentTest.fail(MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}
		
		else if(result.getStatus() == ITestResult.SUCCESS)
		{
			extentTest.pass("Test Passed.");
		}
		
		else
		{
			extentTest.skip("Test Skipped");
		}
		
		DBConnection.closedbConnection();
		
		if (driver != null) {
			Thread.sleep(2000);
			driver.quit();
		}
	}
	
	@AfterSuite
	public void tearDown()
	{
		extentReports.flush();
	}
	
	public String captureScreenshot(WebDriver driver, String screenshotName) throws IOException {
		
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String dest = projectDirectory + "/src/main/resources/screenshots/"+	screenshotName + ".png";
		File destination = new File(dest);
		FileHandler.copy(source, destination);
		return dest;
	}
	

}
