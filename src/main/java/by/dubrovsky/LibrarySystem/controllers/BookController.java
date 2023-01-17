package by.dubrovsky.LibrarySystem.controllers;

import by.dubrovsky.LibrarySystem.models.Book;
import by.dubrovsky.LibrarySystem.models.Views;
import by.dubrovsky.LibrarySystem.services.BookService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    @JsonView(Views.IdTitleAuthor.class)
    public List<Book> list() {
        return bookService.list();
    }

    @GetMapping("{id}")
    public Book getOne(@PathVariable("id") Book book) {
        return bookService.getOne(book);
    }

    @PostMapping()
    public Book create(@RequestBody Book bookToAdd) {
        return bookService.create(bookToAdd);
    }

    @PutMapping("{id}")
    public Book update(@PathVariable("id") Book bookFromDb, @RequestBody Book bookToUpdate) {
        return bookService.update(bookFromDb, bookToUpdate);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Book book) {
        bookService.delete(book);
    }

/*    @PatchMapping("/{id}/set-user")
    public Book setUser(@PathVariable("id") Book bookFromDb, @RequestBody Book bookToUpdate) {
        BeanUtils.copyProperties(bookToUpdate, bookFromDb, "id", "title", "author", "year", "pages");

        Book updatedBook = bookRepository.save(bookFromDb);

        wsSender.accept(EventType.UPDATE, updatedBook);

        return updatedBook;
    }*/
}
