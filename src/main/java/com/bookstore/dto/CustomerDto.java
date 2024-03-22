package com.bookstore.dto;

import com.bookstore.dto.base.BaseDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto extends BaseDto {

    @NotNull
    @NotEmpty
    @NotBlank
    @Length(max = 100)
    private String customerName;

    @NotNull
    @NotEmpty
    @NotBlank
    @Length(max = 150)
    private String customerAddress;

    @NotNull
    @NotEmpty
    @NotBlank
    @Length(max = 32)
    private String customerPhoneNumber;

    @NotNull
    @Email
    @NotEmpty
    @NotBlank
    @Length(max = 40)
    private String customerEmail;
}