package com.havesweets.accounts.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class AccountsDto {


    private long accountId;
    private long accountNumber;
    @NotEmpty(message = "Account Type Must Not Be Empty!")
    private String accountType;
    @NotEmpty(message = "Street Should Not Empty!")
    @Size(min = 5,max = 10,message = "Street Name Must be Enter minimum 5 Chars.")
    private String street;
    @NotEmpty(message = "District Should Not Empty!")
    @Size(min = 5,max = 10,message = "District Name Must be Enter minimum 5 Chars.")
    private String district;
    @NotEmpty(message = "Country Should Not Empty!")
    @Size(min = 5,max = 10,message = "Country Name Must be Enter minimum 5 Chars.")
    private String country;
    private int pincode;
}
