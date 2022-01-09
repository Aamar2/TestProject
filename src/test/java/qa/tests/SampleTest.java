package qa.tests;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class SampleTest extends BaseTest implements Xpaths{
	
	@Test(priority=1)
	public void searchItemAndAddToCart() {
		 test = extent.createTest("Search Item", "Verify the search Result");
		 driver.get("https://demo.opencart.com/");
		 wait.until(ExpectedConditions.presenceOfElementLocated(appHeader));
		 String item = "iMac";
		 driver.findElement(searchInput).sendKeys(item);
		 driver.findElement(searchBtn).click();
		 
		 //verify the search Header displayed
		 if(driver.findElement(By.xpath(searchHeaderText.replace("replace", item))) != null) {
			 test.pass("The item search event is triggered");
		 }
		 
		 //verify the item is dislayed
		 if(driver.findElement(By.xpath(productImg.replace("replace", item))) != null) {
			 test.pass("The item is displayed");
		 }
		 
		 //click on the item
		 driver.findElement(By.xpath(productImg.replace("replace", item))).click();
		 //verify the add to cart page is opened
		 String itemName = driver.findElement(navigateToProduct).getText();
		 if(itemName.equalsIgnoreCase(item)) {
			 test.pass("navigated successfully yo add to cart page");
		 }else {
			 Assert.assertEquals(item, itemName);
		 }
		 
		 //add to cart
		 driver.findElement(addToCartBtn).click();
		 wait.until(ExpectedConditions.presenceOfElementLocated(successMsg));
		 
		 //verify teh success message
		 //"Success: You have added "
		 
		 String msg = driver.findElement(successMsg).getText();
		 if(msg.contains("Success: You have added")) {
			 test.pass("item added to cart successfully");
		 }else {
			 test.fail("Item is not added to cart hence failed");
		 }
		
	}
	
	@Test(priority=2)
	public void uploadFile() {
		test = extent.createTest("Upload File", "Verify the file upload");
		 driver.get("https://cgi-lib.berkeley.edu/ex/fup.html");
		 wait.until(ExpectedConditions.presenceOfElementLocated(uploadFileBtn));
		 driver.findElement(uploadFileBtn).sendKeys("C:\\SELENIUM\\fileUpload.txt");
		 driver.findElement(notes).sendKeys("file uploading");
		 driver.findElement(pressBtn).click();
		 wait.until(ExpectedConditions.presenceOfElementLocated(uploadMessage));
		 if(driver.findElement(uploadMessage) != null) {
			 test.pass("File Uploaded successfully");
		 }
	}
	
	
	@Test(priority=3)
	public void createAccount() {
		Random rand = new Random();
		int rand_int1 = rand.nextInt(10000);
		String email = "test"+String.valueOf(rand_int1)+"@cui.com";
		test = extent.createTest("Search Item", "Verify the search Result");
		 driver.get("https://demo.opencart.com/");
		 wait.until(ExpectedConditions.presenceOfElementLocated(appHeader));
		 
		 driver.findElement(account).click();
		 if(driver.findElement(registerMenu) != null){
			 driver.findElement(registerMenu).click();
		 }
		 
		 if(driver.findElement(registerPageOpened) != null) {
			 test.pass("user registration page is opened");
		 }
		 wait.until(ExpectedConditions.presenceOfElementLocated(appHeader));
		 
		 driver.findElement(firstNameInput).sendKeys(PropertyReader.readItem("firstName"));
		 driver.findElement(lastNameInput).sendKeys(PropertyReader.readItem("lastName"));
		 driver.findElement(emailInput).sendKeys(email);
		 driver.findElement(telephoneInput).sendKeys(PropertyReader.readItem("telephone"));
		 driver.findElement(passwordInput).sendKeys(PropertyReader.readItem("password"));
		 driver.findElement(confirmPasswordInput).sendKeys(PropertyReader.readItem("password"));
		 driver.findElement(agreeCheckBox).click();
		 driver.findElement(continueBtn).click();
		 
		 //verify the account is created successfully
		 wait.until(ExpectedConditions.presenceOfElementLocated(accountCreated));
		 if(driver.findElement(accountCreated) != null) {
			 test.pass("user account is created");
		 }
		 
	}
	

}
