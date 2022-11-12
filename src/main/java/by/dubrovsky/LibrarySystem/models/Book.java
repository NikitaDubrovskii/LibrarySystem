package by.dubrovsky.LibrarySystem.models;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Book")
@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"id", "title", "author"})
@EqualsAndHashCode(of = {"id"})
public class Book implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.IdTitleAuthor.class)
    private Long id;

    @Column(name = "title")
    @JsonView(Views.IdTitleAuthor.class)
    private String title;

    @Column(name = "author")
    @JsonView(Views.IdTitleAuthor.class)
    private String author;

    @Column(name = "year")
    @JsonView(Views.FullBook.class)
    private Integer year;

    @Column(name = "pages")
    @JsonView(Views.FullBook.class)
    private Integer pages;

    @ManyToOne
    @JoinColumn(name = "usr_id", referencedColumnName = "id")
    @JsonView(Views.FullBook.class)
    private User userId;

    public Book(String title, String author, Integer year, Integer pages) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.pages = pages;
    }
}
