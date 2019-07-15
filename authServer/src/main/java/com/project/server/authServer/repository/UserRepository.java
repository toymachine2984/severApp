package com.project.server.authServer.repository;


import com.project.server.authServer.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    <S extends User> S save(S s);

    @Override
    Optional<User> findById(Long aLong);

    @Override
    Iterable<User> findAll();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(User user);


    Optional<User> findByLoginEquals(String login);

}
