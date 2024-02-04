package org.fittrack.fitnesstrackerdemo.models.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.fittrack.fitnesstrackerdemo.enums.UserStatus;
import org.hibernate.validator.constraints.Range;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp = "[a-zA-Z]+")
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Pattern(regexp = "[a-zA-Z]+")
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Pattern(regexp = ".+@.+\\..+")
    @Column(name = "email")
    private String email;

    @NotNull
    @Range(min = 18, max = 75)
    @Column(name = "age")
    private Integer age;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_workouts",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "workout_id"))
    Set<Workout> workouts;

    @NotNull
    @Column(name = "date_created")
    private LocalDateTime dateTimeCreated;

    @NotNull
    @Column(name = "status")
    @Range(min = 0, max = 1)
    private Integer status;

}
