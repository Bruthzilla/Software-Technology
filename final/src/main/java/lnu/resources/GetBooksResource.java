package lnu.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import lnu.models.Book;
import lnu.dao.*;

import java.io.*;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.JsonMappingException;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerationException;
// Response will be JSON
@Produces(MediaType.APPLICATION_JSON)

// This is the class that will be called when "localhost:9090/api/books" get called by the browser.
// FOR THE CURIOUS: Take a look in the config.yml to find out why "/api" is present in the path.
/**
	 * Creates a new object from the xml file.
	 * Convert the booklist to a Json string
	 */
@Path("/books")
public class GetBooksResource {
	private booksDAO bDao = new booksDAO();

	@GET
	public String getBooks(@DefaultValue("All") @QueryParam("title") String title) {
		Catalog cat = bDao.readXml();
		if(cat == null){ //Catalog null an exception is thrown
			throw new NotFoundException();
		}
		if(cat.getBooks().size() == 0){ //if size is zero
			throw new NotFoundException();
		}

		if(title.equals("All")){
      String str="[" + cat.toJson() + "]"; //converting to json
			return str;
		}else {
			Catalog cat2 = new Catalog();
			cat.nwCat(cat2, title);

			if(cat2.getBooks().isEmpty()){
				throw new NotFoundException();
			}
      String str2="[" + cat2.toJson() + "]"; //converting to json
      return str2;
		}
	}
}
