package ru.namelesscompany.subAdmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.namelesscompany.subAdmin.model.SubAdmin;

import java.util.List;

public interface SubAdminRepository extends JpaRepository<SubAdmin, Long> {
    List<SubAdmin> findAllByIdIn(List<Long> ids);
    SubAdmin findByEmailContainingIgnoreCase(String email);
}
