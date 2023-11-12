package ru.namelesscompany.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.namelesscompany.Marker;
import ru.namelesscompany.admin.dto.AdminDto;
import ru.namelesscompany.admin.dto.FullAdminDto;
import ru.namelesscompany.admin.dto.NewAdminDto;
import ru.namelesscompany.admin.service.AdminService;

import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin")
@Slf4j
@Validated
public class AdminController {

    private final AdminService service;

    @GetMapping("/{adminId}")
    public FullAdminDto get(@PathVariable @Positive Long adminId) {
        log.info("Получение по ID админа.");
        return service.getById(adminId);
    }

    @PatchMapping("/{adminId}")
    public FullAdminDto update(@PathVariable @Positive Long adminId,
                                  @Validated({Marker.Update.class}) @RequestBody NewAdminDto updatedAdmin) {
        log.info("Админ редактирует свои данные.");
        return service.update(updatedAdmin, adminId);
    }

    @GetMapping("/forAll/{adminId}")
    public AdminDto getAdminForUsers(@PathVariable @Positive Long adminId) {
        log.info("Получение по ID админа другими пользователями.");
        return service.getAdminForUsers(adminId);
    }
}
