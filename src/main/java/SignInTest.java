import util.TestBase;
import util.Util;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class SignInTest extends TestBase {

	@Test
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
		try {
			invokebrowser();

			driver.findElement(By.linkText("Your trips")).click();
			driver.findElement(By.id("SignIn")).click();
			Util.waitFor(5000);
			int size = driver.findElements(By.tagName("iframe")).size();
	        System.out.println(size);
			driver.switchTo().frame("modal_window");
			new WebDriverWait(driver, 1)
					.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("signInButton"))));
			driver.findElement(By.id("signInButton")).click();

			String errors1 = driver.findElement(By.id("errors1")).getText();
			Assert.assertTrue(errors1.contains("There were errors in your submission"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 @AfterTest
	    public void closedriver() {
	    	driver.quit();
	    }

}
