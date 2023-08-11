package ru.namelesscompany.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.namelesscompany.user.dto.ShortUserDto;
import ru.namelesscompany.user.service.publicService.PublicUserService;

import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "public/users")
@Slf4j
@Validated
public class UserPublicController {

    private final PublicUserService service;

    @GetMapping("/{userId}")
    public ShortUserDto get(@PathVariable @Positive Long userId) {
        log.info("Получение пользователя по ID в публичном контроллере.");
        return service.get(userId);
    }
}
