package com.havesweets.accounts.controller;

import com.havesweets.accounts.constants.AccountConstants;
import com.havesweets.accounts.dao.Accounts;
import com.havesweets.accounts.dto.*;
import com.havesweets.accounts.exception.AccountNotFoundException;
import com.havesweets.accounts.serviceImpl.AccountsService;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for Accounts in BankOfBabji",
        description = "CRUD REST APIs in BankOfBabji to CREATE, UPDATE, FETCH AND DELETE account details"
)

@RestController
@RequestMapping(value = "api")
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class AccountsController {

    private static final Logger logger = LoggerFactory.getLogger(AccountsController.class);

    @Autowired
    AccountsService accountsService;

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private AccountsContactInfoDto accountsContactInfoDto;

        @Operation(
                summary = "Create Account REST API",
                description = "REST API to create new Account inside Babji Bank"
        )
        @ApiResponses({
                @ApiResponse(
                        responseCode = "201",
                        description = "HTTP Status CREATED"
                ),
                @ApiResponse(
                        responseCode = "500",
                        description = "HTTP Status Internal Server Error",
                        content = @Content(
                                schema = @Schema(implementation = ErrorResponse.class)
                        )
                )
        }
        )
    @PostMapping(value = "accounts/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> addAccount(@Valid @RequestBody AccountsDto accountsDto) {
        AccountsDto accounts = accountsService.addAccount(accountsDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.CREATED_STATUS,
                        AccountConstants.CREATED_MESSAGE));
    }

        @Operation(
                summary = "Update Account Details REST API",
                description = "REST API to update Account details based on a account id"
        )
        @ApiResponses({
                @ApiResponse(
                        responseCode = "200",
                        description = "HTTP Status OK"
                ),
                @ApiResponse(
                        responseCode = "417",
                        description = "Expectation Failed"
                ),
                @ApiResponse(
                        responseCode = "500",
                        description = "HTTP Status Internal Server Error",
                        content = @Content(
                                schema = @Schema(implementation = ErrorResponse.class)
                        )
                )
        }
        )
    @PutMapping(value = "accounts/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody AccountsDto accountsDto) throws AccountNotFoundException {
        Accounts accounts = accountsService.updateAccount(accountsDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(AccountConstants.OK_STATUS,
                        AccountConstants.OK_MESSAGE));
    }

        @Operation(
                summary = "Fetch Account Details REST API",
                description = "REST API to Account details based on a Account Id"
        )
        @ApiResponses({
                @ApiResponse(
                        responseCode = "200",
                        description = "HTTP Status OK"
                ),
                @ApiResponse(
                        responseCode = "500",
                        description = "HTTP Status Internal Server Error",
                        content = @Content(
                                schema = @Schema(implementation = ErrorResponse.class)
                        )
                )
        }
        )

    @GetMapping(value = "accounts/fetch/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountsDto> getAccount(@PathVariable String id) throws AccountNotFoundException {
        AccountsDto accountsDto = accountsService.getAccounts(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsDto);
    }

        @Operation(
                summary = "Fetch Account Details REST API",
                description = "REST API to Account details based on a Account Id"
        )
        @ApiResponses({
                @ApiResponse(
                        responseCode = "200",
                        description = "HTTP Status OK"
                ),
                @ApiResponse(
                        responseCode = "500",
                        description = "HTTP Status Internal Server Error",
                        content = @Content(
                                schema = @Schema(implementation = ErrorResponse.class)
                        )
                )
        }
        )

    @GetMapping(value = "accounts/fetch", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AccountsDto>> getAccounts() {
        List<AccountsDto> accountsDto = accountsService.getAccountList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsDto);
    }

        @Operation(
                summary = "Delete Account Details REST API",
                description = "REST API to delete Account details based on a Account Id"
        )
        @ApiResponses({
                @ApiResponse(
                        responseCode = "200",
                        description = "HTTP Status OK"
                ),
                @ApiResponse(
                        responseCode = "417",
                        description = "Expectation Failed"
                ),
                @ApiResponse(
                        responseCode = "500",
                        description = "HTTP Status Internal Server Error",
                        content = @Content(
                                schema = @Schema(implementation = ErrorResponse.class)
                        )
                )
        }
        )

    @DeleteMapping(value = "accounts/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> deleteAccounts(@PathVariable long id) throws AccountNotFoundException {
        accountsService.deleteAccount(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(AccountConstants.OK_STATUS,
                        AccountConstants.OK_MESSAGE));
    }

        @Operation(
                summary = "Fetch Account, Cards, Loans Details REST API",
                description = "REST API to fetch Account, Cards, and Loans details based on a Account Id"
        )
        @ApiResponses({
                @ApiResponse(
                        responseCode = "200",
                        description = "HTTP Status OK"
                ),
                @ApiResponse(
                        responseCode = "417",
                        description = "Expectation Failed"
                ),
                @ApiResponse(
                        responseCode = "500",
                        description = "HTTP Status Internal Server Error",
                        content = @Content(
                                schema = @Schema(implementation = ErrorResponse.class)
                        )
                )
        }
        )
    @GetMapping(value = "accounts/fetchDetails/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDetailsDto> getAccountDetails(@RequestHeader("bankofbabji-correlation-id") String correlationId,
                                                                   @PathVariable(name = "id") Long accountId) throws AccountNotFoundException {

        logger.debug("BankofBabji Correlation Id  : "+correlationId);
        AccountDetailsDto accountDetails = accountsService.getAccountDetails(correlationId,accountId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountDetails);
    }
    @Operation(
            summary = "Fetch Account Contact Info REST API",
            description = "REST API to fetch Account Contact Info"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    }
    )
    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsContactInfoDto);
    }
    @Operation(
            summary = "Fetch Account Build Info",
            description = "REST API to fetch Account Build Info"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    }
    )
    @Retry(name = "accountsRetry")
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
            logger.info("buildinfo method  called...");
            throw new NullPointerException();
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(buildVersion);
    }

    public ResponseEntity<String> getBuildInfoFallback(Throwable throwable) {

        logger.info("buildinfo callback method  called...");
            return ResponseEntity.status(HttpStatus.OK).body("build version not displaying");
    }
}
