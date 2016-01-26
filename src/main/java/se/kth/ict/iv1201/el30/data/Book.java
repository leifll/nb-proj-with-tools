package se.kth.ict.iv1201.el30.data;

import java.util.Objects;

/**
 * Describes a book.
 */
public class Book {

    private String title;
    private String category;
    private int price;

    /**
     * Constructs a new <code>Book</code> instance.
     * 
     * @param title     The newly constructed book's title.
     * @param category  The newly constructed book's category.
     * @param price     The newly constructed book's price.
     */
    public Book(String title, String category, int price) {
        this.title = title;
        this.category = category;
        this.price = price;
    }

    /**
     * Get the value of Title
     *
     * @return the value of Title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the value of category
     *
     * @return the value of category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Set the value of category
     *
     * @param category new value of category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Set the value of Title
     *
     * @param Title new value of Title
     */
    public void setTitle(String Title) {
        this.title = Title;
    }

    /**
     * Get the value of price
     *
     * @return the value of price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Set the value of price
     *
     * @param price new value of price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" + "title=" + title + ", category=" + category + ", price=" + price + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.title);
        hash = 71 * hash + Objects.hashCode(this.category);
        hash = 71 * hash + this.price;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        if (this.price != other.price) {
            return false;
        }
        return true;
    }
    
}
