package pageObject;

import java.util.Comparator;

public class SunscreenPriceComparator implements Comparator <SunscreenDetailsDTO>{

	

	public int compare(SunscreenDetailsDTO o1, SunscreenDetailsDTO o2) {
		// TODO Auto-generated method stub
		return o1.getPrice() - o2.getPrice();
	}

}
