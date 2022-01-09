package qa.tests;

import org.openqa.selenium.By;

public interface Xpaths {
	
	//Register User
	By account = By.xpath("//a[@title='My Account']");
	By registerMenu = By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//li//a[@href='https://demo.opencart.com/index.php?route=account/register']");
	By registerPageOpened = By.xpath("//h1[text()='Register Account']");
	By firstNameInput = By.xpath("//input[@id='input-firstname']");
	By lastNameInput = By.xpath("//input[@id='input-lastname']");
	By emailInput = By.xpath("//input[@id='input-email']");
	By telephoneInput = By.xpath("//input[@id='input-telephone']");
	By passwordInput = By.xpath("//input[@id='input-password']");
	By confirmPasswordInput = By.xpath("//input[@id='input-confirm']");
	By agreeCheckBox = By.xpath("//input[@name='agree']");
	By continueBtn = By.xpath("//input[@value='Continue']");
	By accountCreated = By.xpath("//h1[text()='Your Account Has Been Created!']");
	
	//Search item
	By appHeader = By.xpath("//a[text()='Your Store']");
	By searchInput = By.xpath("//input[@name='search']");
	By searchBtn = By.xpath("//button[@class='btn btn-default btn-lg']");
	By searchHeader = By.xpath("//div//h1[text()='Search - replace']");
	String searchHeaderText = "//div//h1[text()='Search - replace']";
	//By productImg = By.xpath("//img[@alt='replace']");
	String productImg = "//img[@alt='replace']";
	By navigateToProduct = By.xpath("//ul[@class='breadcrumb']/li[3]/a");
	By addToCartBtn = By.xpath("//button[text()='Add to Cart']");
	By successMsg = By.xpath("//div[@class='alert alert-success alert-dismissible']");
	
	//Upload case
	By uploadFileBtn = By.xpath("//input[@name='upfile']");
	By notes = By.xpath("//input[@name='note']");
	By pressBtn = By.xpath("//input[@value='Press']");
	By uploadMessage = By.xpath("//h1[text()='File Upload Results']");

}
