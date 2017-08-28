package lnu.resources;

import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;

import lnu.dao.*;
import lnu.models.Book;
import java.io.File;
import java.util.List;
import java.io.*;

/** Consumes the json string from the Client
	 * Converts that String into an Object
	 * Adds the new book to the database.
	 */
@Consumes(MediaType.APPLICATION_JSON)
@Path("/books")
public class AddBookResource {
	private booksDAO bDao = new booksDAO();

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void addBook(String json){

		Catalog cat = bDao.readXml(); //reading the xml file
		System.out.println("String " + json);
		Book book = new Book();

		try{
			book = cat.convertToBook(json); //converting from json
		}catch(Exception e){
			e.printStackTrace();
		}

		if(book.getId() == null){
			book.setId((cat.getNewId() + 1)+""); //giving the new ID a +1 to be the highest
		}
		cat.addBook(book);
		bDao.writeXml(cat);
	}
}
