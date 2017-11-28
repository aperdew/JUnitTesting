import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class test1 {
	
	WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://wheelofhunger.ddns.net:8080/WheelOfHunger/WOH-login.jsp");
		
	}
	
	@Test
	public void LoginIncorrectly() throws Exception {
		Thread.sleep(2000);		
		driver.findElement(By.cssSelector(".WOH-login-UserNameInput")).sendKeys("InvalidUserName");
		driver.findElement(By.cssSelector(".WOH-login-restaurantNameInput")).sendKeys("InvalidPassword");
		driver.findElement(By.cssSelector(".WOH-login-submitButton")).click();
		Thread.sleep(500);
		
		//dealing with an alert
		String mainWindow = driver.getWindowHandle();
		Alert alert = driver.switchTo().alert();
		if(alert.getText().equals("Username or password was incorrect")){
			System.out.println("test1 is successful");
		}
		alert.accept();
		driver.switchTo().window(mainWindow);
	}
	
	@Test
	public void Login() throws Exception {
		Thread.sleep(2000);		
		driver.findElement(By.cssSelector(".WOH-login-UserNameInput")).sendKeys("Aaron");
		driver.findElement(By.cssSelector(".WOH-login-restaurantNameInput")).sendKeys("1234");
		driver.findElement(By.cssSelector(".WOH-login-submitButton")).click();
		Thread.sleep(500);
		if(driver.getTitle().equals("Wheel of Hunger")){
			System.out.println("test2 is successful");
		}		
	}
	
	@Test
	public void AddRestaurant() throws Exception{
		Thread.sleep(2000);		
		driver.findElement(By.cssSelector(".WOH-login-UserNameInput")).sendKeys("Aaron");
		driver.findElement(By.cssSelector(".WOH-login-restaurantNameInput")).sendKeys("1234");
		driver.findElement(By.cssSelector(".WOH-login-submitButton")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//a[@href='WOH-addRestaurant.jsp']")).click();
		driver.findElement(By.cssSelector(".WOH-addRestaurants-restaurantNameInput")).sendKeys("Test Qdoba");
		
		//Using a dropdown
		Select dropdownPrice = new Select(driver.findElement(By.cssSelector(".WOH-addRestaurant-priceInput")));
		dropdownPrice.selectByVisibleText("$");
		
		Select dropdownType = new Select(driver.findElement(By.cssSelector(".WOH-addRestaurants-typeInput")));
		dropdownType.selectByVisibleText("Fast Food");
		
		//Dealing with checkboxes that dont have a unique class or id			
		driver.findElement(By.xpath("//label[text()[contains(.,'Mexican')]]")).click();
		driver.findElement(By.xpath("//label[text()[contains(.,'American')]]")).click();
		
		driver.findElement(By.cssSelector(".WOH-addRestaurants-notesTextArea")).sendKeys("Great Food!");
		driver.findElement(By.cssSelector(".WOH-addRestaurants-submitButton")).click();
		Thread.sleep(500);
		
		if(driver.findElement(By.xpath("//td[text()[contains(.,'Test Qdoba')]]"))!= null){
			System.out.println("Step 3 Successful");
		}
	}
	

}
