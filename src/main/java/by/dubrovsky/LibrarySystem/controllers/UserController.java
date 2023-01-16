package by.dubrovsky.LibrarySystem.controllers;

import by.dubrovsky.LibrarySystem.dto.EventType;
import by.dubrovsky.LibrarySystem.dto.ObjectType;
import by.dubrovsky.LibrarySystem.models.User;
import by.dubrovsky.LibrarySystem.models.Views;
import by.dubrovsky.LibrarySystem.repositories.BookRepository;
import by.dubrovsky.LibrarySystem.repositories.UserRepository;
import by.dubrovsky.LibrarySystem.util.WsSender;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BiConsumer<EventType, User> wsSender;

    @Autowired
    public UserController(UserRepository userRepository, BookRepository bookRepository, WsSender wsSender) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.wsSender = wsSender.getSender(ObjectType.USER, Views.FullUser.class);
    }

    @GetMapping()
    @JsonView(Views.IdFullNameAge.class)
    public List<User> list() {
        return userRepository.findAll();
    }

    @GetMapping("{id}")
    public User getOne(@PathVariable("id") User user) {
        //List<Book> books = bookRepository.findBooksByUserId(user);
        //return userRepository.findById(user.getId()).get();
        return user;
    }

    @PostMapping()
    public User create(@RequestBody User userToAdd) {
        userToAdd.setCreationDate(LocalDateTime.now());
        User updatedUser = userRepository.save(userToAdd);

        wsSender.accept(EventType.CREATE, updatedUser);

        return updatedUser;
    }

    @PutMapping("{id}")
    public User update(@PathVariable("id") User userFromDb, @RequestBody User userToUpdate) {
        BeanUtils.copyProperties(userToUpdate, userFromDb, "id", "creationDate", "sub", "picture");

        User updatedUser = userRepository.save(userFromDb);

        wsSender.accept(EventType.UPDATE, updatedUser);

        return updatedUser;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") User user) {
        userRepository.delete(user);
        wsSender.accept(EventType.REMOVE, user);
    }
}
