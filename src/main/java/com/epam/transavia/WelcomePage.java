package com.epam.transavia;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WelcomePage extends BasePage {
	private static final Logger LOG = Logger.getLogger(WelcomePage.class);
	public static final String URL = "https://www.transavia.com";
	private By country = By.xpath("//a[contains(.,'United Kingdom')]");

	@FindBy(xpath = "//a[contains(.,'United Kingdom')]")
	private WebElement clickCountry;

	public WelcomePage(WebDriver driver) {
		super(driver);
	}

	public MainPage clickCountry() {
		LOG.info("click: 'Select your country'");
		waitPresenceAndVisibility(country);
		clickCountry.click();
		return new MainPage(driver);
	}

	@Override
	public void waitForLoaded() {
	}
}