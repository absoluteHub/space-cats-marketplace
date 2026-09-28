package catsmarketplacetsap.spacecatsmarketplace.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class SecurityIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldBlockRequestWithoutKey() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldBlockRequestWithWrongKey() throws Exception {
        mockMvc.perform(get("/products")
                        .header("X-COSMO-KEY", "wrong-key"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldAllowRequestWithCorrectKey() throws Exception {

        mockMvc.perform(get("/products")
                        .header("X-COSMO-KEY", "meow-secret-key-123"))
                .andExpect(status().isOk());
    }
}