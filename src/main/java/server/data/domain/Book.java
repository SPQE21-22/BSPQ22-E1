package server.data.domain;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import java.util.Date;

@PersistenceCapable(detachable="true")
public class Book {
    @PrimaryKey
    private String name;
    private String author;
    private Date publishDate;
    private Boolean available;

    public Book(String name, String author, Date publishDate, Boolean available) {
        this.name = name;
        this.author = author;
        this.publishDate = publishDate;
        this.available = available;
    }

    public Book() {
		super();
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "data.domain.Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publishDate=" + publishDate +
                ", available=" + available +
                '}';
    }
}
