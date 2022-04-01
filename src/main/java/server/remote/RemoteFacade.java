package server.remote;

import server.data.domain.Book;
import server.data.domain.User;
import server.service.LoginAppService;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public class RemoteFacade implements IRemoteFacade{
    @Override
    public User register(int id, String name, String email, String password, Date birthDate, List<Book> books) throws RemoteException {
        return LoginAppService.getInstance().register(id, name, email, password, birthDate, books);
    }

    @Override
    public long login(String email, String password) throws RemoteException {
        return 0;
    }

    @Override
    public void logout(long token) throws RemoteException {

    }

    @Override
    public void deleteUser(long token) throws RemoteException {

    }

    @Override
    public void getListedBooks() throws RemoteException {

    }

    @Override
    public void getBookByName(String name) throws RemoteException {

    }

    @Override
    public void addBook(Book book) throws RemoteException {

    }

    @Override
    public void deleteBook(String name) throws RemoteException {

    }
}
