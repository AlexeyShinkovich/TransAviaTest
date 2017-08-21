package com.epam.transavia;

import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class OneWayTicketTest extends BaseTest {
	private static final Logger LOG = Logger.getLogger(OneWayTicketTest.class);
	private static final String FROM = "Ibiza";
	private static final String BUDGET = "200";

	@Test
	public void findDestination() {
		LOG.info("start: findDestination");
		WelcomePage welcome = navigate(WelcomePage.URL);
		MainPage mainPage = welcome.clickCountry();
		DestinationsPage destination = mainPage.clickDestinations();
		FindDestinationPage findDestination = destination.clickFindDestination();
		assertTrue(findDestination.getAnyDestinations(FROM, BUDGET) != 0);
		LOG.info("findDestination: check OK;");
	}
}