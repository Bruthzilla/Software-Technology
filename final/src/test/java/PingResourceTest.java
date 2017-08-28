import lnu.resources.*;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;
import lnu.dao.*;
import lnu.models.*;
import org.junit.Before;
import org.junit.After;
import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.client.Entity;
// Look at this to find out how to create an api test.
// Info on how to test with dropwizard: http://www.dropwizard.io/0.8.0/docs/manual/testing.html
public class PingResourceTest {
	booksDAO bDao;
	Catalog back;
	Catalog test;
	Book one;

	@ClassRule
	public static final ResourceTestRule resources = ResourceTestRule.builder()
			.addResource(new PingResource())
			.addResource(new AddBookResource())
			.addResource(new EditBookResource())
			.addResource(new GetBooksResource())
			.addResource(new RemoveBookResource())
			.build();

	@Before
	public void setUp(){
		bDao = new booksDAO();
		back = bDao.readXml();
		test = bDao.readXml();
		one = new Book("title","author","genre","2015-06-02","0123456789","description","price");
	}

	@Test
	public void testGetPing() {
		assertThat(resources.client().target("/ping").request().get(String.class))
				.isEqualTo("{\"answer\": \"pong\"}");
	}

	@Test
	public void testAddBook(){
		assertThat(resources.client().target("/books/").request().put(Entity.entity(one.toJson(),MediaType.APPLICATION_JSON)).getStatus())
		.isEqualTo(204);
	}

	@Test
	public void testEditBook(){
		test.addBook(one);
		bDao.writeXml(test);
		assertThat(resources.client().target("/books/0123456789").request().post(Entity.entity(one.toJson(),MediaType.APPLICATION_JSON)).getStatus())
		.isEqualTo(404);
		assertThat(resources.client().target("/books/   ").request().post(Entity.entity(one.toJson(),MediaType.APPLICATION_JSON)).getStatus())
		.isEqualTo(404);
		assertThat(resources.client().target("/books/222b").request().post(Entity.entity(one.toJson(),MediaType.APPLICATION_JSON)).getStatus())
		.isEqualTo(404);
	}

	@After
	public void reset(){
		bDao.writeXml(back);
	}
}
