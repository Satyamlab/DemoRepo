package Pages;

import java.awt.print.Pageable;
import java.sql.Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.Base;

public class LoginPage{

	private WebDriver driver;
	
	@FindBy(xpath = "//input[@type='text']")
	WebElement Email;
	
	@FindBy(xpath = "//input[@type='password']")
	WebElement Password;
	
	@FindBy(xpath = "//*[contains(text(),'Login')]")
	WebElement LoginButton;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void Login(String Username, String Pass) {
		Email.sendKeys(Username);
		Password.sendKeys(Pass);
		LoginButton.click();
	}
	
}
