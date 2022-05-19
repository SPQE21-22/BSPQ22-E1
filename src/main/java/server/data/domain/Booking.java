package server.data.domain;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import java.util.Date;
@PersistenceCapable(detachable = "true")
public class Booking {
    @PrimaryKey
    private int id;
    private static int count = 0;
    private int user_id;
    private int book_id;
    private Date dueDate;

    public Booking(int user_id, int book_id, Date dueDate) {
        this.id = count++;
        this.user_id = user_id;
        this.book_id = book_id;
        this.dueDate = dueDate;
    }
    
    

    public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }


    public int getBookId() {
        return book_id;
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
                "id=" + id +
                ", user_id=" + user_id +
                ", book_id=" + book_id +
                ", dueDate=" + dueDate +
                '}';
    }
}
