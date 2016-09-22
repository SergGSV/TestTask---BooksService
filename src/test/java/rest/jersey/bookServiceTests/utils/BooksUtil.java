package rest.jersey.bookServiceTests.utils;

import rest.jersey.model.Book;
import rest.jersey.model.BookCatalog;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Serg on 22.09.2016.
 */
public class BooksUtil {
    private static BookCatalog catalogTestdata = new BookCatalog();

    static {
        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();
        Book book4 = new Book();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            book1.setId("bk101");
            book1.setAuthor("Gambardella, Matthew");
            book1.setTitle("XML Developer's Guide");
            book1.setGenre("Computer");
            book1.setPrice(44.95f);
            book1.setPublish_date(sdf.parse("2000-10-01"));
            book1.setDescription("An in-depth look at creating applications with XML.");

            book2.setId("bk102");
            book2.setAuthor("Ralls, Kim");
            book2.setTitle("Midnight Rain");
            book2.setGenre("Fantasy");
            book2.setPrice(5.95f);
            book2.setPublish_date(sdf.parse("2000-12-16"));
            book2.setDescription("A former architect battles corporate zombies, \n" +
                    "      an evil sorceress, and her own childhood to become queen \n" +
                    "      of the world.");

            book3.setId("bk102");
            book3.setAuthor("updated_"+book2.getAuthor());
            book3.setTitle("updated_"+book2.getTitle());
            book3.setGenre("updated_"+book2.getAuthor());
            book3.setPrice(99.99f);
            book3.setPublish_date(sdf.parse("2000-01-01"));
            book3.setDescription("updated_"+book2.getDescription());

            book4.setId("bk103");
            book4.setAuthor("Corets, Eva");
            book4.setTitle("Maeve Ascendant");
            book4.setGenre("Fantasy");
            book4.setPrice(5.95f);
            book4.setPublish_date(sdf.parse("2000-11-17"));
            book4.setDescription("After the collapse of a nanotechnology \n" +
                    "      society in England, the young survivors lay the \n" +
                    "      foundation for a new society.");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        catalogTestdata.getBookList().add(book1);
        catalogTestdata.getBookList().add(book2);
        catalogTestdata.getBookList().add(book3);
        catalogTestdata.getBookList().add(book4);
    }

    public static BookCatalog getCatalog() {
        return catalogTestdata;
    }
}
