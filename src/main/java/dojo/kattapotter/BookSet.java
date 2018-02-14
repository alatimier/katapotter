package dojo.kattapotter;

import java.util.HashSet;

class BookSet extends HashSet<String> {

	int getDiscount() {
		switch (this.size()) {
			case 2:
				return 95;
			case 3:
				return 90;
			case 4:
				return 80;
			case 5:
				return 75;
			default:
				return 100;
		}
	}

}
