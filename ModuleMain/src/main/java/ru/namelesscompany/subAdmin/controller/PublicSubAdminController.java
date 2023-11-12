package ru.namelesscompany.subAdmin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.namelesscompany.subAdmin.dto.SubAdminDto;
import ru.namelesscompany.subAdmin.service.publicSubAdmin.PublicSubAdminService;

import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/public/subAdmin")
@Slf4j
@Validated
public class PublicSubAdminController {
    private final PublicSubAdminService service;

    @GetMapping("/{subAdminId}")
    public SubAdminDto get(@PathVariable @Positive Long subAdminId) {
        log.info("Получение субадмина по ID.");
        return service.getById(subAdminId);
    }
}
