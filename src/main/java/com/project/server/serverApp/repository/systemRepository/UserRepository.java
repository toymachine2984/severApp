package com.project.server.serverApp.repository.systemRepository;


import com.project.server.serverApp.entity.system.User;
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
