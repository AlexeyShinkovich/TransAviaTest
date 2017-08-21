package com.epam.transavia;

import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FindDestinationPage extends BasePage {
	private static final Logger LOG = Logger.getLogger(FindDestinationPage.class);
	private static final By TEXT = By.xpath("//h3[text()='We found the following destinations for you:']");
	private static final By TAXLIMIT = By.xpath("//a[text()='€600 per person']");

	@FindBy(xpath = "//input[@id='countryStationSelection_Origin-input']")
	private WebElement fromField;

	@FindBy(xpath = "//input[@id='countryStationSelection_Destination-input']")
	private WebElement toField;

	@FindBy(xpath = "//form[@id='alternativesearch']//h3[contains(.,'What is your budget per person?')]")
	private WebElement buttonWhatIsYourBudget;

	@FindBy(xpath = "//form[@id='alternativesearch']//h3[contains(.,'When will you be taking off?')]")
	private WebElement buttonWhenWillYouBeTakingOff;

	@FindBy(xpath = "//input[@id='budgetSelection_EurosBudget']")
	private WebElement myBudgetField;

	@FindBy(xpath = "//select[@id='data-flight-type']")
	private WebElement selectTrip;

	@FindBy(xpath = "//form[@id='alternativesearch']//label[text()='Specific month']")
	private WebElement buttonSpecificMonth;

	@FindBy(xpath = "//select[@id='timeFrameSelection_SingleFlight_SpecificMonth']")
	private WebElement selectSpecificMonth;

	@FindBy(xpath = "//select[@id='timeFrameSelection_SingleFlight_DepartureDayOfTheWeek']")
	private WebElement selectDayOfTheWeek;

	@FindBy(xpath = "//ol[@class='bulletless list AS-destinations-list']/li[1]//h3")
	private WebElement countryName;

	@FindBy(xpath = "//ol[@class='bulletless list AS-destinations-list']/li[1]//h2")
	private WebElement cityName;

	@FindBy(xpath = "//ol[@class='bulletless list AS-destinations-list']/li[1]//span[@class='integer']")
	private WebElement priceFlight;

	@FindBy(xpath = "//form[@id='alternativesearch']//button[contains(.,'Search')]")
	private WebElement buttonSearch;

	@FindBy(xpath = "//ol[@class='bulletless list AS-destinations-list']/descendant::li")
	private List<WebElement> flights;

	WebDriverWait wait = new WebDriverWait(driver, 20);

	public FindDestinationPage(WebDriver driver) {
		super(driver);
	}

	public String lowCostTicket(String cityFrom, String cityTo, String date, String trip, String day) {
		LOG.info("'Add Text to FROM Field'");
		fromField.click();
		fromField.sendKeys(cityFrom);
		LOG.info("'Add Text to TO Field'");
		toField.click();
		toField.sendKeys(cityTo);
		LOG.info("'Click Button: 'Will You Be Taking Off'");
		buttonWhenWillYouBeTakingOff.click();
		LOG.info("'Select DropDown Fields'");
		Select dropDownTrip = new Select(selectTrip);
		dropDownTrip.selectByValue(trip);
		buttonSpecificMonth.click();
		Select dropDownSpecificMonth = new Select(selectSpecificMonth);
		dropDownSpecificMonth.selectByValue(date);
		Select dropDownDayOfTheWeek = new Select(selectDayOfTheWeek);
		dropDownDayOfTheWeek.selectByValue(day);
		LOG.info("'Click Button: 'Search'");
		buttonSearch.click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(TEXT));
		String result = cityName.getText() + ", " + countryName.getText() + "; " + priceFlight.getText() + " евро";
		return result;
	}

	public int getAnyDestinations(String city, String myBudget) {
		LOG.info("'Add Text to FROM Fields'");
		fromField.click();
		fromField.sendKeys(city);
		LOG.info("'Click Button: 'What Is Your Budget'");
		buttonWhatIsYourBudget.click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(TAXLIMIT));
		myBudgetField.click();
		myBudgetField.sendKeys(myBudget);
		buttonSearch.sendKeys(Keys.PAGE_DOWN);
		LOG.info("'Click Button: 'Search'");
		buttonSearch.click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(TEXT));
		int amountFlights = flights.size();
		return amountFlights;
	}
}