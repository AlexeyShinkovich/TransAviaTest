package com.epam.transavia;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BookingOverview extends BasePage {
	private static final Logger LOG = Logger.getLogger(BookingOverview.class);

	@FindBy(xpath = "//a[(@class='icon-left-side icon-animation-next') and contains(.,'Booking details')]")
	private WebElement bookingDetails;

	@FindBy(xpath = "//div[@class='flight-info flight-info--small flight-info--normal--bp5 flight-info--important']/p[@class='flight-info_value']")
	private WebElement flightNumber;

	@FindBy(xpath = "//div[@class='flight-details']//h3[@class='h5 h5--white no-margin-bottom']/span[1]")
	private WebElement from;

	@FindBy(xpath = "//div[@class='flight-details']//h3[@class='h5 h5--white no-margin-bottom']/span[3]")
	private WebElement to;

	@FindBy(xpath = "//div[contains(@class,'flight-info flight-info--small flight-info--normal--bp5') and .//h5[contains(.,'Arrival')]]//time")
	private WebElement arrivalTime;

	public BookingOverview(WebDriver driver) {
		super(driver);
	}

	public BookingDetails clickDetails() {
		LOG.info("'Click Button: 'Booking details'");
		bookingDetails.click();
		return new BookingDetails(driver);
	}

	public String getFlightNumber() {
		LOG.info("'Get Flight Number Text'");
		return flightNumber.getText();
	}

	public String getDestinationFrom() {
		LOG.info("'Get From Field Text'");
		return from.getText();
	}

	public String getDestinationTo() {
		LOG.info("'Get To Field Text'");
		return to.getText();
	}

	public String getArrivalTime() {
		LOG.info("'Get Arrival Time Text'");
		return arrivalTime.getText();
	}
}