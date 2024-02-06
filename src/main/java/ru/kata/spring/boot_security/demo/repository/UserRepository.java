package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u left join fetch u.roles where u.id = :id")
    Optional<User> findByIdWithRoles(@Param("id") Long id);

    @Query("select distinct u from User u left join fetch u.roles")
    List<User> findAllWithRoles();

    @Query("select u from User u left join fetch u.roles where u.name = :name")
    Optional<User> findByNameWithRoles(@Param("name") String name);

    List<User> findByRolesContaining(Role role);
}
