package com.github.grusnac.taco.cloud.user;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterRequestConverter implements Converter<UserRegisterRequest, UserEntity> {

    private final PasswordEncoder passwordEncoder;

    public UserRegisterRequestConverter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity convert(UserRegisterRequest userRegisterRequest) {
        final UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userRegisterRequest.username);
        userEntity.setPassword(passwordEncoder.encode(userRegisterRequest.password));
        userEntity.setFullName(userRegisterRequest.fullName);
        userEntity.setStreet(userRegisterRequest.street);
        userEntity.setCity(userRegisterRequest.city);
        userEntity.setState(userRegisterRequest.state);
        userEntity.setZip(userRegisterRequest.zip);
        userEntity.setPhoneNumber(userRegisterRequest.phone);
        return userEntity;
    }
}
