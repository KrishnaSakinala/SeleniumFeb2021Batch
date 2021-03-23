package in.at.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;
import in.at.util.TestDataUtil;

public class AdminSignupPage extends BasePage{
	
	
	
	public AdminSignupPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	By firstNameTextbox = By.id("id_first_name");
	By lastNameTextbox = By.id("id_last_name");
	By usernameTextbox = By.id("id_username");
	By passwordTextbox = By.id("id_password");
	By submitButton = By.className("btnSubmit");
	
	public AdminLoginPage createAdmin(String username, String password)
	{
		driver.findElement(firstNameTextbox).sendKeys(TestDataUtil.firstName());
		driver.findElement(lastNameTextbox).sendKeys(TestDataUtil.lastName());
		driver.findElement(usernameTextbox).sendKeys(username);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(submitButton).click();
		return new AdminLoginPage(driver);
	}
}
