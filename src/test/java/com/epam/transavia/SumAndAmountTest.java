package com.epam.transavia;

import static org.testng.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class SumAndAmountTest extends BaseTest {
	private static final Logger LOG = Logger.getLogger(SumAndAmountTest.class);
	private static final String BOOKINGNUMBER = "MF8C9R";
	private static final String LASTNAME = "kukharau";
	private static final String DATE = "9 June 2016";

	@Test
	public void doCompareSumAndAmount() {
		LOG.info("start: doCompareSumAndAmount");
		WelcomePage welcome = navigate(WelcomePage.URL);
		MainPage mainPage = welcome.clickCountry();
		MyTransaviaLoginPage myTransavia = mainPage.clickManageBooking();
		BookingOverview bookingOverview = myTransavia.enterBooking(BOOKINGNUMBER, LASTNAME, DATE);
		BookingDetails details = bookingOverview.clickDetails();
		assertEquals(details.getSum(), details.getAmount());
		LOG.info("doCompareSumAndAmount: check OK;");
	}
}