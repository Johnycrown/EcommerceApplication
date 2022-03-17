package com.pheonix.pheonix.services.AppUser;

import com.pheonix.pheonix.data.dto.AppUserRequestDto;
import com.pheonix.pheonix.data.dto.AppUserResponseDto;
import com.pheonix.pheonix.web.exceptions.UserAlreadyEXist;

public interface AppUserService {

    AppUserResponseDto createUser(AppUserRequestDto appUserRequestDto) throws UserAlreadyEXist;
}
