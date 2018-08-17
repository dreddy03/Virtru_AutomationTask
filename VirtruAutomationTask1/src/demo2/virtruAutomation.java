package demo2;


import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class virtruAutomation  {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    baseUrl = "https://accounts.google.com/signin";
  }

  @Test
  public void test1() throws Exception {
	 
	  System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/chromedriver");
	driver = new ChromeDriver();
	
	//Go to Google Signin page
    driver.get(baseUrl);
    driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
    
    //Enter Username
    driver.findElement(By.id("identifierId")).clear();
    driver.findElement(By.id("identifierId")).sendKeys("dreddyhelp03");
    
    //Click on Next
    driver.findElement(By.xpath("//content[(text() = 'Next' or . = 'Next')]")).click();
    driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
    
    //Enter password
    driver.findElement(By.name("password")).sendKeys("password123#");
   // WebDriverWait wait = new WebDriverWait(driver, 30000);
    
    //Click on Next
    click_Element(driver,"//content[(text() = 'Next' or . = 'Next')]");
    click_Element(driver,"//a[@href = 'https://mail.google.com']");
    
    //Click on the encrypted email 
    click_Element(driver,"//span[(text() = 'Test subject' or . = 'Test subject')]");
    driver.getWindowHandle();
    
    //Click on Unlock message
    click_Element(driver,"//*[@class='CToWUd']");
    Thread.sleep(4000);
    System.out.println("Curently on " + driver.getTitle() + " tab");
    
    //Switch to Virtru window
    for (String childTab:driver.getWindowHandles())
    {
    	driver.switchTo().window(childTab);
    	
    }
    System.out.println("Now switched to " + driver.getTitle() + "tab");
    
  //Click on email address
    click_Element(driver,"//span[@class='userEmail']");
    
  //Click on Unlock message
    click_Element(driver,"//a[@class='btn btn-lg auth-choice-btn oauthButton']");
    
  //Click on the email body
    String myEmailbody = get_Text(driver,"//span[@id='tdf-body']/div");
    System.out.println("Body of the encrypted email is " + myEmailbody);
   //Validating if the body contains the expected text
    //assertEquals("Test body", driver.findElement(By.xpath("//span[@id='tdf-body']/div")).getText());
    Assert.assertEquals("Test body", driver.findElement(By.xpath("//span[@id='tdf-body']/div")).getText());
   
  }
  
  public static void click_Element(WebDriver myDriver,String xPath)
  {
	    WebDriverWait wait = new WebDriverWait(myDriver, 30000);

	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
	  myDriver.findElement(By.xpath(xPath)).click();
  }
  
  public String get_Text(WebDriver myDriver,String xPath)
  {
	    WebDriverWait wait = new WebDriverWait(myDriver, 30000);

	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
	    String text = myDriver.findElement(By.xpath(xPath)).getText();
	    return text;
  }

  
  @After
  public void tearDown() throws Exception {
   driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

}

