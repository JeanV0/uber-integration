package br.com.jean.uberintegration.controller;


import br.com.jean.uberintegration.domain.response.HomeDTO;
import br.com.jean.uberintegration.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    private HomeService service;
    @Autowired
    public void setHomeService(HomeService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/product")
    public ResponseEntity<HomeDTO> getHome() {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.GetHome());
    }
}
