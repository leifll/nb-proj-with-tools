package se.kth.ict.iv1201.el30.data;

import se.kth.ict.iv1201.el30.integration.BookDAO;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class BookCatalogTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(BookDAO.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    BookDAO instance;

    /**
     * Test of findByTitle method, of class BookDAO.
     */
    @org.junit.Test
    public void testFindByTitle() {
        String title = "bibleBook1";
        String expResult = title;
        String result = instance.findByTitle(title).getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of findByCategory method, of class BookDAO.
     */
    @org.junit.Test
    public void testFindByCategory() {
        String category = "science";
        int expResult = 3;
        int result = instance.findByCategory(category).size();
        assertEquals(expResult, result);
    }

    /**
     * Test of findByCategory method, of class BookDAO.
     */
    @org.junit.Test
    public void testFindAllBooks() {
        int expResult = 9;
        int result = instance.findAllBooks().size();
        assertEquals(expResult, result);
    }

}
