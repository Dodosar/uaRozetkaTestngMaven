package ua.rozetka;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RozetkaSite {
	
	WebDriver driver;	
	
	public RozetkaSite(WebDriver driver) {
		this.driver = driver;
	}	
	public MainPage mainPage() {return new MainPage(driver);}
	
	public PhTvElectronicsPage electronics() {return new PhTvElectronicsPage(driver);}
	
	public TelephonePage phone() {return new TelephonePage(driver);}
	
	public SmartPhonePage smartphone() {return new SmartPhonePage(driver);}
	
	public SelectProductsFromPages productlist(){return new SelectProductsFromPages(driver);}
	

	
}
