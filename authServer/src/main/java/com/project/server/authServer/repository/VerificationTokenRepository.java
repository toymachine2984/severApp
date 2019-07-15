package com.project.server.authServer.repository;

import com.project.server.authServer.entity.User;
import com.project.server.authServer.entity.VerificationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Long> {

    @Override
    <S extends VerificationToken> S save(S entity);

    Optional<VerificationToken> findByTokenEquals(String token);

    Optional<VerificationToken> findByUser(User user);


}
