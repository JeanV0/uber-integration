package br.com.jean.uberintegration.repository;

import br.com.jean.uberintegration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
