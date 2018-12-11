package com.project.server.serverApp.service.interfaces;



import com.project.server.serverApp.entity.system.Role;


public interface RoleService {


    Role save(Role role);

    Role findById(Long aLong);

    Iterable<Role> findAll();

    void deleteById(Long aLong);

    void delete(Role role);

}
