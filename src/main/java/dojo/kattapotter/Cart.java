package dojo.kattapotter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Cart {

	private final Map<String, Integer> books = new HashMap<>();

	public void addBook(String bookName) {
		books.merge(bookName, 1, (a, b) -> a + b);
	}

	public BigDecimal checkout() {
		return generateAllBookSetsPossibilities().stream()
				.map(BookSetsPossibility::getPrice)
				.sorted()
				.findFirst()
				.orElse(BigDecimal.ZERO);
	}

	private List<BookSetsPossibility> generateAllBookSetsPossibilities() {
		return IntStream.rangeClosed(1, books.size())
				.mapToObj(i -> new BookSetsPossibility(books, i))
				.collect(Collectors.toList());
	}

}
