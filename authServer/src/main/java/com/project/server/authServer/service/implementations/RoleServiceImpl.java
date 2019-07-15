package com.project.server.authServer.service.implementations;

import com.project.server.authServer.entity.Role;
import com.project.server.authServer.repository.RoleRepository;
import com.project.server.authServer.service.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("roleService")
@Repository
@Transactional("systemTransactionManager")
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override

    @Transactional(readOnly = true)
    public Role findById(Long aLong) {
        return roleRepository.findById(aLong).orElse(new Role());
    }

    @Override

    @Transactional(readOnly = true)
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public void deleteById(Long aLong) {
        roleRepository.deleteById(aLong);

    }

    @Override
    public void delete(Role role) {
        roleRepository.delete(role);
    }
}
