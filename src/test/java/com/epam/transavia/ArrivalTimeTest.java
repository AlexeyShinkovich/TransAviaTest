package com.epam.transavia;

import static org.testng.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class ArrivalTimeTest extends BaseTest {
	private static final Logger LOG = Logger.getLogger(ArrivalTimeTest.class);
	private static final String BOOKINGNUMBER = "MF8C9R";
	private static final String LASTNAME = "kukharau";
	private static final String DATE = "9 June 2016";
	private static final String FLIGHTNUMBER = "HV5426";
	private static final String FROM = "Pisa";
	private static final String TO = "Amsterdam (Schiphol)";
	private static final String ARRIVALTIME = "23:35";

	@Test
	public void findBookingFlight() {
		LOG.info("start: findBookingFlight");
		WelcomePage welcome = navigate(WelcomePage.URL);
		MainPage mainPage = welcome.clickCountry();
		MyTransaviaLoginPage myTransavia = mainPage.clickManageBooking();
		BookingOverview bookingOverview = myTransavia.enterBooking(BOOKINGNUMBER, LASTNAME, DATE);
		assertEquals(FLIGHTNUMBER, bookingOverview.getFlightNumber());
		assertEquals(FROM, bookingOverview.getDestinationFrom());
		assertEquals(TO, bookingOverview.getDestinationTo());
		assertEquals(ARRIVALTIME, bookingOverview.getArrivalTime());
		LOG.info("findBookingFlight: check OK;");
	}
}