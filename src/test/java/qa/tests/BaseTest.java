package qa.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import qa.drivers.DriverManager;

public class BaseTest {
	protected static WebDriver driver;
	ExtentHtmlReporter htmlReporter;
    
    ExtentReports extent;
    ExtentTest test;
    
    WebDriverWait wait ;
	
    /* 
    * Launches the driver using base url
    * 
    * @throws URISyntaxException
    */
   public static void launchApp(){    	
     	driver = DriverManager.initdriver("chrome");
   		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
      
   }
   
   @BeforeMethod
   public void setup() {
	   launchApp();
	   wait = new WebDriverWait(driver,30);
	   
   }
   
   @AfterMethod
   public void tearDown(ITestResult result) {
	   if(result.getStatus() == ITestResult.FAILURE) {
           test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
           test.fail(result.getThrowable());
       }
       else if(result.getStatus() == ITestResult.SUCCESS) {
           test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
       }
       else {
           test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
           test.skip(result.getThrowable());
       }
	   
	   driver.quit();
   }
   
   @BeforeClass
   public void startReport() {
   	// initialize the HtmlReporter
	   PropertyReader.loadAllProperties();
	   
       htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/testReport.html");
       
       //initialize ExtentReports and attach the HtmlReporter
       extent = new ExtentReports();
       extent.attachReporter(htmlReporter);
        
       //To add system or environment info by using the setSystemInfo method.
       extent.setSystemInfo("OS", "Windows 16");
       extent.setSystemInfo("Browser", "Chrome");
       
       //configuration items to change the look and feel
       //add content, manage tests etc
       htmlReporter.config().setChartVisibilityOnOpen(true);
       htmlReporter.config().setDocumentTitle("Extent Report Demo");
       htmlReporter.config().setReportName("Test Report");
       htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
       htmlReporter.config().setTheme(Theme.STANDARD);
       htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
   }
   
   @AfterClass
   public void tearDown() {
   	//to write or update test information to reporter
       extent.flush();
   }

}
