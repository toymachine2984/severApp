package com.project.server.serverApp.repository.systemRepository;

import com.project.server.serverApp.entity.system.User;
import com.project.server.serverApp.entity.system.VerificationToken;
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
