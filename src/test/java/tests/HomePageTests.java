package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends BaseTest {

	@Test
	public void verifyHomePageTitle() {
		// Verify the homepage title contains 'Entrata'
		String title = driver.getTitle();
		Assert.assertTrue(title.contains("Entrata"), "Title does not contain 'Entrata'");
	}

	@Test
	public void navigateToProductsPage() {
		// Click 'Products' link and verify navigation
		driver.findElement(By.linkText("Products")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlContains("products"));

		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("products"), "Did not navigate to Products page");
	}

	@Test
	public void interactWithContactFormWithoutSubmitting() {
		// Click 'Contact Us' link and interact with form
		driver.findElement(By.linkText("Contact Us")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName")));

		driver.findElement(By.name("firstName")).sendKeys("John");
		driver.findElement(By.name("lastName")).sendKeys("Doe");

		String firstNameValue = driver.findElement(By.name("firstName")).getAttribute("value");
		Assert.assertEquals(firstNameValue, "John", "First name was not entered correctly");
	}
}
