package in.at.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class AdminHomePage extends BasePage{
	

	public AdminHomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	By adminSignupButton = By.linkText("SignUp");
	
	public AdminSignupPage navigateToAdminSignupPage()
	{
		driver.findElement(adminSignupButton).click();
		return new AdminSignupPage(driver);
	}
}
