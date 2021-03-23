package in.at.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DoctorFunctionality {

	@Test
	public void ApplyDoctor(){
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://127.0.0.1:8000");
		
		driver.findElement(By.linkText("Doctor")).click();
		driver.findElement(By.linkText("Apply")).click();
		
		driver.findElement(By.id("id_first_name")).sendKeys("Hazmina");
		driver.findElement(By.id("id_last_name")).sendKeys("Patel");
		driver.findElement(By.id("id_username")).sendKeys("Hazmina");
		driver.findElement(By.id("id_password")).sendKeys("password");
		
		Select department = new Select(driver.findElement(By.id("id_department")));
		department.selectByVisibleText("Dermatologists");
		
		driver.findElement(By.id("id_mobile")).sendKeys("9872222222");
		driver.findElement(By.id("id_address")).sendKeys("Hyderabad");
		
		driver.findElement(By.id("id_profile_pic")).sendKeys("D:\\eclipse-workspace\\SampleDummy2\\target\\classes\\pictures\\Doctor.png");
			
		driver.findElement(By.className("btnSubmit")).click();
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://127.0.0.1:8000/doctorlogin");
	}
	

}
