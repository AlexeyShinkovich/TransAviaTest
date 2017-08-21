package com.epam.transavia;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LuggagePage extends BasePage {
	private static final Logger LOG = Logger.getLogger(LuggagePage.class);
	private static final By BUTTONSELECT = By.xpath("//td[3]/div/div/button[text()='Select']");
	private static final By SELECTEDBUTTON = By.xpath("//td[3]/div/div/button[text()='Selected']");
	private static final By ICONSEAT = By.xpath("//footer//div[@class='inbound']//div[9]");

	@FindBy(xpath = "//th[@class='th position-relative cursor-pointer' and  .//span[text()='Basic']]/span[@class='price pull-right']")
	private WebElement pricePerson;

	@FindBy(xpath = "//th[@class='th position-relative cursor-pointer' and  .//span[text()='Plus']]/span[@class='price pull-right']")
	private WebElement addLuggage;

	@FindBy(xpath = "//div[@class='grand-total__price-container']//div[@class='back']")
	private WebElement totalAmount;

	@FindBy(xpath = "//td[3]/div/div/button[text()='Select']")
	private WebElement selectButton;
	
	public LuggagePage(WebDriver driver) {
		super(driver);
	}

	public int getTotalPrice() {
		LOG.info("'Get Price one person and Price Luggage'");
		String priceOnePerson = pricePerson.getText();
		String priceLuggage = addLuggage.getText();
		selectButton.sendKeys(Keys.PAGE_DOWN);
		waitPresenceAndVisibility(BUTTONSELECT);
		LOG.info("'Click Button: 'Select Plus'");
		selectButton.click();
		waitPresenceAndVisibility(SELECTEDBUTTON);
		waitPresenceAndVisibility(ICONSEAT);
		LOG.info("'Get Total amount'");
		priceOnePerson = priceOnePerson.substring(2);
		int onePerson = Integer.parseInt(priceOnePerson);
		priceLuggage = priceLuggage.substring(4);
		int plusLuggageSum = Integer.parseInt(priceLuggage);
		int totalTicket = 2 * onePerson + onePerson + plusLuggageSum * 3;
		return totalTicket;
	}
	
	public int getTotalAmount(){
		String totalPrice = totalAmount.getText();
		String total = totalPrice.substring(2);
		int length = total.length();
		total = total.substring(0, length - 3);
		int grandTotal = Integer.parseInt(total);
		return grandTotal;
	}	
}