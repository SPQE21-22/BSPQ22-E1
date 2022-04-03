package server.data.domain;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import java.util.Date;
@PersistenceCapable(detachable = "true")
public class Booking {
    @PrimaryKey
    private int id;
    private User user;
    private Book book;
    private Date dueDate;
    private static int count = 0;

    public Booking(User user, Book book, Date dueDate) {
        this.id = count++;
        this.user = user;
        this.book = book;
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Booking.count = count;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", user=" + user +
                ", book=" + book +
                ", dueDate=" + dueDate +
                '}';
    }
}
