package com.epam.transavia;

import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class FindFlightFromDubaiTest extends BaseTest {
	private static final Logger LOG = Logger.getLogger(FindFlightFromDubaiTest.class);
	private static final String FROM = "Dubai";
	private static final String TO = "Agadir";
	private static final String CHECKTEXT = "Please change your destination and try again";

	@Test
	public void findFlightFromDubai() {
		LOG.info("start: findFlightFromDubai");
		WelcomePage welcome = navigate(WelcomePage.URL);
		MainPage mainPage = welcome.clickCountry();
		SearchFlightPage searchpage = mainPage.searchFlight(FROM, TO);
		assertTrue(searchpage.getTextError().contains(CHECKTEXT));
		LOG.info("findFlightFromDubai: check OK;");
	}
}