package dojo.kattapotter;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Cart {

	public static final BigDecimal BOOK_PRICE = new BigDecimal(8);

	private final Map<String, Integer> books = new HashMap<>();

	public void addBook(String bookName) {
		books.merge(bookName, 1, (a, b) -> a + b);
	}

	public BigDecimal checkout() {
		return generateBookSets().stream()
				.map(bookSet -> new BigDecimal(bookSet.size())
						.multiply(BOOK_PRICE)
						.multiply(new BigDecimal(bookSet.getDiscount()))
						.divide(new BigDecimal(100))
				)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}

	private List<BookSet> generateBookSets() {
		List<BookSet> bookSets = new LinkedList<>();
		boolean hasMoreBooks;
		do {
			hasMoreBooks = false;
			for (Map.Entry<String, Integer> bookEntry : books.entrySet()) {
				String bookName = bookEntry.getKey();
				Integer number = bookEntry.getValue();
				if (number > 0) {
					getBestBookSet(bookName, bookSets).add(bookName);
					books.put(bookName, --number);
				}
				hasMoreBooks = hasMoreBooks || number > 0;
			}
		} while (hasMoreBooks);
		return bookSets;
	}

	// Return the biggest available book set or a new one
	private BookSet getBestBookSet(String bookName, List<BookSet> bookSets) {
		Optional<BookSet> availableSet = bookSets.stream()
				.sorted(Comparator.comparingInt(BookSet::size).reversed())
				.filter(b -> !b.contains(bookName))
				.findFirst();
		if (availableSet.isPresent()) {
			return availableSet.get();
		}
		BookSet bookSet = new BookSet();
		bookSets.add(bookSet);
		return bookSet;
	}

	// Return the biggest available book set or a new one
	private BookSet getBestBookSetForMaxSize(String bookName, List<BookSet> bookSets, int maxSize) {
		Optional<BookSet> availableSet = bookSets.stream()
				.filter(b -> b.size() < maxSize)
				.filter(b -> !b.contains(bookName))
				.findFirst();
		if (availableSet.isPresent()) {
			return availableSet.get();
		}
		BookSet bookSet = new BookSet();
		bookSets.add(bookSet);
		return bookSet;
	}

}
