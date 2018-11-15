package dojo.kattapotter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;

class BookSetsPossibility {

    private final List<BookSet> bookSets;

    BookSetsPossibility(List<String> books, int maxBookSetSize) {
        this.bookSets = generateBookSets(books, maxBookSetSize);
    }

    private List<BookSet> generateBookSets(List<String> books, int maxBookSetSize) {
        List<BookSet> bookSets = new ArrayList<>();
        for (String book : books) {
            Optional<BookSet> biggestAvailableBookSet = bookSets.stream()
                    .filter(bookSet -> bookSet.size() < maxBookSetSize && !bookSet.contains(book))
                    .max(comparing(BookSet::size));

            if (biggestAvailableBookSet.isPresent()) {
                biggestAvailableBookSet.get().add(book);
            } else {
                BookSet bookSet = new BookSet();
                bookSet.add(book);
                bookSets.add(bookSet);
            }
        }
        return bookSets;
    }

    BigDecimal getPrice() {
        return bookSets.stream()
                .map(BookSet::getPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

}
