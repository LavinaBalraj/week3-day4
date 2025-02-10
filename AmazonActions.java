package week3.day4;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class AmazonActions {

	public static void main(String[] args) throws IOException {

		//Initialize ChromeDriver 
	    ChromeDriver driver=new ChromeDriver();
	    //Load the URL
		driver.get("https://www.amazon.in/");
		// Maximize the browser window
		driver.manage().window().maximize();
		//Implicit wait to ensure the web page elements are fully loaded
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//Search for oneplus 9 pro
		      //Find Element and Enter the Search text 
	     driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro");
	          //Find Element and Select the Search icon
	     driver.findElement(By.id("nav-search-submit-button")).click();
	     
		//Get the price of the first product
	    //System.out.println(driver.findElement(By.xpath("(//span[@class='a-price'])[1]")).getText());                    //Method1
	     String price1 = driver.findElement(By.xpath("(//span[@class='a-price'])[1]")).getText();                         //Method2 
		 System.out.println("Price of the product: "+price1);
		 
	     //Print the number of customer ratings for the first displayed product
		driver.findElement(By.xpath("(//a[@class='a-popover-trigger a-declarative'])[1]")).click();
	           //Number of ratings
	   //System.out.println(driver.findElement(By.xpath("(//h2[@id='acr-popover-title']/span)[3]")).getText());
	    String ratings = driver.findElement(By.xpath("(//h2[@id='acr-popover-title']/span)[1]")).getText();
	    System.out.println("Ratings of the product: "+ratings);
	   
	    //Click the first text link of the first image
	    driver.findElement(By.xpath("(//a[@class='a-link-normal s-line-clamp-2 s-link-style a-text-normal'])[1]")).click();
		
	    //Take a screenshot of the product displayed
	           //Switch to driver
	    Set<String> windowHandles = driver.getWindowHandles();
	    List<String> allwindow = new ArrayList<String>(windowHandles);
	    driver.switchTo().window(allwindow.get(1));
	    
	           //Screenshot of the product
	    WebElement screenshot = driver.findElement(By.xpath("//li[@class='image item itemNo0 maintain-height selected']"));
	    File source = screenshot.getScreenshotAs(OutputType.FILE);
	    File destination = new File ("./Screenshot/PhoneInAmazon.png");
	    FileUtils.copyFile(source, destination);
	    
		//Click the Add to Cart button
	    driver.findElement(By.xpath("(//input[@id='add-to-cart-button'])[2]")).click();
	    
		//Get the cart subtototal and Verify if it is correct
	    //System.out.println(driver.findElement(By.xpath("//div[@class='a-column a-span11 a-text-left a-spacing-top-large']")).getText());
	    String price2 = driver.findElement(By.xpath("//div[@class='a-column a-span11 a-text-left a-spacing-top-large']")).getText();
	    System.out.println("Price in cart: "+price2);          
	    	    
		//Close the browser
	   // driver.quit();                                                             //To close all windows
	    driver.close();                                                              //To close child window
	    driver.switchTo().window(allwindow.get(0));                                  //To close parent window
	    driver.close(); 

	}

}
