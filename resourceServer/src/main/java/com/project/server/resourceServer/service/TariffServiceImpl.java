package com.project.server.resourceServer.service;

import com.project.server.resourceServer.entity.Tariff;
import com.project.server.resourceServer.repository.TariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("tariffService")
@Transactional("dataTransactionManager")
public class TariffServiceImpl implements TariffService {

    private TariffRepository tariffRepository;

    @Override
    public Iterable<Tariff> getAll() {
        return tariffRepository.findAll();
    }

    @Override
    public Tariff save(Tariff tariff) {
        return tariffRepository.save(tariff);
    }

    @Autowired
    public void setTariffRepository(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }
}
