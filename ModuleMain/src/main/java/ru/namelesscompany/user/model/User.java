package ru.namelesscompany.user.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "registration_date", nullable = false)
    @DateTimeFormat(style = "yyyy-MM-dd HH:mm")
    private LocalDate registrationDate;
//    @Column(name = "birthday", nullable = true)
//    private LocalDate birthday;
//    @Column(name = "sex", nullable = false)
//    private Sex sex;
}
