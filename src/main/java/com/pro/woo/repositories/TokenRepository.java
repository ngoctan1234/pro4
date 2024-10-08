package com.pro.woo.repositories;

import com.pro.woo.models.Token;
import com.pro.woo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenRepository extends JpaRepository<Token,Long> {
    List<Token> findByUser(User user);
    Token findByToken(String token);
    Token findByRefreshToken(String refreshToken);
}
