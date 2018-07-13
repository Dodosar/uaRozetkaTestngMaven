package ua.rozetka;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;



public class MainPage extends Page{

	/**
	 * @param driver
	 */
	public MainPage(WebDriver driver) {
		super(driver);
		url = "http://rozetka.com.ua/";
		title = TitleNames.MAINPAGE;
		// TODO Auto-generated constructor stub
	}

}
