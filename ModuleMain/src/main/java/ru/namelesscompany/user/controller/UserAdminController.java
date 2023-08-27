package ru.namelesscompany.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.namelesscompany.Marker;
import ru.namelesscompany.user.dto.FullUserDto;
import ru.namelesscompany.user.dto.NewUserDto;
import ru.namelesscompany.user.service.adminService.AdminUserService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/users")
@Slf4j
@Validated
public class UserAdminController {

    private final AdminUserService service;

    @PostMapping
    public FullUserDto add(@Validated({Marker.Create.class}) @RequestBody NewUserDto newUserDto) {
        log.info("Добавление нового пользователя админом с именем: {} и Email: {}", newUserDto.getName(), newUserDto.getEmail());
        return service.add(newUserDto);
    }

    @GetMapping("/{userId}")
    public FullUserDto get(@PathVariable @Positive Long userId) {
        log.info("Получение пользователя по ID админом.");
        return service.getById(userId);
    }

    @GetMapping("/all")
    public List<FullUserDto> getAll() {
        log.info("Получение всех пользователей админом");
        return service.getAll();
    }

    @GetMapping("/several")
    public List<FullUserDto> getByIds(@RequestParam(defaultValue = "") List<Long> ids) {
        log.info("Получение пользователей админом с ID: {}", ids);
        return service.getByIds(ids);
    }

    @PatchMapping("/{userId}")
    public FullUserDto update(@PathVariable @Positive Long userId,
                              @Validated({Marker.Update.class}) @RequestBody NewUserDto updateUser) {
        log.info("Админ редактирует данные пользователя с ID: {}", userId);
        return service.update(updateUser, userId);
    }

    @DeleteMapping("/{userId}")
    public String delete(@PathVariable @Positive Long userId) {
        log.info("Удаление пользователя админом.");
        return service.delete(userId);
    }
}
