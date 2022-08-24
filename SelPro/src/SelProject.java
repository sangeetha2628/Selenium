import org.openqa.selenium.WebDriver;

//import org.junit.Assert;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;

public class SelProject {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "⁨chromedriver");
		WebDriver driver = new WebDriver();
		driver.navigate().to("https://neowise.examly.io/");
		String title = driver.getTitle();
		Assert.assertEquals(title,"NeoWise");
		/*if(title.equalsIgnoreCase(title))
			System.out.println("Title Matched");
		else
			System.out.println("Title didn't match");*/
		driver.close();
		driver.quit();

	}

}
