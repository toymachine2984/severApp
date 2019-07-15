package com.project.server.resourceServer.service;


import com.project.server.resourceServer.entity.Tariff;

public interface TariffService {

    Iterable<Tariff> getAll();

    Tariff save(Tariff tariff);
}
