package team.projectpulse.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AuthServicePrincipalTest {

    @Test
    void rejectsBearerPrincipalWhenExchangingLoginCredentials() {
        AuthService authService = new AuthService(null, null, null);
        Jwt jwt = Jwt.withTokenValue("existing-token")
                .header("alg", "RS256")
                .subject("student")
                .build();
        JwtAuthenticationToken authentication = new JwtAuthenticationToken(jwt);

        assertThrows(InsufficientAuthenticationException.class,
                () -> authService.createLoginInfo(authentication));
    }
}
