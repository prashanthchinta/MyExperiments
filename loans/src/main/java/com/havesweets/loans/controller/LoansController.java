package com.havesweets.loans.controller;

import com.havesweets.loans.dto.AccountsContactInfoDto;
import com.havesweets.loans.dto.ErrorResponse;
import com.havesweets.loans.dto.LoansDto;
import com.havesweets.loans.dto.ResponseDto;
import com.havesweets.loans.exception.LoansNotFoundException;
import com.havesweets.loans.service.LoansService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for Loans in BankOfBabji",
        description = "CRUD REST APIs in BankOfBabji to CREATE, UPDATE, FETCH AND DELETE cards details"
)
@RestController
@RequestMapping(value = "api")
@Validated
public class LoansController {

    private static final Logger logger = LoggerFactory.getLogger(LoansController.class);

    @Autowired
    LoansService loansService;

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private AccountsContactInfoDto accountsContactInfoDto;

    @Operation
            (
                    summary = "Create Loans REST API",
                    description = "REST API to create new Loans inside Babji Bank"
            )
    @ApiResponses
            (
                    {
                            @ApiResponse(
                                    responseCode = "201",
                                    description = "HTTP Status CREATED"),
                            @ApiResponse(responseCode = "500",
                                    description = "HTTP Status Internal Server Error",
                                    content = @Content(
                                            schema = @Schema(implementation = ErrorResponse.class
                                            )
                                    )
                            )
                    }
                    )
    @PostMapping(value = "loans/create")
    public ResponseEntity<ResponseDto> addLoan(@RequestBody LoansDto loansDto) {
        LoansDto response = loansService.addLoans(loansDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(HttpStatus.CREATED.value(),
                        "Loans Successfully Created...!"));
    }

    @Operation
            (
                    summary = "Update Loans Details REST API",
                    description = "REST API to update Loans details based on a Loans DTO"
            )
    @ApiResponses
            (
                    {
                            @ApiResponse(responseCode = "200",
                                    description = "HTTP Status OK"),
                            @ApiResponse(responseCode = "417",
                                    description = "Expectation Failed"),
                            @ApiResponse(responseCode = "500",
                                    description = "HTTP Status Internal Server Error",
                                    content = @Content(
                                            schema = @Schema(implementation = ErrorResponse.class
                                            )
                                    )
                            )
                    }
            )
    @PutMapping(value = "loans/update")
    public ResponseEntity<ResponseDto> updateLoan(@RequestBody LoansDto loansDto) throws LoansNotFoundException {
        LoansDto response = loansService.updateLoans(loansDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.value(),
                        "Loans Successfully Updated...!"));
    }

    @Operation
            (
                    summary = "Fetch Loans Details By Loan Id REST API",
                    description = "REST API to Loans details Based on Loans Id"
            )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "HTTP Status OK"),
                    @ApiResponse(responseCode = "500",
                            description = "HTTP Status Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class
                                    )
                            )
                    )
            }
    )
    @GetMapping(value = "loans/fetch/{id}")
    public ResponseEntity<LoansDto> getLoanById(@PathVariable(name = "id") Long loanId) throws LoansNotFoundException {
        LoansDto loansDto = loansService.getLoanById(loanId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loansDto);
    }

    @Operation
            (
                    summary = "Fetch Loans Details REST API",
                    description = "REST API to All Loans details"
            )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema =
                            @Schema(implementation = ErrorResponse.class
                            )
                    )
            )
    })
    @GetMapping(value = "loans/fetch")
    public ResponseEntity<List<LoansDto>> getAllLoans() throws LoansNotFoundException {
        List<LoansDto> allLoans = loansService.getAllLoans();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allLoans);
    }

    @Operation
            (
                    summary = "Delete Loans Details REST API",
                    description = "REST API to Delete Loans details Based on Loans Id"
            )
    @ApiResponses
            (
                    {
                            @ApiResponse(responseCode = "200",
                                    description = "HTTP Status OK"),
                            @ApiResponse(responseCode = "500",
                                    description = "HTTP Status Internal Server Error",
                                    content = @Content(
                                            schema = @Schema(implementation = ErrorResponse.class
                                            )
                                    )
                            )
                    }
                    )
    @DeleteMapping(value = "loans/delete/{id}")
    public ResponseEntity<ResponseDto> updateLoan(@PathVariable(name = "id") Long loanId) throws LoansNotFoundException {
        String response = loansService.deleteLoanById(loanId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.value(),
                        "Loans Successfully Deleted...!"));
    }

    @Operation
            (
            summary = "Fetch Loans Details REST API",
            description = "REST API to Loans details Based on Account Id"
            )
    @ApiResponses
            (
                    {
                            @ApiResponse(responseCode = "200",
                                description = "HTTP Status OK"),
                            @ApiResponse(responseCode = "500",
                                description = "HTTP Status Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class
                            )
                            )
                            )
                    }
                    )
    @GetMapping(value = "loans/fetchByAcc/{id}")
    public ResponseEntity<List<LoansDto>> getByAccountId(@RequestHeader("bankofbabji-correlation-id") String correlationId,
                                                         @PathVariable(name = "id") Long accId) {
        logger.debug("BankofBabji Correlation Id  : "+correlationId);
        List<LoansDto> response = loansService.getByAccountId(accId,correlationId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @Operation
            (
                    summary = "Fetch Loans Contact Info REST API",
                    description = "REST API to Loans Contact Info"
            )
    @ApiResponses
            (
                    {
                            @ApiResponse(responseCode = "200",
                                    description = "HTTP Status OK"),
                            @ApiResponse(responseCode = "500",
                                    description = "HTTP Status Internal Server Error",
                                    content = @Content(
                                            schema = @Schema(implementation = ErrorResponse.class
                                            )
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

    @Operation
            (
                    summary = "Fetch Loans Build Info REST API",
                    description = "REST API to Loans Build Info"
            )
    @ApiResponses
            (
                    {
                            @ApiResponse(responseCode = "200",
                                    description = "HTTP Status OK"),
                            @ApiResponse(responseCode = "500",
                                    description = "HTTP Status Internal Server Error",
                                    content = @Content(
                                            schema = @Schema(implementation = ErrorResponse.class
                                            )
                                    )
                            )
                    }
            )

    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }

}
