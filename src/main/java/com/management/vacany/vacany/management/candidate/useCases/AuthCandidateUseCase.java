package com.management.vacany.vacany.management.candidate.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.management.vacany.vacany.management.candidate.CandidateRepository;
import com.management.vacany.vacany.management.candidate.dto.AuthCandidateRequestDto;
import com.management.vacany.vacany.management.candidate.dto.AuthCandidateResponseDto;
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
public class AuthCandidateUseCase {

    @Value("${security.token.secret.candidate")
    private String secretKey;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateResponseDto execute(AuthCandidateRequestDto authCandidateRequestDto) throws AuthenticationException {
        var candidate = this.candidateRepository.findByUserName(authCandidateRequestDto.username())
                .orElseThrow(() -> new UsernameNotFoundException("Username/Password incorrect!"));

        var passowrdMatches = this.passwordEncoder.matches(authCandidateRequestDto.password(),candidate.getPassword());

        if(!passowrdMatches){
            throw new AuthenticationException() ;
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        var token = JWT.create().withIssuer("javagas")
                .withSubject(candidate.getId().toString())
                .withClaim("roles", List.of("CANDIDATE"))
                .withExpiresAt(expiresIn)
                .sign(algorithm);

        var authCandidateResponse= AuthCandidateResponseDto.builder().acessToken(token).expiresIn(expiresIn.toEpochMilli()).build();
        return authCandidateResponse;
    }


}
