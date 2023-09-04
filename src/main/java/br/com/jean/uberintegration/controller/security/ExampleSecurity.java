package br.com.jean.uberintegration.controller.security;

import br.com.jean.uberintegration.domain.request.AuthenticationDTO;
import br.com.jean.uberintegration.domain.request.RegisterDTO;
import br.com.jean.uberintegration.domain.response.LoginResponseDTO;
import br.com.jean.uberintegration.entity.User;
import br.com.jean.uberintegration.repository.UserRepository;
import br.com.jean.uberintegration.service.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security")
public class ExampleSecurity {

    private AuthenticationManager authenticationManager;
    private UserRepository repository;
    private TokenService tokenService;

    @Autowired
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO data){
        if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/role")
    public ResponseEntity<Boolean> getRole() {
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/general")
    public ResponseEntity<Boolean> getAuth() {
        return ResponseEntity.ok().body(true);
    }
}

