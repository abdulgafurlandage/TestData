package testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MyFirstTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


		//Nevigate to homepage
		driver.get("https://www.fitpeo.com/home");

		//Nevigate to Revenue calculate Page
		driver.findElement(By.linkText("Revenue Calculator")).click();

		// scroll down untill condition mate	
		WebElement slider=driver.findElement(By.xpath("//*[@class='MuiSlider-track css-10opxo5']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", slider);
		Thread.sleep(2000);

		//adjust slider to 820 
		WebElement slider1 = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]/div/div/span[1]"));
		Actions action = new Actions(driver);
		action.clickAndHold(slider1).moveByOffset(620, 0).release().perform();

		// Validate that the text field updates to 820
		WebElement sliderValueField = driver.findElement(By.id(":R57alklff9da:"));
		String sliderValue = sliderValueField.getAttribute("value");
		assert sliderValue.equals("820") : "Slider value should be 820";

		// update the text field by 560
		driver.findElement(By.id(":R57alklff9da:")).clear();
		driver.findElement(By.id(":R57alklff9da:")).sendKeys("560");

		//Validate Slider Value updates to reflect 560
		String updatedSliderValue = driver.findElement(By.id(":R57alklff9da:")).getAttribute("value");
		assert updatedSliderValue.equals("560") : "Slider value should be updated to 560";


		//select the checkboxes
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[1]/label/span[1]/input")).click();
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/label/span[1]/input")).click();
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[3]/label/span[1]/input")).click();
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[8]/label/span[1]/input")).click();


		//validate total recurring reimbursment
		WebElement totalReimbursementHeader = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[1]/div/div[3]/p[2]"));

		String totalReimbursementValue = totalReimbursementHeader.getText();

		if (totalReimbursementValue.contains("$110700")) {
			System.out.println("Total Recurring Reimbursement is correctly displayed as $110700");
		} else {
			System.out.println("Total Recurring Reimbursement value is incorrect. Current value: " + totalReimbursementValue);



			//close the browser   
			driver.close();


		}

	}}
