package util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CommonFunctions extends TestBase{
	
	/** This method to print the log message in cat portal 
	 * 
	 * @param messageToLog what are the message we passing that is printing in jenkins and cat portal
	 */
	public static void logMessage(String messageToLog) {

		try {
			System.out.println(messageToLog);
		} catch (Exception e) {
		}
	}
	

	/** This method for prints the error messages and stop the execution 
	 * 
	 * @param messageToLog this is for print the erroe message and stop the execution
	 * @throws Exception
	 */
	public static void logErrorMessagestopExecution(String messageToLog) throws Exception {
		fail = true;
		System.err.println(messageToLog);
		takeSnap(messageToLog);
		throw new Exception();
	}
	
	/** This method for take the screenshot for failed test cases *
	 * 
	 * @param msg it is for storing the screenshot with the error message
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void takeSnap(String msg) throws IOException, InterruptedException {
		DateFormat dateFormat = new SimpleDateFormat("hh_mm_ss");
		Date date = new Date();
		if (msg.length() > 199) {
			msg = msg.substring(0, 100);
		}
		msg = msg.trim() + dateFormat.format(date);
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "//src//testsnaps//" + msg + ".png"));
		Thread.sleep(2000);
	}

	/** This method for Clicking the Web element 
	 * @throws Exception **/
	public static void clickWebelement(WebElement webele, String Ele_name) throws Exception {
		try {
			new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(webele));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", webele);
			logMessage("The " + Ele_name + " is clicked");
		} catch (Exception e) {
			logErrorMessagestopExecution("The " + Ele_name + " is not avilable pls refer the screenshot\n");
		}
	}

	/** This method for Sending the values to the Web element 
	 * @throws Exception **/
	public static void Sendkeys(WebElement webele, String value, String Ele_name) throws Exception {
		try {
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(webele));
			webele.clear();
			webele.sendKeys(value);
			logMessage(value + " is entered into the " + Ele_name + " textbox");
		} catch (Exception e) {
			logErrorMessagestopExecution("\nThe " + Ele_name + " is not avilable pls refer the screenshot\n");
		}

	}

	/** This Method for checking the web element is visible or not
	 * @param webele pass the web element.
	 * @param name we have to pass the name of the element if it is not displayed it prints the error message
	 * @throws Exception
	 */
	public static boolean elementIsVisible(WebElement webele, String name) throws Exception {
		try {
			new WebDriverWait(driver, 1).until(ExpectedConditions.visibilityOf(webele));
	     	return true;
		} catch (Exception e) {
			return false;
		}
		
		
	}
	
	
	/** This Method for checking the web element is clickable or not
	 * @param ele  pass the web element.
	 * @param objname we have to pass the name of the element if it is not displayed it prints the error message
	 * @throws Exception
	 */
	public static void elementToBeClickable(WebElement ele, String objname) throws Exception {

		try {
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(ele));			
		} catch (Exception e) {
			logErrorMessagestopExecution("The object " + objname + " is not clickable");
		}
	}

	/** This method to get the date from todays date 
	 * 
	 * @param days We have to pass the current date by passing the number of days
	 * @return
	 */
	public static String getDatefromToday(int days) {

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		return dateFormat.format(cal.getTime());
	}

}
