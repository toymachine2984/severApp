package com.project.server.resourceServer.repository;

import com.project.server.resourceServer.entity.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address,Long> {


    Address getById(Long id);

}
