package com.epam.transavia;

import static org.testng.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class TotalSumTicketsTest extends BaseTest {
	private static final Logger LOG = Logger.getLogger(TotalSumTicketsTest.class);
	private static final String FROM = "Barcelona";
	private static final String TO = "Paris";

	@Test
	public void buyTicketsTest() {
		LOG.info("start: buyTicketsTest");
		WelcomePage welcome = navigate(WelcomePage.URL);
		MainPage mainPage = welcome.clickCountry();
		SearchFlightPage searchpage = mainPage.searchFlightBarcelonaToParis(FROM, TO);
		LuggagePage selectLuggage = searchpage.clickSelectFlight();
		assertEquals(selectLuggage.getTotalPrice(), selectLuggage.getTotalAmount());
		LOG.info("buyTicketsTest: check OK;");
	}
}