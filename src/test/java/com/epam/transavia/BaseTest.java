package com.epam.transavia;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.uncommons.reportng.HTMLReporter;

@Listeners({ HTMLReporter.class })
public class BaseTest {

	private static final Logger LOG = Logger.getLogger(BaseTest.class);
	private WebDriver driver;

	@BeforeClass
	public WebDriver startBrowser() {
		LOG.info("start, 'startBrowser'");
		System.setProperty("webdriver.gecko.driver", "c://driver//geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		LOG.info("finish, 'startBrowser'");
		return driver;
	}

	protected WelcomePage navigate(String url) {
		LOG.info("Open page, 'navigate;");
		driver.get(url);
		LOG.info("finish, 'navigate;");
		return new WelcomePage(driver);
	}

	@AfterClass
	public void quitBrowser() {
		if (driver != null) {
			driver.quit();
		}
		LOG.info("Close browser");
	}
}