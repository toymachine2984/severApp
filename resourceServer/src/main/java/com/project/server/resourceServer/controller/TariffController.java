package com.project.server.resourceServer.controller;

import com.project.server.resourceServer.entity.Tariff;
import com.project.server.resourceServer.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "tariffs")
public class TariffController {

    private TariffService tariffService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<Tariff> getTariffs() {
        return tariffService.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Tariff addTariff(@RequestBody Tariff tariff) {
        return tariffService.save(tariff);
    }

    @Autowired
    public void setTariffService(TariffService tariffService) {
        this.tariffService = tariffService;
    }
}
