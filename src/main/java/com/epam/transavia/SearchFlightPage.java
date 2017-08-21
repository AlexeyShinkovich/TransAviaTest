package com.epam.transavia;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchFlightPage extends BasePage {
	private static final Logger LOG = Logger.getLogger(SearchFlightPage.class);
	private static final By BUTTONSELECTOUT = By.xpath("//section[@class='flight outbound']//div[3]//button[@class='flight-result-button']");
	private static final By BUTTONSELECTIN = By.xpath("//section[@class='flight inbound']//div[3]//button[@class='flight-result-button']");
	private static final By CHECKOUT = By.xpath("//div[@class='outbound']//div[@class='is-enabled']");
	private static final By CHECKIN = By.xpath("//div[@class='inbound']//div[@class='is-enabled']");
	
	@FindBy(xpath = "//div[@class='current-view']//span[@class='price']")
	private List<WebElement> flights;

	@FindBy(xpath = "//p[contains(text(),'try again')]")
	private WebElement textError;
	
	@FindBy(xpath = "//section[@class='flight outbound']//div[3]//button[@class='flight-result-button']")
	private WebElement buttonSelectOut;
	
	@FindBy(xpath = "//div[@class='panel_section panel_section--content results c-flight-results-panel' and .//h2[text()=' Inbound flight']]/div[2]/div/div[3]/div/form/div/button")
	private WebElement buttonSelectIn;
	
	@FindBy(xpath = "//div[@class='panel panel-total']/button[contains(.,'Next')]")
	private WebElement buttonNext;
	
	
	public SearchFlightPage(WebDriver driver) {
		super(driver);
	}

	public int findFlight() {
		LOG.info("'Checked the presencse of flights'");
		return flights.size();
	}

	public String getTextError() {
		LOG.info("'Checked the error text'");
		return textError.getText();
	}

	public LuggagePage clickSelectFlight() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		LOG.info("'Select flight out'");
		waitPresenceAndVisibility(BUTTONSELECTOUT);
		wait.until(ExpectedConditions.elementToBeClickable(BUTTONSELECTOUT));
		buttonSelectOut.click();
		waitPresenceAndVisibility(CHECKOUT);
		buttonSelectIn.sendKeys(Keys.PAGE_DOWN);
		LOG.info("'Select flight in'");
		waitPresenceAndVisibility(BUTTONSELECTIN);
		wait.until(ExpectedConditions.elementToBeClickable(BUTTONSELECTIN));
		LOG.info("'Click Button: 'Next'");
		buttonSelectIn.click();
		waitPresenceAndVisibility(CHECKIN);
		buttonNext.click();
		return new LuggagePage(driver);
	}
}