package in.at.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class AdminLoginPage extends BasePage{

	public AdminLoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	By usernameTextbox = By.id("id_username");
	By passwordTextbox = By.id("id_password");
	By loginButton = By.className("btnSubmit");
	
	public String adminLogin(String username, String password)
	{
		driver.findElement(usernameTextbox).sendKeys(username);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(loginButton).click();
		return getPageUrl();
	}

}
