package com.project.server.resourceServer.controller;

import com.project.server.resourceServer.entity.Address;
import com.project.server.resourceServer.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "address")
public class AddressController {

    private AddressService addressService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<Address> getAddress() {
        return this.addressService.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Address addAddress(@RequestBody Address address) {
        return this.addressService.save(address);
    }


    @Autowired
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }
}
