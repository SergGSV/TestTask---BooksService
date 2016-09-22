package rest.jersey.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Serg on 22.09.2016.
 */
@XmlRootElement(name="catalog")
public class BookCatalog {
    private List<Book> bookList = new LinkedList<>();

    @XmlElement(name = "book")
    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
