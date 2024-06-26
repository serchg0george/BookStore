package com.bookstore.dtos;

import com.bookstore.dtos.base.BaseDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    @Length(max = 100)
    private String customerName;

    @NotBlank
    @Length(max = 150)
    private String customerAddress;

    @NotBlank
    @Length(max = 32)
    private String customerPhoneNumber;

    @Email
    @NotBlank
    @Length(max = 40)
    private String customerEmail;
}