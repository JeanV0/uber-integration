package br.com.jean.uberintegration.e2e;

import br.com.jean.uberintegration.domain.request.AuthenticationDTO;
import br.com.jean.uberintegration.domain.request.RegisterDTO;
import br.com.jean.uberintegration.domain.response.LoginResponseDTO;
import br.com.jean.uberintegration.domain.user.UserRole;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Role;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void register() throws Exception {
        RegisterDTO login = RegisterDTO
                .builder()
                .login("admin")
                .password("123")
                .role(UserRole.ADMIN)
                .build();

        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.post("/security/register")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(login)));

        result.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Role()
    void login() throws Exception {

        AuthenticationDTO login = AuthenticationDTO
                .builder()
                .login("admin")
                .password("123")
                .build();

        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.post("/security/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(login)));

        String content = result.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();

        LoginResponseDTO loginToken = new ObjectMapper().readValue(content, LoginResponseDTO.class);

        ResultActions role = this.mockMvc.perform(MockMvcRequestBuilders.get("/product")
                .contentType("application/json")
                .header("authentication", "Bearer " + loginToken.token()));

        role.andExpect(MockMvcResultMatchers.status().isOk());
    }
}
