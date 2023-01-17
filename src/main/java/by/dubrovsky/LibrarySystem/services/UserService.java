package by.dubrovsky.LibrarySystem.services;

import by.dubrovsky.LibrarySystem.dto.EventType;
import by.dubrovsky.LibrarySystem.dto.ObjectType;
import by.dubrovsky.LibrarySystem.models.User;
import by.dubrovsky.LibrarySystem.models.Views;
import by.dubrovsky.LibrarySystem.repositories.BookRepository;
import by.dubrovsky.LibrarySystem.repositories.UserRepository;
import by.dubrovsky.LibrarySystem.util.WsSender;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BiConsumer<EventType, User> wsSender;

    @Autowired
    public UserService(UserRepository userRepository, BookRepository bookRepository, WsSender wsSender) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.wsSender = wsSender.getSender(ObjectType.USER, Views.FullUser.class);
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    public User getOne(User user) {
        return user;
    }

    @Transactional
    public User create(User userToAdd) {
        userToAdd.setCreationDate(LocalDateTime.now());
        User updatedUser = userRepository.save(userToAdd);
        wsSender.accept(EventType.CREATE, updatedUser);
        return updatedUser;
    }

    @Transactional
    public User update(User userFromDb, User userToUpdate) {
        BeanUtils.copyProperties(userToUpdate, userFromDb, "id", "creationDate", "sub", "picture");
        User updatedUser = userRepository.save(userFromDb);
        wsSender.accept(EventType.UPDATE, updatedUser);
        return updatedUser;
    }

    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
        wsSender.accept(EventType.REMOVE, user);
    }
}
