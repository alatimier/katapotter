package dojo.kattapotter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;

class BookSet extends HashSet<String> {

    private static final BigDecimal BOOK_PRICE = new BigDecimal(8);

    BigDecimal getPrice() {
        return new BigDecimal(this.size())
                .multiply(BOOK_PRICE)
                .multiply(new BigDecimal(getDiscount()))
                .setScale(2, RoundingMode.HALF_DOWN);
    }

    private double getDiscount() {
        switch (this.size()) {
            case 2:
                return 0.95;
            case 3:
                return 0.90;
            case 4:
                return 0.80;
            case 5:
                return 0.75;
            default:
                return 1;
        }
    }

}
