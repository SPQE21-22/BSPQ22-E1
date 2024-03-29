package server.data.domain;
/**
 * Room class.
 * @author Alex Egaña, Eneko Eguiguren, Rubén García, Aida Gomezbueno & Tyler de Mier - BSPQ22-E1
 * @version 1.0
 * @since 2022-03-20
 */

public class Room {
    private int id;
    private static int count = 0;
    private String name;
    private User user;
    private int day;
    private String month;
    private int hourBeg;
    private int hourEnd;
    private Boolean booked;

    /**
     * Room constructor.
     * @param name
     * @param user
     * @param day
     * @param month
     * @param hourBeg
     * @param hourEnd
     * @param booked
     */
    public Room(String name, User user, int day, String month, int hourBeg, int hourEnd, Boolean booked) {
        this.id = count++;
        this.name = name;
        this.user = user;
        this.day = day;
        this.month = month;
        this.hourBeg = hourBeg;
        this.hourEnd = hourEnd;
        this.booked = booked;
    }

    public Room() {
        super();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public static int getCount() {
        return count;
    }
    public static void setCount(int count) {
        Room.count = count;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public int getDay() {
        return day;
    }
    public void setDay(int day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }
    public void setMonth(String month) {
        this.month = month;
    }

    public int getHourBeg() {
        return hourBeg;
    }
    public void setHourBeg(int hourBeg) {
        this.hourBeg = hourBeg;
    }

    public int getHourEnd() {
        return hourEnd;
    }
    public void setHourEnd(int hourEnd) {
        this.hourEnd = hourEnd;
    }

    public Boolean getBooked() {
        return booked;
    }
    public void setBooked(Boolean booked) {
        this.booked = booked;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name +
                ", user=" + user +
                ", day=" + day +
                ", month=" + month +
                ", hourBeg=" + hourBeg +
                ", hourEnd=" + hourEnd +
                ", booked=" + booked +
                '}';
    }
}
