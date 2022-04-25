package com.huchuchu.paper.springboot.domain.user;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /*OAuth2.0*/
    Optional<User> findByEmail(String email);

}
