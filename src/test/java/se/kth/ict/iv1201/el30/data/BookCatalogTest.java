package se.kth.ict.iv1201.el30.data;

import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class BookCatalogTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(BookCatalog.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    BookCatalog instance;

    /**
     * Test of findByTitle method, of class BookCatalog.
     */
    @org.junit.Test
    public void testFindByTitle() {
        String title = "bibleBook1";
        String expResult = title;
        String result = instance.findByTitle(title).getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of findByCategory method, of class BookCatalog.
     */
    @org.junit.Test
    public void testFindByCategory() {
        String category = "science";
        int expResult = 3;
        int result = instance.findByCategory(category).size();
        assertEquals(expResult, result);
    }

    /**
     * Test of findByCategory method, of class BookCatalog.
     */
    @org.junit.Test
    public void testGetBooks() {
        int expResult = 9;
        int result = instance.getBooks().size();
        assertEquals(expResult, result);
    }

}
