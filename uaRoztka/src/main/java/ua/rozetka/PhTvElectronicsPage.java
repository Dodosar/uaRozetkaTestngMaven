package ua.rozetka;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PhTvElectronicsPage extends Page {

	WebDriverWait wait;
	@FindBy(xpath = "//a[contains(@href,'telefony-tv-i-ehlektronika')]")
	protected WebElement link;

	@Override
	protected WebElement GetLink()
	{
		return link;
	}
	
	public PhTvElectronicsPage(WebDriver driver) {
		super(driver);
		title = TitleNames.SMARTPAGE;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30, 500);
	}

	/*public void GoToSmartTVElect() {		
		try{	
		and().clickON(link).
				then().clickON(link);
		System.out.println("Test PAssed");
	}catch(Exception e){
		System.out.println("Test Failded");
	}
	}*/
}
