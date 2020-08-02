package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {
	private WebDriver driver;

	By i = By.xpath("//body/div/div/span[1]");
	By iText = By.xpath("//div[contains(text(),'Shop for moisturizers if the weather is below 19 d')]");
	
	By moisturizers = By.xpath("//button[contains(text(),'Buy moisturizers')]");
	By sunscreen = By.xpath("//button[contains(text(),'Buy sunscreens')]");
	By currentTemp = By.xpath("//span[@id='temperature']");
	public LandingPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement geti() {
		return driver.findElement(i);
	}
	public WebElement getiText() {
		return driver.findElement(iText);
	}

	public WebElement getcurrentTemp() {
		return driver.findElement(currentTemp);
	}
	

	public moisturizerPage getMoisturizer() {
		driver.findElement(moisturizers).click();
		moisturizerPage mp = new moisturizerPage(driver);
		return mp;

}
	public sunscreenPage getSunscreen() {
		driver.findElement(sunscreen).click();
		sunscreenPage ss = new sunscreenPage(driver);
		return ss;

}
}
