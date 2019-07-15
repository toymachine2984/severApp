package com.project.server.resourceServer.service;

import com.project.server.resourceServer.entity.Address;

public interface AddressService {

    Iterable<Address> getAll();

    Address save(Address address);
}
