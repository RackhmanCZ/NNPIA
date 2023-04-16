package com.example.cv1.repository;

import com.example.cv1.domain.AppUser;
import com.example.cv1.domain.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserRepository extends PagingAndSortingRepository<AppUser, Long> {
    List<AppUser> findAllByActiveEquals(boolean active);

    @Query("""
        select ap
        from AppUser ap
        inner join ap.roles r
        where r = :role
    """) // Using JPQL
    List<AppUser> findAllByRolesContaining(final Role role);

    @Query("SELECT u from AppUser u where u.id = ?1")
    AppUser getUserById(int id);
    @Query("SELECT u from AppUser u where u.username = ?1")
    AppUser getUserByUsername(String username);
    @Query("SELECT u from AppUser u where u.active = ?1")
    List<AppUser> getUsersByActivity(boolean active);
    @Query("SELECT u from AppUser u where u.active = true")
    List<AppUser> getActiveUsers();
}