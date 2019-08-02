package Test;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.Base;
import Pages.HomePage;
import Pages.LoginPage;

public class Login{

	WebDriver driver;
	
	@Test
	public void Login0() {
		
		Base.initialise();
		Base.getAppiumDriver().close();
	}
	
	@AfterMethod
	public void quitDriver() {
	//	Base.getAppiumDriver().quit();;
	}
}
