package data.domain;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import java.util.Date;
import java.util.List;

@PersistenceCapable(detachable="true")
public class User {
    @PrimaryKey
    private String name;
    private String email;
    private String password;
    private Date birthDate;
    @Join
    //@PersistenceCapable(defaultFetchGroup="true")
    private List<Book> books;

    public User(String name, String email, String password, Date birthDate, List<Book> books) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public List<Book> getBooks() {
        return books;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "data.domain.User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthDate=" + birthDate +
                ", books=" + books +
                '}';
    }
}
