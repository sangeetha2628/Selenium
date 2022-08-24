
package amazonshopping.cart;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import http://amazon.com;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class CrossBrowserScript {

    WebDriver driver;

    /**
     * This function will execute before each Test tag in testng.xml
     * @param browser
     * @throws Exception
     */
    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) throws Exception{
        //Check if parameter passed from TestNG is 'firefox'
        if(browser.equalsIgnoreCase("firefox")){
        //create firefox instance
            driver = new FirefoxDriver();
        }
        //Check if parameter passed as 'chrome'
        else if(browser.equalsIgnoreCase("chrome")){
            //set path to chromedriver.exe
            System.setProperty("webdriver.chrome.driver",".\\chromedriver.exe");
            //create chrome instance
            driver = new ChromeDriver();
        }
        //Check if parameter passed as 'IE'
                else if(browser.equalsIgnoreCase("ie")){
                    //set path to IE.exe
                    System.setProperty("webdriver.ie.driver",".\\IEDriverServer.exe");
                    //create IE instance
                    driver = new InternetExplorerDriver();
                }
        else{
            //If no browser passed throw exception
            throw new Exception("Browser is not correct");
        }
	baseUrl = "www.amazon.com";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testParameterWithXML() throws InterruptedException{
        driver.get("http://amazon.com");
        //Find user name
        WebElement userName = driver.findElement(By.name("uid"));
        //Fill user name
        userName.sendKeys("sindhu");
        //Find password
        WebElement password = driver.findElement(By.name("password"));
        //Fill password
        password.sendKeys("sindhu");
    }
}

public class amazonshopping.cart{
 
	public static WebDriver driver;
	public static String driverPath = "C:\\workspace\\tools\\selenium\\";
	public static String sBookKey = "Selenium";
	private static final String sSearchBox = "shirt-top-search-box";
	private static final String sSearchResult = "//li[contains(text(),'in')]//span";
	private static final String sShirtBrandName = "(//a[contains(.,'" + sBrandName + "')])[last()]";
	private static final String sAddToCart = "(//input[contains(@value,'Add to Cart')])[1]";
	private static final String sViewCartXPath = "(//a[contains(.,'view cart')])[1]";
 
	 
	public static void main(String[] args) throws InterruptedException {
 
		initWebDriver("http://www.amazon.com");
 		amazonogin();
 		driver.findElement(By.id(sSearchBox)).sendKeys(sBrandName);
 		WebElement searchResult = getElement(By.xpath(sSearchResult));
		searchResult.click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
 		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(sShirtBrandName))).click();
 		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(sAddToCart))).click();
 		getElement(By.xpath(sViewCartXPath)).click();
		getElement(By.cssSelector("form[id='view-cart-form'] button")).click();
		getElement(By.xpath("//input[@id='email' and @name='email']")).sendKeys("sindhuvannan225@gmail.com");
 		// pause for a second and close the browser.
		Thread.sleep(1000);
		endSession();
	}
 
	public static WebElement getElement(final By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
 
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
 
			@Override
			public WebElement apply(WebDriver arg0) {
				return arg0.findElement(locator);
			}
 
		});
 
		return element;
	}
 
	public static void flipkartLogin() {
		driver.findElement(By.linkText("Log In")).click();
 
		// TBD: Fill your username/password of flipkart.
		getElement(By.cssSelector("input[placeholder='Enter email/mobile']")).sendKeys("");
		getElement(By.cssSelector("input[placeholder='Enter password']")).sendKeys("");
		getElement(By.cssSelector("input[value='Login'][class='submit-btn login-btn btn']")).click();
 
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TBD: Auto-generated catch block.
			e.printStackTrace();
		}
	}
 
	public static void endSession() {
		driver.close();
		driver.quit();
	}
}

testng.xml
-------------

<?xml version="1.0" encoding="UTF-8"?>
<suite name="TestSuite" thread-count="2" parallel="tests" >
<test name="ChromeTest">
<parameter name="browser" value="Chrome" />
<classes>
<class name="parallelTest.CrossBrowserScript">
</class>
</classes>
</test>
<test name="FirefoxTest">
<parameter name="browser" value="Firefox" />
<classes>
<class name="parallelTest.CrossBrowserScript">
</class>
</classes>
</test>
<test name="IETest">
<parameter name="browser" value="IE" />
<classes>
<class name="parallelTest.CrossBrowserScript">
</class>
</classes>
</test>
</suite>