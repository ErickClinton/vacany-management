package com.management.vacany.vacany.management.company.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.management.vacany.vacany.management.company.CompanyRepository;
import com.management.vacany.vacany.management.company.dto.AuthCompanyDto;
import com.management.vacany.vacany.management.company.dto.AuthCompanyResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCompanyResponseDto execute(AuthCompanyDto authCompanyDto) throws AuthenticationException {
        var company = this.companyRepository.findByUserName(authCompanyDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username/Password incorrect!"));

        var comparyPasswords = this.passwordEncoder.matches(authCompanyDto.getPassword(),company.getPassword());

        if(!comparyPasswords){
            throw new AuthenticationException() ;
        };

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create().withIssuer("javagas")
                .withExpiresAt(expiresIn)
                .withSubject(company.getId().toString())
                .withClaim("roles", List.of("COMPANY"))
                .sign(algorithm);

        return AuthCompanyResponseDto.builder().acessToken(token).expiresIn(expiresIn.toEpochMilli()).build();
    }
}
