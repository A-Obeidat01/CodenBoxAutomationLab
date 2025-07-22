package CodenBox;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.awt.Desktop.Action;
import java.awt.event.WindowEvent;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap.KeySetView;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCasses { 
	
WebDriver driver = new EdgeDriver();
String theURL= "https://codenboxautomationlab.com/practice/";
Random rand=  new Random();
Connection con;
Statement stat;
ResultSet rs;
String firstName;
String lastName;
String phone;
String customerName;

@BeforeTest
	public void MySetup()throws SQLException {
	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","123456");
	driver.get(theURL);
	driver.manage().window().maximize();
}

@Test(priority = 1,enabled = false)
public void RadioButton() throws InterruptedException {
	WebElement radioButton = driver.findElement(By.xpath("//div[@id='radio-btn-example']//fieldset"));
	int radioButtonCount = radioButton.findElements(By.name("radioButton")).size();
	int randRadioButton = rand.nextInt(radioButtonCount);
	radioButton.findElements(By.name("radioButton")).get(randRadioButton).click();
	
}
	@Test (priority = 2,enabled = false)
	public void SelectCountries () throws InterruptedException {
		
		String[] Countries = { "Jor", "unit", "ira" };
		WebElement countriesInput = driver.findElement(By.id("autocomplete"));
		
		countriesInput.sendKeys(Countries[0]);
		Thread.sleep(1000);
		countriesInput.sendKeys(Keys.chord(Keys.ARROW_DOWN,Keys.ENTER));	
	}
	
	@Test (priority = 3,enabled =false)
	public void SelectTag () {
	WebElement mySelectTag = driver.findElement(By.id("dropdown-class-example"));
		Select mySelect = new Select(mySelectTag);
// 	mySelect.selectByIndex(1);
	mySelect.selectByValue("option2");
//	mySelect.selectByVisibleText("API");

	}
	
	@Test (priority = 4,enabled = false)
	public void CheckBox  () throws InterruptedException {
		WebElement checkBox = driver.findElement(By.id("checkbox-example"));
		List<WebElement> Option = checkBox.findElements(By.tagName("input"));
		for (int i = 0;i<Option.size();i++) {
			Option.get(i).click();
		}
			
	}
		
	@Test (priority = 5,enabled = false)
	public void SwitchWindow  () {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,700)");
		
		WebElement openWindow = driver.findElement(By.id("opentab"));
		openWindow.click();
		Set<String> handles = driver.getWindowHandles();
		List<String> allTabs = new ArrayList<>(handles);
		driver.switchTo().window(allTabs.get(1));
		driver.switchTo().window(allTabs.get(0));
	}
	
	@Test (priority = 6,enabled = false)
	public void SwitchToAlert  ()  {
		WebElement yourName = driver.findElement(By.id("name"));
		yourName.sendKeys("abd");
		driver.findElement(By.id("alertbtn")).click();
		boolean actual = driver.switchTo().alert().getText().contains("abd");
		Assert.assertEquals(actual, true);
		driver.switchTo().alert().accept();
		
	}
	

	@Test (priority = 7,enabled = false)
	public void Table  ()  {
		WebElement myTable = driver.findElement(By.id("product"));
		List<WebElement> data= myTable.findElements(By.tagName("td"));
		
		for (int i=0 ; i<data.size();i++) {
			System.out.println(data.get(i).getText());
		}
	}
	
	@Test (priority = 8,enabled = false)
	public void ElementDisplayed  () throws InterruptedException {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,1500)");
//Elements
		WebElement text = driver.findElement(By.id("displayed-text"));
		WebElement hide = driver.findElement(By.id("hide-textbox"));
		WebElement show = driver.findElement(By.id("show-textbox"));
//Action
		text.sendKeys("abd");
		Thread.sleep(1000);
		hide.click();
		Thread.sleep(1000);
		show.click();	
	}
	
	@Test (priority = 9,enabled = false)
	public void EnabledAndDisabled  () throws InterruptedException {
		
		driver.findElement(By.id("disabled-button")).click();
		System.out.println(driver.findElement(By.id("enabled-example-input")).isEnabled());
		driver.findElement(By.id("enabled-button")).click();
		System.out.println(driver.findElement(By.id("enabled-example-input")).isEnabled());
		
	}
	
	@Test (priority = 10,enabled = false)
	public void MouseHover  () throws InterruptedException {
		
		WebElement mousehover = driver.findElement(By.id("mousehover"));
		Actions action = new Actions(driver);
		action.moveToElement(mousehover).build().perform();
		driver.findElement(By.linkText("Top")).click();
		
	}
	
	@Test(priority = 11,enabled = true)
	public void Calender() throws InterruptedException, SQLException {
		
		driver.findElement(By.linkText("Booking Calendar")).click();
		
		Set<String> handles = driver.getWindowHandles();

		List<String> allTabs = new ArrayList<>(handles);

		driver.switchTo().window(allTabs.get(1));
		
		Thread.sleep(3000);
		int randId = rand.nextInt(144,147);
		String queryToRead = "select * from customers where customerNumber ="+randId;
		stat = con.createStatement();
		rs = stat.executeQuery(queryToRead);
		while (rs.next()) {
			firstName = rs.getString("contactFirstName");
			lastName = rs.getString("contactLastName");
			phone = rs.getString("phone");
			customerName = rs.getString("customerName");
		}
		int randNumber= rand.nextInt(1000);
		driver.findElement(By.id("name1")).sendKeys(firstName);
		driver.findElement(By.id("secondname1")).sendKeys(lastName);
		driver.findElement(By.id("email1")).sendKeys(firstName+lastName+randNumber+"@gmail.com");
		driver.findElement(By.id("phone1")).sendKeys(phone);
		driver.findElement(By.id("details1")).sendKeys(customerName);

	}
	
}


