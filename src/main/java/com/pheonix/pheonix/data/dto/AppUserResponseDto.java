package com.pheonix.pheonix.data.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppUserResponseDto {

    private String firstName;
    private String lastName;

    private String Email;
   // do not return password;
    private String address;

}
