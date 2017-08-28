// Use this file to write and read the xml file.
// http://www.journaldev.com/1234/jaxb-tutorial-example-to-convert-object-to-xml-and-xml-to-object
// javax.xml.bind is added as a part of the sdk from java7 and forward.
package lnu.dao;

import java.util.List;
import lnu.models.Book;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
//import javax.xml.bind.annotation.XmlAccessType;
//import javax.xml.bind.annotation.XmlAccessorType;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonView;
import org.codehaus.jackson.map.DeserializationConfig;


@XmlRootElement(name = "catalog")
public class Catalog {

	private List<Book> books;

public Catalog(){
		books = new ArrayList<Book>();
	}

@XmlElement(name = "book")
public void setBooks(List<Book> books) {
	this.books = books;
}

public List<Book> getBooks() {
	return books;
}

public boolean deleteBook(String id){
		for(int i = 0; i < books.size(); i++){
			if(books.get(i).getId().equals(id)){
				books.remove(i);
				return true;
			}
		}
		return false;
	}

public void addBook(Book book){
	books.add(book);
}

public String toJson(){
	int length = 0;
	ObjectMapper map = new ObjectMapper();
	String jsonInString = "";
	try {
		for(int i = length;i < books.size();i++){
			jsonInString += map.writeValueAsString(books.get(i));
			if(i + 1 != books.size()){
				jsonInString += ",";
			}
		}
	} catch (JsonGenerationException e) {
			e.printStackTrace();
	} catch (JsonMappingException e) {
			e.printStackTrace();
	} catch (IOException e) {
			e.printStackTrace();
	}
		//System.out.println(jsonString);
		return jsonInString;
}

public Book getBook(String id){
	for(int i = 0; i < books.size(); i++){
		if(books.get(i).getId().equals(id)){
			return books.get(i);
		}
	}
	return null;
}

public boolean editBook(Book book, String id){
	for(int i = 0; i < books.size(); i++){
		if(books.get(i).getId().equals(id)){
			books.set(i, book);
			return false;
		}
	}
	return true;
}

public Catalog nwCat(Catalog catalog, String title){
	for(int i = 0; i < books.size(); i++){
		if(books.get(i).getTitle().contains(title)){
			catalog.addBook(books.get(i));
		}
	}
	return catalog;
}

public int getNewId(){
	int nw = 0;
	for(int i = 0; i < books.size(); i++ ){
		int current = Integer.parseInt(books.get(i).getId());
		if(current > nw){
			nw = current;
		}
	}
	return nw;
}

public Book convertToBook(String json){
	ObjectMapper map = new ObjectMapper();
	Book booki = null;
	try {
		map.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		booki = map.readValue(json, Book.class);
	}catch (JsonGenerationException e) {
			e.printStackTrace();
	} catch (JsonMappingException e) {
			e.printStackTrace();
	} catch (IOException e) {
			e.printStackTrace();
	}
	return booki;
}
}
