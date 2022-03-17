package com.pheonix.pheonix.services.AppUser;

import com.pheonix.pheonix.data.dto.AppUserRequestDto;
import com.pheonix.pheonix.data.dto.AppUserResponseDto;
import com.pheonix.pheonix.data.model.AppUser;
import com.pheonix.pheonix.data.repository.AppUserRepository;
import com.pheonix.pheonix.web.exceptions.UserAlreadyEXist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class AppUserServiceImpl implements AppUserService{
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public AppUserResponseDto createUser(AppUserRequestDto appUserRequestDto) throws UserAlreadyEXist {
        Optional<AppUser >  qerryUser = appUserRepository.findByEmail(appUserRequestDto.getEmail());
        if(qerryUser.isPresent()){
            throw new UserAlreadyEXist("this user Already exist");
        }
        AppUser newUser = new AppUser();
        newUser.setEmail(appUserRequestDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(appUserRequestDto.getPassword()));
        newUser.setAddress(appUserRequestDto.getAddress());
        newUser.setFirstName(appUserRequestDto.getFirstName());
        newUser.setLastName(appUserRequestDto.getLastName());

        appUserRepository.save(newUser);

         return buildResponse(newUser);
    }
    private AppUserResponseDto buildResponse(AppUser appUser){
        return AppUserResponseDto.builder()
                .Email(appUser.getEmail())
                .address(appUser.getAddress())
                .lastName(appUser.getLastName())
                .firstName(appUser.getFirstName())
                .build();
    }
}
