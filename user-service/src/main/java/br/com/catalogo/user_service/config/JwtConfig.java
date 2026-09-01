package br.com.catalogo.user_service.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Configuration
public class JwtConfig {

    // Cria o JwtEncoder que será utilizado posteriormente
    // para gerar e assinar nossos tokens JWT.
    @Bean
    public JwtEncoder jwtEncoder(
            @Value("${jwt.secret}") String secret) {

        // Converte a chave secreta em uma chave criptográfica
        // utilizando o algoritmo HMAC SHA-256.
        SecretKey key = new SecretKeySpec(
                secret.getBytes(StandardCharsets.UTF_8),
                "HmacSHA256"
        );

        // Transforma nossa SecretKey em uma chave JWK.
        OctetSequenceKey jwk = new OctetSequenceKey.Builder(key)
                .build();

        // Cria uma fonte de chaves JWK contendo nossa chave.
        JWKSource<SecurityContext> jwkSource =
                new ImmutableJWKSet<>(new JWKSet(jwk));

        // O NimbusJwtEncoder utiliza essa fonte de chaves
        // para assinar os tokens JWT.
        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean
    public JwtDecoder jwtDecoder(
            @Value("${jwt.secret}") String secret) {

        // Converte a chave secreta em uma chave criptográfica
        // utilizando o algoritmo HMAC SHA-256.
        SecretKey key = new SecretKeySpec(
                secret.getBytes(StandardCharsets.UTF_8),
                "HmacSHA256"
        );

        // Cria o JwtDecoder utilizando a mesma chave secreta.
        // Essa chave será utilizada para validar a assinatura do JWT.
        return NimbusJwtDecoder.withSecretKey(key).build();

    }
}