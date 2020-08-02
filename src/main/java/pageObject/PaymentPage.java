package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaymentPage {
	public WebDriver driver;

	By Email=By.xpath("//input[@placeholder='Email']");
	By CardNumb=By.xpath("//input[@placeholder='Card number']");
	By ExpiryDate=By.xpath("//input[@placeholder='MM / YY']");
	By CVCnumb=By.xpath("//input[@placeholder='CVC']");
	By ZipCode=By.xpath("//input[@placeholder='ZIP Code']");
	By payButton=By.xpath("//button[@type='submit']");
	public PaymentPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getEmail() {
		return driver.findElement(Email);
	}
	public WebElement getCardNumb() {
		return driver.findElement(CardNumb);
	}
	public WebElement getValidityOfCard() {
		return driver.findElement(ExpiryDate);
	}
	public WebElement getCVCnumb() {
		return driver.findElement( CVCnumb);
	}
	public WebElement getZipCode() {
		return driver.findElement(ZipCode);
	}
	public PaymentMessage getpayButton() {
		 driver.findElement(payButton).click();
		 PaymentMessage Paymssg=new PaymentMessage(driver);
		 return Paymssg;
	}
}
