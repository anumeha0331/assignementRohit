package pageObject;

import java.util.Comparator;

public class MoisturizerPriceComparator implements Comparator<MoisturizerDetailsDTO> {



	public int compare(MoisturizerDetailsDTO o1, MoisturizerDetailsDTO o2) {
		// TODO Auto-generated method stub
		return o1.getPrice() - o2.getPrice();
	}

}
