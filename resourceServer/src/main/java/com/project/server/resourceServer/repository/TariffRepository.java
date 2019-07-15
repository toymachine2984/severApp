package com.project.server.resourceServer.repository;

import com.project.server.resourceServer.entity.Tariff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TariffRepository extends CrudRepository<Tariff, Long> {


}
