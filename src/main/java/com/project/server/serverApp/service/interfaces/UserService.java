package com.project.server.serverApp.service.interfaces;


import com.project.server.serverApp.entity.system.User;
import com.project.server.serverApp.entity.system.VerificationToken;
import com.project.server.serverApp.util.exceptions.EmailExistsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.ClientDetailsService;

import java.util.Optional;


public interface UserService extends UserDetailsService {


    <S extends User> S save(S s);


    User findById(Long aLong);


    Iterable<User> findAll();


    void deleteById(Long aLong);


    void delete(User user);

    User findUserByRevision(Long id, int revision);

    User registerNewUserAccount(User accountDto) throws EmailExistsException;

    boolean isLoginExist(String email);

    <S extends VerificationToken> S save(S entity);

    VerificationToken createVerificationToken(User user, String token);

    Optional<VerificationToken> findByTokenEquals(String token);

    Optional<VerificationToken> reissueVerificationToken(String token);

    Optional<User> update(User s);

}
