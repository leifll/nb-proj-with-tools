package se.kth.ict.iv1201.el30.integration;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import se.kth.ict.iv1201.el30.model.Book;

/**
 * Contains all books
 */
@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class BookDAO {

    @PersistenceContext(unitName = "booksPU")
    private EntityManager em;
    @Resource
    private UserTransaction utx;

    @PostConstruct
    private void createBooks() {
        // Container managed transactions not supported in lifecycle
        // methods when using JTA only.
        try {
            utx.begin();
            em.persist(new Book("childrenBook1", "children", 100));
            em.persist(new Book("childrenBook2", "children", 100));
            em.persist(new Book("childrenBook3", "children", 100));
            em.persist(new Book("bibleBook1", "bible", 10));
            em.persist(new Book("bibleBook2", "bible", 10));
            em.persist(new Book("bibleBook3", "bible", 100));
            em.persist(new Book("scienceBook1", "science", 1000));
            em.persist(new Book("scienceBook2", "science", 1000));
            em.persist(new Book("scienceBook3", "science", 1000));
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (IllegalStateException | SecurityException |
                    SystemException ex) {
                Logger.getLogger(BookDAO.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                utx.commit();
            } catch (RollbackException | HeuristicMixedException |
                    HeuristicRollbackException | SecurityException |
                    IllegalStateException | SystemException ex) {
                Logger.getLogger(BookDAO.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @return a list of all books.
     */
    public List<Book> findAllBooks() {
        return em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    /**
     * @param title The title of the searched book.
     * @return the book with the specified title or <code>null</code> if there
     * is no such book.
     */
    public Book findByTitle(String title) {
        return em.createQuery("SELECT b FROM Book b WHERE b.title LIKE :title",
                Book.class).
                setParameter("title", title).getSingleResult();
    }

    /**
     * @param category The category of the searched books.
     * @return a list of all books in the specified category or
     * <code>null</code> if there are no such books.
     */
    public List<Book> findByCategory(String category) {
        return em.createQuery("SELECT b FROM Book b WHERE b.category LIKE :category",
                Book.class).
                setParameter("category", category).getResultList();
    }
}
