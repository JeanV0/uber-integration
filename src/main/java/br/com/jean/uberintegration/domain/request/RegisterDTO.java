package br.com.jean.uberintegration.domain.request;

import br.com.jean.uberintegration.domain.user.UserRole;
import lombok.Builder;

@Builder
public record RegisterDTO(String login, String password, UserRole role) {
}
