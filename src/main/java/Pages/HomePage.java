package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.Base;

public class HomePage {

	private WebDriver driver = Base.getDriver();
	
	@FindBy(xpath = "//*[contains(text(),'Log In')]")
	WebElement LogInButton;


	public HomePage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public LoginPage Login() {
		LogInButton.click();
		return new LoginPage(driver);
	}
	
}
