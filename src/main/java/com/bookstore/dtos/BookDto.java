package com.bookstore.dtos;

import com.bookstore.dtos.base.BaseDto;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto extends BaseDto {

    @NotBlank
    @Length(max = 60)
    private String bookTitle;

    @NotBlank
    @Length(max = 100)
    private String bookAuthor;

    @NotBlank
    @Size(min = 10, max = 13)
    @Pattern(regexp = "\\d+")
    private String bookIsbn;

}