package com.sarvesh.userservicejul25.repositories;

import com.sarvesh.userservicejul25.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;
@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {
    @Override
    Token save(Token token);


    Optional<Token> findByValue(String value);

    Optional<Token> findByValueAndDeletedAndExpiryAtGreaterThan(
            String tokenValue,
            Boolean deleted,
            Date currentTime

    );
}
