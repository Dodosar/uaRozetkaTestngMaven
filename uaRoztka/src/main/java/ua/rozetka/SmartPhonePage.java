package ua.rozetka;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;



public class SmartPhonePage extends Page{
	
	WebDriverWait wait;
	@FindBy(xpath="//a[contains(@href,'preset=smartfon')]")
	WebElement link;
	
	
	@Override
	protected WebElement GetLink() {
		return link;
	};

	public SmartPhonePage(WebDriver driver) {
		super(driver);
		title = TitleNames.SMARTPHONE;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30, 500);
		// TODO Auto-generated constructor stub
	}
	
	

}
