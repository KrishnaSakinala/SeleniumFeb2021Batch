package in.at.tests;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import in.at.base.BaseTest;
import in.at.pages.HomePage;
import in.at.util.TestDataUtil;

public class AdminFunctionality extends BaseTest{

	@Test 
	public void verifyAdminCreation()
	{
		extentTest = extentReports.createTest("verifyAdminCreation");
		
		String username = TestDataUtil.username();
		String password = TestDataUtil.password();
		
		HomePage homePage = new HomePage(driver);
		String pageTitle = homePage.navigateToAdminHomePage()
		.navigateToAdminSignupPage()
		.createAdmin(username,password)
		.adminLogin(username,password);
		
		Assert.assertEquals(pageTitle, "http://127.0.0.1:8000/admin-dashboard");
	}
	
	@Test
	public void verifyAdminCreationInDB() throws SQLException, InterruptedException
	{
		extentTest = extentReports.createTest("verifyAdminCreationInDB");
		
		String username = TestDataUtil.username();
		String password = TestDataUtil.password();
		
		HomePage homePage = new HomePage(driver);
		homePage.navigateToAdminHomePage()
		.navigateToAdminSignupPage()
		.createAdmin(username,password);
		
		Connection con = DBConnection.getdbConnection();
		ResultSet resultSet = DBConnection.getResultSet(con, "SELECT * FROM auth_user");
		
		List<String> usernames = new ArrayList<String>();
		
		while(resultSet.next())
		{
			String uname = resultSet.getString("username");
			usernames.add(uname);
		}
		
		Assert.assertTrue(usernames.contains(username));
	}
	
	/*public void createAdmin(){
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://127.0.0.1:8000");
		
		driver.findElement(By.linkText("Admin")).click();
		driver.findElement(By.linkText("SignUp")).click();
		
		driver.findElement(By.id("id_first_name")).sendKeys("Hazmina");
		driver.findElement(By.id("id_last_name")).sendKeys("Patel");
		driver.findElement(By.id("id_username")).sendKeys("Hazmina");
		driver.findElement(By.id("id_password")).sendKeys("password");
		driver.findElement(By.className("btnSubmit")).click();
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://127.0.0.1:8000/adminlogin");
	}*/
	
}

/*
 * doctorAdminDashboard();
 * gotoDoctorSingup();
 * fillDoctorDeatils();
 */
