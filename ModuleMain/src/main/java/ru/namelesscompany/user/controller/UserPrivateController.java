package ru.namelesscompany.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.namelesscompany.Marker;
import ru.namelesscompany.user.dto.NewUserDto;
import ru.namelesscompany.user.dto.UserDto;
import ru.namelesscompany.user.service.privateService.PrivateUserService;

import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/private/users")
@Slf4j
@Validated
public class UserPrivateController {

    private final PrivateUserService service;

    @GetMapping("/{userId}")
    public UserDto get(@PathVariable @Positive Long userId) {
        log.info("Получение пользователя по ID в приватном контроллере.");
        return service.get(userId);
    }

    @PostMapping()
    public UserDto add(@RequestBody @Validated({Marker.Create.class}) NewUserDto newUserDto) {
        log.info("Добавление нового пользователя в приватном контроллере.");
        return service.add(newUserDto);
    }

    @DeleteMapping("/{userId}")
    public String delete(@PathVariable @Positive Long userId) {
        log.info("Удаление пользователя в приватном контроллере.");
        return service.delete(userId);
    }

    @PatchMapping("/{userId}")
    public UserDto update(@PathVariable @Positive Long userId,
                          @Validated({Marker.Update.class}) @RequestBody NewUserDto updateUser) {
        log.info("Изменение данных пользователя в приватном контроллере.");
        return service.update(updateUser, userId);
    }
}
