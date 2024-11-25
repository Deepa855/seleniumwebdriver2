package Assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FitPeo {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();

		// 1.navigate to the fitpeo homepage : open to the web browser and navigate to
		// fitpeo homepage

		driver.get("https://www.fitpeo.com");
		driver.manage().window().maximize();

		Thread.sleep(1000);

		// 2.navigate to the revenue calculator page:
		// from the homepage ,navigate to the revenue calculator page

		WebElement rc = driver.findElement(By.linkText("Revenue Calculator"));
		rc.click();

		Thread.sleep(3000);

		// 3.Scroll down to the slider section: Scroll down the page until the revenue
		// calculator slider is visible.

		WebElement revenueCalculator = driver
				.findElement(By.xpath("//h4[normalize-space()='Medicare Eligible Patients']"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView()", revenueCalculator);

		// 4.Adjust the slider: Adjust the slider to set its value to 820. we have
		// highlighted the slider in red color, once the slider is moved the bottom text
		// field value should be updated to 820.

		WebElement slider = driver.findElement(By.cssSelector("input[type='number']"));
		slider.sendKeys("value='820'");

		// 5.Updated the text field: click on the text field associated with the slider.
		// Enter the value 560 in the text field. now the slider also should change
		// accordingly.

		Thread.sleep(1000);
		WebElement input = driver.findElement(By.id(":r0:"));
		input.sendKeys("value='560'");

		// 6.validate slider value: ensure that when the value 560 is entered in that
		// text field, the sliders position is updated to reflect the value 560.

		Thread.sleep(1000);
		String sliderVal = slider.getAttribute("value");
		if (sliderVal.equals("560")) {
			System.out.println("Slider is updated to 560");
		}

		// 7.Select CPT codes: Scroll down further and select the checkboxes for
		// CPT-99091,CPT-99453,CPT-99454,CPT-99474.

		Thread.sleep(1000);
		WebElement cptsDiv = driver.findElement(By.xpath("//div[@class='MuiBox-root css-1p19z09']"));
		jse.executeScript("arguments[0].scrollIntoView()", cptsDiv);

		String[] cpts = { "CPT-99091", "CPT-99453", "CPT-99454", "CPT-99474" };

		for (String cpt : cpts) {
			Thread.sleep(1000);
			String xpath = "//p[contains(text(), '" + cpt
					+ "')]/ancestor::div[contains(@class, 'MuiBox-root css-1eynrej')]/descendant::input[@type='checkbox']";

			WebElement cpt99091 = driver.findElement(By.xpath(xpath));
			if (!cpt99091.isSelected()) {
				cpt99091.click();
			}
		}
		Thread.sleep(1000);

		// 8.Validate total recurring Reimbursement:

		WebElement reimburse = driver.findElement(By.xpath(
				"//p[@class='MuiTypography-root MuiTypography-body2 inter css-1xroguk'][contains(text(),'Total Recurring Reimbursement for all Patients Per')]"));
		WebElement reimburseValEle = reimburse
				.findElement(By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 inter css-1bl0tdj']"));
		String reimburseVal = reimburseValEle.getText();

		System.out.println(reimburseVal);

		// 9. verify that the header displaying "Total Recurring Reimbursement for all
		// Patients Per Month: shows the value $110700.

		if (!reimburseVal.equals("$110700")) {
			System.out.println("value doesn't equal");
		}

	}

}
