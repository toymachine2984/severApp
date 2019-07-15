package com.project.server.resourceServer.service;

import com.project.server.resourceServer.entity.Address;
import com.project.server.resourceServer.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("addressService")
@Transactional("dataTransactionManager")
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

    @Autowired
    public void setAddressRepository(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Iterable<Address> getAll() {
        return this.addressRepository.findAll();
    }

    @Override
    public Address save(Address address) {
        return this.addressRepository.save(address);
    }
}
