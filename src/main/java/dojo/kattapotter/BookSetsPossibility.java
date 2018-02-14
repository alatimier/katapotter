package dojo.kattapotter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class BookSetsPossibility {

	private static final BigDecimal BOOK_PRICE = new BigDecimal(8);

	private final int maxBookSetSize;
	private final List<BookSet> bookSets;

	BookSetsPossibility(List<String> books, int maxBookSetSize) {
		this.bookSets = new ArrayList<>();
		this.maxBookSetSize = maxBookSetSize;
		assignBooksToSets(books);
	}

	private void assignBooksToSets(List<String> books) {
		for (String book : books) {
			Optional<BookSet> bestAvailableBookSet = bookSets.stream()
					.filter(b -> b.size() < maxBookSetSize)
					.filter(b -> !b.contains(book))
					.findFirst();

			if (bestAvailableBookSet.isPresent()) {
				bestAvailableBookSet.get().add(book);
			} else {
				BookSet bookSet = new BookSet();
				bookSet.add(book);
				bookSets.add(bookSet);
			}
		}
	}

	BigDecimal getPrice() {
		return bookSets.stream()
				.map(bookSet -> new BigDecimal(bookSet.size())
						.multiply(BOOK_PRICE)
						.multiply(new BigDecimal(bookSet.getDiscount()))
						.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_DOWN)
				)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}

}
