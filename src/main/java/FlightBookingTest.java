import util.CommonFunctions;
import util.TestBase;
import util.Util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.List;

public class FlightBookingTest extends TestBase{

    @Test
    public void testThatResultsAppearForAOneWayJourney() throws Exception {

    	invokebrowser();
    	
    	//selecting the trip type
    	CommonFunctions.clickWebelement(driver.findElement(By.id("OneWay")), "OneWay");

    	CommonFunctions.Sendkeys( driver.findElement(By.id("FromTag")), "Bangalore", "Departure City");

    	//wait for the auto complete options to appear for the origin
        List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
        originOptions.get(0).click();
        Util.waitFor(1500);
        
        CommonFunctions.Sendkeys( driver.findElement(By.id("ToTag")), "Delhi", "Destination City");

        //select the first item from the destination auto complete list
        List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
        destinationOptions.get(0).click();

        // Selecting the date according to that passing date.
        Util.selectDate(5);

        //all fields filled in. Now click on search
        CommonFunctions.clickWebelement( driver.findElement(By.id("SearchBtn")),"Search Button");
        Util.waitFor(5000);

        //verify that result appears for the provided journey search
        Assert.assertTrue(CommonFunctions.elementIsVisible(driver.findElement(By.className("searchSummary")), "searchSummary"));

    }
    
    @AfterTest
    public void closedriver() {
    	driver.quit();
    }

}
