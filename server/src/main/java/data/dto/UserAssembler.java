package data.dto;

import data.domain.Book;
import data.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserAssembler {
    private static UserAssembler instance;

    private UserAssembler() { }

    public static UserAssembler getInstance() {
        if (instance == null) {
            instance = new UserAssembler();
        }

        return instance;
    }
    public UserDTO userToDTO(User user) {
        UserDTO dto = new UserDTO();

        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setBirthDate(user.getBirthDate());
        List<Book> userBooks = new ArrayList<>(user.getBooks());
        List<BookDTO> dtoBook = new ArrayList<>();
        for (Book book : userBooks) {
            dtoBook.add(BookAssembler.getInstance().bookToDTO(book));
        }
        dto.setBooks(dtoBook);

        return dto;
    }
}
