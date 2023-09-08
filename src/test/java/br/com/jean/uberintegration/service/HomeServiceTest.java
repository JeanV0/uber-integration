package br.com.jean.uberintegration.service;

import br.com.jean.uberintegration.domain.response.HomeDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomeServiceTest {

    private final HomeService service = new HomeService();

    @Test
    public void getReturn() {

        HomeDTO res = this.service.GetHome();
        
        assertEquals(res,new HomeDTO("Ola"));

    }
}
