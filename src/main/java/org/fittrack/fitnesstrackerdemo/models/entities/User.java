package org.fittrack.fitnesstrackerdemo.models.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.fittrack.fitnesstrackerdemo.enums.UserStatus;
import org.hibernate.validator.constraints.Range;

import java.sql.Date;

@Data
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

    @NotNull
    @Column(name = "date_created")
    private Date dateCreated;

    @NotNull
    @Column(name = "status")
    private UserStatus status;




}
