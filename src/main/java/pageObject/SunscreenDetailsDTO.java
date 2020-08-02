package pageObject;

import java.util.Comparator;

public class SunscreenDetailsDTO implements Comparator<SunscreenDetailsDTO>{

	private String sunscreenName;
	private String buttonName;
	private Integer price;
	private String xPath;
	
	public String getsunscreenName() {
		return sunscreenName;
	}
	public void setsunscreenName(String sunscreenName) {
		this.sunscreenName = sunscreenName;
	}
	public String getButtonName() {
		return buttonName;
	}
	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getxPath() {
		return xPath;
	}
	public void setxPath(String xPath) {
		this.xPath = xPath;
	}
	@Override
	public String toString() {
		return "SunscreenDetailsDTO [getMoisturizerName()=" + getsunscreenName() + ", getButtonName()="
				+ getButtonName() + ", getPrice()=" + getPrice() + ", getxPath()=" + getxPath() + "]";
	}
	public int compare(SunscreenDetailsDTO o1, SunscreenDetailsDTO o2) {
		// TODO Auto-generated method stub
		return o1.getPrice() - o2.getPrice();
	}
	
}
