package dojo.kattapotter;

import java.util.HashSet;

public class BookSet extends HashSet<String> {

	public int getDiscount() {
		switch (this.size()) {
			case 2:
				return 90;
			case 3:
				return 80;
			case 4:
				return 70;
			case 5:
				return 60;
			default:
				return 100;
		}
	}

}
