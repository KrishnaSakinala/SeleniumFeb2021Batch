package in.at.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class HomePage extends BasePage{
		
	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	By adminLink = By.linkText("Admin");
	By doctorLink = By.linkText("Doctor");
	By patientLink = By.linkText("Patient");
	
	public AdminHomePage navigateToAdminHomePage()
	{
		driver.findElement(adminLink).click();
		return new AdminHomePage(driver);
	
	}
	
	public void navigateToDoctorHomePage()
	{
		driver.findElement(doctorLink).click();		
	}
	
	public void navigateToPatientHomePage()
	{
		driver.findElement(patientLink).click();		
	}

}
