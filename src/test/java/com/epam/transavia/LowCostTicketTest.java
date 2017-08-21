package com.epam.transavia;

import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class LowCostTicketTest extends BaseTest {
	private static final Logger LOG = Logger.getLogger(LowCostTicketTest.class);
	private static final String MONTH = "2017-09-01";
	private static final String FLIGHTTYPE = "Single";
	private static final String FROM = "Netherlands";
	private static final String TO = "France";
	private static final String DAYOFTHEWEEK = "";
	private static final String CHECKTEXT = "Nice, France; 29 евро";

	@Test
	public void findFlight() {
		LOG.info("start: findFlight");
		WelcomePage welcome = navigate(WelcomePage.URL);
		MainPage mainPage = welcome.clickCountry();
		FindDestinationPage findDestination = mainPage.clickAdvancedSearch();
		assertTrue(findDestination.lowCostTicket(FROM, TO, MONTH, FLIGHTTYPE, DAYOFTHEWEEK).contains(CHECKTEXT));
		LOG.info("findFlight: check OK;");
	}
}