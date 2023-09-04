package br.com.jean.uberintegration.service;

import br.com.jean.uberintegration.domain.response.HomeDTO;
import org.springframework.stereotype.Service;

@Service
public class HomeService
{
    public HomeDTO GetHome() {
            return new HomeDTO("Ola");
    }
}
