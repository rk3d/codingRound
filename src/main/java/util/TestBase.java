package util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {
	
	public static WebDriver driver;
	public static boolean skip = false;
	public static boolean fail = false;
	   
	public void invokebrowser() {
		
		Util.setDriverPath();;
        driver = new ChromeDriver();
        driver.get("https://www.cleartrip.com/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	

}
