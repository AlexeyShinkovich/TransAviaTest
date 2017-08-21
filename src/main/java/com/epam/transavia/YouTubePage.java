package com.epam.transavia;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YouTubePage extends BasePage {
	private static final Logger LOG = Logger.getLogger(YouTubePage.class);
	private static final By VIDEOAUTHORLOCATOR = By.xpath("//div[@id='watch7-user-header']/div/a");

	@FindBy(xpath = "//div[@id='watch-headline-title']")
	private WebElement videoName;

	@FindBy(xpath = "//div[@id='watch7-user-header']/div/a")
	private WebElement videoAuthor;

	public YouTubePage(WebDriver driver) {
		super(driver);
	}

	public String getVideoName() {
		LOG.info("'Get Video Name'");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		waitPresenceAndVisibility(VIDEOAUTHORLOCATOR);
		return videoName.getText();
	}

	public String getVideoAuthor() {
		LOG.info("'Get Video Author'");
		return videoAuthor.getText();
	}

	@Override
	public void waitForLoaded() {
	}
}