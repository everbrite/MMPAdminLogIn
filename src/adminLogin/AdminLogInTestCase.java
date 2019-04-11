/**
 * 
 */
package adminLogin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author Saigeetha
 *
 */
public class AdminLogInTestCase 
{
	String saigeetha;
	LogInAsAdmin  la;

		public WebDriver driver;
		@BeforeClass
		@Parameters({ "url"})
		public void initialize(String u)
		{
			WebDriverManager.chromedriver().setup();
			
			 driver=new ChromeDriver();
			//driver.manage().window().maximize();
			
		}
		
		
		
		@Parameters({"userid", "password", "expectedurl","pname"})
		@Test(priority =0)
		public void AdminSignIn(String name,String password,String expectedurl,String pname)
		{
			 la=new LogInAsAdmin(driver);

			
			la.LogInToAdmin(name, password,pname);
			boolean result=la.pageNavigationValidation(expectedurl);
	//		Assert.assertTrue(result);
			
		}
		
		/*@Parameters({"patientName"})
		@Test(priority=1,enabled=false)
		public void clickOnPatientNameTest(String name)
		{
			boolean result=la.clickOnPatientName(name);
			Assert.assertTrue(result);
			
		}*/
		
		@Parameters({"name"})
		@Test(priority=1)
		public void patientNameClickTest(String name)
		{
			boolean result=la.patientNameClick(name);
			Assert.assertTrue(result);
		}
		
		@Parameters({"doctorName","ApptMonthDay","ApptDayHour","symptoms"})
		@Test(priority=2)
		public void scheduleApptTest(String doctorName,String ApptMonthDay,String ApptDayHour,String symptoms)
		{
			boolean result=la.selectDoctor(doctorName,ApptMonthDay,ApptDayHour,symptoms);
			
		    Assert.assertTrue(result);
		    Assert.assertEquals(driver.getCurrentUrl(),"http://96.84.175.78/MMP-Release2-Admin-Build.2.1.000/patients.php");
		}

}
