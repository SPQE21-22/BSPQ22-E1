package server;
import server.data.domain.Book;
import java.util.Date;

public class Main {
    public static void main(String[] args){
        System.out.println("Hello World!");
        Book book = new Book("El camino de los Reyes", "Brandon Sanderson", new Date(), true );
        System.out.println(book.getName());
    }
}
