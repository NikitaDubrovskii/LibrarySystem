package by.dubrovsky.LibrarySystem.controllers;

import by.dubrovsky.LibrarySystem.dto.EventType;
import by.dubrovsky.LibrarySystem.dto.ObjectType;
import by.dubrovsky.LibrarySystem.models.Book;
import by.dubrovsky.LibrarySystem.models.Views;
import by.dubrovsky.LibrarySystem.repositories.BookRepository;
import by.dubrovsky.LibrarySystem.util.WsSender;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("book")
public class BookController {
    private final BookRepository bookRepository;
    private final BiConsumer<EventType, Book> wsSender;

    @Autowired
    public BookController(BookRepository bookRepository, WsSender wsSender) {
        this.bookRepository = bookRepository;
        this.wsSender = wsSender.getSender(ObjectType.BOOK, Views.FullBook.class);
    }

    @GetMapping()
    @JsonView(Views.IdTitleAuthor.class)
    public List<Book> list() {

        return bookRepository.findAll();
    }

    @GetMapping("{id}")
    public Book getOne(@PathVariable("id") Book book) {
        return book;
        //return bookRepository.findById(book.getId()).get();
    }

    @PostMapping()
    public Book create(@RequestBody Book bookToAdd) {
        Book updatedBook = bookRepository.save(bookToAdd);

        wsSender.accept(EventType.CREATE, updatedBook);

        return updatedBook;
    }

    @PutMapping("{id}")
    public Book update(@PathVariable("id") Book bookFromDb, @RequestBody Book bookToUpdate) {
        BeanUtils.copyProperties(bookToUpdate, bookFromDb, "id");

        Book updatedBook = bookRepository.save(bookFromDb);

        wsSender.accept(EventType.UPDATE, updatedBook);

        return updatedBook;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Book book) {
        bookRepository.delete(book);
        wsSender.accept(EventType.REMOVE, book);
    }

    @PatchMapping("/{id}/set-user")
    public Book setUser(@PathVariable("id") Book bookFromDb, @RequestBody Book bookToUpdate) {
        BeanUtils.copyProperties(bookToUpdate, bookFromDb, "id", "title", "author", "year", "pages");

        Book updatedBook = bookRepository.save(bookFromDb);

        wsSender.accept(EventType.UPDATE, updatedBook);

        return updatedBook;
    }
}
