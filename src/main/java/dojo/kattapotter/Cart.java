package dojo.kattapotter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Cart {

	private final List<String> books = new ArrayList<>();

	public void addBook(String bookName) {
		books.add(bookName);
	}

	public BigDecimal checkout() {
		return generateAllBookSetsPossibilities().stream()
				.min(Comparator.comparing(BookSetsPossibility::getPrice))
				.map(BookSetsPossibility::getPrice)
				.orElse(BigDecimal.ZERO);
	}

	private List<BookSetsPossibility> generateAllBookSetsPossibilities() {
		return IntStream.rangeClosed(1, (int) books.stream().distinct().count())
				.mapToObj(i -> new BookSetsPossibility(books, i))
				.collect(Collectors.toList());
	}

}
