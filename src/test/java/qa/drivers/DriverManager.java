package qa.drivers;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverManager {
	
	 /**
     * 
     * Gets the Webdriver depending on the Browser type
     * 
     * @return Webdriver for the browser
     */
    public static WebDriver initdriver(String BROWSER_TYPE) {
        WebDriver localDriver = null;

        if (BROWSER_TYPE.equalsIgnoreCase("ie")) {
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(
                InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            capabilities.setCapability("nativeEvents", false);
            capabilities.setCapability("requireWindowFocus", true);
            capabilities.setCapability("ensureCleanSession", true);
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            
            File file = new File(System.getProperty("user.dir")+"\\drivers\\IEDriverServer_x64_2.48.0\\\\IEDriverServer.exe");
            System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
            localDriver = new InternetExplorerDriver(capabilities);
        }
        if (BROWSER_TYPE.equalsIgnoreCase("chrome")) {
        	DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability("setAcceptUntrustedCertificates", true);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.addArguments("chrome.switches", "--disable-extensions");
            options.addArguments("disable-infobars");
            options.addArguments("test-type");
            options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
            //File file = new File(RESOURCES_DIR + File.separator + "selenium" + File.separator
             //       + "chrome" + File.separator + "chromedriver.exe");
            File file = new File(System.getProperty("user.dir")+"\\drivers\\chromedriver_win32\\\\chromedriver.exe");
            //File file = new File("C:\\SELENIUM\\DQ\\bg\\automation\\ui\\BG_UI_10.2\\lib\\chromedriver_win32\\chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
            capabilities.setCapability("chrome.binary", file.getAbsolutePath());

            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			capabilities.setCapability (CapabilityType.ACCEPT_SSL_CERTS, true);
            localDriver = new ChromeDriver(options);

        }
        if (BROWSER_TYPE.equalsIgnoreCase("remote")) {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability("setAcceptUntrustedCertificates", true);
            ChromeOptions options = new ChromeOptions();
            String nodeurl = "http://10.65.164.210:4444/wd/hub";
            options.addArguments("chrome.switches", "--disable-extensions");
            options.addArguments("test-type");
            options.addArguments("--start-maximized");
            File file = new File("C:\\SELENIUM\\DQ\\bg\\automation\\ui\\BG_UI_10.2\\lib\\chromedriver_win32\\chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
            capabilities.setCapability("chrome.binary", file.getAbsolutePath());
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            try {
				return new RemoteWebDriver(new URL(nodeurl), capabilities);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

      }

        return localDriver;
    }


}
