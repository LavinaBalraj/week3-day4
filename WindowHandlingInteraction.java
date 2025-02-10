package week3.day3;
/*- Enter the username and password.
- Click on the Login button.
- Click on the CRM/SFA link.
- Click on the Contacts button.
- Click on Merge Contacts.
- Click on the widget of the "From Contact".
- Click on the first resulting contact.
- Click on the widget of the "To Contact".
- Click on the second resulting contact.
- Click on the Merge button.
- Accept the alert.
- Verify the title of the page
*/

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowHandlingInteraction {

	public static void main(String[] args) {
		ChromeDriver driver=new ChromeDriver();
		 //Load URL
			driver.get("http://leaftaps.com/opentaps/control/login");
			
			//Maximize
			driver.manage().window().maximize();
			
			//Implicit Wait
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
			//Enter username
			driver.findElement(By.id("username")).sendKeys("Demosalesmanager");
			
			// Find Password  and enter data
			driver.findElement(By.id("password")).sendKeys("crmsfa");
			
			// Find login  and click
			driver.findElement(By.className("decorativeSubmit")).click();
			
			// Find CRM/SFA  and click
			driver.findElement(By.linkText("CRM/SFA")).click();
			
			//Click on the Contacts button.
			driver.findElement(By.xpath("//a[text()='Contacts']")).click();
			
			// Click on Merge Contacts.
			driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();
			
			//Click on the widget of the "From Contact".
			driver.findElement(By.xpath("//table[@name='ComboBox_partyIdFrom']/following::a")).click();
			
			//Handle Another Window- Switch to new Window
			Set<String> nextwindow = driver.getWindowHandles();
			List<String> win1=new ArrayList<String>(nextwindow);
			driver.switchTo().window(win1.get(1));
			driver.manage().window().maximize();
			
			//Click on the first resulting contact.
			driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
			
			//switch back to parrent window
			driver.switchTo().window(win1.get(0));
			
			//Click on the widget of the "To Contact".
			driver.findElement(By.xpath("//table[@name='ComboBox_partyIdTo']/following::a")).click();
			
			//Handle Another Window- Switch to new Window
			Set<String> newwindow = driver.getWindowHandles();
			List<String> win2=new ArrayList<String>(newwindow);
			driver.switchTo().window(win2.get(1));
			driver.manage().window().maximize();
			
			//Click on the second resulting contact.
			driver.findElement(By.xpath("((//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[2])")).click();
			
			//switch back to parrent window
			driver.switchTo().window(win2.get(0));
			
			//click on the merge button
			driver.findElement(By.xpath("//a[text()='Merge']")).click();
			
			//Accept Alert
			Alert simpleAlert = driver.switchTo().alert();
			simpleAlert.accept();
			
			String title = driver.getTitle();
			System.out.println("Title of the page is :"+title);
	}

}
