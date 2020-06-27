package ua.Rozetka;

import org.testng.ITestListener;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import ua.DB.DBResult;
import ua.DB.DataBase;
import ua.rozetka.PDFReport;
import ua.rozetka.RozetkaSite;

//@Listeners(RealTimeReport.class)
//@Listeners(value=Report.class)
@Listeners(PDFReport.class)
public class FirstTest extends WebDriverSettings{

	
	@BeforeClass
    public static void createTable(){
        DataBase.connect();
        System.out.println("test Connection");
        DataBase.execute("drop table TopSmartPhones");
        DataBase.execute("create table TopSmartPhones (id int(10) unsigned NOT NULL auto_increment,name nvarchar(200) NOT NULL,price nvarchar(20) NOT NULL,PRIMARY KEY (id));");
        DataBase.close();
    }
	
    @AfterClass
    public static void checkTable(){
        DataBase.connect();
        /*DBResult result = new DBResult(DataBase.select("select * from TopSmartPhones"));
        System.out.println("\n\n==========RESULT==========\n"
                + result + "====================");*/
        DataBase.select("select * from TopSmartPhones");
        DataBase.close();
    } 
  
    
    

	RozetkaSite objRozetka;

	@Test(priority = 0)
	public void MainPage() {
		objRozetka = new RozetkaSite(driver);
		System.out.println("Step 1: Check Title on Main Page");
		objRozetka.mainPage().open().then();
	}

	@Test(priority = 1)
	public void SecondPage() throws InterruptedException {
		System.out.println("Step 2: Click to link Page of All SmartPhones");
		objRozetka.electronics().then().
		OpenLink();
	}
	
	@Test
	public void FirstRestApiHttpTest() {
		System.out.println("Step 3: Click to link Page of All SmartPhones");
	}
	
	/*@Test(priority = 2)
	public void TelephonePage(){
		System.out.println("Step 3: Click to link Page of All Phones");
		objRozetka.phone().then().clickOnXpath();		
	}
	
	@Test(priority = 3)
	public void SmartPhonePage(){
		System.out.println("Step 4: Click to link Page of SmartPhones");
		objRozetka.smartphone().clickOnXpath().then().CheckTheTitle();
	}
	
	@Test(priority = 4)
	public void SelectProductWithStock(){
		System.out.println("Step 4: OnFirstPageSelectProductsWithStock");
		objRozetka.productlist().saveTopProductsFromPage().then().clickOnXpath("page 2");
		objRozetka.productlist().saveTopProductsFromPage().then().clickOnXpath("page 3");
		objRozetka.productlist().saveTopProductsFromPage().then();
	}

*/


}
