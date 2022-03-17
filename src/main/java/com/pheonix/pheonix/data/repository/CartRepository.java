package com.pheonix.pheonix.data.repository;

import com.pheonix.pheonix.data.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
