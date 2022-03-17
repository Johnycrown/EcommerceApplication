package com.pheonix.pheonix.data.model;

import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity

public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @ElementCollection
private List<Authority> authorities;
    private  boolean enabled;


    @Column(length =500)
    private String address;
    @CreationTimestamp
    private LocalDateTime datecreated;
    @OneToOne(cascade = CascadeType.PERSIST)
    @Getter
    private final Cart myCart;

    public AppUser(){
        this.myCart = new Cart();
        myCart.setTotalPrice(0.0);
    }

}
