package server.data.domain;
/**
 * Reserve class.
 * @author Alex Egaña, Eneko Eguiguren, Rubén García, Aida Gomezbueno & Tyler de Mier - BSPQ22-E1
 * @version 1.0
 * @since 2022-03-20
 */
import java.util.List;

public class Reserve {
    private List<Room> reserves;

    public List<Room> getReserves() {
        return this.reserves;
    }

    public Reserve(List<Room> res) {
        this.reserves = res;
    }

    public Reserve() {
        super();
    }
}
