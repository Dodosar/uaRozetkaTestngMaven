package ua.rozetka;

import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import ua.DB.DataBase;

public class SelectProductsFromPages extends Page{	
	WebDriverWait wait;

	
	public SelectProductsFromPages(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30, 500);
		// TODO Auto-generated constructor stub
	}
	

    public SelectProductsFromPages saveTopProductsFromPage(){
    	if(isXPathPresentInaPage("//div[@class='g-i-tile-i-box-desc'][.//*[@class='g-tag g-tag-icon-middle-popularity sprite']]//*[@class='g-i-tile-i-title clearfix']/a")){
        List<WebElement> topProductsNames = getElements("product name");
        List<WebElement> topProductsPrices = getElements("product price");

        DataBase.connect();
        for (int i=0; i<topProductsNames.size(); i++) {
            DataBase.execute("insert into TopSmartPhones (name, price) values ('" +
                    topProductsNames.get(i).getText() + "', '" +
                    topProductsPrices.get(i).getText() + "')");
        }
        System.out.println("save top TopSmartPhones to DB");
        DataBase.close();
        return this;
    	}		
    	System.out.println("In the page not exist element");
		return this;
    }
}
