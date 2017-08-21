package com.epam.transavia;

import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class CheckMultipleFlightTest extends BaseTest {
	private static final Logger LOG = Logger.getLogger(CheckMultipleFlightTest.class);
	private static final String OUTBOUNDFROM = "Bologna";
	private static final String OUTBOUNDTO = "Eindhoven";
	private static final String OUTBOUNDDATE = "02-09-2017";
	private static final String INBOUNDFROM = "Amsterdam";
	private static final String INBOUNDTO = "Casablanca";
	private static final String INBOUNDDATE = "08-09-2017";
	
	@Test
	public void checkFlight() {
		LOG.info("start: checkFlight");
		WelcomePage welcome = navigate(WelcomePage.URL);
		MainPage mainPage = welcome.clickCountry();
		BookAFlightPage multiplePage = mainPage.clickAddMultiple();
		assertTrue(multiplePage.findeTrip(OUTBOUNDFROM, OUTBOUNDTO, OUTBOUNDDATE, INBOUNDFROM, INBOUNDTO, INBOUNDDATE) != 0);
		LOG.info("checkFlight: check OK;");
	}
}