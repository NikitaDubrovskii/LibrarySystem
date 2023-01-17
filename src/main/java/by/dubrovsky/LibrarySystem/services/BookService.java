package by.dubrovsky.LibrarySystem.services;

import by.dubrovsky.LibrarySystem.dto.EventType;
import by.dubrovsky.LibrarySystem.dto.ObjectType;
import by.dubrovsky.LibrarySystem.models.Book;
import by.dubrovsky.LibrarySystem.models.Views;
import by.dubrovsky.LibrarySystem.repositories.BookRepository;
import by.dubrovsky.LibrarySystem.repositories.UserRepository;
import by.dubrovsky.LibrarySystem.util.WsSender;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.BiConsumer;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    private final BiConsumer<EventType, Book> wsSender;

    @Autowired
    public BookService(BookRepository bookRepository, UserRepository userRepository, WsSender wsSender) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.wsSender = wsSender.getSender(ObjectType.BOOK, Views.FullBook.class);
    }

    public List<Book> list() {
        return bookRepository.findAll();
    }

    public Book getOne(Book book) {
        return book;
    }

    @Transactional
    public Book create(Book bookToAdd) {
        Book updatedBook = bookRepository.save(bookToAdd);

        wsSender.accept(EventType.CREATE, updatedBook);

        return updatedBook;
    }

    @Transactional
    public Book update(Book bookFromDb, Book bookToUpdate) {
        BeanUtils.copyProperties(bookToUpdate, bookFromDb, "id");

        Book updatedBook = bookRepository.save(bookFromDb);

        wsSender.accept(EventType.UPDATE, updatedBook);

        return updatedBook;
    }

    @Transactional
    public void delete(Book book) {
        bookRepository.delete(book);
        wsSender.accept(EventType.REMOVE, book);
    }

}