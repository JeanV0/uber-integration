package br.com.jean.uberintegration.controller.cache;

import br.com.jean.uberintegration.entity.Driver;
import br.com.jean.uberintegration.service.drivers.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/")
public class Drivers {

    DriverService service;

    @Autowired
    public void setService(DriverService service) {
        this.service = service;
    }

    @GetMapping("/driver/{id}")
    public ResponseEntity<List<Driver>> getDriver(@PathVariable("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.getDriver());
    }
}
