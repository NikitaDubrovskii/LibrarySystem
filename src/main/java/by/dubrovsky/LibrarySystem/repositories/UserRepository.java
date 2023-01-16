package by.dubrovsky.LibrarySystem.repositories;

import by.dubrovsky.LibrarySystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findBySub(String sub);

    //Optional<User> findUserByBooks(List<Book> books);
}
