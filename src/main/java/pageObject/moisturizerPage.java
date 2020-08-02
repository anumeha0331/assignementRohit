package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class moisturizerPage {

	public WebDriver driver;
    By moisturizersall = By.xpath("//p[@class='font-weight-bold top-space-10']");
	By i = By.xpath("//html//body//div//div//span");
	By almondMoisturizers = By.xpath("//p[contains(text(),'Almond')]");
	By aloeMoisturizers = By.xpath("//p[contains(text(),'Aloe')]");
	By price = By.xpath("//p[contains(text(),'Price: ')]");
	By button = By.xpath("//button[@class='btn btn-primary']");
	By cartbttn= By.xpath("//button[contains(text(),'Cart -')]");
	public moisturizerPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement geti() {
		return driver.findElement(i);
	}
	
	public WebDriver getWebDriver() {
		return driver;
	}
	
	public List<WebElement> getAllMoisturizers() {
		 return driver.findElements(moisturizersall);		 
	}
	public List<WebElement> getAllPrice() {
		return driver.findElements(price);
	}
	public List<WebElement> getAllbutton() {
		 return driver.findElements(button);
		 
	}
	public List<WebElement> getalmondMoisturizers() {
		return driver.findElements(almondMoisturizers);
	}
	public List<WebElement> getaloeMoisturizers() {
		return driver.findElements(aloeMoisturizers);
	}
	
	public CartPage getCartPage() {
		driver.findElement(cartbttn).click();
		CartPage bk = new CartPage(driver);
		return bk;

	}

}
