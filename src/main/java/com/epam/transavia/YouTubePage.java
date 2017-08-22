package com.epam.transavia;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YouTubePage extends BasePage {
	private static final Logger LOG = Logger.getLogger(YouTubePage.class);
	private static final By VIDEONAME = By.xpath("//div[@id='watch-headline-title']");
	private static final By VIDEOAUTHORLOCATOR = By.xpath("//div[@id='watch7-user-header']/div");

	@FindBy(xpath = "//div[@id='watch-headline-title']")
	private WebElement videoName1;

	@FindBy(xpath = "//div[@id='container']/h1")
	private WebElement videoName2;

	@FindBy(xpath = "//div[@id='watch7-user-header']/div")
	private WebElement videoAuthor1;

	@FindBy(xpath = "//div[@id='owner-container']//a")
	private WebElement videoAuthor2;

	public YouTubePage(WebDriver driver) {
		super(driver);
	}

	public String getVideoName() {
		LOG.info("'Get Video Name'");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		String videoName;
		if (getDriver().findElements(VIDEONAME).isEmpty()) {
			videoName = videoName2.getText();
		} else {
			videoName = videoName1.getText();
		}
		return videoName;
	}

	public String getVideoAuthor() {
		LOG.info("'Get Video Author'");
		String videoAuthor;
		if (getDriver().findElements(VIDEOAUTHORLOCATOR).isEmpty()) {
			videoAuthor = videoAuthor2.getText();
		} else {
			videoAuthor = videoAuthor1.getText();
		}
		return videoAuthor;
	}

	@Override
	public void waitForLoaded() {
	}
}