package com.pheonix.pheonix.data.repository;

import com.pheonix.pheonix.data.model.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Sql(scripts = "/Db/insert.sql")
@Slf4j
class AppUserRepositoryTest {
    @Autowired
    AppUserRepository appUserRepository;
    @Test
    @DisplayName("Create a new user with cart")
    void whenUserIsCreated_ThenCreateCartTest(){
        //create a new user;
        AppUser appUser = new AppUser();
        appUser.setFirstName("John");
        appUser.setLastName("Badmus");
        appUser.setAddress("goland street");
        appUser.setEmail("oalsfde@gmail.com");
        appUserRepository.save(appUser);
        assertThat(appUser.getId()).isNotNull();
        assertThat(appUser.getMyCart()).isNotNull();
        log.info("App user created :: {} ", appUser);
    }

}