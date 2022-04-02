package remote;

import data.dao.UserDAO;
import data.dao.BookDAO;
import data.domain.Book;
import data.domain.User;
import data.dto.BookAssembler;
import data.dto.BookDTO;
import data.dto.UserAssembler;
import data.dto.UserDTO;
import service.BookAppService;
import service.LoginAppService;

import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;
import java.util.*;

public class RemoteFacade extends UnicastRemoteObject implements IRemoteFacade{
    @Serial
    private static final long serialVersionUID = 1L;
    public Map<Long, User> tokenState = new HashMap<>();

    public RemoteFacade() throws RemoteException {
        super();
    }

    @Override
    public void register(String name, String email, String password, Date birthDate, List<Book> books) throws RemoteException {
        User registerUser = LoginAppService.getInstance().register(name, email, password, birthDate, books);
        if (registerUser != null) {
            long token = Calendar.getInstance().getTimeInMillis();
            this.tokenState.put(token, registerUser);
            System.out.println("New user registered");
        } else {
            throw new RemoteException("Error registering user");
        }
    }

    @Override
    public long login(String email, String password) throws RemoteException {
        User user = LoginAppService.getInstance().login(email, password);
        if (user != null) {
            if (!this.tokenState.containsValue(user)) {
                long token = Calendar.getInstance().getTimeInMillis();
                this.tokenState.put(token, user);
                System.out.println("New user logged in");
                return token;
            } else {
                for (Map.Entry<Long, User> entry : this.tokenState.entrySet()) {
                    if (entry.getValue() == user) {
                        System.out.println("User re-logged in");
                        return entry.getKey();
                    }
                }
                return -1;
            }
        } else {
            throw new RemoteException("Login failed");
        }
    }

    @Override
    public void logout(long token) throws RemoteException {
        if (this.tokenState.containsKey(token)) {
            this.tokenState.remove(token);
            System.out.println("User logged out");
        } else {
            throw new RemoteException("User not logged in");
        }
    }

    @Override
    public void deleteUser(User user) throws RemoteException {
        LoginAppService.getInstance().deleteUser(user);
        System.out.println("User deleted '" + user.getName() + "'");
    }

    @Override
    public List<BookDTO> getListedBooks() throws RemoteException {
        List<Book> books = BookAppService.getInstance().getListedBooks();

        if (books != null) {
            System.out.println("Retrieved all active challenges");
            return BookAssembler.getInstance().bookListToDTO(books);
        } else {
            throw new RemoteException("Recovery of books failed");
        }
    }

    @Override
    public BookDTO getBookByName(String name) throws RemoteException {
        return BookAssembler.getInstance().bookToDTO(BookDAO.getInstance().find(name));
    }

    @Override
    public void addBook(String name, String author, Date publishDate, Boolean available) throws RemoteException {
        BookAppService.getInstance().addBook(name, author, publishDate, available);
        System.out.println("Added new book: '" + name + "'");
    }

    @Override
    public void deleteBook(String name) throws RemoteException {
        BookAppService.getInstance().deleteBook(name);
        System.out.println("Deleted book: '" + name + "'");
    }

    @Override
    public String getA() throws RemoteException {
        return BookAppService.getInstance().getA();
    }
}
