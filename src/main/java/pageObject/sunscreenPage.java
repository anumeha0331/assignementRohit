package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class sunscreenPage {
	public WebDriver driver;

	By i = By.xpath("//span[@class='octicon octicon-info']");
	By sunscreenall = By.xpath("//p[@class='font-weight-bold top-space-10']");
	By SPF30sunscreen = By.xpath("//p[@class='font-weight-bold top-space-10' and contains(text(),'30')]");
	By SPF50sunscreen = By.xpath("//p[@class='font-weight-bold top-space-10' and contains(text(),'50')]");
	By price = By.xpath("//p[contains(text(),'Price:')]");
	By Addbutton = By.xpath("//button[@class='btn btn-primary']");
	By cartbttn = By.xpath("//button[contains(text(),'Cart -')]");

	public sunscreenPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement geti() {
		return driver.findElement(i);
	}

	public WebDriver getWebDriver() {
		return driver;
	}

	public List<WebElement> getAllSunscreen() {
		return driver.findElements(sunscreenall);
	}

	public List<WebElement> getAllPrice() {
		return driver.findElements(price);
	}

	public List<WebElement> getAllbutton() {
		return driver.findElements(Addbutton);

	}

	public List<WebElement> getSPF30sunscreen() {
		return driver.findElements(SPF30sunscreen);
	}

	public List<WebElement> getSPF50sunscreen() {
		return driver.findElements(SPF50sunscreen);
	}

	public CartPage getCartPage() {
		driver.findElement(cartbttn).click();
		CartPage bk = new CartPage(driver);
		return bk;

	}

}
