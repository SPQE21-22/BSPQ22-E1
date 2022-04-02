package data.domain;

import java.util.Date;

public class Booking {
    private User user;
    private Book book;
    private Date dueDate;

    public Booking(User user, Book book, Date dueDate) {
        this.user = user;
        this.book = book;
        this.dueDate = dueDate;
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

    @Override
    public String toString() {
        return "Booking{" +
                "user=" + user +
                ", book=" + book +
                ", dueDate=" + dueDate +
                '}';
    }
}
