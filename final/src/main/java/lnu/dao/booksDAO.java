// Use this file to write and read the xml file.
// http://www.journaldev.com/1234/jaxb-tutorial-example-to-convert-object-to-xml-and-xml-to-object
// javax.xml.bind is added as a part of the sdk from java7 and forward.
package lnu.dao;

import lnu.models.Book;
import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Marshaller;

public class booksDAO {
      public String filepath = "books.xml";
      public List<Book> books;
    	public Catalog catalog;

    	public booksDAO() {
    		try {

            File file = new File("books.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			      Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            this.catalog = (Catalog) jaxbUnmarshaller.unmarshal(file);
			      books = this.catalog.getBooks();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
  public Catalog readXml(){
  try{
    //File file = new File(filepath);
    JAXBContext context = JAXBContext.newInstance(Catalog.class);
    Unmarshaller un = context.createUnmarshaller();
    System.out.println(this.getClass().getResource("/books.xml"));
    Catalog temp = (Catalog) un.unmarshal(new File(filepath));
    return temp;
  } catch(JAXBException e) {
    e.printStackTrace();
  }
  return null;
}
   public void writeXml(Catalog catalog){
    try {
          JAXBContext context = JAXBContext.newInstance(Catalog.class);
          Marshaller m = context.createMarshaller();
          m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

          // Write to File
          m.marshal(catalog, new File(filepath));
      } catch (JAXBException e) {
          e.printStackTrace();
      }
}
}
