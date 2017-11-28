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
	
	@Test
	public void forgotPasswordInvalidUsername() throws Exception {
		driver.findElement(By.xpath("//a[@href='WOH-forgotPasswordUsername.jsp']")).click();
		Thread.sleep(2000);		
		driver.findElement(By.cssSelector(".WOH-forgotPasswordUsername-UsernameInput")).sendKeys("invalidUsername");
		driver.findElement(By.cssSelector(".WOH-forgotPasswordUsername-submitButton")).click();
		
		Thread.sleep(500);
		
		//dealing with an alert
		String mainWindow = driver.getWindowHandle();
		Alert alert = driver.switchTo().alert();
		if(alert.getText().equals("Username does not exist")){
			System.out.println("forgotPasswordInvalidUsername test is successful");
		}
		alert.accept();
		driver.switchTo().window(mainWindow);
		}
	
	@Test
	public void forgotPasswordValidUsername() throws Exception 
	{
		driver.findElement(By.xpath("//a[@href='WOH-forgotPasswordUsername.jsp']")).click();
		Thread.sleep(2000);		
		driver.findElement(By.cssSelector(".WOH-forgotPasswordUsername-UsernameInput")).sendKeys("angapinski");
		driver.findElement(By.cssSelector(".WOH-forgotPasswordUsername-submitButton")).click();
		
		Thread.sleep(500);
		
		if(driver.findElements(By.xpath("//*[contains(text(),'" + "Question 1" + "')]")) != null){
			System.out.println("forgotPasswordValidUsername test is Successful");
		}		
		
	
	}
	
	@Test
	public void forgotPasswordInvalidQuestionAnswers() throws Exception 
	{
		//Progress Through forgotPassword Steps
		driver.findElement(By.xpath("//a[@href='WOH-forgotPasswordUsername.jsp']")).click();
		Thread.sleep(2000);		
		driver.findElement(By.cssSelector(".WOH-forgotPasswordUsername-UsernameInput")).sendKeys("user1");
		driver.findElement(By.cssSelector(".WOH-forgotPasswordUsername-submitButton")).click();
		
		Thread.sleep(2000);
		
		//Invalid Security Answers
		driver.findElement(By.cssSelector(".WOH-forgotPasswordQuestions-secQuestion1Input")).sendKeys("n");
		driver.findElement(By.cssSelector(".WOH-forgotPasswordQuestions-secQuestion2Input")).sendKeys("x");
		driver.findElement(By.cssSelector(".WOH-forgotPasswordQuestions-secQuestion3Input")).sendKeys("y");
		driver.findElement(By.cssSelector(".WOH-forgotPasswordQuestions-submitButton")).click();
		
		Thread.sleep(500);
		
		//dealing with an alert
				String mainWindow = driver.getWindowHandle();
				Alert alert = driver.switchTo().alert();
				if(alert.getText().equals("Incorrect answer to security questions. Please try again!")){
					System.out.println("forgotPasswordInvalidQuestionAnswers test is successful");
				}
				alert.accept();
				driver.switchTo().window(mainWindow);
		}
	
	@Test
	public void forgotPasswordOneInvalidQuestionAnswer() throws Exception 
	{
		//Progress Through forgotPassword Steps
		driver.findElement(By.xpath("//a[@href='WOH-forgotPasswordUsername.jsp']")).click();
		Thread.sleep(2000);		
		driver.findElement(By.cssSelector(".WOH-forgotPasswordUsername-UsernameInput")).sendKeys("user1");
		driver.findElement(By.cssSelector(".WOH-forgotPasswordUsername-submitButton")).click();
		
		Thread.sleep(2000);
		
		//Invalid Security Answers
		driver.findElement(By.cssSelector(".WOH-forgotPasswordQuestions-secQuestion1Input")).sendKeys("a");
		driver.findElement(By.cssSelector(".WOH-forgotPasswordQuestions-secQuestion2Input")).sendKeys("b");
		driver.findElement(By.cssSelector(".WOH-forgotPasswordQuestions-secQuestion3Input")).sendKeys("y");
		driver.findElement(By.cssSelector(".WOH-forgotPasswordQuestions-submitButton")).click();
		
		Thread.sleep(500);
		
		//dealing with an alert
				String mainWindow = driver.getWindowHandle();
				Alert alert = driver.switchTo().alert();
				if(alert.getText().equals("Incorrect answer to security questions. Please try again!")){
					System.out.println("forgotPasswordOneInvalidQuestionAnswer test is successful");
				}
				alert.accept();
				driver.switchTo().window(mainWindow);
		}
	
	@Test
	public void forgotPasswordTwoInvalidQuestionAnswers() throws Exception 
	{
		//Progress Through forgotPassword Steps
		driver.findElement(By.xpath("//a[@href='WOH-forgotPasswordUsername.jsp']")).click();
		Thread.sleep(2000);		
		driver.findElement(By.cssSelector(".WOH-forgotPasswordUsername-UsernameInput")).sendKeys("user1");
		driver.findElement(By.cssSelector(".WOH-forgotPasswordUsername-submitButton")).click();
		
		Thread.sleep(2000);
		
		//Invalid Security Answers
		driver.findElement(By.cssSelector(".WOH-forgotPasswordQuestions-secQuestion1Input")).sendKeys("a");
		driver.findElement(By.cssSelector(".WOH-forgotPasswordQuestions-secQuestion2Input")).sendKeys("x");
		driver.findElement(By.cssSelector(".WOH-forgotPasswordQuestions-secQuestion3Input")).sendKeys("y");
		driver.findElement(By.cssSelector(".WOH-forgotPasswordQuestions-submitButton")).click();
		
		Thread.sleep(500);
		
		//dealing with an alert
				String mainWindow = driver.getWindowHandle();
				Alert alert = driver.switchTo().alert();
				if(alert.getText().equals("Incorrect answer to security questions. Please try again!")){
					System.out.println("forgotPasswordTwoInvalidQuestionAnswers test is successful");
				}
				alert.accept();
				driver.switchTo().window(mainWindow);
		}
	
	@Test
	public void forgotPasswordValidQuestionAnswers() throws Exception 
	{
		//Progress Through forgotPassword Steps
		driver.findElement(By.xpath("//a[@href='WOH-forgotPasswordUsername.jsp']")).click();
		Thread.sleep(2000);		
		driver.findElement(By.cssSelector(".WOH-forgotPasswordUsername-UsernameInput")).sendKeys("user1");
		driver.findElement(By.cssSelector(".WOH-forgotPasswordUsername-submitButton")).click();
		
		Thread.sleep(2000);
		
		//Valid Security Answers
		driver.findElement(By.cssSelector(".WOH-forgotPasswordQuestions-secQuestion1Input")).sendKeys("a");
		driver.findElement(By.cssSelector(".WOH-forgotPasswordQuestions-secQuestion2Input")).sendKeys("b");
		driver.findElement(By.cssSelector(".WOH-forgotPasswordQuestions-secQuestion3Input")).sendKeys("c");
		driver.findElement(By.cssSelector(".WOH-forgotPasswordQuestions-submitButton")).click();
		
		Thread.sleep(500);
		
		if(driver.findElements(By.xpath("//*[contains(text(),'" + "New Password" + "')]")) != null){
			System.out.println("forgotPasswordValidSecurityQuestions test is Successful");
		}
	}
	
	@Test
	public void forgotPasswordNewPassword() throws Exception 
	{
		//Progress Through forgotPassword Steps
		driver.findElement(By.xpath("//a[@href='WOH-forgotPasswordUsername.jsp']")).click();
		Thread.sleep(2000);		
		driver.findElement(By.cssSelector(".WOH-forgotPasswordUsername-UsernameInput")).sendKeys("user1");
		driver.findElement(By.cssSelector(".WOH-forgotPasswordUsername-submitButton")).click();
		
		Thread.sleep(2000);
		
		//Valid Security Answers
		driver.findElement(By.cssSelector(".WOH-forgotPasswordQuestions-secQuestion1Input")).sendKeys("a");
		driver.findElement(By.cssSelector(".WOH-forgotPasswordQuestions-secQuestion2Input")).sendKeys("b");
		driver.findElement(By.cssSelector(".WOH-forgotPasswordQuestions-secQuestion3Input")).sendKeys("c");
		driver.findElement(By.cssSelector(".WOH-forgotPasswordQuestions-submitButton")).click();
		
		Thread.sleep(2000);
		
		driver.findElement(By.cssSelector(".WOH-forgotPassword-passwordInput")).sendKeys("1234");
		driver.findElement(By.cssSelector(".WOH-forgotPassword-submitButton")).click();
		
		//dealing with an alert
		String mainWindow = driver.getWindowHandle();
		Alert alert = driver.switchTo().alert();
		if(alert.getText().equals("Password Updated Successfully")){
			System.out.println("forgotPasswordNewPassword test is successful");
		}
		alert.accept();
		driver.switchTo().window(mainWindow);
	}
	
	@Test
	public void registerNot3SecurityQuestions() throws Exception 
	{
		driver.findElement(By.xpath("//a[@href='WOH-register.jsp']")).click();
		Thread.sleep(2000);	
		driver.findElement(By.cssSelector(".WOH-register-UsernameInput")).sendKeys("user82");
		driver.findElement(By.cssSelector(".WOH-register-passwordInput")).sendKeys("1234");
		
		//Using a dropdown
		Select secQuestion1 = new Select(driver.findElement(By.cssSelector(".WOH-register-secQuestion1Dropdown")));
		secQuestion1.selectByVisibleText("What is your mother's maiden name?");
		
		Select secQuestion2 = new Select(driver.findElement(By.cssSelector(".WOH-register-secQuestion2Dropdown")));
		secQuestion2.selectByVisibleText("What is the name of your favorite pet?");
		
		//Select secQuestion3 = new Select(driver.findElement(By.cssSelector(".WOH-register-secQuestion3Dropdown")));
		//secQuestion3.selectByVisibleText("What is the last name of your favorite teacher?");
			
		driver.findElement(By.cssSelector(".WOH-register-submitButton")).click();
		
		Thread.sleep(500);
		
		//dealing with an alert
				String mainWindow = driver.getWindowHandle();
				Alert alert = driver.switchTo().alert();
				if(alert.getText().equals("Please select a valid security question")){
					System.out.println("registerNot3SecurityQuestions test is successful");
				}
				alert.accept();
				driver.switchTo().window(mainWindow);		
	}
	
	
	
	@Test
	public void registerSameSecurityQuestions() throws Exception 
	{
		driver.findElement(By.xpath("//a[@href='WOH-register.jsp']")).click();
		Thread.sleep(2000);	
		driver.findElement(By.cssSelector(".WOH-register-UsernameInput")).sendKeys("user82");
		driver.findElement(By.cssSelector(".WOH-register-passwordInput")).sendKeys("1234");
		
		//Using a dropdown
		Select secQuestion1 = new Select(driver.findElement(By.cssSelector(".WOH-register-secQuestion1Dropdown")));
		secQuestion1.selectByVisibleText("What is your mother's maiden name?");
		
		Select secQuestion2 = new Select(driver.findElement(By.cssSelector(".WOH-register-secQuestion2Dropdown")));
		secQuestion2.selectByVisibleText("What is the name of your favorite pet?");
		
		Select secQuestion3 = new Select(driver.findElement(By.cssSelector(".WOH-register-secQuestion3Dropdown")));
		secQuestion3.selectByVisibleText("What is the name of your favorite pet?");
			
		driver.findElement(By.cssSelector(".WOH-register-submitButton")).click();
		
		Thread.sleep(500);
		
		//dealing with an alert
				String mainWindow = driver.getWindowHandle();
				Alert alert = driver.switchTo().alert();
				if(alert.getText().equals("Please select 3 unique security questions")){
					System.out.println("registerSameSecurityQuestions test is successful");
				}
				alert.accept();
				driver.switchTo().window(mainWindow);		
	}
	
	@Test
	public void registerNoUsernameOrPassword() throws Exception 
	{
		driver.findElement(By.xpath("//a[@href='WOH-register.jsp']")).click();
		Thread.sleep(2000);	
		
		//Using a dropdown
		Select secQuestion1 = new Select(driver.findElement(By.cssSelector(".WOH-register-secQuestion1Dropdown")));
		secQuestion1.selectByVisibleText("What is your mother's maiden name?");
		driver.findElement(By.cssSelector(".WOH-register-secQuestion1Input")).sendKeys("a");
		
		Select secQuestion2 = new Select(driver.findElement(By.cssSelector(".WOH-register-secQuestion2Dropdown")));
		secQuestion2.selectByVisibleText("What is the name of your favorite pet?");
		driver.findElement(By.cssSelector(".WOH-register-secQuestion2Input")).sendKeys("b");
		
		Select secQuestion3 = new Select(driver.findElement(By.cssSelector(".WOH-register-secQuestion3Dropdown")));
		secQuestion3.selectByVisibleText("What is the last name of your favorite teacher?");
		driver.findElement(By.cssSelector(".WOH-register-secQuestion3Input")).sendKeys("a");
			
		driver.findElement(By.cssSelector(".WOH-register-submitButton")).click();
		
		Thread.sleep(500);
		
		//dealing with an alert
				String mainWindow = driver.getWindowHandle();
				Alert alert = driver.switchTo().alert();
				if(alert.getText().equals("Please enter a username and password")){
					System.out.println("registerNoUsernameOrPassword test is successful");
				}
				alert.accept();
				driver.switchTo().window(mainWindow);		
	}
	
	
	
	@Test
	public void registerNoPassword() throws Exception 
	{
		driver.findElement(By.xpath("//a[@href='WOH-register.jsp']")).click();
		Thread.sleep(2000);	
		
		driver.findElement(By.cssSelector(".WOH-register-UsernameInput")).sendKeys("user82");
		
		//Using a dropdown
		Select secQuestion1 = new Select(driver.findElement(By.cssSelector(".WOH-register-secQuestion1Dropdown")));
		secQuestion1.selectByVisibleText("What is your mother's maiden name?");
		driver.findElement(By.cssSelector(".WOH-register-secQuestion1Input")).sendKeys("a");
		
		Select secQuestion2 = new Select(driver.findElement(By.cssSelector(".WOH-register-secQuestion2Dropdown")));
		secQuestion2.selectByVisibleText("What is the name of your favorite pet?");
		driver.findElement(By.cssSelector(".WOH-register-secQuestion2Input")).sendKeys("b");
		
		Select secQuestion3 = new Select(driver.findElement(By.cssSelector(".WOH-register-secQuestion3Dropdown")));
		secQuestion3.selectByVisibleText("What is the last name of your favorite teacher?");
		driver.findElement(By.cssSelector(".WOH-register-secQuestion3Input")).sendKeys("a");
			
		driver.findElement(By.cssSelector(".WOH-register-submitButton")).click();
		
		Thread.sleep(500);
		
		//dealing with an alert
				String mainWindow = driver.getWindowHandle();
				Alert alert = driver.switchTo().alert();
				if(alert.getText().equals("Please enter a username and password")){
					System.out.println("registerNoUsernameOrPassword test is successful");
				}
				alert.accept();
				driver.switchTo().window(mainWindow);		
	}
	
	
	@Test
	public void registerSuccess() throws Exception 
	{
		driver.findElement(By.xpath("//a[@href='WOH-register.jsp']")).click();
		Thread.sleep(2000);	
		driver.findElement(By.cssSelector(".WOH-register-UsernameInput")).sendKeys("user82");
		driver.findElement(By.cssSelector(".WOH-register-passwordInput")).sendKeys("1234");
		
		//Using a dropdown
		Select secQuestion1 = new Select(driver.findElement(By.cssSelector(".WOH-register-secQuestion1Dropdown")));
		secQuestion1.selectByVisibleText("What is your mother's maiden name?");
		
		Select secQuestion2 = new Select(driver.findElement(By.cssSelector(".WOH-register-secQuestion2Dropdown")));
		secQuestion2.selectByVisibleText("What is the name of your favorite pet?");
		
		Select secQuestion3 = new Select(driver.findElement(By.cssSelector(".WOH-register-secQuestion3Dropdown")));
		secQuestion3.selectByVisibleText("What is the last name of your favorite teacher?");
			
		driver.findElement(By.cssSelector(".WOH-register-submitButton")).click();
		
		Thread.sleep(500);
		
		if(driver.getTitle().equals("Log In")){
			System.out.println("registerSuccess is successful");
		}				
	}	
	
}

	
