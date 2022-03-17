package com.pheonix.pheonix.data.dto;

import com.pheonix.pheonix.data.model.Authority;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Data

public class AppUserRequestDto {

    private String firstName;
    private String lastName;

    private String Email;
    private String password;

    private String address;

}
