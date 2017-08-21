package com.epam.transavia;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BookingDetails extends BasePage {
	private static final Logger LOG = Logger.getLogger(BookingDetails.class);

	@FindBy(xpath = "//div[@class='details-list details-list--header']//div[(@class='amount') and .//span[@class='currency']]")
	private WebElement amount;

	@FindBy(xpath = "//div[(@class='front') and .//span[@class='currency']]")
	private WebElement total;

	public BookingDetails(WebDriver driver) {
		super(driver);
	}

	public String getAmount() {
		LOG.info("'Get Payment amount'");
		return amount.getText();
	}
	
	public String getSum() {
		LOG.info("'Get Sum'");
		return total.getText();
	}
}