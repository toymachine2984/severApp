package com.project.server.authServer.service.interfaces;



import com.project.server.authServer.entity.Role;


public interface RoleService {


    Role save(Role role);

    Role findById(Long aLong);

    Iterable<Role> findAll();

    void deleteById(Long aLong);

    void delete(Role role);

}
