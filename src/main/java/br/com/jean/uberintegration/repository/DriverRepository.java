package br.com.jean.uberintegration.repository;
import br.com.jean.uberintegration.entity.Driver;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DriverRepository extends JpaRepository<Driver, UUID> {
}
