package ru.namelesscompany.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.namelesscompany.admin.model.Admin;
import ru.namelesscompany.admin.repository.AdminRepository;
import ru.namelesscompany.security.role.model.Role;
import ru.namelesscompany.user.model.User;
import ru.namelesscompany.user.repository.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class UserDetailsServ implements UserDetailsService {
    private final AdminRepository repository;

    public Admin findByUserEmail(String email) {
        return repository.findByEmailContainingIgnoreCase(email);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin user = findByUserEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("Пользователь с email: '%s' - не найден.", email));
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                mapRolesToAuthorities(List.of(user.getRole())));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
}
