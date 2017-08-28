import lnu.resources.*;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;
import org.junit.Before;
import lnu.models.*;
import lnu.dao.*;
import java.util.ArrayList;
import java.util.List;
import static io.dropwizard.testing.FixtureHelpers.*;

// Look at this to find out how to create an api test.
// Info on how to test with dropwizard: http://www.dropwizard.io/0.8.0/docs/manual/testing.html
public class CatalogTest {
	Book first;
	Book second;
	Book up;
	List<Book> booksList;
	List<Book> booksList2;
	Catalog books;
	String jsonString;

	@Before
	public void setUp(){

		// Create book Objects with values.

		first = new Book("title:one","author:one","genre:one","publishDate:one","1","description:one","price:one");
		second = new Book("title:two","author:two","genre:two","publishDate:two","2","description:two","price:two");
		up = new Book("title:oneUp","author:oneUp","genre:oneUp","publishDate:oneUp","1","description:oneUp","price:oneUp");

		booksList = new ArrayList<Book>();
		booksList.add(first);
		booksList.add(second);
		books = new Catalog();
		books.setBooks(booksList);
	}

	@Test
	public void testGetBooks(){
		//Test that the method getBooks() returns the right list.
		assertEquals(books.getBooks(), booksList);
	}

	@Test
	public void testDeleteBook(){
		//if the id is not found, nthing will be deleted
		books.deleteBook("7");
		assertEquals(books.getBooks(), booksList);
		//if id is found, book will be deleted
		booksList.remove(0);
		books.deleteBook("1");
		assertEquals(books.getBooks(), booksList);
		booksList.add(first);
	}
  // testing added book
	@Test
	public void testAddBook(){
		books.addBook(first);
		assertEquals(books.getBooks(), booksList);
	}

	@Test
	public void testEditBook(){
		//if no id is found nothing will change
		books.deleteBook("1");
		books.editBook(first,"1");
		assertEquals(books.getBooks(), booksList);
		books.addBook(first);
		books.editBook(up,"1");
		booksList.set(2, up);
		assertEquals(books.getBooks(), booksList);
	}
}
