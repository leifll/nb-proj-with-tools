package se.kth.ict.iv1201.el30.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

/**
 * Contains all books
 */
@ApplicationScoped
public class BookCatalog {

    private List<Book> books = new ArrayList<>();
    private Map<String, Book> titleIndex = new HashMap<>();
    private Map<String, List<Book>> categoryIndex = new HashMap<>();

    @PostConstruct
    private void loadBooks() {
        books.add(new Book("childrenBook1", "children", 100));
        books.add(new Book("childrenBook2", "children", 100));
        books.add(new Book("childrenBook3", "children", 100));
        books.add(new Book("bibleBook1", "bible", 10));
        books.add(new Book("bibleBook2", "bible", 100));
        books.add(new Book("bibleBook3", "bible", 100));
        books.add(new Book("scienceBook1", "science", 1000));
        books.add(new Book("scienceBook2", "science", 1000));
        books.add(new Book("scienceBook3", "science", 1000));

        createTitleIndex();
        createCategoryIndex();
    }

    /**
     * @return a list of all books.
     */
    public List<Book> getBooks() {
        return books;
    }

    /**
     * @param title The title of the searched book.
     * @return the book with the specified title or <code>null</code> if there
     * is no such book.
     */
    public Book findByTitle(String title) {
        return titleIndex.get(title);
    }

    /**
     * @param category The category of the searched books.
     * @return a list of all books in the specified category 
     * or <code>null</code> if there are no such books.
     */
    public List<Book> findByCategory(String category) {
        return categoryIndex.get(category);
    }

    private void createTitleIndex() {
        for (Book book : books) {
            titleIndex.put(book.getTitle(), book);
        }
    }

    private void createCategoryIndex() {
        for (Book book : books) {
            if (categoryIndex.get(book.getCategory()) == null) {
                categoryIndex.put(book.getCategory(), new ArrayList<Book>());
            }
            categoryIndex.get(book.getCategory()).add(book);
        }
    }
}
