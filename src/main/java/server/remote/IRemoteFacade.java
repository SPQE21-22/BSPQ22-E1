package server.remote;

import server.data.domain.Book;
import server.data.domain.User;
import server.data.dto.BookDTO;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public interface IRemoteFacade {
    long register(int id, String name, String email, String password, Date birthDate, List<Book> books) throws RemoteException;
    long login(String email, String password) throws RemoteException;
    void logout(long token) throws RemoteException;
    void deleteUser(long token) throws RemoteException;


    List<BookDTO> getListedBooks() throws RemoteException;
    BookDTO getBookByName(String name) throws RemoteException;
    void addBook(String name, String author, Date publishDate, Boolean available) throws RemoteException;
    void deleteBook(String name) throws RemoteException;

/*    public void getListedRooms() throws RemoteException;
    public void getRoomByName(String name) throws RemoteException;
    public void addRoom(Room room) throws RemoteException;
    public void deleteRoom(String name) throws RemoteException;
    */
}
