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
import org.testng.annotations.AfterTest;
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
		@Test(priority=1, enabled=false)
		public void InsertIntoDatabase() throws SQLException {
			String query="INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, city, country, salesRepEmployeeNumber, creditLimit) VALUES (123, 'New Corp', 'Smith', 'John', '123456789', '123 Main St', 'Los Angeles', 'USA', 1370, 50000.00);";
			
			statment=connection.createStatement();
			int rowEffected=statment.executeUpdate(query);
			System.out.println(rowEffected);
		}
		
		@Test(priority=2,enabled=false)
		public void UpdateDatabase() throws SQLException {
			String query="UPDATE customers SET creditLimit = 76000 WHERE customerNumber = 999;";
			
			statment=connection.createStatement();
			int rowEffected=statment.executeUpdate(query);
			System.out.println(rowEffected);
		}
		
		@Test(priority=3)
		public void ReadDatabase() throws SQLException {
			String query="SELECT * FROM customers WHERE customerNumber = 999;";
			
			statment=connection.createStatement();
			resultSet=statment.executeQuery(query);
			while(resultSet.next()) {
			   String customerName= resultSet.getNString("customerName");
			   System.out.println(customerName);
			}
		}
		
		@Test (priority=4)
		public void DeleteProductDatabase() throws SQLException {
			String query="DELETE FROM customers WHERE  customerNumber = 999;";
			
			statment=connection.createStatement();
			int rowEffected=statment.executeUpdate(query);
			System.out.println(rowEffected);
			if(rowEffected==1) {
			   System.out.println("customer deleted");
			}
			else {
				 System.out.println("customer already deleted or not exists ");
			}
			
		}
		@Test (priority=5)
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
		@AfterTest
		public void tearDown() {
			driver.quit(); 
		}
}
