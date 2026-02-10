package com.POS.POSTienda.repository;

import com.POS.POSTienda.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
