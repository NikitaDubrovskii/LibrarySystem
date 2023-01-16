package by.dubrovsky.LibrarySystem.models;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "usr")
@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"id", "firstName", "secondName"})
@EqualsAndHashCode(of = {"id"})
public class User implements Serializable {
    //private static final long serialVersionUID = 3838819347520452335L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.IdFullNameAge.class)
    private Long id;

    @Column(name = "sub")
    @JsonView(Views.FullUser.class)
    private String sub;

    @Column(name = "first_name")
    @JsonView(Views.IdFullNameAge.class)
    private String firstName;

    @Column(name = "second_name")
    @JsonView(Views.IdFullNameAge.class)
    private String secondName;

    @Column(name = "age")
    @JsonView(Views.IdFullNameAge.class)
    private Integer age;

    @Column(name = "picture")
    @JsonView(Views.FullUser.class)
    private String picture;

    @Column(name = "email")
    @JsonView(Views.FullUser.class)
    private String email;

    @Column(name = "phone")
    @JsonView(Views.FullUser.class)
    private Integer phone;

    @Column(name = "locale")
    @JsonView(Views.FullUser.class)
    private String locale;

    @Column(name = "role")
    @JsonView(Views.FullUser.class)
    private String role;

    @Column(updatable = false, name = "creation_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(Views.FullUser.class)
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "userId", fetch = FetchType.EAGER)
    @JsonView(Views.FullUser.class)
    //@JsonManagedReference
    @JsonIgnoreProperties("userId")
    private List<Book> books;

    public User(String sub, String firstName, String secondName, Integer age, String picture, String email, Integer phone, String role, String locale) {
        this.sub = sub;
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.picture = picture;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.locale = locale;
    }
}
