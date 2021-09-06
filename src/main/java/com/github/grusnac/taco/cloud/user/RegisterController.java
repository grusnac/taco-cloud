package com.github.grusnac.taco.cloud.user;

import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegisterController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConversionService conversionService;

    public RegisterController(UserRepository userRepository, PasswordEncoder passwordEncoder,
                              ConversionService conversionService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.conversionService = conversionService;
    }

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(UserRegisterRequest registerRequest) {
        final UserEntity userEntity = conversionService.convert(registerRequest, UserEntity.class);
        assert userEntity != null;
        userRepository.save(userEntity);
        return "redirect:/login";
    }

}
