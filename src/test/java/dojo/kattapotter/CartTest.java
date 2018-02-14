package dojo.kattapotter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class CartTest {

	private static final String BOOK1 = "book1";
	private static final String BOOK2 = "book2";
	private static final String BOOK3 = "book3";
	private static final String BOOK4 = "book4";
	private static final String BOOK5 = "book5";

	private Cart cart = new Cart();

	@Test
	public void should_return_0_if_no_article() {
		// When
		BigDecimal price = cart.checkout();

		// Then
		Assert.assertEquals(BigDecimal.ZERO, price);
	}

	@Test
	public void should_return_8_for_one_book() {
		// Given
		cart.addBook(BOOK1);

		// When
		BigDecimal price = cart.checkout();

		// Then
		Assert.assertEquals(new BigDecimal("8"), price);
	}

	@Test
	public void should_apply_10_percent_discount_for_two_different_books() {
		// Given
		cart.addBook(BOOK1);
		cart.addBook(BOOK2);

		// When
		BigDecimal price = cart.checkout();

		// Then
		Assert.assertEquals(new BigDecimal("14.4"), price);
	}

	@Test
	public void should_apply_20_percent_discount_for_three_different_books() {
		// Given
		cart.addBook(BOOK1);
		cart.addBook(BOOK2);
		cart.addBook(BOOK3);

		// When
		BigDecimal price = cart.checkout();

		// Then
		Assert.assertEquals(new BigDecimal("19.2"), price);
	}

	@Test
	public void should_apply_30_percent_discount_for_four_different_books() {
		// Given
		cart.addBook(BOOK1);
		cart.addBook(BOOK2);
		cart.addBook(BOOK3);
		cart.addBook(BOOK4);

		// When
		BigDecimal price = cart.checkout();

		// Then
		Assert.assertEquals(new BigDecimal("22.4"), price);
	}

	@Test
	public void should_apply_40_percent_discount_for_five_different_books() {
		// Given
		cart.addBook(BOOK1);
		cart.addBook(BOOK2);
		cart.addBook(BOOK3);
		cart.addBook(BOOK4);
		cart.addBook(BOOK5);

		// When
		BigDecimal price = cart.checkout();

		// Then
		Assert.assertEquals(new BigDecimal("24"), price);
	}

	@Test
	public void should_not_apply_discount_for_two_same_books() {
		// Given
		cart.addBook(BOOK1);
		cart.addBook(BOOK1);

		// When
		BigDecimal price = cart.checkout();

		// Then
		Assert.assertEquals(new BigDecimal("16"), price);
	}

	@Test
	public void should_apply_10_percent_discount_for_a_two_books_set_and_normal_price_for_another_one_book_set() {
		// Given
		cart.addBook(BOOK1);
		cart.addBook(BOOK1);
		cart.addBook(BOOK3);

		// When
		BigDecimal price = cart.checkout();

		// Then
		Assert.assertEquals(new BigDecimal("22.4"), price);
	}

	// Note that this is cheaper then one set of 5 and one set of 3
	@Test
	public void should_apply_30_percent_discount_for_a_two_books_set_40_percent_discount_for_another_four_book_set() {
		// Given
		cart.addBook(BOOK1);
		cart.addBook(BOOK1);
		cart.addBook(BOOK2);
		cart.addBook(BOOK2);
		cart.addBook(BOOK3);
		cart.addBook(BOOK3);
		cart.addBook(BOOK4);
		cart.addBook(BOOK5);

		// When
		BigDecimal price = cart.checkout();

		// Then
		Assert.assertEquals(new BigDecimal("44.8"), price);
	}

}