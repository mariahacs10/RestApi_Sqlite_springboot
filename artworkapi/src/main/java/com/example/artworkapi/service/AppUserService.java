package com.example.artworkapi.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.artworkapi.domain.AppUser;
import com.example.artworkapi.model.AppUserRepository;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    public AppUser findById(Long userId) {
        return appUserRepository.findById(userId).orElse(null);
    }
}
