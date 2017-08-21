package com.epam.transavia;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	protected final WebDriver driver;
	protected By frame = By.xpath("//div[@class='usabilla_live_button_container']/iframe");
	private By img = By.xpath("html/body/img");

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitForLoaded();
	}

	public void waitForLoaded() {
		waitPresenceAndVisibility(frame);
		WebElement elementFrame = driver.findElement(frame);
		driver.switchTo().frame(elementFrame);
		waitPresenceAndVisibility(img);
		driver.switchTo().defaultContent();
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	public void waitPresenceAndVisibility(By element) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(element));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
	}
}