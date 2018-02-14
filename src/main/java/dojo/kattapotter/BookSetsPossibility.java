package dojo.kattapotter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

class BookSetsPossibility {

	private static final BigDecimal BOOK_PRICE = new BigDecimal(8);

	private final int maxBookSetSize;
	private final List<BookSet> bookSets;

	BookSetsPossibility(Map<String, Integer> books, int maxBookSetSize) {
		this.bookSets = new ArrayList<>();
		this.maxBookSetSize = maxBookSetSize;
		assignBooksToSets(new HashMap<>(books));
	}

	private void assignBooksToSets(Map<String, Integer> books) {
		boolean hasMoreBooks;
		do {
			hasMoreBooks = false;
			for (Map.Entry<String, Integer> bookEntry : books.entrySet()) {
				String bookName = bookEntry.getKey();
				Integer number = bookEntry.getValue();
				if (number > 0) {
					getBestBookSet(bookName).add(bookName);
					books.put(bookName, --number);
				}
				hasMoreBooks = hasMoreBooks || number > 0;
			}
		} while (hasMoreBooks);
	}

	// Return the biggest available book set or a new one
	private BookSet getBestBookSet(String bookName) {
		Optional<BookSet> bestAvailableBookSet = bookSets.stream()
				.filter(b -> b.size() < maxBookSetSize)
				.filter(b -> !b.contains(bookName))
				.findFirst();
		if (bestAvailableBookSet.isPresent()) {
			return bestAvailableBookSet.get();
		}
		BookSet bookSet = new BookSet();
		bookSets.add(bookSet);
		return bookSet;
	}

	BigDecimal getPrice() {
		return bookSets.stream()
				.map(bookSet -> new BigDecimal(bookSet.size())
						.multiply(BOOK_PRICE)
						.multiply(new BigDecimal(bookSet.getDiscount()))
						.divide(new BigDecimal(100))
				)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}

}
