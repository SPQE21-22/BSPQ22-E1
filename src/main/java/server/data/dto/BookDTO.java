package server.data.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class BookDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String name;
    private String author;
    private Date publishDate;
    private Boolean available;

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
}
