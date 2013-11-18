package se.kth.ict.iv1201.el30.view;

import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import se.kth.ict.iv1201.el30.data.Book;
import se.kth.ict.iv1201.el30.data.BookCatalog;

/**
 * Manages the book inventory.
 */
@Named("inventory")
@RequestScoped
public class BookManager {

    private static final long deployTime  = System.currentTimeMillis();
    @Inject
    private BookCatalog catalog;
    private String searchedTitle;
    private Book book;
    private String filterCategory;

    public static long upTime() {
        return (System.currentTimeMillis() - deployTime) / 1000;
    }

    public void setSearchedTitle(String title) {
        this.searchedTitle = title;
    }

    public String getSearchedTitle() {
        return searchedTitle;
    }

    public void find() {
        book = catalog.findByTitle(searchedTitle);
    }

    public boolean getFound() {
        return book != null;
    }

    public Book getBook() {
        return book;
    }

    public List<Book> getAllBooks() {
        return catalog.getBooks();
    }

    public void setFilterCategory(ValueChangeEvent vce) {
        this.filterCategory = (String) vce.getNewValue();
    }

    public String getFilterCategory() {
        return filterCategory;
    }
}
