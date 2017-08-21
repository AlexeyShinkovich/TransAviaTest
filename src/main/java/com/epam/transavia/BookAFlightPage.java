package com.epam.transavia;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookAFlightPage extends BasePage {
	private static final Logger LOG = Logger.getLogger(BookAFlightPage.class);
	private static final By OUTFLIGHTLOCATOR = By.xpath("//section[@class='flight outbound']//ol/li/div/div/span");
	private static final By INFLIGHTLOCATOR = By.xpath("//section[@class='flight inbound']//ol/li/div/div/span");
	private static final By BUTTONSELECTOUT = By
			.xpath("//section[@class='flight outbound']//button[@class='flight-result-button']");
	private static final By BUTTONSELECTIN = By
			.xpath("//section[@class='flight inbound']//button[@class='flight-result-button']");
	private static final By TOTALAMOUNT = By.xpath("//footer//h3[text()='Total amount']");
	private static final By CHECKOUT = By.xpath("//div[@class='outbound']//div[@class='is-enabled']");
	private static final By CHECKIN = By.xpath("//div[@class='inbound']//div[@class='is-enabled']");
	private static final By SELECTEDINBUTTON = By
			.xpath("//section[@class='flight inbound']//span[@class='icon-font icon-check']");

	@FindBy(xpath = "//input[@id='openJawRouteSelection_DepartureStationOutbound-input']")
	private WebElement fromOutboundField;

	@FindBy(xpath = "//input[@id='openJawRouteSelection_ArrivalStationOutbound-input']")
	private WebElement toOutboundField;

	@FindBy(xpath = "//input[@id='dateSelection_OutboundDate-datepicker']")
	private WebElement dateOutboundField;

	@FindBy(xpath = "//input[@id='openJawRouteSelection_DepartureStationInbound-input']")
	private WebElement fromInboundField;

	@FindBy(xpath = "//input[@id='openJawRouteSelection_ArrivalStationInbound-input']")
	private WebElement toInboundField;

	@FindBy(xpath = "//input[@id='dateSelection_InboundDate-datepicker']")
	private WebElement dateInboundField;

	@FindBy(xpath = "//section[@class='flight inbound']//button[@class='button button-secondary']")
	private WebElement buttonNext;

	@FindBy(xpath = "//form[@id='flights']//button[contains(.,'Search')]")
	private WebElement buttonSearch;

	@FindBy(xpath = "//section[@class='flight outbound']//button[@class='flight-result-button']")
	private WebElement buttonSelectOut;

	@FindBy(xpath = "//section[@class='flight inbound']//button[@class='flight-result-button']")
	private WebElement buttonSelectIn;

	@FindBy(xpath = "//div[@class='grand-total__price-container']//div[@class='back']")
	private WebElement totalAmount;

	WebDriverWait wait = new WebDriverWait(driver, 20);

	public BookAFlightPage(WebDriver driver) {
		super(driver);
	}

	public int findeTrip(String cityFromOut, String cityToOut, String dateOut, String cityFromIn, String cityToIn,
			String dateIn) {
		LOG.info("'Add Text to OUTBOUND Fields'");
		fromOutboundField.click();
		fromOutboundField.sendKeys(cityFromOut);
		toOutboundField.click();
		toOutboundField.sendKeys(cityToOut);
		dateOutboundField.click();
		dateOutboundField.clear();
		dateOutboundField.sendKeys(dateOut);
		LOG.info("'Add Text to INBOUND Fields'");
		fromInboundField.click();
		fromInboundField.sendKeys(cityFromIn);
		toInboundField.click();
		toInboundField.sendKeys(cityToIn);
		dateInboundField.click();
		dateInboundField.clear();
		dateInboundField.sendKeys(dateIn);
		LOG.info("'Click Button: 'Search'");
		buttonSearch.click();
		buttonSearch.click();
		LOG.info("'Select Flight Out'");
		selectDate(OUTFLIGHTLOCATOR, "out");
		waitPresenceAndVisibility(TOTALAMOUNT);
		wait.until(ExpectedConditions.elementToBeClickable(buttonNext));
		waitPresenceAndVisibility(BUTTONSELECTOUT);
		wait.until(ExpectedConditions.elementToBeClickable(BUTTONSELECTOUT));
		buttonSelectOut.click();
		waitPresenceAndVisibility(CHECKOUT);
		buttonNext.sendKeys(Keys.PAGE_DOWN);
		LOG.info("'Select Flight In'");
		selectDate(INFLIGHTLOCATOR, "in");
		waitPresenceAndVisibility(BUTTONSELECTIN);
		wait.until(ExpectedConditions.elementToBeClickable(BUTTONSELECTIN));
		buttonSelectIn.click();
		waitPresenceAndVisibility(SELECTEDINBUTTON);
		waitPresenceAndVisibility(CHECKIN);
		LOG.info("'Get total price'");
		String totalPrice = totalAmount.getText();
		String total = totalPrice.substring(2);
		int length = total.length();
		total = total.substring(0, length - 3);
		System.out.println(total);
		int grandTotal = Integer.parseInt(total);
		return grandTotal;
	}

	private void selectDate(By locator, String to) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//section[@class='flight " + to + "bound']//button[@class='flight-result-button']")));
		} catch (Exception e) {
			List<WebElement> elementList = driver.findElements(locator);
			for (int i = 0; i < elementList.size(); i++) {
				WebElement el = elementList.get(i);
				if (el.getAttribute("class").equals("price")) {
					el.click();
					break;
				}
			}
		}
	}
}