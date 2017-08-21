package com.epam.transavia;

import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.epam.transavia.MainPage;

public class SingleFlyTest extends BaseTest {
	private static final Logger LOG = Logger.getLogger(SingleFlyTest.class);
	private static final String FROM = "Lisbon, Portugal";
	private static final String TO = "Paris";
	private static final String DATE = "29-08-2017";

	@Test
	public void doFly() {
		LOG.info("start: doFly");
		WelcomePage welcome = navigate(WelcomePage.URL);
		MainPage mainPage = welcome.clickCountry();
		SearchFlightPage searchpage = mainPage.searchFlight(FROM, TO, DATE);
		assertTrue(searchpage.findFlight() != 0);
		LOG.info("doFly: check OK;");
	}
}