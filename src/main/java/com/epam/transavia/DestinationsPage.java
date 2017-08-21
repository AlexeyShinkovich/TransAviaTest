package com.epam.transavia;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DestinationsPage extends BasePage {
	private static final Logger LOG = Logger.getLogger(DestinationsPage.class);
	
	@FindBy(xpath = "//div[@id='top']//a[contains(.,'Find the perfect destination')]")
	private WebElement buttonFindDestination;

	public DestinationsPage(WebDriver driver) {
		super(driver);
	}
	
	public FindDestinationPage clickFindDestination() {
		LOG.info("'Click Button: 'Find the perfect destination'");
		buttonFindDestination.click();
		return new FindDestinationPage(driver);
	}
}