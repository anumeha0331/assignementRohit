package TestingProject.Assignment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObject.CartPage;
import pageObject.LandingPage;
import pageObject.MoisturizerDetailsDTO;
import pageObject.MoisturizerPriceComparator;
import pageObject.PaymentMessage;
import pageObject.PaymentPage;
import pageObject.SunscreenDetailsDTO;
import pageObject.SunscreenPriceComparator;
import pageObject.moisturizerPage;
import pageObject.sunscreenPage;
import resource.Base;

public class homePage extends Base {
	public WebDriver driver;
	public static Logger log = LogManager.getLogger(Base.class.getName());

	private static final String XPATH = "//div[";
	private static final String DOUBLE_SLASH = "//";
	private static final String CLOSEING_BRACKET = "]";
	private static final String BUTTON = "button[";

	private static final int ELEMENTS_IN_ONE_ROW = 3; // can be changed base on the UI

	private static final String ALOE = "ALOE";
	private static final String ALMOND = "ALMOND";

	private static final String SPF50 = "50";
	private static final String SPF30 = "30";

	ArrayList<MoisturizerDetailsDTO> aloeMoisturizerList = null;
	ArrayList<MoisturizerDetailsDTO> almondMoisturizerList = null;
	ArrayList<SunscreenDetailsDTO> SFP50SunscreenList = null;
	ArrayList<SunscreenDetailsDTO> SFP30SunscreenList = null;

	@BeforeTest
	public void initialize() throws IOException {
		driver = intializationDriver();
		log.info("driver is initialised");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void basenavigationpage() throws IOException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		log.info("driver is initialised");
		driver.get(prop.getProperty("url"));
		log.info("driver Navigate to home page");
		LandingPage l = new LandingPage(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		l.geti().click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		String iText = l.getiText().getText();

		String currentTemp = l.getcurrentTemp().getText();
		// To replace special characters with space
		String str = currentTemp.replaceAll("[^a-zA-Z0-9]", " ");
		int n = str.length();
		System.out.println("length of string is:" + n);
		String subString = str.substring(0, n - 1);
		String finalString = subString.trim();
		System.out.println("string without special char and char is" + finalString);

		int i = Integer.parseInt(finalString);
		System.out.println("interger is" + i);
		// int i = 18;

		if (i <= 19) {

			// To select the Moisturizer when whether is below 19 C
			System.out.println("Select the Moisturizer");
			log.info("select the moisturizer");
			moisturizerPage m = l.getMoisturizer();
			m.geti().click();

			List<WebElement> moist = m.getAllMoisturizers();
			int moistSize1 = moist.size();

			List<WebElement> price = m.getAllPrice();
			int pricesize = price.size();
			List<WebElement> button = m.getAllbutton();
			int buttonsize = button.size();

			// int counter = 0;

			// code to get the xpath and set it in a list only if buttonsize > 0
			List<String> xPathList = null;
			if (buttonsize > 0) {
				xPathList = getXpath(buttonsize);
			}

			aloeMoisturizerList = new ArrayList<MoisturizerDetailsDTO>();
			almondMoisturizerList = new ArrayList<MoisturizerDetailsDTO>();
			MoisturizerDetailsDTO moisturizerDetails = null;

			for (int j = 0; j < moistSize1; j++) {

				String ma = moist.get(j).getText().toUpperCase();
				System.out.println("moist is" + ma);

				// add in almond list else if name is aloe add in aloe list
				// and set price, name, xpath in object of MoisturizerDetailsDTO.
				if (ma.contains(ALOE)) {

					moisturizerDetails = new MoisturizerDetailsDTO();
					// set the name of moisturizer
					moisturizerDetails.setMoisturizerName(ma);

					// set the price value
					String p = price.get(j).getText();
					String pl = p.replaceAll("[^\\d]", " ");
					String finalpl = pl.trim();
					int ip = Integer.parseInt(finalpl);
					moisturizerDetails.setPrice(ip);
					System.out.println("price is" + ip);

					String btt = button.get(j).getText();
					moisturizerDetails.setButtonName(btt);
					System.out.println("button is" + btt);

					// set xpath of button
					moisturizerDetails.setxPath(xPathList.get(j));
					System.out.println("XPATH is:" + xPathList.get(j));

					aloeMoisturizerList.add(moisturizerDetails);
				} else if (ma.contains(ALMOND)) {
					moisturizerDetails = new MoisturizerDetailsDTO();
					moisturizerDetails.setMoisturizerName(ma);
					String p = price.get(j).getText();
					String pl = p.replaceAll("[^\\d]", " ");
					String finalpl = pl.trim();
					int ip = Integer.parseInt(finalpl);
					moisturizerDetails.setPrice(ip);
					System.out.println("price is" + ip);

					String btt = button.get(j).getText();
					moisturizerDetails.setButtonName(btt);

					// set xpath of button
					moisturizerDetails.setxPath(xPathList.get(j));
					System.out.println("XPATH is:" + xPathList.get(j));

					almondMoisturizerList.add(moisturizerDetails);
				} // String xp=xpathList.get(j).gettext(); moisturizerDetails.setxPath(xp);
			}

			// To sort aloe list on Price value

			Collections.sort(aloeMoisturizerList, new MoisturizerPriceComparator());
			int aloeSize = aloeMoisturizerList.size();

			System.out.println("after sorting aloe moisturizers are" + aloeMoisturizerList);
			// To sort almond list on Price value
			Collections.sort(almondMoisturizerList, new MoisturizerPriceComparator());
			int almndSize = aloeMoisturizerList.size();
			System.out.println("after sorting almond moisturizers are" + almondMoisturizerList);
			List<MoisturizerDetailsDTO> cheapestMoisturizerList = new ArrayList<MoisturizerDetailsDTO>();

			if (aloeMoisturizerList != null && aloeMoisturizerList.size() > 0) {
				cheapestMoisturizerList.add(aloeMoisturizerList.get(0));
			}

			if (almondMoisturizerList != null && almondMoisturizerList.size() > 0) {
				cheapestMoisturizerList.add(almondMoisturizerList.get(0));
			}

			if (cheapestMoisturizerList.size() > 0) {
				// TODO iterate the list one by one and call the required page with action read
				// from each object of type MoisturizerDetailsDTO
				for (int x = 0; x < cheapestMoisturizerList.size(); x++) {
					MoisturizerDetailsDTO obj = cheapestMoisturizerList.get(x);
					System.out.println(obj.toString());
					String xPath = obj.getxPath();
					System.out.println("XPATH is:::" + xPath);
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					m.getWebDriver().findElement(By.xpath(xPath)).click();

				}
			}

			System.out.println("*****************************");
			// after adding least expensive mositurizer that contains Aloe and almond to the
			// cart,go to the cart page
			CartPage cartP = m.getCartPage();
			// To get the text from the webelement
			cartP.getI().click();
			String iMessage = cartP.getI().getText();
			System.out.println("information message is" + iMessage);
			String firstString = cartP.getfirstProductPrice().getText();
			// TODO replace each character with a blank ("")
			String fprc = firstString.replaceAll("[^\\d]", " ");
			// TO removes white space from both ends of a string
			String finalpl = fprc.trim();
			// ToDo Convert String to Integer using parseInt()
			int price1 = Integer.parseInt(finalpl);
			System.out.println("price1 is" + price1);
			// To get the text from the webelement
			String secondString = cartP.getsecondProductPrice().getText();
			// TODO replace each character with a blank ("")
			String sprc = secondString.replaceAll("[^\\d]", " ");
			// TO removes white space from both ends of a string
			String finalpl2 = sprc.trim();
			// ToDo Convert String to Integer using parseInt()

			int price2 = Integer.parseInt(finalpl2);
			System.out.println("price2 is" + price2);
			int sum = price1 + price2;
			// To get the text from the webelement
			System.out.println("sum is" + sum);
			// To get the text from the webelement
			String totaltext = cartP.getTotalPrice().getText();
			// TODO replace each character with a blank ("")
			String tprc = totaltext.replaceAll("[^\\d]", " ");
			// TO removes white space from both ends of a string
			String finaltotal = tprc.trim();
			// ToDo Convert String to Integer using parseInt()
			int totalPrice = Integer.parseInt(finaltotal);
			// verifying the total amount by comparing totalprice of products(which is
			// autogenerated) with the total sum of products in the cart
			Assert.assertEquals(totalPrice, sum);
			System.out.println("total amount of products selected is correct");
			System.out.println("Assert passed");
			// TODO after verifying the total now start the payment process by clicking the
			// "pay with card " button
			PaymentPage pay = cartP.getPayWithCardButton();

			// Finding all iframe tags on a web page
			int size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("integer frames are" + size);
			// To Switch to the frame by index
			driver.switchTo().frame(0);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			pay.getEmail().sendKeys("testng@rediffmail.com");
			pay.getCardNumb().sendKeys("4242424242424242");
			pay.getValidityOfCard().sendKeys("0524");
			pay.getCVCnumb().sendKeys("234");
			pay.getZipCode().sendKeys("110018");
			PaymentMessage paymentMssgDisplay = pay.getpayButton();
			// To switchTo() parentFrame()
			driver.switchTo().defaultContent();
			paymentMssgDisplay.getI();

			String Result = paymentMssgDisplay.getpayMessage().getText();
			System.out.println("The result of this transaction is:" + Result); 

		}

		else if (i > 34) {
			// To select the Sunscreen when weather is above 34 C
			System.out.println("Select the Sunscreen");
			log.info("select the Sunscreen when whether is above 34 C");
			sunscreenPage sunscreen = l.getSunscreen();
			log.info("land on the screen page");
			sunscreen.geti().click();
			log.info("read the information");
			// To get the list of all sunscreen
			List<WebElement> allsunscreen = sunscreen.getAllSunscreen();
			// To get the number of sunscreens present on the page
			int sunscreenNumb = allsunscreen.size();
			// To get the list of all sunscreenprice
			List<WebElement> sunscreenPrice = sunscreen.getAllPrice();
			// To get how many prices present on the page
			int sunscreenPrc = sunscreenPrice.size();

			// To get the list of all add buttons
			List<WebElement> sunscreenbttn = sunscreen.getAllbutton();
			// To get how many add buttons present on the page
			int sunscreenAddBttn = sunscreenbttn.size();
			int counter = 0;

			// code to get the xpath and set it in a list only if buttonsize > 0
			List<String> xPathList = null;
			if (sunscreenAddBttn > 0) {
				xPathList = getXpath(sunscreenAddBttn);
			}

			SFP50SunscreenList = new ArrayList<SunscreenDetailsDTO>();
			SFP30SunscreenList = new ArrayList<SunscreenDetailsDTO>();
			SunscreenDetailsDTO sunscreenDetails = null;

			for (int j = 0; j < sunscreenNumb; j++) {
				String sunscreenname = allsunscreen.get(j).getText().toUpperCase();

				// To make the list of sunscreen with spf50
				if (sunscreenname.contains(SPF50)) {
					sunscreenDetails = new SunscreenDetailsDTO();
					// set the name of sunscreen
					sunscreenDetails.setsunscreenName(sunscreenname);

					// set the price value
					String p = sunscreenPrice.get(j).getText();
					// To replace characters with space
					String pl = p.replaceAll("[^\\d]", " ");
					// To trim white space
					String finalpl = pl.trim();
					int ip = Integer.parseInt(finalpl);
					sunscreenDetails.setPrice(ip);
					System.out.println("price is" + ip);

					// set the Addbutton value
					String btt = sunscreenbttn.get(j).getText();
					sunscreenDetails.setButtonName(btt);
					System.out.println("button is" + btt);

					// set xpath of Addbutton
					sunscreenDetails.setxPath(xPathList.get(j));
					System.out.println("XPATH is:" + xPathList.get(j));

					SFP50SunscreenList.add(sunscreenDetails);

				}
				// To make the list of sunscreen with spf30
				else if (sunscreenname.contains(SPF30)) {
					sunscreenDetails = new SunscreenDetailsDTO();
					// set the name of sunscreen
					sunscreenDetails.setsunscreenName(sunscreenname);

					// set the price value
					String p = sunscreenPrice.get(j).getText();
					// To replace characters with space
					String pl = p.replaceAll("[^\\d]", " ");
					// To trim white space
					String finalpl = pl.trim();
					int ip = Integer.parseInt(finalpl);
					sunscreenDetails.setPrice(ip);
					System.out.println("price is" + ip);

					// set the Addbutton value
					String btt = sunscreenbttn.get(j).getText();
					sunscreenDetails.setButtonName(btt);
					System.out.println("button is" + btt);

					// set xpath of Addbutton
					sunscreenDetails.setxPath(xPathList.get(j));
					System.out.println("XPATH is:" + xPathList.get(j));

					SFP30SunscreenList.add(sunscreenDetails);
				}

			}
			// To sort list on Price value

			Collections.sort(SFP50SunscreenList, new SunscreenPriceComparator());
			int SPF50Size = SFP50SunscreenList.size();

			System.out.println("after sorting aloe moisturizers are" + SFP50SunscreenList);

			Collections.sort(SFP30SunscreenList, new SunscreenPriceComparator());
			int SPF30Size = SFP30SunscreenList.size();
			System.out.println("after sorting almond moisturizers are" + SFP30SunscreenList);
			List<SunscreenDetailsDTO> cheapestSunscreenList = new ArrayList<SunscreenDetailsDTO>();

			if (SFP50SunscreenList != null && SFP50SunscreenList.size() > 0) {
				cheapestSunscreenList.add(SFP50SunscreenList.get(0));
			}

			if (SFP30SunscreenList != null && SFP30SunscreenList.size() > 0) {
				cheapestSunscreenList.add(SFP30SunscreenList.get(0));
			}

			if (cheapestSunscreenList.size() > 0) {
				// TODO iterate the list one by one and call the required page with action read
				// from each object of type MoisturizerDetailsDTO
				for (int x = 0; x < cheapestSunscreenList.size(); x++) {
					SunscreenDetailsDTO obj = cheapestSunscreenList.get(x);
					System.out.println(obj.toString());
					String xPath = obj.getxPath();
					System.out.println("XPATH is:::" + xPath);
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					sunscreen.getWebDriver().findElement(By.xpath(xPath)).click();

				}
			}

			// after adding least expensive mositurizer that contains sunscreen SPF 50 And
			// SPF 30 to the cart,go to the cart page
			CartPage cartP = sunscreen.getCartPage();
		
			// To get the text from the webelement
			cartP.getI().click();
			String iMessage = cartP.getI().getText();
			System.out.println("information message is" + iMessage);
			String firstString = cartP.getfirstProductPrice().getText();
			// TODO replace each character with a blank ("")
			String fprc = firstString.replaceAll("[^\\d]", " ");
			// TO removes white space from both ends of a string
			String finalpl = fprc.trim();
			// ToDo Convert String to Integer using parseInt()
			int price1 = Integer.parseInt(finalpl);
			System.out.println("price1 is" + price1);
			// To get the text from the webelement
			String secondString = cartP.getsecondProductPrice().getText();
			// TODO replace each character with a blank ("")
			String sprc = secondString.replaceAll("[^\\d]", " ");
			// TO removes white space from both ends of a string
			String finalpl2 = sprc.trim();
			// ToDo Convert String to Integer using parseInt()

			int price2 = Integer.parseInt(finalpl2);
			System.out.println("price2 is" + price2);
			int sum = price1 + price2;
			// To get the text from the webelement
			System.out.println("sum is" + sum);
			// To get the text from the webelement
			String totaltext = cartP.getTotalPrice().getText();
			// TODO replace each character with a blank ("")
			String tprc = totaltext.replaceAll("[^\\d]", " ");
			// TO removes white space from both ends of a string
			String finaltotal = tprc.trim();
			// ToDo Convert String to Integer using parseInt()
			int totalPrice = Integer.parseInt(finaltotal);
			// verifying the total amount by comparing totalprice of products(which is
			// autogenerated) with the total sum of products in the cart
			Assert.assertEquals(totalPrice, sum);
			System.out.println("total amount of products selected is correct");
			System.out.println("Assert passed");
			// TODO after verifying the total now start the payment process by clicking the
			// "pay with card " button
			PaymentPage pay = cartP.getPayWithCardButton();

			// Finding all iframe tags on a web page
			int size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("integer frames are" + size);
			// To Switch to the frame by index
			driver.switchTo().frame(0);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			pay.getEmail().sendKeys("testng@rediffmail.com");
			pay.getCardNumb().sendKeys("4242424242424242");
			pay.getValidityOfCard().sendKeys("0524");
			pay.getCVCnumb().sendKeys("234");
			pay.getZipCode().sendKeys("110018");
			PaymentMessage paymentMssgDisplay = pay.getpayButton();
			// To switchTo() parentFrame()
			driver.switchTo().defaultContent();
			paymentMssgDisplay.getI();

			String Result = paymentMssgDisplay.getpayMessage().getText();
			System.out.println("The result of this transaction is:" + Result);

		}
		driver.close();
		log.info("driver is closed");
	}

	private int getNoOfRows(int totalButtons) {
		int noOfRows = totalButtons / ELEMENTS_IN_ONE_ROW;
		return noOfRows;
	}

	private List<String> getXpath(int buttonsize) {
		int numberOfRows = getNoOfRows(buttonsize);
		ArrayList<String> xPathList = new ArrayList<String>();

		for (int i = 1; i <= numberOfRows; i++) {
			for (int j = 1; j <= ELEMENTS_IN_ONE_ROW; j++) {
				StringBuilder xPath = new StringBuilder(XPATH);

				xPath.append(i + 1).append(CLOSEING_BRACKET).append(XPATH).append(j).append(CLOSEING_BRACKET)
						.append(DOUBLE_SLASH).append(BUTTON).append(1).append(CLOSEING_BRACKET);

				xPathList.add(xPath.toString());
			}
		}
		return xPathList;
	}

}
