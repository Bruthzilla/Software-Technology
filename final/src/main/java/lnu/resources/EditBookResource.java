package lnu.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;
import java.io.*;
import javax.ws.rs.NotFoundException;

import lnu.dao.*;
import lnu.models.Book;
/**
	 * Accepts the Json string and ID
	 * the id matches with the correct book in the List
	 * and replaces that book with the one created
	 * with the provided Json string.
	 */
@Path("/books")
public class EditBookResource {
	private booksDAO bDao = new booksDAO();

	@POST
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void editBook(@PathParam("id") String id, String json){

		Catalog cat = bDao.readXml(); //reading

		if(id == null || id.trim().length() == 0){
			throw new NotFoundException();
		}

		if(cat.editBook(cat.convertToBook(json), id)){
			throw new NotFoundException();
		}
		bDao.writeXml(cat); //writing back to xml
	}

}
