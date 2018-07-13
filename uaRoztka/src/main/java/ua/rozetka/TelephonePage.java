package ua.rozetka;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;



public class TelephonePage extends Page{
	
	WebDriverWait wait;
	
	
	@FindBy(xpath="//a[@href='https://rozetka.com.ua/telefony/c4627900/']")
	protected WebElement link;
	
	@Override
	protected WebElement GetLink()
	{
		return link;
	}

	public TelephonePage(WebDriver driver) {
		super(driver);
		title = TitleNames.TELEPHONE;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30, 500);
		// TODO Auto-generated constructor stub
	}

}
