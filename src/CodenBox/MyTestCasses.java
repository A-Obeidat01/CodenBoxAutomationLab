package CodenBox;

import java.awt.event.WindowEvent;
import java.net.URI;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap.KeySetView;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCasses { 
	
WebDriver driver = new EdgeDriver();
String theURL= "https://codenboxautomationlab.com/practice/";
Random rand=  new Random();

@BeforeTest
	public void MySetup() {
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
	
	@Test (priority = 4,invocationCount = 1,enabled = true)
	public void cart () throws InterruptedException {
		WebElement box = driver.findElement(By.id("checkbox-example"));
		List<WebElement> ch = box.findElements(By.tagName("input"));
		for (int i = 0;i<ch.size();i++) {
			ch.get(i).click();
		}
			
			
		}
		
}


