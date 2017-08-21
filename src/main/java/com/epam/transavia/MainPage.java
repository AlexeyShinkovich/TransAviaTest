package com.epam.transavia;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {
	private static final Logger LOG = Logger.getLogger(MainPage.class);
	private static final String BUTTONYESXPATH = "//div[@class='cc-left']/button";

	@FindBy(id = "routeSelection_DepartureStation-input")
	private WebElement fromField;

	@FindBy(id = "routeSelection_ArrivalStation-input")
	private WebElement toField;

	@FindBy(id = "dateSelection_OutboundDate-datepicker")
	private WebElement dataSelectionOut;

	@FindBy(xpath = "//div[contains(@class,'checkfield-wrapper') and .//input[@id='dateSelection_IsReturnFlight']]//label[@class='h6']")
	private WebElement returnOn;

	@FindBy(id = "booking-passengers-input")
	private WebElement passengers;

	@FindBy(xpath = "//button[@class='button button-secondary close']")
	private WebElement buttonSave;

	@FindBy(xpath = ".//*[@id='desktop']//div[@class='panel_section-button-container']/button[@class='button button-primary']")
	private WebElement buttonSearch;

	@FindBy(xpath = "//a[(@class='h5 primary-navigation_link') and contains(.,'Manage your booking')]")
	private WebElement manage;

	@FindBy(xpath = "//span[(@class='sub-navigation-level-1_link-text') and contains(.,'View your booking')]")
	private WebElement viewBooking;

	@FindBy(xpath = "//div[contains(@class,'HV-gs--bp0 HV-gs-type-e--bp10') and .//label[contains(.,'Adults')]]//button[@class='button button-secondary increase']")
	private WebElement buttonAddAdults;

	@FindBy(xpath = "//div[contains(@class,'HV-gs--bp0 HV-gs-type-e--bp10') and .//label[contains(.,'Children')]]//button[@class='button button-secondary increase']")
	private WebElement buttonAddChildren;

	@FindBy(xpath = "//form[@id='desktop']//a[contains(.,'Add multiple destinations')]")
	private WebElement buttonAddMultiple;

	@FindBy(xpath = "//ul[@class='bulletless secondary-navigation_list']//a[text()='Destinations']")
	private WebElement buttonDestinations;

	@FindBy(xpath = "//header//a[contains(.,'Plan and book')]")
	private WebElement buttonPlanAndBook;

	@FindBy(xpath = "//div[@id='horizontal-sub-navigation-planandbook']//a[contains(.,'Advanced search')]")
	private WebElement buttonAdvancedSearch;

	@FindBy(xpath = "//nav//a[@class='h5 primary-navigation_link' and contains(.,'Service')]")
	private WebElement buttonService;

	@FindBy(xpath = "//div[@id='horizontal-sub-navigation-service']//a[contains(.,'Handluggage')]")
	private WebElement buttonHandLuggage;

	@FindBy(xpath = BUTTONYESXPATH)
	private WebElement buttonYes;

	public MainPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public void waitForLoaded() {
		waitForButtonYes();
		clickButton();
		super.waitForLoaded();
	}

	public HandLuggagePage clickHandLuggage() {
		LOG.info("Click Button: 'Service'");
		buttonService.click();
		LOG.info("Click Button: 'HandLuggage'");
		buttonHandLuggage.click();
		return new HandLuggagePage(driver);
	}

	public FindDestinationPage clickAdvancedSearch() {
		LOG.info("Click Button: 'Plan And Book'");
		buttonPlanAndBook.click();
		LOG.info("Click Button: 'AdvancedSearch'");
		buttonAdvancedSearch.click();
		return new FindDestinationPage(driver);
	}

	public DestinationsPage clickDestinations() {
		LOG.info("Click Button: 'Destinations'");
		buttonDestinations.click();
		return new DestinationsPage(driver);
	}

	public BookAFlightPage clickAddMultiple() {
		LOG.info("Click Add multiple destinations link");
		buttonAddMultiple.click();
		return new BookAFlightPage(driver);
	}

	public SearchFlightPage searchFlight(String from, String to, String data) {
		textToFields(from, to);
		returnOn.click();
		dataSelectionOut.click();
		dataSelectionOut.clear();
		LOG.info("Select Date");
		dataSelectionOut.sendKeys(data);
		buttonSearch.click();
		return new SearchFlightPage(driver);
	}

	public SearchFlightPage searchFlight(String from, String to) {
		textToFields(from, to);
		LOG.info("'Click Search Flight from  Dubai to Agadir'");
		buttonSearch.click();
		return new SearchFlightPage(driver);
	}

	public MyTransaviaLoginPage clickManageBooking() {
		LOG.info("Click Button: 'Manage your Booking'");
		manage.click();
		LOG.info("Click Button: 'View your Booking'");
		viewBooking.click();
		return new MyTransaviaLoginPage(driver);
	}

	public SearchFlightPage searchFlightBarcelonaToParis(String from, String to) {
		textToFields(from, to);
		passengers.click();
		buttonAddAdults.click();
		buttonAddChildren.click();
		buttonSave.click();
		LOG.info("Add One adult and One Child to passengers completed");
		buttonSearch.click();
		return new SearchFlightPage(driver);
	}

	private void clickButton() {
		LOG.info("Click Button: 'Yes, I understand'");
		buttonYes.click();
	}

	private void textToFields(String from, String to) {
		LOG.info("Add Text to fields 'From' and 'To'");
		fromField.click();
		fromField.sendKeys(from);
		toField.click();
		toField.sendKeys(to);
	}

	public void waitForButtonYes() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		waitPresenceAndVisibility(By.xpath(BUTTONYESXPATH));
	}
}