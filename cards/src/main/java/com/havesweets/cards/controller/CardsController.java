package com.havesweets.cards.controller;

import com.havesweets.cards.dto.AccountsContactInfoDto;
import com.havesweets.cards.dto.CardsDto;
import com.havesweets.cards.dto.ErrorResponse;
import com.havesweets.cards.dto.ResponseDto;
import com.havesweets.cards.exception.CardsNotFoundException;
import com.havesweets.cards.service.CardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for Cards in BankOfBabji",
        description = "CRUD REST APIs in BankOfBabji to CREATE, UPDATE, FETCH AND DELETE cards details"
)
@RestController
@Validated
@AllArgsConstructor
@RequestMapping(value = "api")
public class CardsController {
    private static final Logger logger = LoggerFactory.getLogger(CardsController.class);

    @Autowired
    private AccountsContactInfoDto accountsContactInfoDto;

    @Value("${build.version}")
    private String buildVersion;


    @Autowired
    CardsService cardsService;

        @Operation(
                summary = "Create Cards REST API",
                description = "REST API to create new Cards inside Babji Bank"
        )
        @ApiResponses(
                {
                        @ApiResponse(
                                responseCode = "201",
                                description = "HTTP Status CREATED"),
                        @ApiResponse(responseCode = "500",
                                description = "HTTP Status Internal Server Error",
                                content = @Content(
                                        schema = @Schema(implementation = ErrorResponse.class)
                                )
                        )
                }
                )
    @PostMapping(value = "cards/create",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> saveCards(@RequestBody CardsDto cardsDto) {
        CardsDto cards = cardsService.addCards(cardsDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(HttpStatus.CREATED.value(),
                        "Card Successfully Inserted...!"));

    }
        @Operation(
                summary = "Update Cards Details REST API",
                description = "REST API to update Account details based on a Cards id"
        )
        @ApiResponses(
                {
                        @ApiResponse(
                                responseCode = "200",
                                description = "HTTP Status OK"),
                        @ApiResponse(responseCode = "417",
                                description = "Expectation Failed"),
                        @ApiResponse(responseCode = "500",
                                description = "HTTP Status Internal Server Error",
                                content = @Content(
                                        schema = @Schema(implementation = ErrorResponse.class)
                                )
                        )
                }
                )
    @PutMapping(value = "cards/upadte",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> updateCards(@RequestBody CardsDto cardsDto) throws CardsNotFoundException {
        CardsDto cards = cardsService.updateCards(cardsDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.value()
                        , "Card Successfully Updated...!"));
    }

        @Operation(
                summary = "Fetch Cards Details REST API",
                description = "REST API to All Accounts details"
        )
        @ApiResponses(
                {
                        @ApiResponse(
                                responseCode = "200",
                                description = "HTTP Status OK"),
                        @ApiResponse(responseCode = "500",
                                description = "HTTP Status Internal Server Error",
                                content = @Content(
                                        schema = @Schema(implementation = ErrorResponse.class)
                                )
                        )
                }
                )
    @GetMapping(value = "cards/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CardsDto>> getCards() throws CardsNotFoundException {
        List<CardsDto> cardsDto = cardsService.getCards();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardsDto);
    }

        @Operation(
                summary = "Fetch Cards Details REST API",
                description = "REST API to Cards details Based on Cards Id"
        )
        @ApiResponses(
                {
                        @ApiResponse(
                                responseCode = "200",
                                description = "HTTP Status OK"),
                        @ApiResponse(responseCode = "500",
                                description = "HTTP Status Internal Server Error",
                                content = @Content(
                                        schema = @Schema(implementation = ErrorResponse.class)
                                )
                        )
                }
                )
    @GetMapping(value = "cards/fetch/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardsDto> getCardsById(@PathVariable Long id) throws CardsNotFoundException {
        CardsDto cardsDto = cardsService.getCardsById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardsDto);
    }

        @Operation(summary = "Delete Cards Details REST API",
                description = "REST API to Delete Cards details Based on Cards Id"
        )
        @ApiResponses(
                {
                        @ApiResponse(
                                responseCode = "200",
                                description = "HTTP Status OK"),
                        @ApiResponse(responseCode = "500",
                                description = "HTTP Status Internal Server Error",
                                content =
                                @Content(
                                        schema =
                                        @Schema(implementation = ErrorResponse.class)
                                )
                        )
                }
                )
    @DeleteMapping(value = "cards/delete/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> deleteCardById(@PathVariable Long id) throws CardsNotFoundException {
        String cards = cardsService.deleteCards(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.value(),
                        "Card Successfully Deleted...!"));
    }

        @Operation(summary = "Fetch Cards Details REST API",
                description = "REST API to Cards details Based on Account Id"
        )
        @ApiResponses({@ApiResponse(responseCode = "200",
                description = "HTTP Status OK"),
                @ApiResponse(
                        responseCode = "500",
                        description = "HTTP Status Internal Server Error",
                        content = @Content
                                (schema =
                                @Schema(implementation = ErrorResponse.class)
                                )
                )
        }
        )
    @GetMapping(value = "cards/fetchByAcc/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CardsDto>> getCardsByAccId(@RequestHeader("bankofbabji-correlation-id") String correlationId,
                                                          @PathVariable(name = "id") Long accId) {
        logger.debug("BankofBabji Correlation Id  : "+correlationId);

        List<CardsDto> cardsDtos = cardsService.getCardsByAccId(accId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardsDtos);
    }

        @Operation(summary = "Fetch Cards Contact Info REST API",
                description = "REST API to Cards Contact Info"
        )
        @ApiResponses({@ApiResponse(responseCode = "200",
                description = "HTTP Status OK"),
                @ApiResponse(
                        responseCode = "500",
                        description = "HTTP Status Internal Server Error",
                        content = @Content
                                (schema =
                                @Schema(implementation = ErrorResponse.class)
                                )
                )
        }
        )
    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo() {
            logger.info("contact-info calling....");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsContactInfoDto);
    }

        @Operation
                (
                        summary = "Fetch Cards Build Info REST API",
                        description = "REST API to Cards Build Info"
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
