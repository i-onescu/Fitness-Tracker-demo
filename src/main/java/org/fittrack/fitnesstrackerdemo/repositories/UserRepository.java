package org.fittrack.fitnesstrackerdemo.repositories;

import org.fittrack.fitnesstrackerdemo.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findUserById(Long id);


}