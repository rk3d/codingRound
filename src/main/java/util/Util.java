package util;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.sun.javafx.PlatformUtil;


@SuppressWarnings("restriction")
public class Util extends TestBase{
	
	public static int Depmonth = 0;
	public static String depmonth = "";

	public static void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }
	
	public static void waitFor(int durationInMilliSeconds) {
	        try {
	            Thread.sleep(durationInMilliSeconds);
	        } catch (InterruptedException e) {
	            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	        }
	    }
	
	/**
	 * This method is used to calculate the exact date what we are passing from the
	 * excel and checking that day flights are available are not. If flights not
	 * available automatically select the next available dates
	 * 
	 * @throws Exception
	 **/
	public static void selectDate(int days) throws Exception {
		
		JavascriptExecutor jse=(JavascriptExecutor) driver;

		String date = CommonFunctions.getDatefromToday(days);
		String[] givendate = date.split("\\/");

		int givenMonth = Integer.parseInt(givendate[0]);
		int dates = Integer.parseInt(givendate[1]);

		Long startTime = System.currentTimeMillis();

		Map<String,Integer> myMap = new HashMap<String,Integer>();
        myMap.put("january", 1);
        myMap.put("february", 2);
        myMap.put("march", 3);
        myMap.put("april", 4);
        myMap.put("may", 5);
        myMap.put("june", 6);
        myMap.put("july", 7);
        myMap.put("august", 8);
        myMap.put("september", 9);
        myMap.put("october", 10);
        myMap.put("november", 11);
        myMap.put("december", 12);
				
		try {
			for (int loop = 0; loop < 4; loop++) {
				
				String month = Integer.toString(givenMonth);
				SimpleDateFormat monthParse = new SimpleDateFormat("MM");
				SimpleDateFormat monthDisplay = new SimpleDateFormat("MMMM");
				String currentmonth = monthDisplay.format(monthParse.parse(month));

				if (driver.findElement(By.xpath("//*[@class='ui-datepicker-month']")).getText().toUpperCase().equalsIgnoreCase(currentmonth.toUpperCase())) {

					do {
						if (driver.findElements(By.xpath("(//*[@class='calendar'])[1]//td[@data-handler='selectDay']")).size() != 0) {
							try {
								WebElement ele = driver.findElement(
										By.xpath("(//*[@class='calendar'])[1]//td[@data-handler='selectDay']/a[text()='" + dates + "']"));
								jse.executeScript("arguments[0].click();", ele);
								CommonFunctions
										.logMessage("Selected Travel Date is " + dates + "/" + givenMonth + "/2018");
								Depmonth = givenMonth;
								break;
							} catch (Exception e) {
								dates++;
								if (dates >= 30) {
									dates = 1;
									driver.findElement(By.xpath("//a[contains(@class,'nextMonth')]")).click();
									if (givenMonth == 12) {
										givenMonth = 1;
									} else {
										givenMonth++;
									}
								}
							}
						} else {
							driver.findElement(By.xpath("//a[contains(@class,'nextMonth')]")).click();
							if (givenMonth == 12) {
								givenMonth = 1;
							} else {
								givenMonth = givenMonth + 1;
							}
						}

					} while ((System.currentTimeMillis() - startTime) < 400000);
					break;
				} else {
					
					if(myMap.get(driver.findElement(By.xpath("//*[@class='ui-datepicker-month']")).getText().toLowerCase())<givenMonth) {
						driver.findElement(By.xpath("//a[contains(@class,'nextMonth')]")).click();
					}else {
						givenMonth++;
						Thread.sleep(2000);
					}
					
					
					Thread.sleep(2000);
				}

			}
		} catch (Exception e) {
			CommonFunctions.logErrorMessagestopExecution("Error while selecting the Date pls refer the screenshot");
		}
	}
	
}
