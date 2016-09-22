package rest.jersey.services;


import rest.jersey.model.Book;
import rest.jersey.model.BookCatalog;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Serg on 22.09.2016.
 */
@Path("/rest")
public class BookService {
    private static BookCatalog bookCatalog = new BookCatalog();

    @POST
    @Path("/changeBook")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public BookCatalog create(Book book) {
        if (book == null || book.getId() == null) return bookCatalog;

        if (bookCatalog.getBookList().stream().filter(o -> o.getId().equals(book.getId())).findFirst().isPresent()) {
            bookCatalog.getBookList().stream().filter(o -> o.getId().equals(book.getId())).findFirst().ifPresent(o -> {
                o.setAuthor(book.getAuthor());
                o.setDescription(book.getDescription());
                o.setGenre(book.getGenre());
                o.setPrice(book.getPrice());
                o.setPublish_date(book.getPublish_date());
                o.setTitle(book.getTitle());
            });
        } else {
            bookCatalog.getBookList().add(book);
        }

        return bookCatalog;
    }

}
