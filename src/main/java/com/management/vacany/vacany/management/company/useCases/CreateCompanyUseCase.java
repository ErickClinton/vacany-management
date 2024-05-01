package com.management.vacany.vacany.management.company.useCases;

import com.management.vacany.vacany.management.company.CompanyRepository;
import com.management.vacany.vacany.management.company.entity.CompanyEntity;
import com.management.vacany.vacany.management.exceptions.UserFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity){
        System.out.println(companyEntity);
        this.companyRepository.findByUserNameOrEmail(companyEntity.getUserName(),companyEntity.getEmail()).ifPresent((user)->{
            throw new UserFoundException();
        });
        var encryptedPassWord = passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(encryptedPassWord);
        return this.companyRepository.save(companyEntity);
    }
}
