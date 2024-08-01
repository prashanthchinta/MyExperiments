package com.havesweets.loans;

import com.havesweets.loans.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@OpenAPIDefinition(
        info = @Info(
                title = "Loans microservice REST API Documentation",
                description = "Bankofbabji Loans microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Prashanth Chinta",
                        email = "prashanthchinta29@gmail.com",
                        url = "https://www.bankofbabji.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.bankofbabji.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description =  "Bankofbabji Loans microservice REST API Documentation",
                url = "https://www.bankofbabji.com/swagger-ui.html"
        )
)

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
public class LoansApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoansApplication.class, args);
    }

}
