package server.data.domain;

import java.util.Date;
import java.util.List;

public class Reserv {
    private List<Room> reservs;

    public List<Room> getReservs() {
        return this.reservs;
    }

    public Reserv(List<Room> res) {
        this.reservs = res;
    }

    public Reserv() {
        super();
    }
}
