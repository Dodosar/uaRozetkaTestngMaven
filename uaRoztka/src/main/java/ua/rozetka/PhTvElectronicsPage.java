package ua.rozetka;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PhTvElectronicsPage extends Page {

	WebDriverWait wait = new WebDriverWait(driver, 10);;
	@FindBy(xpath = "//a[@class='menu-categories__link js-menu-categories__link menu-categories__link_state_hovered']")
	protected WebElement link;

	@Override
	protected WebElement GetLink()
	{
		return link;
	}
	
	public PhTvElectronicsPage(WebDriver driver) {
		super(driver);
		title = driver.getTitle();
		PageFactory.initElements(driver, this);
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
