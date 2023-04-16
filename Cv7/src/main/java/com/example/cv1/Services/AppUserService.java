package com.example.cv1.Services;

import com.example.cv1.domain.AppUser;
import com.example.cv1.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AppUserService {
    private final AppUserRepository appUserRepository;

    @Transactional(readOnly = true)
    public List<AppUser> findAllByActiveEquals() {
        return appUserRepository.getUsersByActivity(true);
    }

    @Transactional(readOnly = true)
    public AppUser findById(long id) {
        var result = appUserRepository.findById(id);
        return result.get();
    }

    @Transactional
    public AppUser create(final AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    @Transactional
    public AppUser update(final AppUser toEntity) {
        return appUserRepository.save(toEntity);
    }

    @Transactional
    public void delete(final long id) {
        appUserRepository.deleteById(id);
    }
}
