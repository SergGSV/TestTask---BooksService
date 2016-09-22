package rest.jersey.bookServiceTests;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import rest.jersey.bookServiceTests.utils.BooksUtil;
import rest.jersey.model.Book;
import rest.jersey.model.BookCatalog;
import rest.jersey.services.BookService;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by Serg on 22.09.2016.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookRestServiceTest extends JerseyTest {
    private static BookCatalog catalog = BooksUtil.getCatalog();

    @Override
    public Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(BookService.class);
    }

    @Test
    //  check send request to wrong url,
    //  "/rest/wrongURL"
    public void test1_FailTest() {
        Response output = target("/rest/wrongURL").request().post(Entity.entity(null, MediaType.APPLICATION_XML));

        assertEquals("should return status 404", 404, output.getStatus());
        assertNotNull("Should return list", output.getEntity());
    }

    @Test
    //  first reading must return empty Catalog structure
    public void test2_ReadCatalog() {
        Response output = target("/rest/changeBook").request().post(Entity.entity(new Book(), MediaType.APPLICATION_XML));

        assertEquals("should return status 200", 200, output.getStatus());
        assertNotNull("Should return list", output.getEntity());
    }

    @Test
    //  add of the first two books in the Catalog structure,
    //  catalog.getBookList().subList(0,1)
    public void test3_AddCatalogData() {
        for (Book book : catalog.getBookList().subList(0,1)) {
            Response output = target("/rest/changeBook").request().post(Entity.entity(book, MediaType.APPLICATION_XML));

            assertEquals("Should return status 200", 200, output.getStatus());
            assertNotNull("Should return some XML-data", output.getEntity());
        }
    }

    @Test
    //  re-reading Catalog structure
    public void test4_ReadData() {
        test2_ReadCatalog();
    }

    @Test
    //  update Book with id="bk102",
    //  catalog.getBookList().get(2)
    public void test5_UpdateCatalogData() {
        Response output = target("/rest/changeBook").request().post(Entity.entity(catalog.getBookList().get(2), MediaType.APPLICATION_XML));

        assertEquals("Should return status 200", 200, output.getStatus());
        assertNotNull("Should return some XML-data", output.getEntity());
    }

    @Test
    //  re-reading Catalog structure
    public void test6_ReadData() {
        test2_ReadCatalog();
    }

    @Test
    //  add one more book in the Catalog structure,
    //  (catalog.getBookList().get(3))
    public void test7_AddCatalogData() {
        Response output = target("/rest/changeBook").request().post(Entity.entity(catalog.getBookList().get(3), MediaType.APPLICATION_XML));

        assertEquals("Should return status 200", 200, output.getStatus());
        assertNotNull("Should return some XML-data", output.getEntity());
    }

    @Test
    //  re-reading Catalog structure
    public void test8_ReadData() {
        test2_ReadCatalog();
    }
}
