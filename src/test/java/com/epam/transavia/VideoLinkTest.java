package com.epam.transavia;

import static org.testng.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class VideoLinkTest extends BaseTest {
	private static final Logger LOG = Logger.getLogger(VideoLinkTest.class);
	private static final String LINK= "https://www.youtube.com/watch?v=fQMuhniqWAg";
	private static final String NAME = "Luggage";
	private static final String VIDEOAUTHOR = "Transavia";

	@Test
	public void getVideoTest() {
		LOG.info("start: getVideoTest");
		WelcomePage welcome = navigate(WelcomePage.URL);
		MainPage mainPage = welcome.clickCountry();
		HandLuggagePage handLuggage = mainPage.clickHandLuggage();
		assertEquals(LINK, handLuggage.getVideoLink());
		YouTubePage youtube = handLuggage.clickVideo();
		assertEquals(NAME, youtube.getVideoName());
		assertEquals(VIDEOAUTHOR, youtube.getVideoAuthor());
		LOG.info("getVideoTest: check OK;");
	}
}