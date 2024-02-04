package org.fittrack.fitnesstrackerdemo.services;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fittrack.fitnesstrackerdemo.converters.ObjectConverter;
import org.fittrack.fitnesstrackerdemo.converters.impl.UserConverter;
import org.fittrack.fitnesstrackerdemo.exceptions.UserNotFoundException;
import org.fittrack.fitnesstrackerdemo.models.dtos.UserDto;
import org.fittrack.fitnesstrackerdemo.models.entities.User;
import org.fittrack.fitnesstrackerdemo.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;


    public UserDto getUserById(Long id) {
        return userConverter.convertFirstToSecond(userRepository.findUserById(id)
                .orElseThrow(UserNotFoundException::new));
    }

    public void deleteUserById(Long id) {
        User user = userRepository.findUserById(id)
                .orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }

    public void save(@Valid UserDto userDto) {
        User user = userConverter.convertSecondToFirst(userDto);
        userRepository.save(user);
    }

    public void updateUserDetails(Long id, UserDto userDto) {
        if (userRepository.findUserById(id).isPresent()) {
            User user = userRepository.findUserById(id).get();
            User patchUser = userConverter.convertSecondToFirst(userDto);

            updateUser(user, patchUser);
            userRepository.save(user);
        } else throw new UserNotFoundException();
    }

    private void updateUser(User user, User patchUser) {
        if (patchUser.getFirstName() != null) {
            user.setFirstName(patchUser.getFirstName());
        }

        if (patchUser.getLastName() != null) {
            user.setLastName(patchUser.getLastName());
        }

        if (patchUser.getAge() != null) {
            user.setAge(patchUser.getAge());
        }

        if (patchUser.getEmail() != null) {
            user.setEmail(patchUser.getEmail());
        }

        if (patchUser.getStatus() != null) {
            user.setStatus(patchUser.getStatus());
        }

    }



}
