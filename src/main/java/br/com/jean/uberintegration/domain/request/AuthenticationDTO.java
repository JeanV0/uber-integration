package br.com.jean.uberintegration.domain.request;

import lombok.Builder;
@Builder
public record AuthenticationDTO(String login, String password) {
}
