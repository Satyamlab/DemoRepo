package Base;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import Listener.WebEventListener;
import Utilities.TestUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Base {

	private static WebDriver driver;
	private static AppiumDriver<MobileElement> ad;
	static Properties prop;
	private static ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();
	private static ThreadLocal<AppiumDriver<MobileElement>> appiumDriver = new ThreadLocal<AppiumDriver<MobileElement>>();
	private static EventFiringWebDriver e_driver;
	private static WebEventListener eventListener;
	
	private static void setAppiumDriver(AppiumDriver<MobileElement> ad) {
		appiumDriver.set(ad);
	}
	
	public static AppiumDriver<MobileElement> getAppiumDriver() {
		return appiumDriver.get();
	}
	
	private static void setDriver(WebDriver driver) {
		dr.set(driver);
	}
	
	public static WebDriver getDriver() {
		return dr.get();
	}
	
	private Base() {
	
	}
	
	public synchronized static void initialise() {
		
			if(prop==null) {
				Prop();
			}
			
			if(prop.getProperty("Type").equals("Web")) {
				
				if(prop.getProperty("Browser").equals("Chrome")) {
					System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
					driver = new ChromeDriver();				
					
				}
				else if(prop.getProperty("Browser").equals("Firefox")) {
					System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\drivers\\geckodriver.exe");
					driver = new FirefoxDriver();				
					
				}
				e_driver = new EventFiringWebDriver(driver);
				eventListener = new WebEventListener();
				e_driver.register(eventListener);
				setDriver(e_driver);
				getDriver().manage().window().maximize();
				getDriver().manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
				getDriver().manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
				getDriver().get(prop.getProperty("url"));			
				
			}
			
			else if(prop.getProperty("Type").equals("Mobile")) {

				
				DesiredCapabilities capabilities = new DesiredCapabilities();
//				capabilities.setCapability("device","Android");
				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Satyam");
				capabilities.setCapability(MobileCapabilityType.UDID,"7456a52");
				capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
				capabilities.setCapability("appPackage","com.alrajhiretailapp");
				capabilities.setCapability("appActivity","com.alrajhiretailapp.MainActivity");
				capabilities.setCapability("platformVersion", "9");
				capabilities.setCapability("automationName", "UiAutomator2");
				try {
					ad=	new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
					setAppiumDriver(ad);
					
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
	}
	
	public synchronized static void Prop() {
		prop = new Properties();
		try {
			InputStream input = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\PropertyFile\\config.properties");
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
