package org.fittrack.fitnesstrackerdemo.converters.impl;

import org.fittrack.fitnesstrackerdemo.converters.ObjectConverter;
import org.fittrack.fitnesstrackerdemo.models.dtos.UserDto;
import org.fittrack.fitnesstrackerdemo.models.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements ObjectConverter<User, UserDto> {
    @Override
    public User convertSecondToFirst(UserDto userDTO) {
        User user = new User();

        user.setAge(userDTO.age());
        user.setFirstName(userDTO.firstName());
        user.setLastName(userDTO.lastName());
        user.setEmail(userDTO.email());

        return user;
    }

    @Override
    public UserDto convertFirstToSecond(User user) {
        return UserDto.builder()
                .age(user.getAge())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
