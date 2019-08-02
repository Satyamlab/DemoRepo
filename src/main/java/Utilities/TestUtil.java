package Utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.BeforeMethod;

import Base.Base;

public class TestUtil {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 10;
	public static long Explicit_WAIT = 20;
	
	
	public static void takeScrenshot() throws IOException {
		File scrFile = ((TakesScreenshot)Base.getDriver()).getScreenshotAs(OutputType.FILE);
		String curDir = System.getProperty("user.dir");
		
		FileHandler.copy(scrFile, new File(curDir + "\\test-output\\screenshots\\"+ System.currentTimeMillis()+ ".png"));
	}
	
	
}
