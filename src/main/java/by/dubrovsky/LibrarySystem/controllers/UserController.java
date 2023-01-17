package by.dubrovsky.LibrarySystem.controllers;

import by.dubrovsky.LibrarySystem.models.User;
import by.dubrovsky.LibrarySystem.models.Views;
import by.dubrovsky.LibrarySystem.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    @JsonView(Views.IdFullNameAge.class)
    public List<User> list() {
        return userService.list();
    }

    @GetMapping("{id}")
    public User getOne(@PathVariable("id") User user) {
        return userService.getOne(user);
    }

    @PostMapping()
    public User create(@RequestBody User userToAdd) {
        return userService.create(userToAdd);
    }

    @PutMapping("{id}")
    public User update(@PathVariable("id") User userFromDb, @RequestBody User userToUpdate) {
        return userService.update(userFromDb, userToUpdate);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") User user) {
        userService.delete(user);
    }
}
