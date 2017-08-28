
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import lnu.models.Book;
import lnu.dao.*;
import static org.assertj.core.api.Assertions.assertThat;

public class BookTest {
	Book first;
	Book sec;
	String title = "TitleTest";
	String id = "1";
	String author = "AuthorTest";
	String genre = "genreTest";
	String publish_date = "publishDateTest";
	String price = "priceTest";
	String description = "descriptionTest";

	@Before
	public void setUp(){
		sec = new Book();
		first = new Book();
		first.setTitle(title);
		first.setId(id);
		first.setAuthor(author);
		first.setGenre(genre);
		first.setPublish_date(publish_date);
		first.setPrice(price);
		first.setDescription(description);
	}

	@Test
	public void testBook(){
		assertEquals(first.getTitle(),title);
		assertEquals(first.getId(),id);
		assertEquals(first.getAuthor(),author);
		assertEquals(first.getGenre(),genre);
		assertEquals(first.getPublish_date(),publish_date);
		assertEquals(first.getPrice(),price);
		assertEquals(first.getDescription(),description);
	}

	@Test
	public void testEmpty(){
		assertNull(sec.getTitle());
		assertNull(sec.getId());
		assertNull(sec.getAuthor());
		assertNull(sec.getGenre());
		assertNull(sec.getPublish_date());
		assertNull(sec.getPrice());
		assertNull(sec.getDescription());
	}

	@Test
	public void testToString(){
		assertEquals(first.toString(),toStringTest());
	}

	public String toStringTest(){
		String output = id+ " "+title+" "+author+" "+genre+" "+publish_date+" "+price+" "+description;
		return output;
	}
//  String temp = id+ " "+title+" "+author+" "+genre+" "+publish_date+" "+price+" "+description;
}
