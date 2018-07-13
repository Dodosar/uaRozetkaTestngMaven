package ua.rozetka;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;


public class Page {
	protected String url = "";
	protected TitleNames title;
	protected static WebDriver driver;
	protected WebElement link;
	static int b = 0;
	

	public Page(WebDriver driver) {
		this.driver = driver;
	}

	protected WebElement GetLink()
	{
		return link;
	}	

	
	public WebDriver getDriver() {
		return driver;
	}

	public Page open() {
		driver.get(url);
		pause(1);
		System.out.println("open page " + url);
		return this;
	}

	public boolean isElemenPresentInaPage(WebElement a) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		List<WebElement> list = new ArrayList<WebElement>();
		list.add(a);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (list.size() == 0) {
			System.out.println("xpath is incorrect");
			return false;
		} else {
			System.out.println("xpath is correct");
			return list.get(0).isDisplayed();
		}
	}	
	
	public boolean isXPathPresentInaPage(String a) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		List<WebElement> list = driver.findElements(By.xpath(a));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (list.size() == 0) {
			System.out.println("xpath is incorrect");
			return false;
		} else {
			System.out.println("xpath is correct");
			return list.get(0).isDisplayed();
		}
	}	


	public Page then() {
		return this;
	}

	public Page and() {
		return this;
	}
	
	
	public Page clickOnXpath() {
		isElemenPresentInaPage(GetLink());
		GetLink().click();		
		return this;
	}

	public Page clickON(WebElement element) {
		isElemenPresentInaPage(element);
		element.click();
		return this;
	}
	
    public Page clickOnXpath(String elementName){
        getElement(elementName).click();
        pause(1);
        System.out.println("click on '" + elementName + "'");
        return this;
    }
	public String getTitlePage() {
		return driver.getTitle();
	}

	public void CheckTheTitle() {
		try {
			System.out.println(title.toString());
			AssertJUnit.assertTrue(getTitlePage().equals(
					title.toString()));
			title = null;
			System.out.println("Title Correct");
		} catch (Exception e) {
			System.out.println("Title Incorrect");
		}
	}
		
    protected WebElement getElement(String elementName){
        return driver.findElement(By.xpath(getXPath(elementName)));
    }   



    private String getXPath(String elementName) {
		// TODO Auto-generated method stub    	
		return Elements.getEntryForElementName(elementName).getXPath();
	}

	protected List<WebElement> getElements(String elementName){
        return driver.findElements(By.xpath(getXPath(elementName)));
    }
	
	public static void takeSnapShot(WebDriver driver,String fileWithPath) throws Exception{
		TakesScreenshot scrShot = ((TakesScreenshot)driver);
			File ScrFile = scrShot.getScreenshotAs(OutputType.FILE);
			File DestFile = new File(fileWithPath);
			
			FileUtils.copyFile(ScrFile, DestFile);
	}
	
	
	/*public static void isExistPng(){
		String  a = "D://ProjectWebDriverRozetka/test.png";
		try {
			if(new File(a).exists()){
				a = String.format("D://ProjectWebDriverRozetka/test%d.png",b++);
				takeSnapShot(driver,a);
			}
			else {
				takeSnapShot(driver,a);
			}
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}*/
	
	
	public static void pause(int time){
		 try {
	            Thread.sleep(time*1000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	}

}
