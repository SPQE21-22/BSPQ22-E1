package data.dto;

import data.domain.Book;

import java.util.ArrayList;
import java.util.List;

public class BookAssembler {
    private static BookAssembler instance;

    private BookAssembler() { }

    public static BookAssembler getInstance() {
        if (instance == null) {
            instance = new BookAssembler();
        }

        return instance;
    }

    public BookDTO bookToDTO(Book book) {
        BookDTO dto = new BookDTO();

        dto.setName(book.getName());
        dto.setAuthor(book.getAuthor());
        dto.setPublishDate(book.getPublishDate());
        dto.setAvailable(book.getAvailable());

        return dto;
    }

    public List<BookDTO> bookListToDTO(List<Book> books) {
        List<BookDTO> dtos = new ArrayList<>();

        for (Book book : books) {
            dtos.add(this.bookToDTO(book));
        }

        return dtos;
    }
}
