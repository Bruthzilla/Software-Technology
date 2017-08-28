
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import lnu.models.Book;
import lnu.dao.*;
import lnu.resources.*;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;

public class BooksDAOTest{
	Catalog backup;
	Catalog test1;
	Catalog test2;
	booksDAO bDao;
	ArrayList<Book> booksList;

	@Before
	public void setUp(){
		bDao = new booksDAO();
		backup = bDao.readXml();
		Book first = new Book("1","title:one","author:one","genre:one","publishDate:one","price:one","description:one");
		Book sec = new Book("2","title:two","author:two","genre:two","publishDate:two","price:two","description:two");
		Book third = new Book("3","title:three","author:three","genre:three","publishDate:three","price:three","description:three");
		Book fourth = new Book("4","title:four","author:four","genre:four","publishDate:four","price:four","description:four");

		booksList = new ArrayList<Book>();
		booksList.add(first);
		booksList.add(sec);
		booksList.add(third);
		booksList.add(fourth);

		test1 = new Catalog();
		test1.setBooks(booksList);
	}

	@Test
	public void testDao(){
		bDao.writeXml(test1);
		test2 = bDao.readXml();
		assertEquals(test1.toJson(),test2.toJson());
	}

	@After
	public void reset(){
		bDao.writeXml(backup);
	}
}
