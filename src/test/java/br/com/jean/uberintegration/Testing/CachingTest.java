package br.com.jean.uberintegration.Testing;

import br.com.jean.uberintegration.entity.Driver;
import br.com.jean.uberintegration.repository.DriverRepository;
import br.com.jean.uberintegration.service.drivers.DriverService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CachingTest {

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    DriverService driverService;

    List<Driver> drivers = new ArrayList<>() {{
        new Driver().setName("Rodrigo");
        new Driver().setName("Joel");
        new Driver().setName("Marcelo");
        new Driver().setName("Jean");
        new Driver().setName("Khirya");
    }};

    @Test
    void getDrivers() {
        this.drivers = this.driverRepository.saveAll(this.drivers);

        List<Driver> drivers = this.driverService.getDriver();

        List<Driver> driversToo = null;
        try {
            driversToo = this.driverService.getDriverByRedis();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        assertEquals(driversToo, drivers);

        this.driverRepository.deleteAll(this.drivers);
    }
}
