package com.epam.transavia;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyTransaviaLoginPage extends BasePage {
	private static final Logger LOG = Logger.getLogger(MyTransaviaLoginPage.class);
	
	@FindBy(xpath = "//*[@id='retrieveBookingByLastname_RecordLocator']")
	private WebElement bookingNumberField;

	@FindBy(xpath = "//*[@id='retrieveBookingByLastname_LastName']")
	private WebElement lastNameField;

	@FindBy(xpath = "//*[@id='retrieveBookingByLastname_FlightDate-datepicker']")
	private WebElement flightDateField;

	@FindBy(xpath = "//*[@id='access-booking']//button")
	private WebElement buttonViewBooking;
	
	public MyTransaviaLoginPage(WebDriver driver) {
		super(driver);
	}

	public BookingOverview enterBooking(String bookingNumber, String lastName, String flightDate) {
		LOG.info("'Add Text to Booking Number Field'");
		bookingNumberField.click();
		bookingNumberField.sendKeys(bookingNumber);
		LOG.info("'Add Text to LastName Field'");
		lastNameField.click();
		lastNameField.sendKeys(lastName);
		LOG.info("'Add Text to Date Field'");
		flightDateField.click();
		flightDateField.sendKeys(flightDate);
		bookingNumberField.click();
		LOG.info("'Click Button: 'View Booking'");
		buttonViewBooking.submit();
		return new BookingOverview(driver);
	}
}