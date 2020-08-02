package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaymentMessage {
WebDriver driver;
By payMessage=By.xpath("//h2[contains(text(),'PAYMENT')]");
By payI=By.xpath("//span[@class='octicon octicon-info']");
public PaymentMessage(WebDriver driver) {
	this.driver = driver;
}
public WebElement getI() {
	return driver.findElement(payI);
}
public WebElement getpayMessage() {
	return driver.findElement(payMessage);
}
}
