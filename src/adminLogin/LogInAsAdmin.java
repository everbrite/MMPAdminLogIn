/**
 * 
 */
package adminLogin;

import java.sql.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Saigeetha
 * This class is written to LogIn with Admin Credentials.
 *http://96.84.175.78/MMP-Release2-Admin-Build.2.1.000/login.php- Admin Login URL 
 */

//// All web elements in the page are the data members of this class.
public class LogInAsAdmin {

	public WebDriver driver;
	public WebDriverWait wait;
	By uname=By.xpath("//input[@id='username']");
	By pwd=By.xpath("//input[@id='password']");
	By submit= By.xpath("//input[@type='submit']");
	By register= By.xpath("//input[@value='Register']");
	By forgotUnameandPwdLink=By.xpath("//a[contains(text(),'forgot Username')]");
	String url="http://96.84.175.78/MMP-Release2-Admin-Build.2.1.000/login.php";
	By iframe=By.xpath("//iframe[@id='myframe']");
	By closeTheIframeButton=By.xpath("//tr/td/button[@class='ui-dialog-titlebar-close']");
	//clicks on patients link in Admin module
	By PatientsLink=By.xpath("/html/body/div/div/div[1]/div/ul/li[5]/a/span");
	// Enter Patient name in the text box web element locator path.
	By enterName=By.xpath("//div/input[@name='search']");
	//click on search button
	By search=By.xpath("//input[@value='search']");
	//*[@id='show']
	// Create visit button
	By createVisit=By.xpath("//input[@value='Create Visit']");
	// Add Prescription
	By addPrescription=By.xpath("//input[@value='Add Precription']");
	// Create Fee
	By createFee=By.xpath("//input[@value='Create Fee']");
	//create Reports
	By reports =By.xpath("//input[@value='Reports']");
	
	// constructor
	public LogInAsAdmin( WebDriver driver)
	{
		this.driver=driver;

		wait= new WebDriverWait(driver,15);

		// initialize the url

		driver.get(url);
		driver.manage().window().maximize();

	}
	// This method will take a username and password and logs into Admin module.

	public void LogInToAdmin(String username, String password,String pname)
	{
		wait.until(ExpectedConditions.presenceOfElementLocated(uname));
		driver.findElement(uname).clear();
		driver.findElement(uname).sendKeys(username);
		driver.findElement(pwd).clear();
		driver.findElement(pwd).sendKeys(password);
		wait.until(ExpectedConditions.elementToBeClickable(submit));
		driver.findElement(submit).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(PatientsLink));
		driver.findElement(PatientsLink).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(enterName));
		driver.findElement(enterName).sendKeys(pname);
		driver.findElement(search).click();

	}

	public boolean pageNavigationValidation(String expectedurl)
	{	
		String e= driver.getCurrentUrl();
		System.out.println(e);

		if(expectedurl.equals(e))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	// This method must be called after an admin user logs in and
	// a table of patient appointments for the current day is displayed.
	// This method takes 1 input parameter "Pname", the name  of the patient
	// to click so that his appointment details can be viewed.
	// This method returns true if the input patient name is found in the table
	// and upon clicking reveals his details.
	// It returns false otherwise.
	/*public boolean clickOnPatientName(String Pname)
	{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody/tr/td/a[@id='opener']")));
		List<WebElement> PatientNames = driver.findElements(By.xpath("//tbody/tr/td/a[@id='opener']"));
		System.out.println(PatientNames.size());
		if(PatientNames.isEmpty())
		{
			return false; // no patient names in table
		}
		
		
		for(WebElement ele : PatientNames) // go through each patient in table
		{
			String s=ele.getText();
			if(s.contains(Pname))   // is there a match for the input patient name?
			{
				ele.click(); // click on the matched patient
				
				driver.switchTo().frame(driver.findElement(iframe));  
				
				// search for td element in frame that contains "patient name" label
				WebElement pnameTd =
				 driver.findElement(By.xpath("//tbody/tr/td[contains(text(),'Patient Name:')]"));
				
				// Go to the parent <tr> element
				WebElement parentTr = pnameTd.findElement(By.xpath("./.."));
				
				// 2nd child <td> element contains patient name
				By secondChildBy = By.xpath("./td[2]");
				WebElement secondChildTd = parentTr.findElement(secondChildBy);
				
				String secondChildText = secondChildTd.getText();
				System.out.println("2nd child txt " + secondChildText);
				
				driver.findElement(closeTheIframeButton).click();
				
				if (secondChildText.contains(Pname))
				{					
					return true;  // clicking reveals input patient  details
				}
				else
				{
					return false;  // clicking does not reveal input patient  details
				}
			}
			System.out.println(s);
			/// If we are here no patient name has matched with pname
			return false;
			
		}
		*/
		public boolean patientNameClick(String name)
		{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody/tr/td/a[@href='patientapp.php?pid=2725']")));
			List <WebElement> names =driver.findElements(By.xpath("//tbody/tr/td/a[@href='patientapp.php?pid=2725']"));
			int s=names.size();
			System.out.println(s);
			if(names.isEmpty())
			{
				return false;
			}
			
			for (WebElement n : names)
			{
				String p=n.getText();
				if(p.contains(name)) 
				{
					n.click();
					driver.findElement(createVisit).click();
					return true;

					
				}
			
			}
			return false;
		}
			
			//given a doctor name,this method will click on Book Appointment button under the specified doctor with appt date,time  
			//and hit the continue button.
			public boolean selectDoctor(String DrName,String DayOfTheMonth,String HourOfTheDay,String Symptoms)
			{
				List<WebElement> drName= driver.findElements(By.xpath("//td/ul/li/h4"));
						int s1=drName.size();
				System.out.println(s1);
				if(drName.isEmpty())
						{
							return false;
						}
				for (WebElement dr:drName)
				{
					String d=dr.getText();
					if(d.contains(DrName))
					{
					WebElement BookApptButton=	dr.findElement(By.xpath("./../../../button[@id='opener']"));
					BookApptButton.click();
					//WebElement frame=driver.findElement(By.xpath("//iframe[@id='myframe']"));
					//driver.switchTo().frame(frame);
					driver.switchTo().frame("myframe");
				WebElement date=driver.findElement(By.xpath("//p[contains(text(),'Date:')]/input"));
					date.click();
					List<WebElement> day=driver.findElements(By.xpath("//table/tbody/tr/td/a"));
					int s2=day.size();
					System.out.println(s2);
					for(WebElement d1:day)
					{
						String d2=d1.getText();
						if(d2.contains(DayOfTheMonth))
						{
							d1.click();
						}
						Select sel =new Select(driver.findElement(By.id("time")));
						sel.selectByVisibleText(HourOfTheDay);
						driver.findElement(By.id("ChangeHeatName")).click();
						 WebElement writesymptoms=driver.findElement(By.xpath("//form/textarea[contains(text(),'Write Symptons here')]"));
						 writesymptoms.clear();
						 writesymptoms.sendKeys(Symptoms);
						 driver.findElement(By.xpath("//input[@type='submit']")).click();

							return true;
					}
					
					
					
					}
				
					
				}
				return false;
				
						
			}
			
		
			
			
			
					
					//*[@id="datepicker"]
				
			
			
		
		
		
		
		
		


}
	
		
		


	






