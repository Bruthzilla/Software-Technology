package lnu.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import lnu.dao.*;
import lnu.models.Book;

import java.io.*;
import javax.ws.rs.NotFoundException;
/**
	 * Creating a new object from the xml file.
	 * Updates the List where the book with the
	 * id was matched and removed.
	 */
@Produces(MediaType.APPLICATION_JSON)
@Path("/books")
public class RemoveBookResource {
	private booksDAO bDao = new booksDAO();

	@DELETE
	@Path("{id}")
	public void removeBooks(@PathParam("id") String id) {

		Catalog cat = bDao.readXml(); //reading file

		if(id == null || id.trim().length() == 0){
			throw new NotFoundException();
		}

		if(!cat.deleteBook(id)){ //matching id and deleting
			throw new NotFoundException();
		}
		bDao.writeXml(cat); //updating list + writing to xmlfile
	}
}
