import util.CommonFunctions;
import util.TestBase;
import util.Util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class HotelBookingTest extends TestBase{


    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;

    @FindBy(xpath = "//*[@id='ui-id-3']")
    private WebElement selectFirstoption;
    
    

    public HotelBookingTest() {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 1), this);
	}
    
    @Test
    public void shouldBeAbleToSearchForHotels() throws Exception {
        
    	invokebrowser();
    	HotelBookingTest hotel=new HotelBookingTest();
    	
    	CommonFunctions.clickWebelement(hotel.hotelLink, "Hotel Link");

    	CommonFunctions.Sendkeys( hotel.localityTextBox, "Indiranagar, Bangalore", "Localty Text");

    	CommonFunctions.clickWebelement(hotel.selectFirstoption, "Selecting First optin");
    	
    	// Selecting the check-in date according to that passing date.
        Util.selectDate(5);
        Util.waitFor(1500);
        
        // Selecting the check-out date according to that passing date.
        Util.selectDate(8);

        // Selecting the traveleres Selection
        new Select(hotel.travellerSelection).selectByVisibleText("1 room, 2 adults");
        
        CommonFunctions.clickWebelement(hotel.searchButton, "Search Button");

    }

    @AfterTest
    public void closedriver() {
    	driver.quit();
    }

}
