package server.data.domain;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@PersistenceCapable(detachable="true")
public class User implements Serializable {
    
	private int id;
    private static int count = 0;
    private static final long serialVersionUID = 1L;
	private String name;
    @PrimaryKey
    private String email;
    private String password;
    private Date birthDate;
    @Join
    //@PersistenceCapable(defaultFetchGroup="true")
    private List<Book> books;
    private List<Fine> fines;

//    public User(int id, String name, String email, String password, Date birthDate, List<Book> books, List<Fine> fines) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.password = password;
//        this.birthDate = birthDate;
//        this.books = books;
//        this.fines = fines;
//    }
    
    
    public User(String name, String email, String password, Date birthDate, List<Book> books, List<Fine> fines) {
    	this.id = count++;
    	this.name = name;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.books = books;
        this.fines = fines;
    }
    
    public User(String name, String email, String password, Date birthDate) {
    	this.id = count++;
    	this.name = name;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
//        this.books = books;
//        this.fines = fines;
    }
    
    public User() {
		super();
	}

    public int getId() {
        return id;
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

    public List<Fine> getFines() {
        return fines;
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

    public void setFines(List<Fine> fines) {
        this.fines = fines;
    }

    @Override
    public String toString() {
        return "User [" +
                " id = " + id +
                " name = " + name +
                ", email = " + email +
                ", password = " + password +
                ", birthDate = " + birthDate +
//                ", books = " + books +
                ']';
    }
}
