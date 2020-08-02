package pageObject;

public class MoisturizerDetailsDTO { 

	private String moisturizerName;
	private String buttonName;
	private Integer price;
	private String xPath;

	public String getMoisturizerName() {
		return moisturizerName;
	}

	public void setMoisturizerName(String moisturizerName) {
		this.moisturizerName = moisturizerName;
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
		return "MoisturizerDetailsDTO [moisturizerName=" + moisturizerName + ", buttonName=" + buttonName + ", price="
				+ price + ", xPath=" + xPath + "]";
	}

}
