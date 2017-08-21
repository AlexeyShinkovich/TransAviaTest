package com.epam.transavia;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HandLuggagePage extends BasePage {
	private static final Logger LOG = Logger.getLogger(HandLuggagePage.class);

	@FindBy(xpath = "//div[@id='top']//h2[text()='Video: hand luggage and Transavia']")
	private WebElement videoSection;

	@FindBy(xpath = "//div[@id='top']//section[@class='component_container-intro']//iframe")
	private WebElement iframeLocator;

	@FindBy(xpath = "//a[text()='Luggage']")
	private WebElement videoNameLocator;

	public HandLuggagePage(WebDriver driver) {
		super(driver);
	}

	public String getVideoLink() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", videoSection);
		driver.switchTo().frame(iframeLocator);
		LOG.info("'Get VideoLink'");
		return videoNameLocator.getAttribute("href");
	}

	public YouTubePage clickVideo() {
		LOG.info("'Click Button: 'VideoLink'");
		videoNameLocator.click();
		return new YouTubePage(driver);
	}
}