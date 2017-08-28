package lnu.models;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

@XmlRootElement(name = "Book")
public class Book{
  private String id;
  private String title;
  private String author;
  private String genre;
  private String publish_date;
  private String price;
  private String description;

  public Book(String newId, String newTitle,String newAuthor, String newGenre, String newPublishDate, String newPrice, String newDescription){
    id = newId;
    title = newTitle;
		author = newAuthor;
		genre = newGenre;
		publish_date = newPublishDate;
    price = newPrice;
		description = newDescription;
	}

	public Book(){

	}
  /*public book(String id, String title, String author, String genre, String publishDate, String price, String description){
    this.id = id;
    this.title = title;
    this.author = author;
    this.genre = genre;
    this.publishDate = publishDate;
    this.price = price;
    this.description = description;
  }*/
    @XmlAttribute
    public String getId() {
			return id;
		}
    public void setId(String id) {
        this.id = id;
    }
		public String getTitle() {
			return title;
		}
    public void setTitle(String title) {
        this.title = title;
    }
		public String getAuthor() {
			return author;
		}
    public void setAuthor(String author) {
        this.author = author;
    }
		public String getGenre() {
			return genre;
		}
    public void setGenre(String genre) {
        this.genre = genre;
    }
		public String getPublish_date() {
			return publish_date;
		}
    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }
    public String getPrice() {
			return price;
		}
    public void setPrice(String price) {
        this.price = price;
    }
		public String getDescription() {
			return description;
		}
    public void setDescription(String description) {
        this.description = description;
    }
    public String toString(){
      String temp = id+ " "+title+" "+author+" "+genre+" "+publish_date+" "+price+" "+description;
      return temp;
  }
  public String toJson(){
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try{
			json = mapper.writeValueAsString(this);
		}catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}
}
