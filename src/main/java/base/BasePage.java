package base;

import org.openqa.selenium.WebDriver;

public class BasePage {
	
	public WebDriver driver;
	
	public BasePage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public String getPageUrl()
	{
		return driver.getCurrentUrl();
	}
	
	public String getTitle()
	{
		return driver.getTitle();
	}
	
	

}
