package SmartBuyWebsiteTest.SmartBuyWebsiteTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest {

	WebDriver driver = new ChromeDriver();
	String URL = "https://smartbuy-me.com/account/register";
	//global objects
		Connection connection;
		Statement statment;
		ResultSet resultSet;
		
		@BeforeTest 
		public void Setup() throws SQLException {
			driver.manage().window().maximize();
			
			driver.get(URL);
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","1234");
		}
		
		@Test (priority=1)
		public void RegisterForm() throws SQLException {
			String query="SELECT * FROM customers WHERE customerNumber = 406;";
			statment=connection.createStatement();
			resultSet=statment.executeQuery(query);
			String customerFirstName="";
			String customerLastName="";
			String customerEmail= "";
		    String customerPassword="";
		    String customerName;
		    String customerNumber;
            while(resultSet.next()) {
            	customerFirstName=resultSet.getString("contactFirstName");
            	customerLastName=resultSet.getString("contactLastName");
            	customerEmail=customerFirstName+customerLastName+"@gmail.com";
            	customerName=resultSet.getString("customerName");
            	customerNumber=resultSet.getString("customerNumber");
            	customerPassword=customerName+customerNumber;
            }
		    
            WebElement firstNameInput=driver.findElement(By.id("customer[first_name]"));
            firstNameInput.sendKeys(customerFirstName);
            WebElement lastNameInput=driver.findElement(By.id("customer[last_name]"));
            lastNameInput.sendKeys(customerLastName);
            WebElement emailInput=driver.findElement(By.id("customer[email]"));
            emailInput.sendKeys(customerEmail);
            WebElement passwordInput=driver.findElement(By.id("customer[password]"));
            passwordInput.sendKeys(customerPassword);
            
		}
}
