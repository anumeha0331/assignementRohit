package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {
	public WebDriver driver;
	By firstProductPrice= By.xpath("/html[1]/body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]");
	By secondProductPrice= By.xpath("/html[1]/body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[2]/td[2]");
	By TotalPrice= By.xpath("//p[@id='total']");
	By i= By.xpath("//body/div/div/span[1]");
	By payButton=By.xpath("//span[contains(text(),'Pay with Card')]");
	public CartPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getI() {
		return driver.findElement(i);
	}
	public WebElement getfirstProductPrice() {
		return driver.findElement(firstProductPrice);
	}
	public WebElement getsecondProductPrice() {
		return driver.findElement(secondProductPrice);
	}
	public WebElement getTotalPrice() {
		return driver.findElement(TotalPrice);
	}
	
	public PaymentPage getPayWithCardButton() {
		driver.findElement(payButton).click();
		PaymentPage payment = new PaymentPage(driver);
		return payment;

	}
}
